package edu.ProyectoFinal.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

/**
 * Controlador donde se encuentra todos los metodos de la pagina del perfil del
 * mismo usuario y de los administradores
 * 
 * @author jpribio - 30/01/25
 */
@Controller
public class perfilUsuarioControlador {

	@GetMapping("/PerfilUsuario")
	public ModelAndView vistaPerfilYAdministradores(HttpSession sesionIniciada) {
		ModelAndView vista = new ModelAndView();
		try {
			vista.setViewName("perfilUsuario");
		} catch (Exception e) {
			vista.setViewName("LandinPage");
			vista.addObject("error", "No se ha cargado la página del perfil personal");
		}

		return vista;
	}

}
