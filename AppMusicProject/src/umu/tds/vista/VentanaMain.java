package umu.tds.vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import umu.tds.controlador.AppMusic;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.PlayList;
import umu.tds.persistencia.DAOException;

import javax.swing.*;
import java.awt.*;

public class VentanaMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelListas;
	private JTextField textFieldTituloGestion, textFieldBuscarInterprete, textFieldBuscarTitulo;
	private JTable tableCanciones;
	private JFileChooser fileChooser;
	private JButton btnEliminarLista, btnAnadirLista;
	private JList<PlayList> playlistJList;
	private JCheckBox chckbxFavoritos;
	private JComboBox<PlayList> comboBoxPlaylists;
	private List<Integer> filasSeleccionadasEnBuscar = new ArrayList<>();
	private JComboBox<String> comboBoxEstiloMusical;

	private static final String TITULO_APP = "AppMusic";
	private static final String FUENTE = "Tahoma";
	private static final String TEXTO_BOTON_BUSCAR = "Buscar";
	private static final String TEXTO_BOTON_GESTION_PLAYLIST = "Gestion Playlists";
	private static final String TEXTO_BOTON_RECIENTES = "Recientes";
	private static final String TEXTO_BOTON_MIS_PLAYLIST = "Mis Playlists";
	private static final String LABEL_LISTAS = "Listas";
	private static final String LABEL_BIENVENIDO = "Bienvenido, ";
	private static final String TEXTO_BOTON_PREMIUM = "Premium";
	private static final String TEXTO_BOTON_SALIR = "Salir";
	private static final String TEXTO_BOTON_ADD_LISTA = "Añadir Lista";
	private static final String TEXTO_BOTON_ELIMINAR_LISTA = "Eliminar Lista";
	private static final String MENSAJE_PLAYLIST_CREADA = "Playlist creada correctamente";
	private static final String TITULO_EXITO = "Exito";
	private static final String MENSAJE_NO_CANCIONES_SELECCIONADAS = "No se han seleccionado canciones";
	private static final String TITULO_ADVERTENCIA = "Advertencia";
	private static final String MENSAJE_SELECCIONAR_PLAYLIST = "Seleccionar Playlist";
	private static final String MENSAJE_CANCION_EXISTENTE_PLAYLIST = "La cancion ya existe en la playlist";
	private static final String MENSAJE_CANCIONES_ANADIDAS = "Canciones añadidas a la playlist correctamente";
	private static final String MENSAJE_CANCIONES_NO_SELECCIONADAS = "No se han seleccionado canciones";
	private static final String MENSAJE_NOMBRE_PLAYLIST = "Introduce un nombre para la playlist";
	private static final String TITULO = "titulo";
	private static final String INTERPRETE = "interprete";
	private static final String ESTILO = "estilo";
	private static final String CARGAR_CANCIONES = "Cargar canciones";
	private static final String MENSAJE_CREAR_PLAYLIST = "¿Deseas crear la playlist?";
	private static final String RUTA_IMAGEN_MUSICA = "/utilidades/imagenes/musica.png";
	private static final String RUTA_IMAGEN_ANTERIOR = "/utilidades/imagenes/anterior.png";
	private static final String RUTA_IMAGEN_STOP = "/utilidades/imagenes/stop.png";
	private static final String RUTA_IMAGEN_PAUSE = "/utilidades/imagenes/pausa.png";
	private static final String RUTA_IMAGEN_PLAY = "/utilidades/imagenes/play.png";
	private static final String RUTA_IMAGEN_SIGUIENTE = "/utilidades/imagenes/siguiente.png";
	private static final String RUTA_IMAGEN_LUPA = "/utilidades/imagenes/lupa.png";
	private static final String RUTA_IMAGEN_PLUS = "/utilidades/imagenes/signo-de-mas.png";
	private static final String RUTA_IMAGEN_RELOJ = "/utilidades/imagenes/reloj.png";
	private static final String RUTA_IMAGEN_ALTAVOZ = "/utilidades/imagenes/altavoz.png";

	public VentanaMain() {
		inicializarVentana();
		configurarContentPane();
	}

	private void inicializarVentana() {
		setTitle(TITULO_APP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 370);
		setMinimumSize(new Dimension(800, 600));
		ImageIcon icono = new ImageIcon(getClass().getResource(RUTA_IMAGEN_MUSICA));
		setIconImage(icono.getImage());
	}

	private void configurarContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelCardLayout = new JPanel();
		inicializarBarraLateral(panelCardLayout);

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[] { 0, 0 };
		gbl_panelCentro.rowHeights = new int[] { 0, 0, 340, 0 };
		gbl_panelCentro.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelCentro.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		panelCentro.setLayout(gbl_panelCentro);

		GridBagConstraints gbc_panelUsuario = new GridBagConstraints();
		gbc_panelUsuario.fill = GridBagConstraints.BOTH;
		gbc_panelUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_panelUsuario.gridx = 0;
		gbc_panelUsuario.gridy = 0;
		panelCentro.add(crearPanelUsuario(), gbc_panelUsuario);

		GridBagConstraints gbc_panelCardLayout = new GridBagConstraints();
		gbc_panelCardLayout.fill = GridBagConstraints.BOTH;
		gbc_panelCardLayout.insets = new Insets(0, 0, 5, 0);
		gbc_panelCardLayout.gridx = 0;
		gbc_panelCardLayout.gridy = 1;
		panelCentro.add(panelCardLayout, gbc_panelCardLayout);
		panelCardLayout.setLayout(new CardLayout(0, 0));

		// Panel buscar
		PanelBuscar panelBuscar = new PanelBuscar(e -> buscarCancion());
		panelCardLayout.add(panelBuscar, "panelBuscar");
		textFieldBuscarInterprete = panelBuscar.getTextFieldBuscarInterprete();
		textFieldBuscarTitulo = panelBuscar.getTextFieldBuscarTitulo();
		chckbxFavoritos = panelBuscar.getChckbxFavoritos();
		comboBoxEstiloMusical = panelBuscar.getComboBoxEstiloMusical();

		// Panel gestion
		PanelGestion panelGestion = new PanelGestion(e -> administrarPlaylist(), e -> eliminarPlaylist());
		panelCardLayout.add(panelGestion, "panelGestion");
		textFieldTituloGestion = panelGestion.getTextFieldTituloGestion();

		JPanel panelRecientes = new JPanel();
		panelCardLayout.add(panelRecientes, "panelRecientes");

		JPanel panelPlaylists = new JPanel();
		panelCardLayout.add(panelPlaylists, "panelPlaylists");
		panelPlaylists.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		GridBagConstraints gbc_panelTablaCanciones = new GridBagConstraints();
		gbc_panelTablaCanciones.fill = GridBagConstraints.BOTH;
		gbc_panelTablaCanciones.gridx = 0;
		gbc_panelTablaCanciones.gridy = 2;
		panelCentro.add(crearPanelTablaCanciones(), gbc_panelTablaCanciones);
	}

	private JPanel crearPanelTablaCanciones() {
		JPanel panelTablaCanciones = new JPanel();
		panelTablaCanciones.setLayout(new BorderLayout(0, 0));

		crearPanelBotonesReproduccion(panelTablaCanciones);

		tableCanciones = new JTable();
		tableCanciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				filasSeleccionadasEnBuscar.clear();

				for (int fila = 0; fila < tableCanciones.getRowCount(); fila++) {
					boolean isChecked = (boolean) tableCanciones.getValueAt(fila, 3);

					if (isChecked) {
						filasSeleccionadasEnBuscar.add(fila);
					}
				}

				// Play al hacer click 2 veces
				if (e.getClickCount() == 2) {
					AppMusic.getUnicaInstancia().stopAllCanciones();
					reproducirCancion();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(tableCanciones);
		panelTablaCanciones.add(scrollPane, BorderLayout.CENTER);

		try {
			cargarCancionesEnTabla(AppMusic.getUnicaInstancia().getCanciones());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}

		return panelTablaCanciones;
	}

	private JPanel crearPanelUsuario() {
		JPanel panelUsuario = new JPanel();
		FlowLayout fl_panelUsuario = (FlowLayout) panelUsuario.getLayout();
		fl_panelUsuario.setAlignment(FlowLayout.RIGHT);

		JButton btnCargarCanciones = new JButton(CARGAR_CANCIONES);
		btnCargarCanciones.addActionListener(e -> seleccionarArchivo());
		panelUsuario.add(btnCargarCanciones);

		JLabel lblBienvenido = new JLabel(LABEL_BIENVENIDO + AppMusic.getUnicaInstancia().getUsuarioActual().getNick());
		panelUsuario.add(lblBienvenido);

		JButton btnPremium = new JButton(TEXTO_BOTON_PREMIUM);
		btnPremium.addActionListener(e -> comprobarPremium());
		panelUsuario.add(btnPremium);

		JButton btnSalir = new JButton(TEXTO_BOTON_SALIR);
		btnSalir.addActionListener(e -> System.exit(0));
		panelUsuario.add(btnSalir);

		return panelUsuario;
	}

	private void crearPanelBotonesReproduccion(JPanel panelTablaCanciones) {
		JPanel panelBotonesReproduccion = new JPanel();
		panelBotonesReproduccion.setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton btnAtras = new JButton("");
		btnAtras.setIcon(new ImageIcon(VentanaMain.class.getResource(RUTA_IMAGEN_ANTERIOR)));
		btnAtras.addActionListener(e -> {
			AppMusic.getUnicaInstancia().stopCancion();

			int filaSeleccionada = tableCanciones.getSelectedRow();
			int totalFilas = tableCanciones.getRowCount();
			int indiceCancionActual = (filaSeleccionada - 1 + totalFilas) % totalFilas;

			tableCanciones.setRowSelectionInterval(indiceCancionActual, indiceCancionActual);

			reproducirCancion();
		});

		JButton btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon(VentanaMain.class.getResource(RUTA_IMAGEN_STOP)));
		btnStop.addActionListener(e -> AppMusic.getUnicaInstancia().stopCancion());

		JButton btnPause = new JButton("");
		btnPause.addActionListener(e -> AppMusic.getUnicaInstancia().pausarCancion());
		btnPause.setIcon(new ImageIcon(VentanaMain.class.getResource(RUTA_IMAGEN_PAUSE)));

		JButton btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(VentanaMain.class.getResource(RUTA_IMAGEN_PLAY)));
		btnPlay.addActionListener(e -> reproducirCancion());

		JButton btnSiguiente = new JButton("");
		btnSiguiente.setIcon(new ImageIcon(VentanaMain.class.getResource(RUTA_IMAGEN_SIGUIENTE)));
		btnSiguiente.addActionListener(e -> {
			AppMusic.getUnicaInstancia().stopCancion();

			int filaSeleccionada = tableCanciones.getSelectedRow();
			int indiceCancionActual = (filaSeleccionada + 1) % tableCanciones.getRowCount();

			tableCanciones.setRowSelectionInterval(indiceCancionActual, indiceCancionActual);

			reproducirCancion();
		});

		btnAnadirLista = new JButton(TEXTO_BOTON_ADD_LISTA);
		btnAnadirLista.addActionListener(e -> {
			addCancionesToPlaylist();
		});

		btnEliminarLista = new JButton(TEXTO_BOTON_ELIMINAR_LISTA);
		btnEliminarLista.addActionListener(e -> {
			eliminarCancion();
		});
		btnEliminarLista.setVisible(false);

		panelBotonesReproduccion.add(btnAtras);
		panelBotonesReproduccion.add(btnStop);
		panelBotonesReproduccion.add(btnPause);
		panelBotonesReproduccion.add(btnPlay);
		panelBotonesReproduccion.add(btnSiguiente);
		panelBotonesReproduccion.add(btnAnadirLista);
		panelBotonesReproduccion.add(btnEliminarLista);
		panelTablaCanciones.add(panelBotonesReproduccion, BorderLayout.SOUTH);
	}

	private void inicializarBarraLateral(JPanel panelCardLayout) {
		JPanel panelBotonera = new JPanel();
		panelBotonera.setLayout(new BoxLayout(panelBotonera, BoxLayout.Y_AXIS));
		contentPane.add(panelBotonera, BorderLayout.WEST);

		JButton btnBuscar = new JButton(TEXTO_BOTON_BUSCAR);
		btnBuscar.addActionListener(e -> {
			cambiarPanelCard(panelCardLayout, "panelBuscar");
			actualizarVisibilidadListas(false, false, true);
		});

		btnBuscar.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnBuscar.setFont(new Font(FUENTE, Font.BOLD, 14));
		btnBuscar.setIcon(new ImageIcon(VentanaMain.class.getResource(RUTA_IMAGEN_LUPA)));
		btnBuscar.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnBuscar.getPreferredSize().height));
		panelBotonera.add(btnBuscar);

		JButton btnGestionPlaylist = new JButton(TEXTO_BOTON_GESTION_PLAYLIST);
		btnGestionPlaylist.addActionListener(e -> {
			cambiarPanelCard(panelCardLayout, "panelGestion");
			actualizarVisibilidadListas(true, false, false);

			if (filasSeleccionadasEnBuscar != null && filasSeleccionadasEnBuscar.size() > 0) {
				List<Cancion> cancionesSeleccionadas = new ArrayList<>();

				for (int fila : filasSeleccionadasEnBuscar) {
					String titulo = (String) tableCanciones.getValueAt(fila, 0);
					cancionesSeleccionadas.add(AppMusic.getUnicaInstancia().getCancionPorTitulo(titulo));
				}

				cargarCancionesEnTabla(cancionesSeleccionadas);
			} else {
				cargarCancionesEnTabla(new ArrayList<>());
			}
		});

		btnGestionPlaylist.setIcon(new ImageIcon(VentanaMain.class.getResource(RUTA_IMAGEN_PLUS)));
		btnGestionPlaylist.setFont(new Font(FUENTE, Font.BOLD, 14));
		btnGestionPlaylist.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnGestionPlaylist
				.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnGestionPlaylist.getPreferredSize().height));
		panelBotonera.add(btnGestionPlaylist);

		JButton btnRecientes = new JButton(TEXTO_BOTON_RECIENTES);
		btnRecientes.addActionListener(e -> {
			cambiarPanelCard(panelCardLayout, "panelRecientes");
			actualizarVisibilidadListas(false, false, true);

			if (filasSeleccionadasEnBuscar.size() > 0) {
				filasSeleccionadasEnBuscar.clear();
			}

			List<Cancion> recientes = AppMusic.getUnicaInstancia().getRecientes();
			cargarCancionesEnTabla(recientes);
		});

		btnRecientes.setIcon(new ImageIcon(VentanaMain.class.getResource(RUTA_IMAGEN_RELOJ)));
		btnRecientes.setFont(new Font(FUENTE, Font.BOLD, 14));
		btnRecientes.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnRecientes.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnRecientes.getPreferredSize().height));
		panelBotonera.add(btnRecientes);

		DefaultListModel<PlayList> listModel = new DefaultListModel<>();
		playlistJList = new JList<>(listModel);

		playlistJList.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				if (value instanceof PlayList) {
					PlayList playList = (PlayList) value;
					setText(playList.getNombre());
				}
				return this;
			}
		});

		playlistJList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				PlayList playlistSeleccionada = playlistJList.getSelectedValue();
				if (playlistSeleccionada != null) {
					String nombrePlaylist = playlistSeleccionada.getNombre();
					cargarCancionesEnTabla(nombrePlaylist);
				}
			}
		});

		JScrollPane scrollPaneLista = new JScrollPane(playlistJList);

		JButton btnMisPlaylist = new JButton(TEXTO_BOTON_MIS_PLAYLIST);
		btnMisPlaylist.addActionListener(e -> {
			cambiarPanelCard(panelCardLayout, "panelPlaylists");
			actualizarVisibilidadListas(true, true, false);

			if (filasSeleccionadasEnBuscar.size() > 0) {
				filasSeleccionadasEnBuscar.clear();
			}

			List<PlayList> playlists = AppMusic.getUnicaInstancia().getAllPlayListPorUsuario();
			listModel.clear();
			playlists.forEach(listModel::addElement);
		});

		btnMisPlaylist.setIcon(new ImageIcon(VentanaMain.class.getResource(RUTA_IMAGEN_ALTAVOZ)));
		btnMisPlaylist.setFont(new Font(FUENTE, Font.BOLD, 14));
		scrollPaneLista.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnMisPlaylist.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnMisPlaylist.getPreferredSize().height));
		panelBotonera.add(btnMisPlaylist);

		panelListas = new JPanel();
		panelListas.setVisible(false);
		panelListas.setPreferredSize(new Dimension(50, 50));
		panelListas.setLayout(new BorderLayout(0, 0));
		panelBotonera.add(panelListas);

		panelListas.add(scrollPaneLista, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel(LABEL_LISTAS);
		panelListas.add(lblNewLabel, BorderLayout.NORTH);
	}

	private void actualizarVisibilidadListas(boolean mostrarPanel, boolean mostrarEliminar, boolean mostrarAnadir) {
		panelListas.setVisible(mostrarPanel);
		btnEliminarLista.setVisible(mostrarEliminar);
		btnAnadirLista.setVisible(mostrarAnadir);
	}

	private void addCancionesAlCrear() {
		List<Integer> filasSeleccionadas = new ArrayList<>();

		for (int fila = 0; fila < tableCanciones.getRowCount(); fila++) {
			boolean isChecked = (boolean) tableCanciones.getValueAt(fila, 3);

			if (isChecked) {
				filasSeleccionadas.add(fila);
			}
		}

		if (filasSeleccionadas.size() > 0) {
			String nombrePlaylist = textFieldTituloGestion.getText();
			List<PlayList> playlists = AppMusic.getUnicaInstancia().getAllPlayListPorUsuario();
			PlayList playlistSeleccionada = new PlayList(nombrePlaylist);
			for (PlayList playlist : playlists) {
				if (playlistSeleccionada.getNombre() == playlist.getNombre()) {
					playlistSeleccionada = playlist;
				}
			}

			for (int fila : filasSeleccionadas) {
				String titulo = (String) tableCanciones.getValueAt(fila, 0);
				Cancion cancion = AppMusic.getUnicaInstancia().getCancionPorTitulo(titulo);
				AppMusic.getUnicaInstancia().addCancionToPlayList(cancion, playlistSeleccionada);
			}

			JOptionPane.showMessageDialog(this, MENSAJE_PLAYLIST_CREADA, TITULO_EXITO, JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(this, MENSAJE_NO_CANCIONES_SELECCIONADAS, TITULO_ADVERTENCIA,
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void addCancionesToPlaylist() {
		List<Integer> filasSeleccionadas = new ArrayList<>();

		for (int fila = 0; fila < tableCanciones.getRowCount(); fila++) {
			boolean isChecked = (boolean) tableCanciones.getValueAt(fila, 3);

			if (isChecked) {
				filasSeleccionadas.add(fila);
			}

		}

		if (filasSeleccionadas.size() > 0) {
			List<PlayList> playlists = AppMusic.getUnicaInstancia().getAllPlayListPorUsuario();

			comboBoxPlaylists = new JComboBox<>(playlists.toArray(new PlayList[0]));
			comboBoxPlaylists.setRenderer(new PlaylistCellRenderer());

			int resultado = JOptionPane.showConfirmDialog(this, comboBoxPlaylists, MENSAJE_SELECCIONAR_PLAYLIST,
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (resultado == JOptionPane.OK_OPTION) {
				PlayList playlistSeleccionada = (PlayList) comboBoxPlaylists.getSelectedItem();

				for (int fila : filasSeleccionadas) {
					String titulo = (String) tableCanciones.getValueAt(fila, 0);
					Cancion cancion = AppMusic.getUnicaInstancia().getCancionPorTitulo(titulo);

					if (!playlistSeleccionada.contieneCancion(cancion)) {
						AppMusic.getUnicaInstancia().addCancionToPlayList(cancion, playlistSeleccionada);
					} else {
						JOptionPane.showMessageDialog(this, MENSAJE_CANCION_EXISTENTE_PLAYLIST, TITULO_ADVERTENCIA,
								JOptionPane.WARNING_MESSAGE);
					}
				}

				JOptionPane.showMessageDialog(this, MENSAJE_CANCIONES_ANADIDAS, TITULO_EXITO,
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, MENSAJE_CANCIONES_NO_SELECCIONADAS, TITULO_ADVERTENCIA,
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void administrarPlaylist() {
		String nombrePlaylist = textFieldTituloGestion.getText();

		if (!nombrePlaylist.isEmpty()) {
			if (!AppMusic.getUnicaInstancia().isPlayListCreada(nombrePlaylist)) {
				dialogoCrearPlayList(nombrePlaylist);
			} else {
				cargarCancionesEnTabla(nombrePlaylist);
			}
		} else
			mensajeNombrePlayListVacio();
	}

	private void mensajeNombrePlayListVacio() {
		JOptionPane.showMessageDialog(this, MENSAJE_NOMBRE_PLAYLIST, TITULO_ADVERTENCIA,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void dialogoCrearPlayList(String nombrePlaylist) {
		Object[] opciones = { "Crear", "Cancelar" };

		int opcion = JOptionPane.showOptionDialog(this, MENSAJE_CREAR_PLAYLIST + nombrePlaylist, "Crear nueva playlist",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);

		if (opcion == 0) {
			registrarPlayList(nombrePlaylist);
			addCancionesAlCrear();
		}
	}

	private void registrarPlayList(String nombrePlaylist) {
		AppMusic.getUnicaInstancia().registrarPlayList(nombrePlaylist);
	}

	private void cargarEstilosComboBox() {
		try {
			List<String> estilosList = AppMusic.getUnicaInstancia().getEstilos();
			DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(estilosList.toArray(new String[0]));
			comboBoxEstiloMusical.setModel(comboBoxModel);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
	}

	private void buscarCancion() {
		String titulo = textFieldBuscarTitulo.getText();
		String interprete = textFieldBuscarInterprete.getText();
		Object itemSeleccionado = comboBoxEstiloMusical.getSelectedItem();
		boolean incluirEnFavoritos = chckbxFavoritos.isSelected();

		try {
			List<Cancion> resultadoBusqueda;

			if (itemSeleccionado != null) {
				String estilo = itemSeleccionado.toString();
				resultadoBusqueda = AppMusic.getUnicaInstancia().buscarCancion(titulo, interprete, estilo);
			} else {
				resultadoBusqueda = AppMusic.getUnicaInstancia().buscarCancion(titulo, interprete, null);
			}

			if (incluirEnFavoritos) {
				List<Cancion> cancionesFiltradas = filtrarCancionesEnPlaylist(resultadoBusqueda);
				cargarCancionesEnTabla(cancionesFiltradas);
			}

			cargarCancionesEnTabla(resultadoBusqueda);

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	private List<Cancion> filtrarCancionesEnPlaylist(List<Cancion> canciones) {
		return canciones.stream().filter(cancion -> AppMusic.getUnicaInstancia().getUsuarioActual().getPlaylists()
				.stream().anyMatch(playlist -> playlist.getCanciones().contains(cancion))).toList();
	}

	private void cambiarPanelCard(JPanel panelCardLayout, String panel) {
		CardLayout card = (CardLayout) panelCardLayout.getLayout();
		card.show(panelCardLayout, panel);
	}

	private void seleccionarArchivo() {
		fileChooser = new JFileChooser();
		int resultado = fileChooser.showOpenDialog(this);

		if (resultado == JFileChooser.APPROVE_OPTION) {
			String xml = fileChooser.getSelectedFile().getAbsolutePath();
			AppMusic.getUnicaInstancia().cargarCanciones(xml);
			try {
				List<Cancion> canciones = AppMusic.getUnicaInstancia().getCanciones();
				cargarCancionesEnTabla(canciones);
				cargarEstilosComboBox();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
	}

	private void cargarCancionesEnTabla(List<Cancion> canciones) {
		Object[][] data = new Object[canciones.size()][4];

		for (int i = 0; i < canciones.size(); i++) {
			Cancion cancion = canciones.get(i);
			data[i][0] = cancion.getTitulo();
			data[i][1] = cancion.getInterprete();
			data[i][2] = cancion.getEstilo();
			data[i][3] = false;
		}

		TableModelCanciones model = new TableModelCanciones(data, new String[] { TITULO, INTERPRETE, ESTILO, "" });

		tableCanciones.setModel(model);
	}

	private void cargarCancionesEnTabla(String nombrePlaylist) {
		List<Cancion> canciones = AppMusic.getUnicaInstancia().getCancionesDePlaylist(nombrePlaylist);

		Object[][] data = new Object[canciones.size()][4];

		for (int i = 0; i < canciones.size(); i++) {
			Cancion cancion = canciones.get(i);
			data[i][0] = cancion.getTitulo();
			data[i][1] = cancion.getInterprete();
			data[i][2] = cancion.getEstilo();
			data[i][3] = false;
		}

		TableModelCanciones model = new TableModelCanciones(data, new String[] { TITULO, INTERPRETE, ESTILO, "" });

		tableCanciones.setModel(model);
	}

	private String obtenerRutaCancionSeleccionada() {
		int filaSeleccionada = tableCanciones.getSelectedRow();
		String rutaCancion = "";

		if (filaSeleccionada != -1) {
			String tituloSeleccionado = (String) tableCanciones.getValueAt(filaSeleccionada, 0);

			try {
				List<Cancion> canciones = AppMusic.getUnicaInstancia().getCanciones();
				Optional<Cancion> cancionSeleccionada = canciones.stream()
						.filter(c -> c.getTitulo().equals(tituloSeleccionado)).findFirst();

				rutaCancion = cancionSeleccionada.map(Cancion::getURL).orElse("");
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}

		return rutaCancion;
	}

	private void reproducirCancion() {
		String ruta = obtenerRutaCancionSeleccionada();
		if (ruta != "") {
			AppMusic.getUnicaInstancia().reproducirCancion(ruta);
		}
	}

	private void comprobarPremium() {
		if (AppMusic.getUnicaInstancia().getUsuarioActual().isPremium())
			opcionesUsuarioPremium();
		else
			altaPremium();
	}

	private void opcionesUsuarioPremium() {
		Object[] opciones = { "crear PDF de las playlist", "Reproducir TOP 10 canciones" };

		int opcion = JOptionPane.showOptionDialog(this, "Elige una opcion por ser premium", "Servicios premium",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);

		switch (opcion) {
		case 0:
			AppMusic.getUnicaInstancia().crearPDF();
			break;
		case 1:
			try {
				cargarCancionesEnTabla(AppMusic.getUnicaInstancia().getTopRecientes());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		default:
			break;
		}
	}

	private void altaPremium() {
		Object[] opciones = { "Pagar", "Mas tarde" };

		int opcion = JOptionPane.showOptionDialog(this,
				"Coste original: " + AppMusic.getUnicaInstancia().getUsuarioActual().getDescuentoAplicado().getPrecio()
						+ "\nDescuento aplicado: "
						+ AppMusic.getUnicaInstancia().getUsuarioActual().getDescuentoAplicado().getClass()
								.getSimpleName()
						+ "\nPrecio final: "
						+ AppMusic.getUnicaInstancia().getUsuarioActual().getDescuentoAplicado().calcularDescuento(),
				"Alta servicio premium", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
				opciones[1]);

		if (opcion == 0) {
			AppMusic.getUnicaInstancia().altaUsuarioPremium();
		}
	}

	private void eliminarPlaylist() {
		if (AppMusic.getUnicaInstancia().borrarPlayListDelUsuario(textFieldTituloGestion.getText())) {
			textFieldTituloGestion.setText("");
			cargarCancionesEnTabla(new LinkedList<Cancion>());
		}
	}

	private Integer obtenerCodigoCancion() {
		int filaSeleccionada = tableCanciones.getSelectedRow();
		int codigoCancion = 0;

		if (filaSeleccionada != -1) {
			String tituloSeleccionado = (String) tableCanciones.getValueAt(filaSeleccionada, 0);

			try {
				List<Cancion> canciones = AppMusic.getUnicaInstancia().getCanciones();
				Optional<Cancion> cancionSeleccionada = canciones.stream()
						.filter(c -> c.getTitulo().equals(tituloSeleccionado)).findFirst();

				codigoCancion = cancionSeleccionada.map(Cancion::getCodigo).orElse(0);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}

		return codigoCancion;
	}

	private void eliminarCancion() {
		AppMusic.getUnicaInstancia().eliminarCancion(obtenerCodigoCancion());
		cargarCancionesEnTabla(new LinkedList<Cancion>());
	}

}