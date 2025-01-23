package edu.ProyectoFinal.Controladores;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador de la vista principal
 * 
 * @author jpribio - 23/01/25
 */
@Controller
public class ControladorIndex {

	//private GruposServicios serviciosGrupos;

	/**
	 * Metodo que muestra la vista y objetos de la misma
	 * 
	 * @return devuelve la vista y los metodos equivalentes
	 * @throws IOException
	 */
	@GetMapping("/")
	protected ModelAndView index()  {
		ModelAndView vista = new ModelAndView("LandinPage");
		// vista = serviciosGrupos.obtenerLosGruposTops();
		return vista;

	}
}
