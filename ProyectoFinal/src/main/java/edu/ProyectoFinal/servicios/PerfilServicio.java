package edu.ProyectoFinal.servicios;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ProyectoFinal.Dto.ComentariosPerfilDto;
import edu.ProyectoFinal.Dto.UsuarioPerfilDto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Clase donde se encuentra los metodos que tienen relacion con la pagina de
 * perfil
 * 
 * @author jpribio - 03/02/25
 */
public class PerfilServicio {
	
	private static final Logger logger = LoggerFactory.getLogger(PerfilServicio.class);

	public ModelAndView busquedaDelComentarioDelUsuario(UsuarioPerfilDto usuarioParaBuscar) {
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/perfil/comentario";

		try (Client cliente = ClientBuilder.newClient()) {
			String usuarioJson = new ObjectMapper().writeValueAsString(usuarioParaBuscar);
			Response respuestaApi = cliente.target(url).request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(usuarioJson, MediaType.APPLICATION_JSON));

			if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
				String jsonString = respuestaApi.readEntity(String.class);
				JSONObject jsonResponse = new JSONObject(jsonString);
				ComentariosPerfilDto comentario = obtenerComentario(jsonResponse);

				if (comentario != null) {
					vista.addObject("comentario", comentario);
				} else {
					vista.addObject("mensaje", "No se encontraron comentarios para el usuario.");
				}
			} else {
				logger.warn("Error en la API, código de estado: {}", respuestaApi.getStatus());
				vista.addObject("error", "No se ha encontrado ningún comentario debido a un error en la API.");
				vista.setViewName("error");
			}
		} catch (Exception e) {
			logger.error("Error al conectar con la API", e);
			vista.addObject("error", "Error al conectar con la API: " + e.getMessage());
			vista.setViewName("error");
		}

		return vista;
	}


	public ComentariosPerfilDto obtenerComentario(JSONObject jsonResponse) {
		try {
			JSONObject jsonComentario = jsonResponse.optJSONObject("comentarios");

			if (jsonComentario == null) {
				logger.warn("No se encontró el objeto 'comentario' en la respuesta JSON.");
				return null; // Retorna un objeto vacío en lugar de null
			}

			ComentariosPerfilDto comentariosPerfilDto = new ComentariosPerfilDto();
			comentariosPerfilDto.setComentarioTexto(jsonComentario.optString("comentarioTexto"));
			comentariosPerfilDto.setCategoriaTipo(jsonComentario.optString("categoriaTipo"));
			comentariosPerfilDto.setSubCategoriaTipo(jsonComentario.optString("subCategoriaTipo"));
			return comentariosPerfilDto;
		} catch (JSONException e) {
			logger.error("Error al parsear la respuesta JSON", e);
			throw new RuntimeException("Error procesando la respuesta JSON", e);
		}
	}
}
