package edu.ProyectoFinal.Dto;

/**
 * Clase dto donde se encuentra la eliminacion de usuario o grupos segun su id y
 * su nombre
 * 
 * @author jpribio - 14/02/25
 */
public class eliminarElementoPerfilDto {

	private Long idElementoEliminar;
	private String elementoEliminar = "aaaaa";

	public Long getIdElementoEliminar() {
		return idElementoEliminar;
	}

	public void setIdElementoEliminar(Long idElementoEliminar) {
		this.idElementoEliminar = idElementoEliminar;
	}

	public String getElementoEliminar() {
		return elementoEliminar;
	}

	public void setElementoEliminar(String elementoEliminar) {
		this.elementoEliminar = elementoEliminar;
	}

}
