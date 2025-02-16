package edu.ProyectoFinal.Dto;

/**
 * Clase donde se encuentra los atributos de un nuevo grupo
 * 
 * @author jpribio - 16/02/25
 */
public class GruposDto {

	private String nombreGrupo = "aaaaa";
	private String categoriaNombre = "aaaaa";
	private String subCategoriaNombre = "aaaaa";
	private long idCreador;
	private String aliasCreadorUString = "aaaaa";
	
	

	public String getAliasCreadorUString() {
		return aliasCreadorUString;
	}

	public void setAliasCreadorUString(String aliasCreadorUString) {
		this.aliasCreadorUString = aliasCreadorUString;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public String getCategoriaNombre() {
		return categoriaNombre;
	}

	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}

	public String getSubCategoriaNombre() {
		return subCategoriaNombre;
	}

	public void setSubCategoriaNombre(String subCategoriaNombre) {
		this.subCategoriaNombre = subCategoriaNombre;
	}

	public long getIdCreador() {
		return idCreador;
	}

	public void setIdCreador(long idCreador) {
		this.idCreador = idCreador;
	}

}
