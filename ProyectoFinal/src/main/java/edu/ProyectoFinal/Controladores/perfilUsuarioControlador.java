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
	
	PerfilServicio servicioPerfil=new PerfilServicio();

	@GetMapping("/PerfilUsuario")
	public ModelAndView vistaPerfilYAdministradores(HttpSession sesionIniciada) {
		ModelAndView vista = new ModelAndView();
		UsuarioPerfilDto usuarioABuscar = (UsuarioPerfilDto) sesionIniciada.getAttribute("Usuario");
		try {
			vista=servicioPerfil.obtenerGruposDelUsuario(usuarioABuscar);
			vista=servicioPerfil.busquedaDelComentarioDelUsuario(usuarioABuscar);
		} catch (Exception e) {
			vista.setViewName("error");
			vista.addObject("error", "No se ha cargado la página del perfil personal");
		}

		return vista;
	}

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