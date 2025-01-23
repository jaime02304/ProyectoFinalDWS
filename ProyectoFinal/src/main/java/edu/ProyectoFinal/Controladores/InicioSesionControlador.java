package edu.ProyectoFinal.Controladores;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.ServletException;

@Controller
public class InicioSesionControlador {

	@GetMapping("InicioSesion")
	public ModelAndView InicioSesionVista() throws ServletException, IOException {
		ModelAndView vista = new ModelAndView("InicioSesion");
		return vista;
	}

}
