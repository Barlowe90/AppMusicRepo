package umu.tds.vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VentanaLoginRegistro {

	private JFrame frmAppmusic;
	private JTextField textFieldUsuarioRegistro;
	private JTextField textFieldContrasena;
	private JTextField textFieldNombre;
	private JPasswordField passwordFieldLogin;
	private JTextField textFieldUsuarioLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
					VentanaLoginRegistro window = new VentanaLoginRegistro();
					window.frmAppmusic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public VentanaLoginRegistro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAppmusic = new JFrame();
		frmAppmusic.getContentPane().setBackground(new Color(0, 128, 255));
		frmAppmusic.getContentPane().setForeground(new Color(0, 0, 0));
		frmAppmusic.setTitle("AppMusic");
		frmAppmusic.setBounds(100, 100, 450, 300);
		frmAppmusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppmusic.getContentPane().setLayout(new CardLayout(0, 0));
		frmAppmusic.setLocationRelativeTo(null);

		JPanel panelLogin = new JPanel();
		panelLogin.setForeground(new Color(0, 128, 255));
		frmAppmusic.getContentPane().add(panelLogin, "panelLogin");
		panelLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelDatos = new JPanel();
		panelDatos.setForeground(new Color(0, 0, 0));
		panelLogin.add(panelDatos, BorderLayout.CENTER);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelDatos.setLayout(gbl_panelDatos);

		JLabel lblIconoPortada = new JLabel("");
		lblIconoPortada.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/musica.png")));
		GridBagConstraints gbc_lblIconoPortada = new GridBagConstraints();
		gbc_lblIconoPortada.gridwidth = 2;
		gbc_lblIconoPortada.insets = new Insets(0, 0, 5, 5);
		gbc_lblIconoPortada.gridx = 2;
		gbc_lblIconoPortada.gridy = 1;
		panelDatos.add(lblIconoPortada, gbc_lblIconoPortada);

		JLabel lblusuarioLogin = new JLabel("");
		lblusuarioLogin.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/user.png")));
		GridBagConstraints gbc_lblusuarioLogin = new GridBagConstraints();
		gbc_lblusuarioLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblusuarioLogin.anchor = GridBagConstraints.EAST;
		gbc_lblusuarioLogin.gridx = 1;
		gbc_lblusuarioLogin.gridy = 2;
		panelDatos.add(lblusuarioLogin, gbc_lblusuarioLogin);

		textFieldUsuarioLogin = new JTextField();
		GridBagConstraints gbc_textFieldUsuarioLogin = new GridBagConstraints();
		gbc_textFieldUsuarioLogin.anchor = GridBagConstraints.WEST;
		gbc_textFieldUsuarioLogin.gridwidth = 2;
		gbc_textFieldUsuarioLogin.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsuarioLogin.gridx = 2;
		gbc_textFieldUsuarioLogin.gridy = 2;
		panelDatos.add(textFieldUsuarioLogin, gbc_textFieldUsuarioLogin);
		textFieldUsuarioLogin.setColumns(25);

		JLabel lblPasswordLogin = new JLabel("");
		lblPasswordLogin.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/password.png")));
		GridBagConstraints gbc_lblPasswordLogin = new GridBagConstraints();
		gbc_lblPasswordLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordLogin.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordLogin.gridx = 1;
		gbc_lblPasswordLogin.gridy = 3;
		panelDatos.add(lblPasswordLogin, gbc_lblPasswordLogin);

		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setColumns(25);
		GridBagConstraints gbc_passwordFieldLogin = new GridBagConstraints();
		gbc_passwordFieldLogin.anchor = GridBagConstraints.WEST;
		gbc_passwordFieldLogin.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldLogin.gridwidth = 2;
		gbc_passwordFieldLogin.gridx = 2;
		gbc_passwordFieldLogin.gridy = 3;
		panelDatos.add(passwordFieldLogin, gbc_passwordFieldLogin);

		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.WEST;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 4;
		panelDatos.add(btnLogin, gbc_btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMain main = new VentanaMain();
				main.setVisible(true);
				frmAppmusic.setVisible(false);
			}
		});

		JButton btnRegistroLogin = new JButton("Registro");
		GridBagConstraints gbc_btnRegistroLogin = new GridBagConstraints();
		gbc_btnRegistroLogin.anchor = GridBagConstraints.EAST;
		gbc_btnRegistroLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistroLogin.gridx = 3;
		gbc_btnRegistroLogin.gridy = 4;
		panelDatos.add(btnRegistroLogin, gbc_btnRegistroLogin);
		btnRegistroLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelRegistro");
			}
		});

		JPanel panelRegistro = new JPanel();
		panelRegistro.setForeground(new Color(0, 0, 0));
		frmAppmusic.getContentPane().add(panelRegistro, "panelRegistro");
		GridBagLayout gbl_panelRegistro = new GridBagLayout();
		gbl_panelRegistro.columnWeights = new double[] { 1.0 };
		gbl_panelRegistro.rowWeights = new double[] { 0.0 };
		gbl_panelRegistro.rowHeights = new int[] { 0 };
		panelRegistro.setLayout(gbl_panelRegistro);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_panelFormulario = new GridBagConstraints();
		gbc_panelFormulario.gridx = 0;
		gbc_panelFormulario.gridy = 0;
		panelRegistro.add(panelFormulario, gbc_panelFormulario);

		GridBagLayout gbl_panelFormulario = new GridBagLayout();
		gbl_panelFormulario.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelFormulario.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelFormulario.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelFormulario.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panelFormulario.setLayout(gbl_panelFormulario);

		JLabel lblUsuarioRegistro = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuarioRegistro = new GridBagConstraints();
		gbc_lblUsuarioRegistro.anchor = GridBagConstraints.EAST;
		gbc_lblUsuarioRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuarioRegistro.gridx = 1;
		gbc_lblUsuarioRegistro.gridy = 1;
		panelFormulario.add(lblUsuarioRegistro, gbc_lblUsuarioRegistro);

		textFieldUsuarioRegistro = new JTextField();
		GridBagConstraints gbc_textFieldUsuarioRegistro = new GridBagConstraints();
		gbc_textFieldUsuarioRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsuarioRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsuarioRegistro.gridx = 2;
		gbc_textFieldUsuarioRegistro.gridy = 1;
		panelFormulario.add(textFieldUsuarioRegistro, gbc_textFieldUsuarioRegistro);
		textFieldUsuarioRegistro.setColumns(10);

		JLabel lblPasswordRegistro = new JLabel("Contraseña");
		GridBagConstraints gbc_lblPasswordRegistro = new GridBagConstraints();
		gbc_lblPasswordRegistro.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordRegistro.gridx = 3;
		gbc_lblPasswordRegistro.gridy = 1;
		panelFormulario.add(lblPasswordRegistro, gbc_lblPasswordRegistro);

		textFieldContrasena = new JTextField();
		GridBagConstraints gbc_textFieldContrasena = new GridBagConstraints();
		gbc_textFieldContrasena.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldContrasena.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldContrasena.gridx = 4;
		gbc_textFieldContrasena.gridy = 1;
		panelFormulario.add(textFieldContrasena, gbc_textFieldContrasena);
		textFieldContrasena.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		panelFormulario.add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 2;
		panelFormulario.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.fill = GridBagConstraints.VERTICAL;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 3;
		gbc_lblFecha.gridy = 3;
		panelFormulario.add(lblFecha, gbc_lblFecha);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 4;
		gbc_dateChooser.gridy = 3;
		panelFormulario.add(dateChooser, gbc_dateChooser);

		JPanel panelBotones = new JPanel();
		panelBotones.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.gridwidth = 4;
		gbc_panelBotones.insets = new Insets(0, 0, 0, 5);
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 1;
		gbc_panelBotones.gridy = 4;
		panelFormulario.add(panelBotones, gbc_panelBotones);

		JButton btnRegistrar = new JButton("Registrar");
		panelBotones.add(btnRegistrar);

		JButton btnIrLogin = new JButton("Ir a login");
		panelBotones.add(btnIrLogin);
		btnIrLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelLogin");
			}
		});
	}

}