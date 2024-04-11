package umu.tds.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelBuscar extends JPanel {
	private JTextField textFieldBuscarInterprete;
	private JTextField textFieldBuscarTitulo;
	private JCheckBox chckbxFavoritos;
	private JComboBox<String> comboBoxEstiloMusical;

	private static final String CHECK_FAVORITOS = "Favoritos";
	private static final String PLACEHOLDER_BUSCAR_INTERPRETE = "Interprete";
	private static final String PLACEHOLDER_BUSCAR_TITULO = "Titulo";
	private static final String ESTILO_MUSICAL = "Estilo musical";
	private static final String BUSCAR = "Buscar";

	public PanelBuscar(ActionListener buscarListener) {
		initComponents(buscarListener);
	}

	private void initComponents(ActionListener buscarListener) {
		setLayout(new GridBagLayout());

		textFieldBuscarInterprete = new JTextField();
		textFieldBuscarInterprete.setToolTipText(PLACEHOLDER_BUSCAR_INTERPRETE);
		GridBagConstraints gbc_textFieldBuscarInterprete = new GridBagConstraints();
		gbc_textFieldBuscarInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBuscarInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldBuscarInterprete.gridx = 1;
		gbc_textFieldBuscarInterprete.gridy = 0;
		add(textFieldBuscarInterprete, gbc_textFieldBuscarInterprete);
		textFieldBuscarInterprete.setColumns(10);

		textFieldBuscarTitulo = new JTextField();
		textFieldBuscarTitulo.setToolTipText(PLACEHOLDER_BUSCAR_TITULO);
		GridBagConstraints gbc_textFieldBuscarTitulo = new GridBagConstraints();
		gbc_textFieldBuscarTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBuscarTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldBuscarTitulo.gridx = 3;
		gbc_textFieldBuscarTitulo.gridy = 0;
		add(textFieldBuscarTitulo, gbc_textFieldBuscarTitulo);
		textFieldBuscarTitulo.setColumns(10);

		chckbxFavoritos = new JCheckBox(CHECK_FAVORITOS);
		GridBagConstraints gbc_chckbxFavoritos = new GridBagConstraints();
		gbc_chckbxFavoritos.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxFavoritos.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFavoritos.gridx = 1;
		gbc_chckbxFavoritos.gridy = 1;
		add(chckbxFavoritos, gbc_chckbxFavoritos);

		comboBoxEstiloMusical = new JComboBox<>();
		comboBoxEstiloMusical.setToolTipText(ESTILO_MUSICAL);
		GridBagConstraints gbc_comboBoxEstiloMusical = new GridBagConstraints();
		gbc_comboBoxEstiloMusical.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstiloMusical.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEstiloMusical.gridx = 3;
		gbc_comboBoxEstiloMusical.gridy = 1;
		add(comboBoxEstiloMusical, gbc_comboBoxEstiloMusical);

		JButton btnBuscarCancion = new JButton(BUSCAR);
		btnBuscarCancion.addActionListener(buscarListener);
		GridBagConstraints gbc_btnBuscarCancion = new GridBagConstraints();
		gbc_btnBuscarCancion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscarCancion.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscarCancion.gridx = 3;
		gbc_btnBuscarCancion.gridy = 2;
		add(btnBuscarCancion, gbc_btnBuscarCancion);
	}

	public JTextField getTextFieldBuscarInterprete() {
		return textFieldBuscarInterprete;
	}

	public JTextField getTextFieldBuscarTitulo() {
		return textFieldBuscarTitulo;
	}

	public JCheckBox getChckbxFavoritos() {
		return chckbxFavoritos;
	}

	public JComboBox<String> getComboBoxEstiloMusical() {
		return comboBoxEstiloMusical;
	}

}
