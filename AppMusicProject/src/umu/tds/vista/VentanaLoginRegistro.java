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
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class VentanaLoginRegistro {

	private JFrame frmAppmusic;
	private JTextField textFieldUsuario;
	private JTextField textFieldContrasena;
	private JTextField textFieldNombre;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
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
		frmAppmusic.setTitle("AppMusic");
		frmAppmusic.setBounds(100, 100, 450, 300);
		frmAppmusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppmusic.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panelLogin = new JPanel();
		frmAppmusic.getContentPane().add(panelLogin, "panelLogin");
		panelLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelDatos = new JPanel();
		panelLogin.add(panelDatos, BorderLayout.CENTER);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelDatos.setLayout(gbl_panelDatos);
		
		JLabel lblNewLabel = new JLabel("AppMusic");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 2;
		panelDatos.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblUsuarioLogin = new JLabel("");
		lblUsuarioLogin.setIcon(null);
		GridBagConstraints gbc_lblUsuarioLogin = new GridBagConstraints();
		gbc_lblUsuarioLogin.anchor = GridBagConstraints.EAST;
		gbc_lblUsuarioLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuarioLogin.gridx = 3;
		gbc_lblUsuarioLogin.gridy = 4;
		panelDatos.add(lblUsuarioLogin, gbc_lblUsuarioLogin);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 4;
		panelDatos.add(textField, gbc_textField);
		textField.setColumns(25);

		JLabel lblPassword = new JLabel("");
		lblPassword
				.setIcon(null);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 3;
		gbc_lblPassword.gridy = 5;
		panelDatos.add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.setColumns(25);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.gridx = 4;
		gbc_passwordField.gridy = 5;
		panelDatos.add(passwordField, gbc_passwordField);

		JButton btnLogin = new JButton("Login");
//		btnLogin.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/user.png")));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.WEST;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 4;
		gbc_btnLogin.gridy = 6;
		panelDatos.add(btnLogin, gbc_btnLogin);

		JButton btnRegister = new JButton("Registro");
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.anchor = GridBagConstraints.EAST;
		gbc_btnRegister.gridx = 5;
		gbc_btnRegister.gridy = 6;
		panelDatos.add(btnRegister, gbc_btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelRegistro");
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				VentanaMain main = new VentanaMain();
//				main.setVisible(true);
//				frmAppmusic.setVisible(false);
			}
		});

		JPanel panelRegistro = new JPanel();
		frmAppmusic.getContentPane().add(panelRegistro, "panelRegistro");
		GridBagLayout gbl_panelRegistro = new GridBagLayout();
		gbl_panelRegistro.columnWeights = new double[] { 1.0 };
		gbl_panelRegistro.rowWeights = new double[] { 0.0 };
		gbl_panelRegistro.rowHeights = new int[] { 0 };
		panelRegistro.setLayout(gbl_panelRegistro);

		JPanel panelFormulario = new JPanel();
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

		JLabel lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		panelFormulario.add(lblUsuario, gbc_lblUsuario);

		textFieldUsuario = new JTextField();
		GridBagConstraints gbc_textFieldUsuario = new GridBagConstraints();
		gbc_textFieldUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsuario.gridx = 2;
		gbc_textFieldUsuario.gridy = 1;
		panelFormulario.add(textFieldUsuario, gbc_textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JLabel lblContrasena = new JLabel("Contraseña");
		GridBagConstraints gbc_lblContrasena = new GridBagConstraints();
		gbc_lblContrasena.anchor = GridBagConstraints.EAST;
		gbc_lblContrasena.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasena.gridx = 3;
		gbc_lblContrasena.gridy = 1;
		panelFormulario.add(lblContrasena, gbc_lblContrasena);

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
	}

}