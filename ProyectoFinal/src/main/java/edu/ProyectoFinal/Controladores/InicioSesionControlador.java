package edu.ProyectoFinal.Controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.ProyectoFinal.Dto.UsuarioRegistroDto;
import edu.ProyectoFinal.servicios.InicioSesionServicio;
import jakarta.servlet.http.HttpSession;

@Controller
public class InicioSesionControlador {

	private static final Logger logger = LoggerFactory.getLogger(InicioSesionControlador.class);

	InicioSesionServicio servicioDeInicioDeSesion = new InicioSesionServicio();

	@GetMapping("/InicioSesion")
	public ModelAndView InicioSesionVista() {
		try {
			logger.info("Cargando la vista de inicio de sesión");
			ModelAndView vista = new ModelAndView("InicioSesion");
			return vista;
		} catch (Exception e) {
			logger.error("Error al cargar la página de inicio de sesión", e);
			ModelAndView vista = new ModelAndView("InicioSesion");
			vista.addObject("error", "Error al cargar la pagina inicial.");
			vista.setViewName("error");
			return vista;
		}
	}

	/**
	 * Metodo que devuelve la vista del index y da alta el nuevo usuario
	 * 
	 * @author jpribio - 27/01/25
	 * @return
	 */

	@PostMapping("/Registro")
	public ModelAndView registroUsuario(@ModelAttribute UsuarioRegistroDto nuevoUsuario, HttpSession sesionIniciada) {
		try {
			logger.info("Intentando registrar el usuario: {}", nuevoUsuario.getCorreoElectronicoUsu());
			return servicioDeInicioDeSesion.nuevoUsuario(nuevoUsuario, sesionIniciada);
		} catch (Exception e) {
			logger.error("Error en el registro de usuario: {}", nuevoUsuario.getCorreoElectronicoUsu(), e);
			ModelAndView vista = new ModelAndView("error");
			vista.addObject("error", "Error al registrar usuario. Inténtelo más tarde.");
			return vista;
		}
	}

	@PostMapping("/IS")
	public ModelAndView inicioSesionUsuario(@ModelAttribute UsuarioRegistroDto buscarUsuario,
			HttpSession sesionIniciada) {
		try {
			logger.info("Intentando iniciar sesión para el usuario: {}", buscarUsuario.getCorreoElectronicoUsu());
			ModelAndView vista = new ModelAndView();
			vista = servicioDeInicioDeSesion.inicioSesion(buscarUsuario, sesionIniciada);
			return vista;
		} catch (Exception e) {
			logger.error("Error al iniciar sesión para el usuario: {}", buscarUsuario.getCorreoElectronicoUsu(), e);
			ModelAndView vista = new ModelAndView("InicioSesion");
			vista.addObject("error", "Error al iniciar sesión. Inténtelo de nuevo.");
			return vista;
		}
	}
}
