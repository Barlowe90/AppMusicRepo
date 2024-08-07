package dam.vista;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import dam.controlador.AppMusic;
import dam.exceptions.EmailNoValidoException;
import dam.exceptions.UsuarioDuplicadoException;
import dam.github.LoginGitHub;

import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

public class VentanaLoginRegistro {

	JFrame frmAppmusic;
	private JTextField textFieldUsuarioRegistro, textFieldEmail, textFieldUsuarioLogin;
	private JPasswordField passwordFieldLogin, passwordFieldRegistro;
	private JDateChooser dateChooser;

	private static final String LABEL_USER_LOGIN = "";
	private static final String LABEL_PW_LOGIN = "";
	private static final String TEXTO_BOTON_LOGIN = "Login";
	private static final String TEXTO_BOTON_REGISTRO_LOGIN = "Registro";
	private static final String LABEL_OR_REGISTRO = "- O - ";
	private static final String LABEL_SIGN_IN_REGISTRO = "Accede con";
	private static final String TEXTO_BOTON_GITHUB_LOGIN = "GitHub";
	private static final String TEXTO_BOTON_REGISTRAR = "Registrar";
	private static final String TEXTO_BOTON_IR_LOGIN = "Ir a login";
	private static final String FORMATO_FECHA = "dd/MM/yyyy";
	private static final String MENSAJE_ERROR = "¡Ops! Algo sucedio, comprueba todos tus datos y vuelve a intentarlo";
	private static final String TITULO_ERROR = "Error";
	private static final String MENSAJE_USER_DUPLICADO = "¡Ops! Lo sentimos, ese usuario ya está cogido. Por favor, intenta con otro diferente";
	private static final String MENSAJE_EMAIL_NO_VALIDO = "¡Ops! Lo sentimos, el formato del email no es correcto. Por favor, intenta con otro diferente";
	private static final String TITULO_USER_DUPLICADO = "Usuario duplicado";
	private static final String TITULO_EMAIL_NO_VALIDO = "Formato email no válido";
	private static final String MENSAJE_NUEVO_USUARIO = "Gracias por registrarte. �Ya puedes disfrutar de mas de 1 000 000 de canciones!";
	private static final String TITULO_NUEVO_USUARIO = "Exito";
	private static final String RUTA_IMAGEN_USUARIO = "/utilidades/imagenes/Usuario.png";
	private static final String RUTA_IMAGEN_PASSWORD = "/utilidades/imagenes/password.png";
	private static final String RUTA_IMAGEN_EMAIL = "/utilidades/imagenes/email.png";
	private static final String RUTA_IMAGEN_MUSICA = "/utilidades/imagenes/musica.png";
	private static final String RUTA_IMAGEN_CALENDARIO = "/utilidades/imagenes/calendario.png";

	public void mostrarVentana() {
		frmAppmusic.setVisible(true);
	}

	public VentanaLoginRegistro() {
		inicializarVentana();
		initialize();
		inicializarPanelRegistro();
	}

	private void inicializarVentana() {
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
		ImageIcon icono = new ImageIcon(getClass().getResource(RUTA_IMAGEN_MUSICA));
		frmAppmusic.setIconImage(icono.getImage());
	}

	private void initialize() {
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

		JLabel lblusuarioLogin = new JLabel(LABEL_USER_LOGIN);
		lblusuarioLogin.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource(RUTA_IMAGEN_USUARIO)));
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

		JLabel lblPasswordLogin = new JLabel(LABEL_PW_LOGIN);
		lblPasswordLogin.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource(RUTA_IMAGEN_PASSWORD)));
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

		JButton btnLogin = new JButton(TEXTO_BOTON_LOGIN);
		btnLogin.addActionListener(e -> {
			boolean ok = AppMusic.getUnicaInstancia().loginUsuario(textFieldUsuarioLogin.getText(),
					new String(passwordFieldLogin.getPassword()));

			if (ok) {
				VentanaMain main = new VentanaMain();
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				frmAppmusic.dispose();
			} else {
				mensajeError();
			}
		});

		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 4;
		panelDatos.add(btnLogin, gbc_btnLogin);

		JButton btnRegistroLogin = new JButton(TEXTO_BOTON_REGISTRO_LOGIN);
		GridBagConstraints gbc_btnRegistroLogin = new GridBagConstraints();
		gbc_btnRegistroLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistroLogin.gridx = 4;
		gbc_btnRegistroLogin.gridy = 4;
		panelDatos.add(btnRegistroLogin, gbc_btnRegistroLogin);
		btnRegistroLogin.addActionListener(e -> {
			CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
			card.show(frmAppmusic.getContentPane(), "panelRegistro");
		});

		JLabel lblOrLogin = new JLabel(LABEL_OR_REGISTRO);
		GridBagConstraints gbc_lblOrLogin = new GridBagConstraints();
		gbc_lblOrLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrLogin.gridx = 3;
		gbc_lblOrLogin.gridy = 5;
		panelDatos.add(lblOrLogin, gbc_lblOrLogin);

		JLabel lblSingInWith = new JLabel(LABEL_SIGN_IN_REGISTRO);
		GridBagConstraints gbc_lblSingInWith = new GridBagConstraints();
		gbc_lblSingInWith.insets = new Insets(0, 0, 5, 5);
		gbc_lblSingInWith.gridx = 3;
		gbc_lblSingInWith.gridy = 6;
		panelDatos.add(lblSingInWith, gbc_lblSingInWith);

		// Login con usuario de GitHub
		JButton btnGithubLogin = new JButton(TEXTO_BOTON_GITHUB_LOGIN);
		btnGithubLogin.addActionListener(e -> {
			JFileChooser selectorFichero = AppMusic.getUnicaInstancia().obtenerFicheroToken();
			realizarLoginGithub(selectorFichero);
		});

		GridBagConstraints gbc_btnGithubLogin = new GridBagConstraints();
		gbc_btnGithubLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnGithubLogin.gridx = 3;
		gbc_btnGithubLogin.gridy = 7;
		panelDatos.add(btnGithubLogin, gbc_btnGithubLogin);

		JPanel panelImagen = new JPanel();
		panelLogin.add(panelImagen, BorderLayout.NORTH);

		JLabel lblImagenPortada = new JLabel("");
		lblImagenPortada.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource(RUTA_IMAGEN_MUSICA)));
		lblImagenPortada.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		panelImagen.add(lblImagenPortada);

	}

	private void inicializarPanelRegistro() {
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
		lblUsuarioRegistro.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource(RUTA_IMAGEN_USUARIO)));
		GridBagConstraints gbc_lblUsuarioRegistro = new GridBagConstraints();
		gbc_lblUsuarioRegistro.anchor = GridBagConstraints.EAST;
		gbc_lblUsuarioRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuarioRegistro.gridx = 1;
		gbc_lblUsuarioRegistro.gridy = 1;
		panelFormulario.add(lblUsuarioRegistro, gbc_lblUsuarioRegistro);

		JLabel lblPasswordRegistro = new JLabel("");
		lblPasswordRegistro.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource(RUTA_IMAGEN_PASSWORD)));
		GridBagConstraints gbc_lblPasswordRegistro = new GridBagConstraints();
		gbc_lblPasswordRegistro.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordRegistro.gridx = 3;
		gbc_lblPasswordRegistro.gridy = 1;
		panelFormulario.add(lblPasswordRegistro, gbc_lblPasswordRegistro);

		JLabel lblEmail = new JLabel("");
		lblEmail.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource(RUTA_IMAGEN_EMAIL)));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 2;
		panelFormulario.add(lblEmail, gbc_lblEmail);

		textFieldUsuarioRegistro = new JTextField();
		GridBagConstraints gbc_textFieldUsuarioRegistro = new GridBagConstraints();
		gbc_textFieldUsuarioRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsuarioRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsuarioRegistro.gridx = 2;
		gbc_textFieldUsuarioRegistro.gridy = 1;
		panelFormulario.add(textFieldUsuarioRegistro, gbc_textFieldUsuarioRegistro);
		textFieldUsuarioRegistro.setColumns(10);

		passwordFieldRegistro = new JPasswordField();
		passwordFieldRegistro.setColumns(10);
		GridBagConstraints gbc_passwordFieldRegistro = new GridBagConstraints();
		gbc_passwordFieldRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldRegistro.gridx = 4;
		gbc_passwordFieldRegistro.gridy = 1;
		panelFormulario.add(passwordFieldRegistro, gbc_passwordFieldRegistro);

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
		lblFecha.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource(RUTA_IMAGEN_CALENDARIO)));
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.fill = GridBagConstraints.VERTICAL;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 3;
		gbc_lblFecha.gridy = 3;
		panelFormulario.add(lblFecha, gbc_lblFecha);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString(FORMATO_FECHA);
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

		JButton btnRegistrar = new JButton(TEXTO_BOTON_REGISTRAR);
		registrar(btnRegistrar);

		panelBotones.add(btnRegistrar);

		JButton btnIrLogin = new JButton(TEXTO_BOTON_IR_LOGIN);
		panelBotones.add(btnIrLogin);

		JPanel panelImagenRegistro = new JPanel();
		panelRegistro.add(panelImagenRegistro, BorderLayout.NORTH);

		JLabel lblimagenRegistro = new JLabel("");
		lblimagenRegistro.setIcon(new ImageIcon(VentanaLoginRegistro.class.getResource(RUTA_IMAGEN_MUSICA)));
		panelImagenRegistro.add(lblimagenRegistro);
		lblimagenRegistro.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		btnIrLogin.addActionListener(e -> irPanelLogin());
	}

	private void registrar(JButton btnRegistrar) {
		btnRegistrar.addActionListener(ev -> {
			try {
				AppMusic.getUnicaInstancia().registrarUsuario(textFieldUsuarioRegistro.getText(),
						new String(passwordFieldRegistro.getPassword()), textFieldEmail.getText(),
						dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

				vaciarCampos();
				mensajeRegistroExito();
				irPanelLogin();
			} catch (UsuarioDuplicadoException e) {
				mensajeErrorUserDuplicado();
			} catch (EmailNoValidoException e) {
				mensajeErrorEmailNoValido();
			} catch (Exception e) {
				mensajeError();
			}
		});
	}

	public void vaciarCampos() {
		textFieldUsuarioRegistro.setText("");
		passwordFieldRegistro.setText("");
		textFieldEmail.setText("");
		dateChooser.setDate(null);
	}

	public void vaciarCamposGitHub() {
		textFieldUsuarioRegistro.setText("");
	}

	public void mensajeError() {
		JOptionPane.showMessageDialog(frmAppmusic, MENSAJE_ERROR, TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
	}

	public void mensajeErrorUserDuplicado() {
		JOptionPane.showMessageDialog(frmAppmusic, MENSAJE_USER_DUPLICADO, TITULO_USER_DUPLICADO,
				JOptionPane.ERROR_MESSAGE);
	}

	public void mensajeErrorEmailNoValido() {
		JOptionPane.showMessageDialog(frmAppmusic, MENSAJE_EMAIL_NO_VALIDO, TITULO_EMAIL_NO_VALIDO,
				JOptionPane.ERROR_MESSAGE);
	}

	public void mensajeRegistroExito() {
		JOptionPane.showMessageDialog(frmAppmusic, MENSAJE_NUEVO_USUARIO, TITULO_NUEVO_USUARIO,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void irPanelLogin() {
		CardLayout card = (CardLayout) frmAppmusic.getContentPane().getLayout();
		card.show(frmAppmusic.getContentPane(), "panelLogin");
	}

	public void realizarLoginGithub(JFileChooser selectorFichero) {
		int resultado = selectorFichero.showOpenDialog(frmAppmusic);

		if (resultado == JFileChooser.APPROVE_OPTION) {
			File seleccionado = selectorFichero.getSelectedFile();
			String token = LoginGitHub.INSTANCE.verificar(textFieldUsuarioLogin.getText(),
					seleccionado.getAbsolutePath());
			if (token != null) {
				JOptionPane.showMessageDialog(frmAppmusic, "Login correcto", "Login", JOptionPane.INFORMATION_MESSAGE);
				// Si el usuario ya esta registrado, hacemos un login con su nombre y token
				if (AppMusic.getUnicaInstancia().isUsuarioRegistrado(textFieldUsuarioLogin.getText())) {
					boolean ok = AppMusic.getUnicaInstancia().loginUsuario(textFieldUsuarioLogin.getText(), token);
					if (ok) {
						VentanaMain main = new VentanaMain();
						main.setLocationRelativeTo(null);
						main.setVisible(true);
						frmAppmusic.dispose();
					} else {
						mensajeError();
					}
				}
				// En caso de no estar registrado, lo registramos con valores por defecto y la
				// contrase�a sera su token
				else {
					try {
						String correo = textFieldUsuarioLogin.getText() + "@appmusic.com";
						AppMusic.getUnicaInstancia().registrarUsuario(textFieldUsuarioLogin.getText(), token, correo,
								LocalDate.now());
						vaciarCamposGitHub();
						mensajeRegistroExito();

					} catch (Exception e) {
						mensajeError();
					}
					boolean ok = AppMusic.getUnicaInstancia().loginUsuario(textFieldUsuarioLogin.getText(), token);
					if (ok) {
						VentanaMain main = new VentanaMain();
						main.setLocationRelativeTo(null);
						main.setVisible(true);
						frmAppmusic.dispose();
					} else {
						mensajeError();
					}
				}
			} else {
				JOptionPane.showMessageDialog(frmAppmusic, "Login Fallido", "Login", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}