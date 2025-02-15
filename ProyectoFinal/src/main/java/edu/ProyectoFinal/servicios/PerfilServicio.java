package edu.ProyectoFinal.servicios;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ProyectoFinal.Controladores.perfilUsuarioControlador;
import edu.ProyectoFinal.Dto.ComentariosPerfilDto;
import edu.ProyectoFinal.Dto.GruposListadoDto;
import edu.ProyectoFinal.Dto.UsuarioPerfilDto;
import edu.ProyectoFinal.Dto.eliminarElementoPerfilDto;
import jakarta.servlet.http.HttpSession;
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
				} else {
					vista.addObject("mensajePerfil", "No se encontraron comentarios para el usuario.");
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
	 * metodo que manda una peticion a la api para recoger los grupos creados por el
	 * usuario
	 * 
	 * @author jpribio - 04/02/25
	 * @param ususarioParaFiltrar
	 * @return vista
	 */
	public ModelAndView obtenerGruposDelUsuario(UsuarioPerfilDto ususarioParaFiltrar) {
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/perfil/grupos";

		try {
			String usuarioJson = new ObjectMapper().writeValueAsString(ususarioParaFiltrar);
			Response respuestaApi = ClientBuilder.newClient().target(url).request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(usuarioJson, MediaType.APPLICATION_JSON));

			if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
				List<GruposListadoDto> listadoGruposUsuario = listadoGrupos(respuestaApi);
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
	 * Metodo que manda la peticion y coge todos los grupos para que lo pueda
	 * observar el administrador
	 * 
	 * @author jpribio - 06/02/25
	 * @return vista
	 */
	public ModelAndView obtenerGruposParaAdmin() {
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/grupos";

		try {
			Response respuestaApi = ClientBuilder.newClient().target(url).request(MediaType.APPLICATION_JSON).get();

			if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
				List<GruposListadoDto> listadoGruposAdmin = listadoGrupos(respuestaApi);
				vista.addObject("listadoGruposAdmin", listadoGruposAdmin);

				if (listadoGruposAdmin.isEmpty()) {
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
	 * Metodo que hace la peticion a la api y devuelve todos los usuarios con el rol
	 * de user
	 * 
	 * @author jpribio - 06/02/25
	 * @return
	 */
	public ModelAndView obtenerUsuariosRolUser() {
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/usuariosPerfil";

		try {
			Response respuestaApi = ClientBuilder.newClient().target(url).request(MediaType.APPLICATION_JSON).get();

			if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
				List<UsuarioPerfilDto> listadoUsuario = listadoUsuarios(respuestaApi);
				vista.addObject("listadoUsuariosAdmin", listadoUsuario);

				if (listadoUsuario.isEmpty()) {
					vista.addObject("mensajeGrupo", "No se encontraron usuarios disponibles.");
				}
			} else {
				vista.addObject("error", "Error al obtener los usuarios: " + respuestaApi.getStatusInfo().toString());
			}
		} catch (Exception e) {
			vista.addObject("error", "Error al conectar con la API: " + e.getMessage());
		}

		return vista;
	}

	/**
	 * Metodo que hace la peticion a la api y devuelve todos los usuarios
	 * 
	 * @author jpribio - 06/02/25
	 * @return
	 */
	public ModelAndView obtenerUsuariosParaSAdmin() {
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/usuarioSAdminPerfil";

		try {
			Response respuestaApi = ClientBuilder.newClient().target(url).request(MediaType.APPLICATION_JSON).get();

			if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
				List<UsuarioPerfilDto> listadoUsuario = listadoUsuarios(respuestaApi);
				vista.addObject("listadoUsuariosSAdmin", listadoUsuario);

				if (listadoUsuario.isEmpty()) {
					vista.addObject("mensajeGrupo", "No se encontraron usuarios disponibles.");
				}
			} else {
				vista.addObject("error", "Error al obtener los usuarios: " + respuestaApi.getStatusInfo().toString());
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
	private List<GruposListadoDto> listadoGrupos(Response respuestaApi) {
		List<GruposListadoDto> listaGrupos = new ArrayList<>();

		try {
			String jsonString = respuestaApi.readEntity(String.class);
			JSONObject jsonResponse = new JSONObject(jsonString);

			JSONArray gruposArray = jsonResponse.optJSONArray("gruposPerfil");

			if (gruposArray != null) {
				for (int i = 0; i < gruposArray.length(); i++) {
					JSONObject jsonGrupo = gruposArray.getJSONObject(i);
					GruposListadoDto grupo = new GruposListadoDto();
					grupo.setIdGrupo(jsonGrupo.getLong("idGrupo"));
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

	/**
	 * Metodo privado que coge del texto plano los usuarios y los pasa a
	 * usuarioPerfilDto
	 * 
	 * @author jpribio - 06/02/25
	 * @param respuestaApi
	 * @return
	 */
	private List<UsuarioPerfilDto> listadoUsuarios(Response respuestaApi) {
		List<UsuarioPerfilDto> listadoUsuario = new ArrayList<>();

		try {
			String jsonString = respuestaApi.readEntity(String.class);
			JSONObject jsonResponse = new JSONObject(jsonString);

			JSONArray gruposArray = jsonResponse.optJSONArray("usuarioPerfil");

			if (gruposArray != null) {
				for (int i = 0; i < gruposArray.length(); i++) {
					JSONObject jsonUsuario = gruposArray.getJSONObject(i);
					UsuarioPerfilDto usuario = new UsuarioPerfilDto();
					usuario.setIdUsu(jsonUsuario.getLong("idUsu"));
					usuario.setAliasUsu(jsonUsuario.getString("aliasUsu"));
					usuario.setCorreoElectronicoUsu(jsonUsuario.getString("correoElectronicoUsu"));
					usuario.setEsPremium(jsonUsuario.getBoolean("esPremium"));
					usuario.setEsVerificadoEntidad(jsonUsuario.getBoolean("esVerificadoEntidad"));
					// usuario.setFotoUsu(jsonUsuario.);
					usuario.setMovilUsu(jsonUsuario.getInt("movilUsu"));
					usuario.setNombreCompletoUsu(jsonUsuario.getString("nombreCompletoUsu"));
					usuario.setRolUsu(jsonUsuario.getString("rolUsu"));
					listadoUsuario.add(usuario);
				}
			} else {
				System.out.println("No se encontró el array 'usuarioPerfil' en la respuesta JSON.");
			}

		} catch (Exception e) {
			System.err.println("Error al parsear la respuesta JSON: " + e.getMessage());
		}

		return listadoUsuario;
	}

	/**
	 * Metodo que llama a la api para modificar al usuario y recarga la pagina
	 * 
	 * @author jpribio - 11/02/25
	 * @param usuarioAModificar
	 * @param usuarioPaFiltrar
	 * @return
	 */
	public ModelAndView modificarUsuario(UsuarioPerfilDto usuarioAModificar, HttpSession sesion) {
		logger.info("Iniciando el proceso de modificación de usuario.");

		// Obtener el usuario actual de la sesión y combinarlo con los datos a modificar
		UsuarioPerfilDto usuarioPaFiltrar = (UsuarioPerfilDto) sesion.getAttribute("Usuario");
		usuarioAModificar = combinarUsuario(usuarioAModificar, usuarioPaFiltrar);

		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/ModificarUsuario";

		try {
			// Convertir el objeto a JSON para enviarlo a la API
			String usuarioJson = new ObjectMapper().writeValueAsString(usuarioAModificar);
			logger.debug("Usuario en JSON: {}", usuarioJson);

			// Usar try-with-resources para gestionar el cierre del cliente automáticamente
			try (Client client = ClientBuilder.newClient()) {
				Response respuestaApi = client.target(url).request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(usuarioJson, MediaType.APPLICATION_JSON));

				logger.debug("Respuesta de la API: {}", respuestaApi.getStatus());

				if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
					UsuarioPerfilDto usuarioPerfil = respuestaApi.readEntity(UsuarioPerfilDto.class);
					usuarioPerfil.setFotoUsu(Base64.getDecoder().decode(usuarioPerfil.getFotoString()));
					sesion.setAttribute("Usuario", usuarioPerfil);
					logger.info("Usuario modificado correctamente en la sesión.");
					vista = condicionYCasosPerfil(usuarioPerfil, vista);
				} else {
					String errorMsg = "Error al modificar el usuario: " + respuestaApi.getStatusInfo();
					vista.addObject("error", errorMsg);
					logger.error(errorMsg);
				}
			}
		} catch (Exception e) {
			String errorMsg = "Error al conectar con la API: " + e.getMessage();
			vista.addObject("error", errorMsg);
			logger.error("Excepción al conectar con la API", e);
		}

		return vista;
	}

	/**
	 * Metodo de eliminar un elemento (ya sea usuario o un grupo siendo
	 * administrador)
	 * 
	 * @author jpribio - 14/02/25
	 * @param eliminarElemento
	 * @param sesion
	 * @return
	 */
	public ModelAndView enviarElementoParaBorrar(eliminarElementoPerfilDto eliminarElemento, HttpSession sesion) {
		logger.info("Iniciando el proceso de eliminar elemento siendo administrador.");
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/EliminarElemento";

		try {
			// Convertir el objeto a JSON para enviarlo a la API
			String usuarioJson = new ObjectMapper().writeValueAsString(eliminarElemento);
			logger.debug("Elemento en JSON: {}", usuarioJson);

			// Usar try-with-resources para gestionar el cierre del cliente automáticamente
			try (Client client = ClientBuilder.newClient()) {
				Response respuestaApi = client.target(url).request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(usuarioJson, MediaType.APPLICATION_JSON));

				logger.debug("Respuesta de la API: {}", respuestaApi.getStatus());

				if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
					UsuarioPerfilDto usuarioPerfil = (UsuarioPerfilDto) sesion.getAttribute("Usuario");
					logger.info("Elemento eliminado correctamente.");
					vista = condicionYCasosPerfil(usuarioPerfil, vista);
				} else {
					String errorMsg = "Error al eliminar el elemento: " + respuestaApi.getStatusInfo();
					vista.addObject("error", errorMsg);
					logger.error(errorMsg);
				}
			}
		} catch (Exception e) {
			String errorMsg = "Error al conectar con la API: " + e.getMessage();
			vista.addObject("error", errorMsg);
			logger.error("Excepción al conectar con la API", e);
		}

		return vista;
	}

	/**
	 * MEtodo que envia el formulario del usuario con las modificaciones pertinentes
	 * 
	 * @author jpribio - 15/02/25
	 * @param usuarioAModificar
	 * @param sesion
	 * @return
	 */
	public ModelAndView enviarUsuarioAModificarComoAdmin(UsuarioPerfilDto usuarioAModificar, HttpSession sesion) {
		logger.info("Iniciando el proceso de modificar un usuario siendo administrador.");
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/ModificarUsuarioComoAdmin";

		try {
			// Convertir el objeto a JSON para enviarlo a la API
			String usuarioJson = new ObjectMapper().writeValueAsString(usuarioAModificar);
			logger.debug("Usuario a modificar en JSON: {}", usuarioJson);

			// Usar try-with-resources para gestionar el cierre del cliente automáticamente
			try (Client client = ClientBuilder.newClient()) {
				Response respuestaApi = client.target(url).request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(usuarioJson, MediaType.APPLICATION_JSON));

				logger.debug("Respuesta de la API: {}", respuestaApi.getStatus());

				if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
					UsuarioPerfilDto usuarioPerfil = (UsuarioPerfilDto) sesion.getAttribute("Usuario");
					logger.info("Elemento modificado correctamente.");
					vista = condicionYCasosPerfil(usuarioPerfil, vista);
				} else {
					String errorMsg = "Error al modificar el elemento: " + respuestaApi.getStatusInfo();
					vista.addObject("error", errorMsg);
					logger.error(errorMsg);
				}
			}
		} catch (Exception e) {
			String errorMsg = "Error al conectar con la API: " + e.getMessage();
			vista.addObject("error", errorMsg);
			logger.error("Excepción al conectar con la API", e);
		}

		return vista;
	}

	/**
	 * Metodo que envia el grupo con las caracteristicas cambiadas y devuelve una
	 * vista
	 * 
	 * @author jpribio - 15/02/25
	 * @param usuarioAModificar
	 * @param sesion
	 * @return
	 */
	public ModelAndView enviarGrupoAModificarComoAdmin(GruposListadoDto grupoAModificar, HttpSession sesion) {
		logger.info("Iniciando el proceso de modificar un grupo siendo administrador.");
		ModelAndView vista = new ModelAndView();
		String url = "http://localhost:8081/api/ModificarGrupoComoAdmin";

		try {
			// Convertir el objeto a JSON para enviarlo a la API
			String grupoJson = new ObjectMapper().writeValueAsString(grupoAModificar);
			logger.debug("Grupo a modificar en JSON: {}", grupoJson);

			// Usar try-with-resources para gestionar el cierre del cliente automáticamente
			try (Client client = ClientBuilder.newClient()) {
				Response respuestaApi = client.target(url).request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(grupoJson, MediaType.APPLICATION_JSON));

				logger.debug("Respuesta de la API: {}", respuestaApi.getStatus());

				if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
					UsuarioPerfilDto usuarioPerfil = (UsuarioPerfilDto) sesion.getAttribute("Usuario");
					logger.info("Elemento modificado correctamente.");
					vista = condicionYCasosPerfil(usuarioPerfil, vista);
				} else {
					String errorMsg = "Error al modificar el grupo: " + respuestaApi.getStatusInfo();
					vista.addObject("error", errorMsg);
					logger.error(errorMsg);
				}
			}
		} catch (Exception e) {
			String errorMsg = "Error al conectar con la API: " + e.getMessage();
			vista.addObject("error", errorMsg);
			logger.error("Excepción al conectar con la API", e);
		}

		return vista;
	}

	/**
	 * Metodo para observar la pantalla de inicio en el perfil definiendo los casos
	 * posibles
	 * 
	 * @author jpribio - 14/02/25
	 * @param usuarioABuscar
	 * @param vista
	 * @return
	 */
	public ModelAndView condicionYCasosPerfil(UsuarioPerfilDto usuarioABuscar, ModelAndView vista) {
		if (usuarioABuscar != null) {
			switch (usuarioABuscar.getRolUsu()) {
			case "user":
				vista = obtenerGruposDelUsuario(usuarioABuscar);
				busquedaDelComentarioDelUsuario(usuarioABuscar).getModel().forEach(vista::addObject);
				break;

			case "admin":
				vista = obtenerGruposParaAdmin();
				obtenerUsuariosRolUser().getModel().forEach(vista::addObject);
				break;

			default: // Super Admin u otros roles
				vista = obtenerGruposParaAdmin();
				obtenerUsuariosParaSAdmin().getModel().forEach(vista::addObject);
				break;
			}
			vista.setViewName("perfilUsuario");
		} else {
			vista.setViewName("error");
			vista.addObject("error", "Usuario no encontrado en la sesión.");
		}
		return vista;
	}

	/**
	 * Metodo que mete los valores de la sesion del usuario con el usuario que se
	 * modifica
	 * 
	 * @param usuarioAModificar
	 * @param usuarioPaFiltrar
	 * @return
	 */
	private UsuarioPerfilDto combinarUsuario(UsuarioPerfilDto usuarioAModificar, UsuarioPerfilDto usuarioPaFiltrar) {
		usuarioAModificar.setIdUsu(usuarioPaFiltrar.getIdUsu());
		usuarioAModificar.setCorreoElectronicoUsu(usuarioPaFiltrar.getCorreoElectronicoUsu());
		usuarioAModificar.setEsPremium(usuarioPaFiltrar.getEsPremium());
		usuarioAModificar.setEsVerificadoEntidad(usuarioPaFiltrar.getEsVerificadoEntidad());
		usuarioAModificar.setRolUsu(usuarioPaFiltrar.getRolUsu());
		if (usuarioAModificar.getFotoUsu() == null) {
			usuarioAModificar.setFotoUsu(usuarioPaFiltrar.getFotoUsu());
		}
		return usuarioAModificar;
	}

}
