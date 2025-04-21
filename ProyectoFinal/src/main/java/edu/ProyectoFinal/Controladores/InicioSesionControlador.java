package edu.ProyectoFinal.Controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.ProyectoFinal.Configuraciones.SesionLogger;
import edu.ProyectoFinal.Dto.UsuarioRegistroDto;
import edu.ProyectoFinal.servicios.InicioSesionServicio;
import jakarta.servlet.http.HttpSession;

@Controller
public class InicioSesionControlador {

	private static final SesionLogger logger = new SesionLogger(InicioSesionControlador.class);

	InicioSesionServicio servicioDeInicioDeSesion = new InicioSesionServicio();

	/**
	 * Metodo que devuelve la vista del index y da alta el nuevo usuario
	 * 
	 * @author jpribio - 27/01/25
	 * @return
	 */
	@GetMapping("/InicioSesion")
	public ModelAndView InicioSesionVista() {
		try {
			logger.info("Cargando la vista de inicio de sesión");
			ModelAndView vista = new ModelAndView("InicioSesion");
			return vista;
		} catch (Exception e) {
			logger.error("Error al cargar la página de inicio de sesión\n" + e);
			ModelAndView vista = new ModelAndView("InicioSesion");
			vista.addObject("error", "Error al cargar la pagina inicial.");
			vista.setViewName("error");
			return vista;
		}
	}

	/**
	 * Metodo que registra a un nuevo sesion
	 * 
	 * @author jpribio - 27/01/25
	 * @return
	 */
	@PostMapping("/Registro")
	public ModelAndView registroUsuario(@ModelAttribute UsuarioRegistroDto nuevoUsuario, HttpSession sesionIniciada) {
		try {
			logger.info("Intentando registrar el usuario: " + nuevoUsuario.getCorreoElectronicoUsu());
			return servicioDeInicioDeSesion.nuevoUsuario(nuevoUsuario, sesionIniciada);
		} catch (Exception e) {
			logger.error("Error en el registro de usuario: " + nuevoUsuario.getCorreoElectronicoUsu());
			ModelAndView vista = new ModelAndView("error");
			vista.addObject("error", "Error al registrar usuario. Inténtelo más tarde.");
			return vista;
		}
	}

	/**
	 * Metodo que inicia sesion
	 * 
	 * @author jpribio - 27/01/25
	 * @return
	 */
	@PostMapping("/IS")
	public ModelAndView inicioSesionUsuario(@ModelAttribute UsuarioRegistroDto buscarUsuario,
			HttpSession sesionIniciada) {
		try {
			logger.info("Intentando iniciar sesión para el usuario: " + buscarUsuario.getCorreoElectronicoUsu());
			ModelAndView vista = new ModelAndView();
			vista = servicioDeInicioDeSesion.inicioSesion(buscarUsuario, sesionIniciada);
			return vista;
		} catch (Exception e) {
			logger.error("Error al iniciar sesión para el usuario: " + buscarUsuario.getCorreoElectronicoUsu());
			ModelAndView vista = new ModelAndView("InicioSesion");
			vista.addObject("error", "Error al iniciar sesión. Inténtelo de nuevo.");
			return vista;
		}
	}

	/**
	 * Metodo para poder poner una nueva contraseña para el usuario del correo
	 * electronico
	 * 
	 * @author jpribio - 21/04/25
	 * @param correo
	 * @return
	 */
	@PostMapping("/RecuperarContrasena")
	public ResponseEntity<String> recuperarContrasena(@RequestParam("correoElectronicoUsu") String correo) {
		if (correo == null || correo.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Correo inválido.");
		}
		boolean enviado = servicioDeInicioDeSesion.enviarCorreoRecuperacion(correo);
		if (enviado) {
			return ResponseEntity.ok("Correo enviado con éxito.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el correo.");
		}
	}

	/**
	 * Metodo para poder cambiar la contraseña pòr una nueva
	 * 
	 * @author jpribio - 21/04/25
	 * @param nuevaContrasena
	 * @param token
	 * @return
	 */
	@PostMapping("/CambiarContrasena")
	public ResponseEntity<String> cambiarContrasenaWeb(@RequestParam("nuevaContrasena") String nuevaContrasena,
			@RequestParam("token") String token) {

		boolean exitoso = servicioDeInicioDeSesion.realizarCambioContrasena(token, nuevaContrasena);

		if (exitoso) {
			return ResponseEntity.ok("Contraseña cambiada correctamente.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo cambiar la contraseña.");
		}
	}

}
