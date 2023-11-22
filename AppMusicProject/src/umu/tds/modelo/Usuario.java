package umu.tds.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Usuario {

	private String id;
	private String usuario;
	private String password;
	private String email;
	private boolean premium;
	private LocalDate fechaNacimiento;
	private List<PlayList> playlists;

	public Usuario(String usuario, String password, String email, boolean premium, LocalDate fechaNacimiento,
			List<PlayList> playlists) {
		super();
		this.id = UUID.randomUUID().toString();;
		this.usuario = usuario;
		this.password = password;
		this.email = email;
		this.premium = premium;
		this.fechaNacimiento = fechaNacimiento;
		this.playlists = playlists;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<PlayList> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlayList> playlists) {
		this.playlists = playlists;
	}

}
