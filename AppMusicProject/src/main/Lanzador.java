package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import umu.tds.vista.VentanaMain;

public class Lanzador {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					VentanaMain ventana = new VentanaMain();	
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
