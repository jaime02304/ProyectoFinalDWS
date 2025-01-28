package edu.ProyectoFinal.Controladores;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.ProyectoFinal.Dto.UsuarioPerfilDto;
import edu.ProyectoFinal.servicios.GruposServicios;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador de la vista principal
 * 
 * @author jpribio - 23/01/25
 */
@Controller
public class ControladorIndex {

	GruposServicios serviciosGrupos = new GruposServicios();

	/**
	 * Metodo que muestra la vista y objetos de la misma
	 * 
	 * @return devuelve la vista y los metodos equivalentes
	 * @throws IOException
	 */
	@GetMapping("/")
	protected ModelAndView index(HttpSession sesionIniciada) {
		try {
			UsuarioPerfilDto usuario = (UsuarioPerfilDto) sesionIniciada.getAttribute("Usuario");
			ModelAndView vista = new ModelAndView();
			vista = serviciosGrupos.obtenerLosGruposTops();
			vista.setViewName("LandinPage");
			return vista;
		} catch (Exception e) {
			ModelAndView vista = new ModelAndView("InicioSesion");
			vista.addObject("error", "Error al cargar la pagina inicial.");
			// vista.setViewName("error");
			return vista;
		}

	}
}
