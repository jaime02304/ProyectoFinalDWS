package edu.ProyectoFinal.servicios;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import edu.ProyectoFinal.Dto.GruposIndexDto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Servicio donde se encuentre todos los metodos de los grupos
 * 
 * @author jpribio - 23/01/25
 */
public class GruposServicios {

	/**
	 * Metodo que obtiene los 5 grupos mas top del total
	 * 
	 * @author jpribio - 23/01/25
	 * @return devuelve el modelAndView con el objeto
	 * @throws Exception
	 * @throws NullPointerException
	 */
	public ModelAndView obtenerLosGruposTops()   {
		ModelAndView vista = new ModelAndView();

		Client cliente = ClientBuilder.newClient();
		String url = "http://localhost:8081/api/index/grupos";

		// Llamar a la API
		Response respuestaApi = cliente.target(url).request(MediaType.APPLICATION_JSON).get();
		System.out.println(respuestaApi);

		if (respuestaApi.getStatus() == 200) {
			// Esta es la parte de los ususarios
			List<GruposIndexDto> listadoGruposCompletosTop = listadoGruposTop(respuestaApi);
			// Agregar la lista de usuarios al modelo
			vista.addObject("listaGrupos", listadoGruposCompletosTop);
		} else {
			// En caso de error, agregar mensaje de error
			vista.addObject("error", "No se ha encontrado ningun grupo, por un error de la web (Api)");
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
	private List<GruposIndexDto> listadoGruposTop(Response respuestaApi)   {
		// Leer el JSON recibido
		String jsonString = respuestaApi.readEntity(String.class);

		// Parsear el JSON recibido
		JSONArray gruposArray = new JSONArray(jsonString); // El JSON raíz es un array

		// Crear lista de Usuarios
		List<GruposIndexDto> listaGrupos = new ArrayList<>();

		// recorrer el ArraJSON para ir extrayendo los campos y añadirlo al
		// usuarioCompletoDto
		for (int i = 0; i < gruposArray.length(); i++) {
			JSONObject jsonGrupo = gruposArray.getJSONObject(i);
			GruposIndexDto grupo = new GruposIndexDto();

			// Extraer datos del JSON
			grupo.setNombreGrupo(jsonGrupo.getString("nombreGrupo"));
			grupo.setCategoriaNombre(jsonGrupo.getString("categoriaNombre"));
			grupo.setSubCategoriaNombre(jsonGrupo.getString("subCategoriaNombre"));

			// Crear objeto Usuario y añadirlo a la lista
			listaGrupos.add(grupo);
		}
		return listaGrupos;
	}

}
