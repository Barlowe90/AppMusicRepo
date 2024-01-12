package umu.tds.vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.glassfish.jaxb.runtime.v2.runtime.output.StAXExStreamWriterOutput;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
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
import pulsador.Luz;

import javax.swing.*;
import java.awt.*;

public class VentanaMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTituloGestion;
	private JPanel panelListas;
	private JTextField textFieldBuscarInterprete;
	private JTextField textFieldBuscarTitulo;
	private JTable tableCanciones;
	private JFileChooser fileChooser;
	private JButton btnEliminarLista;
	private JButton btnAnadirLista;
	private JList<PlayList> playlistJList;
	private JCheckBox chckbxFavoritos;
	private JComboBox<PlayList> comboBoxPlaylists;
	private List<Integer> filasSeleccionadasEnBuscar = new ArrayList<>();
	private JComboBox<String> comboBoxEstiloMusical;

	public VentanaMain() {
		setTitle("AppMusic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 370);
		setMinimumSize(new Dimension(800, 600));
		ImageIcon icono = new ImageIcon(getClass().getResource("/umu/tds/images/musica.png"));
		setIconImage(icono.getImage());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panelCardLayout = new JPanel();

		JPanel panelBotonera = new JPanel();
		contentPane.add(panelBotonera, BorderLayout.WEST);
		GridBagLayout gbl_panelBotonera = new GridBagLayout();
		gbl_panelBotonera.columnWidths = new int[] { 0, 0 };
		gbl_panelBotonera.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelBotonera.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelBotonera.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelBotonera.setLayout(gbl_panelBotonera);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> {
			cambiarPanelCard(panelCardLayout, "panelBuscar");
			panelListas.setVisible(false);
			btnEliminarLista.setVisible(false);
			btnAnadirLista.setVisible(true);
		});

		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/lupa.png")));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 0;
		panelBotonera.add(btnBuscar, gbc_btnBuscar);

		JButton btnGestionPlaylist = new JButton("Gestion Playlists");
		btnGestionPlaylist.addActionListener(e -> {
			cambiarPanelCard(panelCardLayout, "panelGestion");
			btnEliminarLista.setVisible(true);
			btnAnadirLista.setVisible(false);
			panelListas.setVisible(false);

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

		btnGestionPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
		btnGestionPlaylist.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/signo-de-mas.png")));
		btnGestionPlaylist.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnGestionPlaylist = new GridBagConstraints();
		gbc_btnGestionPlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGestionPlaylist.insets = new Insets(0, 0, 5, 0);
		gbc_btnGestionPlaylist.gridx = 0;
		gbc_btnGestionPlaylist.gridy = 1;
		panelBotonera.add(btnGestionPlaylist, gbc_btnGestionPlaylist);

		JButton btnRecientes = new JButton("Recientes");
		btnRecientes.addActionListener(e -> {
			cambiarPanelCard(panelCardLayout, "panelRecientes");
			panelListas.setVisible(false);
			btnEliminarLista.setVisible(false);
			btnAnadirLista.setVisible(true);
			if (filasSeleccionadasEnBuscar.size() > 0) {
				filasSeleccionadasEnBuscar.clear();
			}

			List<Cancion> recientes = AppMusic.getUnicaInstancia().getRecientes();
			cargarCancionesEnTabla(recientes);
		});

		btnRecientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecientes.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/reloj.png")));
		btnRecientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnRecientes = new GridBagConstraints();
		gbc_btnRecientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRecientes.insets = new Insets(0, 0, 5, 0);
		gbc_btnRecientes.gridx = 0;
		gbc_btnRecientes.gridy = 2;
		panelBotonera.add(btnRecientes, gbc_btnRecientes);

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

		JButton btnMisPlaylist = new JButton("Mis Playlists");
		btnMisPlaylist.addActionListener(e -> {
			cambiarPanelCard(panelCardLayout, "panelPlaylists");
			panelListas.setVisible(true);
			btnEliminarLista.setVisible(false);
			btnAnadirLista.setVisible(true);
			if (filasSeleccionadasEnBuscar.size() > 0) {
				filasSeleccionadasEnBuscar.clear();
			}

			List<PlayList> playlists = AppMusic.getUnicaInstancia().getAllPlayListPorUsuario();
			listModel.clear();
			playlists.forEach(listModel::addElement);
		});

		btnMisPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
		btnMisPlaylist.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/altavoz.png")));
		btnMisPlaylist.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnMisPlaylist = new GridBagConstraints();
		gbc_btnMisPlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMisPlaylist.gridx = 0;
		gbc_btnMisPlaylist.gridy = 3;
		panelBotonera.add(btnMisPlaylist, gbc_btnMisPlaylist);

		panelListas = new JPanel();
		panelListas.setVisible(false);
		panelListas.setPreferredSize(new Dimension(50, 50));
		GridBagConstraints gbc_panelListas = new GridBagConstraints();
		gbc_panelListas.fill = GridBagConstraints.BOTH;
		gbc_panelListas.gridx = 0;
		gbc_panelListas.gridy = 4;
		panelBotonera.add(panelListas, gbc_panelListas);
		panelListas.setLayout(new BorderLayout(0, 0));
		panelListas.add(scrollPaneLista, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Listas");
		panelListas.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[] { 0, 0 };
		gbl_panelCentro.rowHeights = new int[] { 0, 0, 340, 0 };
		gbl_panelCentro.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelCentro.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		panelCentro.setLayout(gbl_panelCentro);

		JPanel panelUsuario = new JPanel();
		FlowLayout fl_panelUsuario = (FlowLayout) panelUsuario.getLayout();
		fl_panelUsuario.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_panelUsuario = new GridBagConstraints();
		gbc_panelUsuario.fill = GridBagConstraints.BOTH;
		gbc_panelUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_panelUsuario.gridx = 0;
		gbc_panelUsuario.gridy = 0;
		panelCentro.add(panelUsuario, gbc_panelUsuario);

		Luz btnElegirArchivo = new Luz();
		btnElegirArchivo.addEncendidoListener(e -> seleccionarArchivo());
		panelUsuario.add(btnElegirArchivo);

		JLabel lblBienvenido = new JLabel("Bienvenido, " + AppMusic.getUnicaInstancia().getUsuarioActual().getNick());
		panelUsuario.add(lblBienvenido);

		JButton btnPremium = new JButton("Premium");
		btnPremium.addActionListener(e -> comprobarPremium());
		panelUsuario.add(btnPremium);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(e -> System.exit(0));
		panelUsuario.add(btnSalir);

		GridBagConstraints gbc_panelCardLayout = new GridBagConstraints();
		gbc_panelCardLayout.fill = GridBagConstraints.BOTH;
		gbc_panelCardLayout.insets = new Insets(0, 0, 5, 0);
		gbc_panelCardLayout.gridx = 0;
		gbc_panelCardLayout.gridy = 1;
		panelCentro.add(panelCardLayout, gbc_panelCardLayout);
		panelCardLayout.setLayout(new CardLayout(0, 0));

		JPanel panelBuscar = new JPanel();
		panelCardLayout.add(panelBuscar, "panelBuscar");

		GridBagLayout gbl_panelBusqueda = new GridBagLayout();
		gbl_panelBusqueda.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelBusqueda.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panelBusqueda.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelBusqueda.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelBuscar.setLayout(gbl_panelBusqueda);

		textFieldBuscarInterprete = new JTextField();
		textFieldBuscarInterprete.setToolTipText("Interprete");
		GridBagConstraints gbc_textFieldBuscarInterprete = new GridBagConstraints();
		gbc_textFieldBuscarInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBuscarInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldBuscarInterprete.gridx = 1;
		gbc_textFieldBuscarInterprete.gridy = 0;
		panelBuscar.add(textFieldBuscarInterprete, gbc_textFieldBuscarInterprete);
		textFieldBuscarInterprete.setColumns(10);

		textFieldBuscarTitulo = new JTextField();
		GridBagConstraints gbc_textFieldBuscarTitulo = new GridBagConstraints();
		gbc_textFieldBuscarTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBuscarTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldBuscarTitulo.gridx = 3;
		gbc_textFieldBuscarTitulo.gridy = 0;
		panelBuscar.add(textFieldBuscarTitulo, gbc_textFieldBuscarTitulo);
		textFieldBuscarTitulo.setColumns(10);

		chckbxFavoritos = new JCheckBox("Favoritos");
		GridBagConstraints gbc_chckbxFavoritos = new GridBagConstraints();
		gbc_chckbxFavoritos.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxFavoritos.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFavoritos.gridx = 1;
		gbc_chckbxFavoritos.gridy = 1;
		panelBuscar.add(chckbxFavoritos, gbc_chckbxFavoritos);

		comboBoxEstiloMusical = new JComboBox<>();
		comboBoxEstiloMusical.setToolTipText("");
		cargarEstilosComboBox();

		GridBagConstraints gbc_comboBoxEstiloMusical = new GridBagConstraints();
		gbc_comboBoxEstiloMusical.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstiloMusical.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEstiloMusical.gridx = 3;
		gbc_comboBoxEstiloMusical.gridy = 1;
		panelBuscar.add(comboBoxEstiloMusical, gbc_comboBoxEstiloMusical);

		JButton btnBuscarCancion = new JButton("Buscar");
		btnBuscarCancion.addActionListener(e -> buscarCancion());
		GridBagConstraints gbc_btnBuscarCancion = new GridBagConstraints();
		gbc_btnBuscarCancion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscarCancion.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscarCancion.gridx = 3;
		gbc_btnBuscarCancion.gridy = 2;
		panelBuscar.add(btnBuscarCancion, gbc_btnBuscarCancion);

		JPanel panelGestion = new JPanel();
		panelCardLayout.add(panelGestion, "panelGestion");
		GridBagLayout gbl_panelGestion = new GridBagLayout();
		gbl_panelGestion.columnWidths = new int[] { 10, 0, 0, 0, 10, 0 };
		gbl_panelGestion.rowHeights = new int[] { 10, 21, 0, 0, 0 };
		gbl_panelGestion.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelGestion.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelGestion.setLayout(gbl_panelGestion);

		JLabel lblTituloPanelGestion = new JLabel("Titulo: ");
		GridBagConstraints gbc_lblTituloPanelGestion = new GridBagConstraints();
		gbc_lblTituloPanelGestion.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloPanelGestion.gridx = 1;
		gbc_lblTituloPanelGestion.gridy = 1;
		panelGestion.add(lblTituloPanelGestion, gbc_lblTituloPanelGestion);

		textFieldTituloGestion = new JTextField();
		GridBagConstraints gbc_textFieldTituloGestion = new GridBagConstraints();
		gbc_textFieldTituloGestion.gridwidth = 2;
		gbc_textFieldTituloGestion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTituloGestion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTituloGestion.gridx = 2;
		gbc_textFieldTituloGestion.gridy = 1;
		panelGestion.add(textFieldTituloGestion, gbc_textFieldTituloGestion);
		textFieldTituloGestion.setColumns(10);

		JButton btnCrearGestion = new JButton("Crear");
		btnCrearGestion.addActionListener(e -> {
			administrarPlaylist();
		});
		GridBagConstraints gbc_btnCrearGestion = new GridBagConstraints();
		gbc_btnCrearGestion.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrearGestion.gridx = 2;
		gbc_btnCrearGestion.gridy = 2;
		panelGestion.add(btnCrearGestion, gbc_btnCrearGestion);

		JButton btnEliminarTituloGestion = new JButton("Eliminar");
		btnEliminarTituloGestion.addActionListener(e -> {

			if (AppMusic.getUnicaInstancia().borrarPlayListDelUsuario(textFieldTituloGestion.getText())) {
				textFieldTituloGestion.setText("");
				cargarCancionesEnTabla(new LinkedList<Cancion>());
			}

		});
		GridBagConstraints gbc_btnEliminarTituloGestion = new GridBagConstraints();
		gbc_btnEliminarTituloGestion.anchor = GridBagConstraints.WEST;
		gbc_btnEliminarTituloGestion.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminarTituloGestion.gridx = 3;
		gbc_btnEliminarTituloGestion.gridy = 2;
		panelGestion.add(btnEliminarTituloGestion, gbc_btnEliminarTituloGestion);

		JPanel panelRecientes = new JPanel();
		panelCardLayout.add(panelRecientes, "panelRecientes");

		JPanel panelPlaylists = new JPanel();
		panelCardLayout.add(panelPlaylists, "panelPlaylists");
		panelPlaylists.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // new

		JPanel panelTablaCanciones = new JPanel();
		GridBagConstraints gbc_panelTablaCanciones = new GridBagConstraints();
		gbc_panelTablaCanciones.fill = GridBagConstraints.BOTH;
		gbc_panelTablaCanciones.gridx = 0;
		gbc_panelTablaCanciones.gridy = 2;
		panelCentro.add(panelTablaCanciones, gbc_panelTablaCanciones);
		panelTablaCanciones.setLayout(new BorderLayout(0, 0));

		JPanel panelBotonesReproducion = new JPanel();
		panelTablaCanciones.add(panelBotonesReproducion, BorderLayout.SOUTH);
		GridBagLayout gbl_panelBotonesReproducion = new GridBagLayout();
		gbl_panelBotonesReproducion.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelBotonesReproducion.rowHeights = new int[] { 23, 0 };
		gbl_panelBotonesReproducion.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelBotonesReproducion.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelBotonesReproducion.setLayout(gbl_panelBotonesReproducion);

		JButton btnAtras = new JButton("");
		btnAtras.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/anterior.png")));
		btnAtras.addActionListener(e -> {
			AppMusic.getUnicaInstancia().stopCancion();

			int filaSeleccionada = tableCanciones.getSelectedRow();
			int totalFilas = tableCanciones.getRowCount();
			int indiceCancionActual = (filaSeleccionada - 1 + totalFilas) % totalFilas;

			tableCanciones.setRowSelectionInterval(indiceCancionActual, indiceCancionActual);

			reproducirCancion();
		});

		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.insets = new Insets(0, 0, 0, 5);
		gbc_btnAtras.gridx = 1;
		gbc_btnAtras.gridy = 0;
		panelBotonesReproducion.add(btnAtras, gbc_btnAtras);

		JButton btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/stop.png")));
		btnStop.addActionListener(e -> AppMusic.getUnicaInstancia().stopCancion());

		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 0, 5);
		gbc_btnStop.gridx = 2;
		gbc_btnStop.gridy = 0;
		panelBotonesReproducion.add(btnStop, gbc_btnStop);

		JButton btnPause = new JButton("");
		btnPause.addActionListener(e -> AppMusic.getUnicaInstancia().pausarCancion());
		btnPause.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/pausa.png")));
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.insets = new Insets(0, 0, 0, 5);
		gbc_btnPause.gridx = 3;
		gbc_btnPause.gridy = 0;
		panelBotonesReproducion.add(btnPause, gbc_btnPause);

		JButton btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/play.png")));
		btnPlay.addActionListener(e -> reproducirCancion());

		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 0, 5);
		gbc_btnPlay.gridx = 4;
		gbc_btnPlay.gridy = 0;
		panelBotonesReproducion.add(btnPlay, gbc_btnPlay);

		JButton btnSiguiente = new JButton("");
		btnSiguiente.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/siguiente.png")));
		btnSiguiente.addActionListener(e -> {
			AppMusic.getUnicaInstancia().stopCancion();

			int filaSeleccionada = tableCanciones.getSelectedRow();
			int indiceCancionActual = (filaSeleccionada + 1) % tableCanciones.getRowCount();

			tableCanciones.setRowSelectionInterval(indiceCancionActual, indiceCancionActual);

			reproducirCancion();
		});

		GridBagConstraints gbc_btnSiguiente = new GridBagConstraints();
		gbc_btnSiguiente.insets = new Insets(0, 0, 0, 5);
		gbc_btnSiguiente.gridx = 5;
		gbc_btnSiguiente.gridy = 0;
		panelBotonesReproducion.add(btnSiguiente, gbc_btnSiguiente);

		btnAnadirLista = new JButton("Añadir Lista");
		btnAnadirLista.addActionListener(e -> {
			addCancionesToPlaylist();
		});

		GridBagConstraints gbc_btnAnadirLista = new GridBagConstraints();
		gbc_btnAnadirLista.insets = new Insets(0, 0, 0, 5);
		gbc_btnAnadirLista.anchor = GridBagConstraints.EAST;
		gbc_btnAnadirLista.gridx = 6;
		gbc_btnAnadirLista.gridy = 0;
		panelBotonesReproducion.add(btnAnadirLista, gbc_btnAnadirLista);

		btnEliminarLista = new JButton("Eliminar Lista");
		GridBagConstraints gbc_btnEliminarLista = new GridBagConstraints();
		gbc_btnAnadirLista.insets = new Insets(0, 0, 0, 5);
		gbc_btnAnadirLista.anchor = GridBagConstraints.EAST;
		gbc_btnAnadirLista.gridx = 6;
		gbc_btnAnadirLista.gridy = 0;
		panelBotonesReproducion.add(btnEliminarLista, gbc_btnEliminarLista);
		btnEliminarLista.setVisible(false);

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

			JOptionPane.showMessageDialog(this, "Playlist creada correctamente", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(this, "No se han seleccionado canciones", "Advertencia",
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

			int resultado = JOptionPane.showConfirmDialog(this, comboBoxPlaylists, "Seleccionar Playlist",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (resultado == JOptionPane.OK_OPTION) {
				PlayList playlistSeleccionada = (PlayList) comboBoxPlaylists.getSelectedItem();

				for (int fila : filasSeleccionadas) {
					String titulo = (String) tableCanciones.getValueAt(fila, 0);
					Cancion cancion = AppMusic.getUnicaInstancia().getCancionPorTitulo(titulo);

					if (!playlistSeleccionada.contieneCancion(cancion)) {
						AppMusic.getUnicaInstancia().addCancionToPlayList(cancion, playlistSeleccionada);
					} else {
						JOptionPane.showMessageDialog(this, "La canción ya existe en la playlist", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
					}
				}

				JOptionPane.showMessageDialog(this, "Canciones añadidas a la playlist correctamente", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "No se han seleccionado canciones", "Advertencia",
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
		JOptionPane.showMessageDialog(this, "Introduce un nombre para la playlist", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void dialogoCrearPlayList(String nombrePlaylist) {
		Object[] opciones = { "Crear", "Cancelar" };

		int opcion = JOptionPane.showOptionDialog(this, "¿Desear crear la playlist? " + nombrePlaylist,
				"Crear nueva playlist", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
				opciones[1]);

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

		TableModelCanciones model = new TableModelCanciones(data,
				new String[] { "Titulo", "Interprete", "Estilo", "" });

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

		TableModelCanciones model = new TableModelCanciones(data,
				new String[] { "Titulo", "Interprete", "Estilo", "" });

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

}