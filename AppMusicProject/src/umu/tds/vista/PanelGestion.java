package umu.tds.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelGestion extends JPanel {
	private JTextField textFieldTituloGestion;
	private static final String TEXTO_BOTON_CREAR = "Crear";
	private static final String TEXTO_BOTON_ELIMINAR = "Eliminar";
	private static final String TITULO = "Nombre PlayList: ";

	public PanelGestion(ActionListener crearListener, ActionListener eliminarListener) {
		initComponents(crearListener, eliminarListener);
	}

	private void initComponents(ActionListener crearListener, ActionListener eliminarListener) {
		setLayout(new GridBagLayout());

		JLabel lblTituloPanelGestion = new JLabel(TITULO);
		GridBagConstraints gbc_lblTituloPanelGestion = new GridBagConstraints();
		gbc_lblTituloPanelGestion.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloPanelGestion.gridx = 1;
		gbc_lblTituloPanelGestion.gridy = 1;
		add(lblTituloPanelGestion, gbc_lblTituloPanelGestion);

		textFieldTituloGestion = new JTextField();
		GridBagConstraints gbc_textFieldTituloGestion = new GridBagConstraints();
		gbc_textFieldTituloGestion.gridwidth = 2;
		gbc_textFieldTituloGestion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTituloGestion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTituloGestion.gridx = 2;
		gbc_textFieldTituloGestion.gridy = 1;
		add(textFieldTituloGestion, gbc_textFieldTituloGestion);
		textFieldTituloGestion.setColumns(10);

		JButton btnCrearGestion = new JButton(TEXTO_BOTON_CREAR);
		btnCrearGestion.addActionListener(crearListener);
		GridBagConstraints gbc_btnCrearGestion = new GridBagConstraints();
		gbc_btnCrearGestion.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrearGestion.gridx = 2;
		gbc_btnCrearGestion.gridy = 2;
		add(btnCrearGestion, gbc_btnCrearGestion);

		JButton btnEliminarTituloGestion = new JButton(TEXTO_BOTON_ELIMINAR);
		btnEliminarTituloGestion.addActionListener(eliminarListener);
		GridBagConstraints gbc_btnEliminarTituloGestion = new GridBagConstraints();
		gbc_btnEliminarTituloGestion.anchor = GridBagConstraints.WEST;
		gbc_btnEliminarTituloGestion.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminarTituloGestion.gridx = 3;
		gbc_btnEliminarTituloGestion.gridy = 2;
		add(btnEliminarTituloGestion, gbc_btnEliminarTituloGestion);
	}

	public String getTituloPlaylist() {
		return textFieldTituloGestion.getText();
	}

	public void limpiarTitulo() {
		textFieldTituloGestion.setText("");
	}

	public JTextField getTextFieldTituloGestion() {
		return textFieldTituloGestion;
	}

}
