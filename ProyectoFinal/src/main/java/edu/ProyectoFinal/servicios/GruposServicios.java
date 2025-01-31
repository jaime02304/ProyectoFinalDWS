package edu.ProyectoFinal.servicios;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import edu.ProyectoFinal.Dto.GruposIndexDto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.*;
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
	public ModelAndView obtenerLosGruposTops() {
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/index/grupos";

		try (Client cliente = ClientBuilder.newClient()) {
			// Llamar a la API
			Response respuestaApi = cliente.target(url).request(MediaType.APPLICATION_JSON).get();

			if (respuestaApi.getStatus() == 200) {
				// Procesar la respuesta de la API
				List<GruposIndexDto> listadoGruposCompletosTop = listadoGruposTop(respuestaApi);

				// Verificar si la lista está vacía y agregar el mensaje correspondiente
				if (listadoGruposCompletosTop.isEmpty()) {
					vista.addObject("mensajeGrupo", "No se encontraron grupos disponibles.");
				}
				// Agregar la lista de grupos al modelo
				vista.addObject("listaGrupos", listadoGruposCompletosTop);
			} else {
				// En caso de error en la API, agregar mensaje informativo
				vista.addObject("error", "No se ha encontrado ningún grupo debido a un error en la API.");
			}
		} catch (Exception e) {
			// Manejo de cualquier excepción inesperada
			vista.addObject("error", "Error al conectar con la API: " + e.getMessage());
		}

		// Retornar la vista
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
	private List<GruposIndexDto> listadoGruposTop(Response respuestaApi) {
		List<GruposIndexDto> listaGrupos = new ArrayList<>();

		try {
			// Parsear directamente el JSON recibido desde la respuesta
			String jsonString = respuestaApi.readEntity(String.class);
			JSONObject jsonResponse = new JSONObject(jsonString);

			// Extraer el JSONArray de "grupos", si existe
			JSONArray gruposArray = jsonResponse.optJSONArray("grupos");

			if (gruposArray != null) {
				// Recorrer el JSONArray y mapear a GruposIndexDto
				for (int i = 0; i < gruposArray.length(); i++) {
					JSONObject jsonGrupo = gruposArray.getJSONObject(i);
					GruposIndexDto grupo = new GruposIndexDto();

					// Extraer y asignar directamente los campos
					grupo.setNombreGrupo(jsonGrupo.optString("nombreGrupo"));
					grupo.setCategoriaNombre(jsonGrupo.optString("categoriaNombre"));
					grupo.setSubCategoriaNombre(jsonGrupo.optString("subCategoriaNombre"));

					// Añadir el grupo a la lista
					listaGrupos.add(grupo);
				}
			} else {
				System.out.println("No se encontró el array 'grupos' en la respuesta JSON.");
			}
		} catch (Exception e) {
			System.err.println("Error al parsear la respuesta JSON: " + e.getMessage());
		}

		// Retornar la lista de grupos
		return listaGrupos;
	}

}
