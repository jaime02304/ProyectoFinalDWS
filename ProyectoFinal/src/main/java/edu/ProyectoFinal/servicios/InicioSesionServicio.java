package edu.ProyectoFinal.servicios;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ProyectoFinal.Dto.UsuarioPerfilDto;
import edu.ProyectoFinal.Dto.UsuarioRegistroDto;
import edu.ProyectoFinal.Utilidades.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Clase donde se encuentra todos los metodos en relacion con la logica del
 * inicio de sesion y servicio
 * 
 * @author jpribio - 27/01/25
 */
public class InicioSesionServicio {

	GruposServicios servicioGrupos = new GruposServicios();

	Util utilidades = new Util();

	// representa un patrón de expresión regular para luego compararla
	private Pattern email1 = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.(com|net|es)$");

	/**
	 * Metodo privado que valida el email correctamente
	 *
	 * @author jpribio - 27/01/25
	 * @param correo electronico introducido por el usuario
	 * @return devuelve el email validado
	 */
	private boolean validarEmail(String email) {
		if (email == null) {
			return false;
		}
		return email1.matcher(email).matches();
	}

	/**
	 * Metodo que crea el nuevo usuario
	 * 
	 * @author jpribio - 27/01/25
	 * @param usuarioNuevo
	 * @return
	 * @throws NullPointerException
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
	public ModelAndView nuevoUsuario(UsuarioRegistroDto usuarioNuevo, HttpSession sesionIniciada) {
		Logger logger = LoggerFactory.getLogger(getClass());
		ModelAndView vista = new ModelAndView("InicioSesion");

		if (!validarEmail(usuarioNuevo.getCorreoElectronicoUsu())) {
			logger.warn("Correo electrónico inválido: {}", usuarioNuevo.getCorreoElectronicoUsu());
			vista.addObject("error", "Correo electrónico inválido.");
			return vista;
		}

		String url = "http://localhost:8081/api/usuario/registro";

		try (Client cliente = ClientBuilder.newClient()) {
			usuarioNuevo.setContraseniaUsu(utilidades.encriptarASHA256(usuarioNuevo.getContraseniaUsu()));
			String usuarioJson = new ObjectMapper().writeValueAsString(usuarioNuevo);

			Response respuestaApi = cliente.target(url).request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(usuarioJson, MediaType.APPLICATION_JSON));

			if (respuestaApi.getStatus() == Response.Status.OK.getStatusCode()) {
				UsuarioPerfilDto usuarioPerfil = respuestaApi.readEntity(UsuarioPerfilDto.class);
				sesionIniciada.setAttribute("Usuario", usuarioPerfil);
				sesionIniciada.setMaxInactiveInterval(60 * 60 * 24 * 7);
				vista = servicioGrupos.obtenerLosGruposTops();
				vista.setViewName("LandinPage");
			} else {
				logger.error("Error en el registro: Código HTTP {}", respuestaApi.getStatus());
				vista.setViewName("error");
				vista.addObject("error", "Ha habido un error con la web, por favor vuelva en 5 minutos.");
			}
		} catch (Exception e) {
			logger.error("Error al registrar usuario", e);
			vista.setViewName("error");
			vista.addObject("error", "Ocurrió un problema inesperado. Inténtelo más tarde.");
		}

		return vista;
	}

	/**
	 * Metodo que manda los dato necesarios hacia la api para buscar al usuario en
	 * la base de datos y tener la informacion de su perfil
	 * 
	 * @author jpribio - 28/01/25
	 * @param buscarUsuario
	 * @param sesionIniciada
	 * @return
	 * @throws NullPointerException
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
	public ModelAndView inicioSesion(UsuarioRegistroDto usuario, HttpSession sesion) {
		Logger logger = LoggerFactory.getLogger(getClass());
		ModelAndView vista = new ModelAndView("InicioSesion");

		if (!validarEmail(usuario.getCorreoElectronicoUsu())) {
			vista.addObject("error", "Correo electrónico inválido.");
			return vista;
		}

		String url = "http://localhost:8081/api/usuario/inicioSesion";

		try (Client cliente = ClientBuilder.newClient()) {
			usuario.setContraseniaUsu(utilidades.encriptarASHA256(usuario.getContraseniaUsu()));
			String usuarioJson = new ObjectMapper().writeValueAsString(usuario);

			Response respuesta = cliente.target(url).request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(usuarioJson, MediaType.APPLICATION_JSON));

			if (respuesta.getStatus() != Response.Status.OK.getStatusCode()) {
				logger.error("Error en la API de inicio de sesión: Código HTTP {}", respuesta.getStatus());
				vista.setViewName("error");
				vista.addObject("error", "Ha habido un error con la web, por favor intente más tarde.");
				return vista;
			}

			UsuarioPerfilDto usuarioPerfil = respuesta.readEntity(UsuarioPerfilDto.class);
			if (usuarioPerfil != null
					&& usuarioPerfil.getCorreoElectronicoUsu().equals(usuario.getCorreoElectronicoUsu())) {
				sesion.setAttribute("Usuario", usuarioPerfil);
				sesion.setMaxInactiveInterval(60 * 60 * 24 * 7);
				vista = servicioGrupos.obtenerLosGruposTops();
				vista.setViewName("LandinPage");
			} else {
				vista.setViewName("InicioSesion");
				vista.addObject("mensaje", "El usuario no ha sido encontrado.");
			}
		} catch (Exception e) {
			logger.error("Error en el inicio de sesión", e);
			vista.setViewName("error");
			vista.addObject("error", "Ocurrió un problema inesperado. Inténtelo más tarde.");
		}

		return vista;
	}

}
