package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import umu.tds.vista.VentanaLoginRegistro;
import umu.tds.vista.VentanaMain;

public class Lanzador {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					VentanaLoginRegistro ventana = new VentanaLoginRegistro();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
