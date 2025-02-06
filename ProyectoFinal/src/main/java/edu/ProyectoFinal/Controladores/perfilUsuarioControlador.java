package edu.ProyectoFinal.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.ProyectoFinal.Dto.UsuarioPerfilDto;
import edu.ProyectoFinal.servicios.GruposServicios;
import edu.ProyectoFinal.servicios.PerfilServicio;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador donde se encuentra todos los metodos de la pagina del perfil del
 * mismo usuario y de los administradores
 * 
 * @author jpribio - 30/01/25
 */
@Controller
public class perfilUsuarioControlador {

	GruposServicios servicioGrupos = new GruposServicios();

	PerfilServicio servicioPerfil = new PerfilServicio();

	/**
	 * Metodo que muestra la pagina del perfil tanto de los usuarios como la de los
	 * administradores
	 * 
	 * @author jpribio - 06/02/25
	 * @param sesionIniciada
	 * @return
	 */
	@GetMapping("/PerfilUsuario")
	public ModelAndView vistaPerfilYAdministradores(HttpSession sesionIniciada) {
		ModelAndView vista = new ModelAndView();
		UsuarioPerfilDto usuarioABuscar = (UsuarioPerfilDto) sesionIniciada.getAttribute("Usuario");

		try {
			if (usuarioABuscar != null) {
				switch (usuarioABuscar.getRolUsu()) {
				case "user":
					vista = servicioPerfil.obtenerGruposDelUsuario(usuarioABuscar);
					servicioPerfil.busquedaDelComentarioDelUsuario(usuarioABuscar).getModel().forEach(vista::addObject);
					break;

				case "admin":
					vista = servicioPerfil.obtenerGruposParaAdmin();
					servicioPerfil.obtenerUsuariosRolUser().getModel().forEach(vista::addObject);
					break;

				default: // Super Admin u otros roles
					vista = servicioPerfil.obtenerGruposParaAdmin();
					servicioPerfil.obtenerUsuariosParaSAdmin().getModel().forEach(vista::addObject);
					break;
				}
				vista.setViewName("perfilUsuario");
			} else {
				vista.setViewName("error");
				vista.addObject("error", "Usuario no encontrado en la sesión.");
			}
		} catch (Exception e) {
			vista.setViewName("error");
			vista.addObject("error", "No se ha cargado la página del perfil personal");
		}

		return vista;
	}

	/**
	 * Metodo de cerrar la sesion del usuario
	 * 
	 * @author jpribio - 06/02/25
	 * @param cerrarSesion
	 * @return
	 */
	@GetMapping("/CerrarSesion")
	public ModelAndView cerrarSesionUsuario(HttpSession cerrarSesion) {
		ModelAndView vista = new ModelAndView();
		try {
			cerrarSesion.invalidate();
			vista = servicioGrupos.obtenerLosGruposTops();
			vista.setViewName("LandinPage");
		} catch (Exception e) {
			vista.setViewName("error");
			vista.addObject("error", "No se ha cargado la página principal.");
		}

		return vista;
	}
}