package edu.ProyectoFinal.servicios;

import java.util.regex.Pattern;

import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ProyectoFinal.Dto.UsuarioPerfilDto;
import edu.ProyectoFinal.Dto.UsuarioRegistroDto;
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
	public ModelAndView nuevoUsuario(UsuarioRegistroDto usuarioNuevo, HttpSession sesionIniciada)
			throws NullPointerException, Exception, IllegalArgumentException {
		ModelAndView vista = new ModelAndView();

		if (!validarEmail(usuarioNuevo.getCorreoElectronicoUsu())) {
			System.out.println("Correo electrónico inválido.");
			vista.setViewName("InicioSesion");
			return vista; // Aseguramos el retorno
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String usuarioJson = objectMapper.writeValueAsString(usuarioNuevo);
		Client cliente = ClientBuilder.newClient();
		String url = "http://localhost:8081/api/usuario/registro";

		Response respuestaApi = cliente.target(url).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(usuarioJson, MediaType.APPLICATION_JSON));
		if (respuestaApi.getStatus() == 200) {
			UsuarioPerfilDto respuestaCuerpoApi = respuestaApi.readEntity(UsuarioPerfilDto.class);
			sesionIniciada.setAttribute("Usuario", respuestaCuerpoApi);
			vista = servicioGrupos.obtenerLosGruposTops();
			vista.setViewName("LandinPage");
		} else {
			vista.setViewName("InicioSesion");
			vista.addObject("error", "Ha habido un error con la web por favor vuelva en 5 minutos");
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
	public ModelAndView inicioSesion(UsuarioRegistroDto buscarUsuario, HttpSession sesionIniciada)
			throws NullPointerException, Exception, IllegalArgumentException {
		ModelAndView vista = new ModelAndView();
		UsuarioRegistroDto busquedaDeUsuario = new UsuarioRegistroDto(buscarUsuario.getCorreoElectronicoUsu(),
				buscarUsuario.getContraseniaUsu());

		if (!validarEmail(busquedaDeUsuario.getCorreoElectronicoUsu())) {
			System.out.println("Correo electrónico inválido.");
			vista.setViewName("InicioSesion");
			return vista; // Aseguramos el retorno
		}

		Client cliente = ClientBuilder.newClient();
		String url = "http://localhost:8081/api/usuario/inicioSesion";
		ObjectMapper objectMapper = new ObjectMapper();
		String usuarioJson = objectMapper.writeValueAsString(busquedaDeUsuario);
		Response respuestaApi = cliente.target(url).request(MediaType.APPLICATION_JSON).get();

		if (respuestaApi.getStatus() == 200 ) {
			UsuarioPerfilDto respuestaCuerpoApi = respuestaApi.readEntity(UsuarioPerfilDto.class);
			if(respuestaCuerpoApi!=null) {
				sesionIniciada.setAttribute("Usuario", respuestaCuerpoApi);
				vista = servicioGrupos.obtenerLosGruposTops();
				vista.setViewName("LandinPage");
			}else {
				vista.setViewName("InicioSesion");
				vista.addObject("error", "El usuario no ha sido encontrado por favor");
			}
		} else {
			vista.setViewName("InicioSesion");
			vista.addObject("error", "Ha habido un error con la web por favor vuelva en 5 minutos");
		}
		return vista;
	}

}
