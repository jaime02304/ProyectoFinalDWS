package edu.ProyectoFinal.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.ProyectoFinal.Dto.GruposDto;
import edu.ProyectoFinal.Dto.GruposListadoDto;
import edu.ProyectoFinal.Dto.UsuarioPerfilDto;
import edu.ProyectoFinal.Dto.eliminarElementoPerfilDto;
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
			vista = servicioPerfil.condicionYCasosPerfil(usuarioABuscar, vista);
		} catch (Exception e) {
			vista.setViewName("error");
			vista.addObject("error", "No se ha cargado la página del perfil personal");
		}

		return vista;
	}

	/**
	 * Metodo que modifica al usuario con los valores incorporados
	 *
	 * @author jpribio - 11/02/25
	 * @param usuarioAModificar
	 * @param sesionDelUsuario
	 * @return
	 */
	@PostMapping("/ModificarUsuario")
	public ModelAndView modificarUsuario(@ModelAttribute UsuarioPerfilDto usuarioAModificar,
			HttpSession sesionDelUsuario) {
		try {
			return servicioPerfil.modificarUsuario(usuarioAModificar, sesionDelUsuario);
		} catch (Exception ex) {
			ModelAndView vistaError = new ModelAndView("error");
			vistaError.addObject("error",
					"Ha ocurrido un error al modificar el usuario. Por favor, inténtalo de nuevo.");
			return vistaError;
		}
	}

	/**
	 * Metodo que recoge al usuario de la vista y lo manda a la api para que sea
	 * borrado
	 * 
	 * @author jpribio - 15/02/25
	 * @param elemento
	 * @param sesion
	 * @return
	 */
	@PostMapping("/EliminarElementosComoAdmin")
	public ModelAndView eliminarUsuario(@ModelAttribute eliminarElementoPerfilDto elemento, HttpSession sesion) {
		try {
			return servicioPerfil.enviarElementoParaBorrar(elemento, sesion);
		} catch (Exception ex) {
			ModelAndView vistaError = new ModelAndView("error");
			vistaError.addObject("error",
					"Ha ocurrido un error al eliminar el elemento(usuario o grupo). Por favor, inténtalo de nuevo.");
			return vistaError;
		}
	}

	/**
	 * MEtodo que manda el usuario seleccionado a modificar hacia la api y lo
	 * modifica
	 * 
	 * @author jpribio - 15/02/25
	 * @param usuarioAModificar
	 * @param sesion
	 * @return
	 */
	@PostMapping("/ModificarUsuarioComoAdmin")
	public ModelAndView modificarUSuarioComoAdmin(@ModelAttribute UsuarioPerfilDto usuarioAModificar,
			HttpSession sesion) {
		try {
			return servicioPerfil.enviarUsuarioAModificarComoAdmin(usuarioAModificar, sesion);
		} catch (Exception ex) {
			ModelAndView vistaError = new ModelAndView("error");
			vistaError.addObject("error",
					"Ha ocurrido un error al modificar el usuario. Por favor, inténtalo de nuevo.");
			return vistaError;
		}
	}

	/**
	 * Metodo que modifica un grupo segun los nuevos elementos dados en la vista
	 * 
	 * @author jpribio - 15/02/25
	 * @param usuarioAModificar
	 * @param sesion
	 * @return
	 */
	@PostMapping("/ModificarGrupoComoAdmin")
	public ModelAndView modificarGrupoComoAdmin(@ModelAttribute GruposListadoDto usuarioAModificar,
			HttpSession sesion) {
		try {
			return servicioPerfil.enviarGrupoAModificarComoAdmin(usuarioAModificar, sesion);
		} catch (Exception ex) {
			ModelAndView vistaError = new ModelAndView("error");
			vistaError.addObject("error", "Ha ocurrido un error al modificar el grupo. Por favor, inténtalo de nuevo.");
			return vistaError;
		}
	}

	/**
	 * metodo que coge el usuario de la web y lo manda hacia la api
	 * 
	 * @author jptibio - 16/02/25
	 * @param usuarioCreado
	 * @param sesion
	 * @return
	 */
	@PostMapping("/CrearUsuarioComoAdmin")
	public ModelAndView crearUnNuevoUsuarioComoAdmin(@ModelAttribute UsuarioPerfilDto usuarioCreado,
			HttpSession sesion) {
		try {
			return servicioPerfil.crearUsuarioComoAdmin(usuarioCreado, sesion);
		} catch (Exception ex) {
			ModelAndView vistaError = new ModelAndView("error");
			vistaError.addObject("error", "Ha ocurrido un error al crear el usuario. Por favor, inténtalo de nuevo.");
			return vistaError;
		}
	}

	/**
	 * metodo que coge el grupo de la web y lo manda hacia la api
	 * 
	 * @author jptibio - 16/02/25
	 * @param usuarioCreado
	 * @param sesion
	 * @return
	 */
	@PostMapping("/CrearUsuarioComoAdmin")
	public ModelAndView crearUnNuevoGrupoComoAdmin(@ModelAttribute GruposDto grupoCreado, HttpSession sesion) {
		try {
			return servicioPerfil.crearGrupoComoAdmin(grupoCreado, sesion);
		} catch (Exception ex) {
			ModelAndView vistaError = new ModelAndView("error");
			vistaError.addObject("error", "Ha ocurrido un error al crear el grupo. Por favor, inténtalo de nuevo.");
			return vistaError;
		}
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