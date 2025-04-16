package edu.ProyectoFinal.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.ProyectoFinal.Configuraciones.SesionLogger;
import edu.ProyectoFinal.servicios.ComentariosServicios;
import jakarta.servlet.http.HttpSession;

@Controller
public class ComentariosControlador {

	private static final SesionLogger logger = new SesionLogger(ComentariosControlador.class);
	
	ComentariosServicios servicioComentarios = new ComentariosServicios();
	
	@GetMapping("/ComentarioPagina")
	public ModelAndView vistaPaginaPerfil(HttpSession sesionIniciada) {
		try {
			logger.info("Cargando la vista de comenatarios");
			ModelAndView vista = new ModelAndView();
			vista=servicioComentarios.recogidaDeComentarios(sesionIniciada);
			vista.setViewName("ComentarioPagina");
			return vista;
		} catch (Exception e) {
			logger.error("Error al cargar la página de comentarios\n" + e);
			ModelAndView vista = new ModelAndView("ComentarioPagina");
			vista.addObject("error", "Error al cargar la comentarios.");
			vista.setViewName("error");
			return vista;
		}
	}
	
	
	
}
