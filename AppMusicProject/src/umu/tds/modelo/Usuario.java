package umu.tds.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Usuario {

	private int id;
	private String nick;
	private String password;
	private String email;
	private boolean premium;
	private LocalDate fechaNacimiento;
	private Descuento descuentoAplicado;
	private List<PlayList> playLists;
	private List<Cancion> recientes;
	private static int edadJoven = 29;

	public Usuario(String nick, String password, String email, LocalDate fechaNacimiento) {
		this.id = 0;
		this.nick = nick;
		this.password = password;
		this.email = email;
		this.premium = false;
		this.fechaNacimiento = fechaNacimiento;
		this.descuentoAplicado = null;
		aplicarMayorDescuento();
		this.playLists = new LinkedList<PlayList>();
		this.recientes = new LinkedList<Cancion>();
	}

	public Usuario(String nick, String password, String email, boolean premium, LocalDate fechaNacimiento,
			Descuento descuentoAplicado) {
		this(nick, password, email, fechaNacimiento);
		this.premium = premium;
		this.descuentoAplicado = descuentoAplicado;
	}

	private void aplicarMayorDescuento() {
		List<Descuento> descuentosDisponibles = obtenerDescuentosDisponibles();

		if (!descuentosDisponibles.isEmpty()) {
			Descuento mayorDescuento = obtenerMayorDescuento(descuentosDisponibles);
			this.descuentoAplicado = mayorDescuento;
		}
	}

	private List<Descuento> obtenerDescuentosDisponibles() {
		List<Descuento> descuentos = new ArrayList<>();

		if (premium) {
			descuentos.add(new DescuentoFijo());
		}

		if (isJoven()) {
			descuentos.add(new DescuentoJovenes());
		}

		return descuentos;
	}

	private Descuento obtenerMayorDescuento(List<Descuento> descuentos) {
		if (descuentos.isEmpty()) {
			return null;
		}

		Descuento mayorDescuento = Collections.max(descuentos, Comparator.comparingDouble(Descuento::getDescuento));
		return mayorDescuento;
	}

	/**
	 * Función que recibe el nombre de la nueva PlayList que se quiere crear junto
	 * a una o varias canciones que serán añadidas a dicha lista.
	 * 
	 * @param nombrePlayList nombre de la nueva playlist.
	 * @param canciones      canción o canciones que serán añadidas a dicha
	 *                       playlist.
	 * @return la nueva playlist creada.
	 */
	public PlayList addPlayList(String nombrePlayList, Cancion... canciones) {
		PlayList newPlaylist = new PlayList(nombrePlayList, new LinkedList<Cancion>(Arrays.asList(canciones)));
		playLists.add(newPlaylist);
		return newPlaylist;
	}

	public void addToRecientes(Cancion cancion) {
		recientes.add(cancion);
	}

	/**
	 * Función que indica si el usuario es joven (menor de la constante edadJoven)
	 * o no.
	 * 
	 * @return true si tiene menos de edadJoven años o false si tiene edadJoven o
	 *         más.
	 */
	public boolean isJoven() {
		return fechaNacimiento.isBefore(LocalDate.now().minusYears(edadJoven));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
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

	public Descuento getDescuentoAplicado() {
		return descuentoAplicado;
	}

	public void setDescuentoAplicado(Descuento descuentoAplicado) {
		this.descuentoAplicado = descuentoAplicado;
	}

	public List<PlayList> getPlaylists() {
		return playLists;
	}

	public void setPlaylists(List<PlayList> playlists) {
		this.playLists = playlists;
	}

	public List<Cancion> getRecientes() {
		return recientes;
	}

	public void setRecientes(List<Cancion> recientes) {
		this.recientes = recientes;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nick=" + nick + ", password=" + password + ", email=" + email + ", premium="
				+ premium + ", fechaNacimiento=" + fechaNacimiento + ", descuentoAplicado=" + descuentoAplicado
				+ ", playLists=" + playLists + ", recientes=" + recientes + "]";
	}

}
