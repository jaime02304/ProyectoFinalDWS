package edu.ProyectoFinal.servicios;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ProyectoFinal.Dto.ComentariosPerfilDto;
import edu.ProyectoFinal.Dto.GruposListadoDto;
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

	/**
	 * Metodo que coge el comentario por defecto del usuario
	 * 
	 * @author jpribio - 04/02/25
	 * @param usuarioParaBuscar
	 * @return
	 */
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
					vista.setViewName("perfilUsuario");
				} else {
					vista.addObject("mensajePerfil", "No se encontraron comentarios para el usuario.");
					vista.setViewName("perfilUsuario");
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

	/**
	 * Metodo privado que pasa de json a a comentario del perfil (DTO)
	 * 
	 * @author jpribio - 04/02/25
	 * @param jsonResponse
	 * @return
	 */
	private ComentariosPerfilDto obtenerComentario(JSONObject jsonResponse) {
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

	/**
	 * 
	 * @return
	 */
	public ModelAndView obtenerGruposDelUsuario(UsuarioPerfilDto ususarioParaFiltrar) {
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/perfil/grupos";

		try {
			String usuarioJson = new ObjectMapper().writeValueAsString(ususarioParaFiltrar);
			Response respuestaApi = ClientBuilder.newClient().target(url).request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(usuarioJson, MediaType.APPLICATION_JSON));

			if (respuestaApi.getStatus() == 200) {
				List<GruposListadoDto> listadoGruposUsuario = listadoGruposUsuario(respuestaApi);
				vista.addObject("listadoGruposUsuario", listadoGruposUsuario);

				if (listadoGruposUsuario.isEmpty()) {
					vista.addObject("mensajeGrupo", "No se encontraron grupos disponibles.");
				}
			} else {
				vista.addObject("error", "Error al obtener los grupos: " + respuestaApi.getStatusInfo().toString());
			}
		} catch (Exception e) {
			vista.addObject("error", "Error al conectar con la API: " + e.getMessage());
		}

		return vista;
	}

	/**
	 * Metodo privado que coge del texto plano los grupos y los pasa a gruposDTo
	 * 
	 * @author jpribio - 23/01/25
	 * @param respuestaApi (la respuesta de la api con el texto plano)
	 * @return devuelve la lista de los grupos
	 * @throws Exception
	 * @throws NullPointerException
	 */
	private List<GruposListadoDto> listadoGruposUsuario(Response respuestaApi) {
		List<GruposListadoDto> listaGrupos = new ArrayList<>();

		try {
			String jsonString = respuestaApi.readEntity(String.class);
			JSONObject jsonResponse = new JSONObject(jsonString);

			JSONArray gruposArray = jsonResponse.optJSONArray("gruposPerfil");

			if (gruposArray != null) {
				for (int i = 0; i < gruposArray.length(); i++) {
					JSONObject jsonGrupo = gruposArray.getJSONObject(i);
					GruposListadoDto grupo = new GruposListadoDto();

					grupo.setNombreGrupo(jsonGrupo.optString("nombreGrupo"));
					grupo.setCategoriaNombre(jsonGrupo.optString("categoriaNombre"));
					grupo.setSubCategoriaNombre(jsonGrupo.optString("subCategoriaNombre"));

					listaGrupos.add(grupo);
				}
			} else {
				System.out.println("No se encontró el array 'grupos' en la respuesta JSON.");
			}

		} catch (Exception e) {
			System.err.println("Error al parsear la respuesta JSON: " + e.getMessage());
		}

		return listaGrupos;
	}

}
