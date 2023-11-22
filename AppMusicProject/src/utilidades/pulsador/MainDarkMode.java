package utilidades.pulsador;

import javax.swing.*;

public class MainDarkMode extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainDarkMode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Dark Mode Test");

		DarkMode darkMode = new DarkMode();

		getContentPane().add(darkMode);

		setSize(150, 75);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainDarkMode darkModeTest = new MainDarkMode();
			darkModeTest.setVisible(true);
		});
	}
}
