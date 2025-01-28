package edu.ProyectoFinal.Controladores;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.ProyectoFinal.Dto.UsuarioPerfilDto;
import edu.ProyectoFinal.Dto.UsuarioRegistroDto;
import edu.ProyectoFinal.servicios.GruposServicios;
import edu.ProyectoFinal.servicios.InicioSesionServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;

@Controller
public class InicioSesionControlador {

	InicioSesionServicio servicioDeInicioDeSesion = new InicioSesionServicio();

	GruposServicios servicioGrupos = new GruposServicios();

	@GetMapping("/InicioSesion")
	public ModelAndView InicioSesionVista() throws ServletException, IOException {
		ModelAndView vista = new ModelAndView("InicioSesion");
		return vista;
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
			UsuarioPerfilDto usuario = (UsuarioPerfilDto) sesionIniciada.getAttribute("Usuario");
			ModelAndView vista = new ModelAndView();
			vista = servicioDeInicioDeSesion.nuevoUsuario(nuevoUsuario, sesionIniciada);
			return vista;
		} catch (Exception e) {
			ModelAndView vista = new ModelAndView("InicioSesion");
			vista.addObject("error", "Error al iniciar sesión.");
			return vista;
		}
	}

}
