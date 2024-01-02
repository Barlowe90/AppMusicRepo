package umu.tds.vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.toedter.calendar.JDateChooser;

import umu.tds.controlador.AppMusic;

import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

public class VentanaLoginRegistro {

	private JFrame frmAppmusic;
	private JTextField textFieldUsuarioRegistro;
	private JTextField textFieldEmail;
	private JPasswordField passwordFieldLogin;
	private JTextField textFieldUsuarioLogin;
	private JPasswordField passwordFieldRegistro;

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
		frmAppmusic.setBounds(100, 100, 450, 430);
		frmAppmusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppmusic.getContentPane().setLayout(new CardLayout(0, 0));
		frmAppmusic.setLocationRelativeTo(null);
		frmAppmusic.setMinimumSize(new Dimension(400, 350));
		frmAppmusic.setResizable(false);

		JPanel panelLogin = new JPanel();
		panelLogin.setForeground(new Color(0, 128, 255));
		frmAppmusic.getContentPane().add(panelLogin, "panelLogin");
		panelLogin.setLayout(new BorderLayout(0, 0));

		JPanel panelDatos = new JPanel();
		panelDatos.setForeground(new Color(0, 0, 0));
		panelLogin.add(panelDatos, BorderLayout.CENTER);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] { 0, 25, 0, 0, 0, 0, 0 };
		gbl_panelDatos.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatos.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatos.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelDatos.setLayout(gbl_panelDatos);

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
		gbc_textFieldUsuarioLogin.gridwidth = 3;
		gbc_textFieldUsuarioLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsuarioLogin.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsuarioLogin.gridx = 2;
		gbc_textFieldUsuarioLogin.gridy = 2;
		panelDatos.add(textFieldUsuarioLogin, gbc_textFieldUsuarioLogin);

		JLabel lblPasswordLogin = new JLabel("");
		lblPasswordLogin.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/password.png")));
		GridBagConstraints gbc_lblPasswordLogin = new GridBagConstraints();
		gbc_lblPasswordLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordLogin.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordLogin.gridx = 1;
		gbc_lblPasswordLogin.gridy = 3;
		panelDatos.add(lblPasswordLogin, gbc_lblPasswordLogin);

		passwordFieldLogin = new JPasswordField();
		GridBagConstraints gbc_passwordFieldLogin = new GridBagConstraints();
		gbc_passwordFieldLogin.gridwidth = 3;
		gbc_passwordFieldLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldLogin.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldLogin.gridx = 2;
		gbc_passwordFieldLogin.gridy = 3;
		panelDatos.add(passwordFieldLogin, gbc_passwordFieldLogin);

		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 4;
		panelDatos.add(btnLogin, gbc_btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMain main = new VentanaMain();
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				frmAppmusic.setVisible(false);
			}
		});

		JButton btnRegistroLogin = new JButton("Registro");
		GridBagConstraints gbc_btnRegistroLogin = new GridBagConstraints();
		gbc_btnRegistroLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistroLogin.gridx = 4;
		gbc_btnRegistroLogin.gridy = 4;
		panelDatos.add(btnRegistroLogin, gbc_btnRegistroLogin);
		btnRegistroLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelRegistro");
			}
		});

		JLabel lblOrLogin = new JLabel("- OR - ");
		GridBagConstraints gbc_lblOrLogin = new GridBagConstraints();
		gbc_lblOrLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrLogin.gridx = 3;
		gbc_lblOrLogin.gridy = 5;
		panelDatos.add(lblOrLogin, gbc_lblOrLogin);

		JLabel lblSingInWith = new JLabel("Sign in with");
		GridBagConstraints gbc_lblSingInWith = new GridBagConstraints();
		gbc_lblSingInWith.insets = new Insets(0, 0, 5, 5);
		gbc_lblSingInWith.gridx = 3;
		gbc_lblSingInWith.gridy = 6;
		panelDatos.add(lblSingInWith, gbc_lblSingInWith);

		JButton btnGithubLogin = new JButton("GitHub");
		GridBagConstraints gbc_btnGithubLogin = new GridBagConstraints();
		gbc_btnGithubLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnGithubLogin.gridx = 3;
		gbc_btnGithubLogin.gridy = 7;
		panelDatos.add(btnGithubLogin, gbc_btnGithubLogin);

		JPanel panelImagen = new JPanel();
		panelLogin.add(panelImagen, BorderLayout.NORTH);

		JLabel lblImagenPortada = new JLabel("");
		lblImagenPortada.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/musica.png")));
		lblImagenPortada.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		panelImagen.add(lblImagenPortada);

		JPanel panelRegistro = new JPanel();
		panelRegistro.setForeground(new Color(0, 0, 0));
		frmAppmusic.getContentPane().add(panelRegistro, "panelRegistro");
		panelRegistro.setLayout(new BorderLayout(0, 0));

		JPanel panelFormulario = new JPanel();
		panelFormulario.setForeground(new Color(0, 0, 0));
		panelRegistro.add(panelFormulario);

		GridBagLayout gbl_panelFormulario = new GridBagLayout();
		gbl_panelFormulario.columnWidths = new int[] { 0, 50, 0, 50, 0, 0, 0 };
		gbl_panelFormulario.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelFormulario.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelFormulario.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };

		panelFormulario.setLayout(gbl_panelFormulario);

		JLabel lblUsuarioRegistro = new JLabel("");
		lblUsuarioRegistro.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/user.png")));
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

		JLabel lblPasswordRegistro = new JLabel("");
		lblPasswordRegistro
				.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/password.png")));
		GridBagConstraints gbc_lblPasswordRegistro = new GridBagConstraints();
		gbc_lblPasswordRegistro.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordRegistro.gridx = 3;
		gbc_lblPasswordRegistro.gridy = 1;
		panelFormulario.add(lblPasswordRegistro, gbc_lblPasswordRegistro);

		passwordFieldRegistro = new JPasswordField();
		passwordFieldRegistro.setColumns(10);
		GridBagConstraints gbc_passwordFieldRegistro = new GridBagConstraints();
		gbc_passwordFieldRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldRegistro.gridx = 4;
		gbc_passwordFieldRegistro.gridy = 1;
		panelFormulario.add(passwordFieldRegistro, gbc_passwordFieldRegistro);

		JLabel lblEmail = new JLabel("");
		lblEmail.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/email.png")));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 2;
		panelFormulario.add(lblEmail, gbc_lblEmail);

		textFieldEmail = new JTextField();
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 3;
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 2;
		gbc_textFieldEmail.gridy = 2;
		panelFormulario.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblFecha = new JLabel("");
		lblFecha.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/calendario.png")));
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
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
		gbc_panelBotones.insets = new Insets(0, 0, 5, 5);
		gbc_panelBotones.gridwidth = 4;
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 1;
		gbc_panelBotones.gridy = 4;
		panelFormulario.add(panelBotones, gbc_panelBotones);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppMusic.getUnicaInstancia().registrarUsuario(textFieldUsuarioRegistro.getText(),
						new String(passwordFieldRegistro.getPassword()), textFieldEmail.getText(),
						dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
		});

		panelBotones.add(btnRegistrar);

		JButton btnIrLogin = new JButton("Ir a login");
		panelBotones.add(btnIrLogin);

		JPanel panelImagenRegistro = new JPanel();
		panelRegistro.add(panelImagenRegistro, BorderLayout.NORTH);

		JLabel lblimagenRegistro = new JLabel("");
		lblimagenRegistro.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource("/umu/tds/images/musica.png")));
		panelImagenRegistro.add(lblimagenRegistro);
		lblimagenRegistro.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		btnIrLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
				card.show(frmAppmusic.getContentPane(), "panelLogin");
			}
		});
	}

}