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
		this.descuentoAplicado = aplicarMayorDescuento();
		this.playLists = new LinkedList<PlayList>();
		this.recientes = new LinkedList<Cancion>();
	}

	public Usuario(String nick, String password, String email, boolean premium, LocalDate fechaNacimiento,
			Descuento descuentoAplicado) {
		this(nick, password, email, fechaNacimiento);
		this.premium = premium;
		this.descuentoAplicado = descuentoAplicado;
	}

	private Descuento aplicarMayorDescuento() {
		List<Descuento> descuentosDisponibles = obtenerDescuentosDisponibles();

		if (descuentosDisponibles.isEmpty()) {
			return null;
		}

		return obtenerMayorDescuento(descuentosDisponibles);
	}

	private List<Descuento> obtenerDescuentosDisponibles() {
		List<Descuento> descuentos = new ArrayList<>();

		descuentos.add(new DescuentoFijo());

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
	 * Funcion que recibe el nombre de la nueva PlayList que se quiere crear junto a
	 * una o varias canciones que seran a�adidas a dicha lista.
	 * 
	 * @param nombrePlayList nombre de la nueva playlist.
	 * @param canciones      canción o canciones que serán añadidas a dicha
	 *                       playlist.
	 * @return la nueva playlist creada.
	 */
	public void addPlayList(String nombrePlayList, Cancion... canciones) {
		PlayList newPlaylist = new PlayList(nombrePlayList, new LinkedList<Cancion>(Arrays.asList(canciones)));
		playLists.add(newPlaylist);
	}

	public void addPlayList(PlayList playlist) {
		playLists.add(playlist);
	}

	public boolean eliminarPlayList(PlayList playlist) {
		return this.playLists.remove(playlist);
	}

	public void addCancionToPlayList(PlayList playlist, Cancion cancion) {
		PlayList p = getPlayListPorNombre(playlist.getNombre());
		p.addCancion(cancion);
	}

	public PlayList getPlayListPorNombre(String nombrePlayList) {
		return playLists.stream().filter(pl -> pl.getNombre().equals(nombrePlayList)).findFirst().orElse(null);
	}

	public void addToRecientes(Cancion cancion) {
		recientes.add(cancion);
	}

	/**
	 * Funcion que indica si el usuario es joven (menor de la constante edadJoven) o
	 * no.
	 * 
	 * @return true si tiene menos de edadJoven años o false si tiene edadJoven o
	 *         mas.
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
		return new LinkedList<PlayList>(playLists);
	}

	public void setPlaylists(List<PlayList> playlists) {
		this.playLists = playlists;
	}

	public List<Cancion> getRecientes() {
		return new LinkedList<Cancion>(recientes);
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
