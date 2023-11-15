package umu.tds.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VentanaMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBuscarInterprete;
	private JTextField textFieldBuscarTitulo;
	private JTable tableCanciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain frame = new VentanaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMain() {
		setTitle("AppMusic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 370);
		setMinimumSize(new Dimension(800, 600));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panelCardLayout = new JPanel();

		JPanel panelBotonera = new JPanel();
		contentPane.add(panelBotonera, BorderLayout.WEST);
		GridBagLayout gbl_panelBotonera = new GridBagLayout();
		gbl_panelBotonera.columnWidths = new int[] { 0, 0 };
		gbl_panelBotonera.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelBotonera.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelBotonera.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelBotonera.setLayout(gbl_panelBotonera);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelBuscar");
			}
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
		btnGestionPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelGestion");
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
		btnRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelRecientes");
			}
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

		JButton btnMisPlaylist = new JButton("Mis Playlists");
		btnMisPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) panelCardLayout.getLayout();
				card.show(panelCardLayout, "panelPlaylists");
			}
		});
		btnMisPlaylist.setHorizontalAlignment(SwingConstants.LEFT);
		btnMisPlaylist.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/images/altavoz.png")));
		btnMisPlaylist.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_btnMisPlaylist = new GridBagConstraints();
		gbc_btnMisPlaylist.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMisPlaylist.gridx = 0;
		gbc_btnMisPlaylist.gridy = 3;
		panelBotonera.add(btnMisPlaylist, gbc_btnMisPlaylist);

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

		JLabel lblBienvenido = new JLabel("Bienvenido, ");
		panelUsuario.add(lblBienvenido);

		JButton btnPremium = new JButton("Premium");
		panelUsuario.add(btnPremium);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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
		panelBuscar.setLayout(new BorderLayout(0, 0));

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

		JCheckBox chckbxFavoritos = new JCheckBox("Favoritos");
		GridBagConstraints gbc_chckbxFavoritos = new GridBagConstraints();
		gbc_chckbxFavoritos.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxFavoritos.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFavoritos.gridx = 1;
		gbc_chckbxFavoritos.gridy = 1;
		panelBuscar.add(chckbxFavoritos, gbc_chckbxFavoritos);

		JComboBox<String> comboBoxEstiloMusical = new JComboBox<String>();
		comboBoxEstiloMusical.setToolTipText("");
		GridBagConstraints gbc_comboBoxEstiloMusical = new GridBagConstraints();
		gbc_comboBoxEstiloMusical.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstiloMusical.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEstiloMusical.gridx = 3;
		gbc_comboBoxEstiloMusical.gridy = 1;
		panelBuscar.add(comboBoxEstiloMusical, gbc_comboBoxEstiloMusical);

		JButton btnBuscarCancion = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscarCancion = new GridBagConstraints();
		gbc_btnBuscarCancion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscarCancion.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscarCancion.gridx = 3;
		gbc_btnBuscarCancion.gridy = 2;
		panelBuscar.add(btnBuscarCancion, gbc_btnBuscarCancion);

		JPanel panelGestion = new JPanel();
		panelCardLayout.add(panelGestion, "panelGestion");

		JLabel lblPanelgestion = new JLabel("PanelGestion");
		panelGestion.add(lblPanelgestion);

		JPanel panelRecientes = new JPanel();
		panelCardLayout.add(panelRecientes, "panelRecientes");

		JLabel lblPanelRecientes = new JLabel("Panel Recientes");
		panelRecientes.add(lblPanelRecientes);

		JPanel panelPlaylists = new JPanel();
		panelCardLayout.add(panelPlaylists, "panelPlaylists");

		JLabel lblMisPlayList = new JLabel("Mis PlayList");
		panelPlaylists.add(lblMisPlayList);

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

		JButton btnAtras = new JButton("<<");
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.insets = new Insets(0, 0, 0, 5);
		gbc_btnAtras.gridx = 1;
		gbc_btnAtras.gridy = 0;
		panelBotonesReproducion.add(btnAtras, gbc_btnAtras);

		JButton btnStop = new JButton("S");
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 0, 5);
		gbc_btnStop.gridx = 2;
		gbc_btnStop.gridy = 0;
		panelBotonesReproducion.add(btnStop, gbc_btnStop);

		JButton btnPause = new JButton("P");
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.insets = new Insets(0, 0, 0, 5);
		gbc_btnPause.gridx = 3;
		gbc_btnPause.gridy = 0;
		panelBotonesReproducion.add(btnPause, gbc_btnPause);

		JButton btnPlay = new JButton(">");
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 0, 5);
		gbc_btnPlay.gridx = 4;
		gbc_btnPlay.gridy = 0;
		panelBotonesReproducion.add(btnPlay, gbc_btnPlay);

		JButton btnSiguiente = new JButton(">>");
		GridBagConstraints gbc_btnSiguiente = new GridBagConstraints();
		gbc_btnSiguiente.insets = new Insets(0, 0, 0, 5);
		gbc_btnSiguiente.gridx = 5;
		gbc_btnSiguiente.gridy = 0;
		panelBotonesReproducion.add(btnSiguiente, gbc_btnSiguiente);

		JButton btnAnadirLista = new JButton("Añadir Lista");
		GridBagConstraints gbc_btnAnadirLista = new GridBagConstraints();
		gbc_btnAnadirLista.insets = new Insets(0, 0, 0, 5);
		gbc_btnAnadirLista.anchor = GridBagConstraints.EAST;
		gbc_btnAnadirLista.gridx = 6;
		gbc_btnAnadirLista.gridy = 0;
		panelBotonesReproducion.add(btnAnadirLista, gbc_btnAnadirLista);

		JPanel panelTabla = new JPanel();
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] { { "titulo 1", "Adri", "estilo 1", false }, { "titulo 2", "Anh", "estilo 2", true } },
				new String[] { "Titulo", "Interprete", "Estilo", "Seleccionar" });

		tableCanciones = new JTable(model);
		tableCanciones.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			private final JCheckBox checkBox = new JCheckBox();
			{
				checkBox.setHorizontalAlignment(JCheckBox.CENTER);
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				checkBox.setSelected(value != null && Boolean.parseBoolean(value.toString()));
				return checkBox;
			}
		});
		tableCanciones.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		JScrollPane scrollPane = new JScrollPane(tableCanciones);
		panelTablaCanciones.add(scrollPane, BorderLayout.CENTER);

	}

}