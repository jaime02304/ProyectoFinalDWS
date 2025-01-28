package edu.ProyectoFinal.Dto;
/**
 * Clase donde se encuentra los atributos de los usuarios para el perfil
 */
public class UsuarioPerfilDto {

	private String nombreCompletoUsu = "aaaaa";
	private String aliasUsu = "aaaaa";
	private String correoElectronicoUsu = "aaaaa";
	private int movilUsu = 0;
	private byte[] fotoUsu;
	private Boolean esPremium = false;
	private String rolUsu = "user";

	public String getRolUsu() {
		return rolUsu;
	}

	public void setRolUsu(String rolUsu) {
		this.rolUsu = rolUsu;
	}

	public String getNombreCompletoUsu() {
		return nombreCompletoUsu;
	}

	public void setNombreCompletoUsu(String nombreCompletoUsu) {
		this.nombreCompletoUsu = nombreCompletoUsu;
	}

	public String getAliasUsu() {
		return aliasUsu;
	}

	public void setAliasUsu(String aliasUsu) {
		this.aliasUsu = aliasUsu;
	}

	public String getCorreoElectronicoUsu() {
		return correoElectronicoUsu;
	}

	public void setCorreoElectronicoUsu(String correoElectronicoUsu) {
		this.correoElectronicoUsu = correoElectronicoUsu;
	}

	public int getMovilUsu() {
		return movilUsu;
	}

	public void setMovilUsu(int movilUsu) {
		this.movilUsu = movilUsu;
	}

	public byte[] getFotoUsu() {
		return fotoUsu;
	}

	public void setFotoUsu(byte[] fotoUsu) {
		this.fotoUsu = fotoUsu;
	}

	public Boolean getEsPremium() {
		return esPremium;
	}

	public void setEsPremium(Boolean esPremium) {
		this.esPremium = esPremium;
	}

	public UsuarioPerfilDto() {
		super();
	}
}
