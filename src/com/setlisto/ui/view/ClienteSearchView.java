package com.setlisto.ui.view;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.setlisto.criteria.ClienteCriteria;
import com.setlisto.ui.controller.ClienteSearchController;
import com.toedter.calendar.JDateChooser;

public class ClienteSearchView extends AbstractView {
	
	private static ClienteSearchView instance;

	private static final long serialVersionUID = 1L;
	private JTable resultadosTable;
	private JTextField emailTF;
	private JTextField telefonoTF;
	private JTextField nombreTF;
	private JTextField apellidoTF;
	private JButton limpiarCamposButton;
	private JButton buscarButton;
	private JDateChooser registroDesdeDC;
	private JDateChooser registroHastaDC;
	private JCheckBox verificadoCheckBox;
	private JCheckBox activoCheckBox;

	/**
	 * Create the panel.
	 */
	public ClienteSearchView() {
		initialize();
		setName("Buscar Clientes");
	}
	
	public static ClienteSearchView getInstance() {
		if (instance == null) {
			instance = new ClienteSearchView();
		}
		return instance;
	}

	public void initialize () {
		setLayout(new BorderLayout(0, 0));

		JPanel busquedaPanel = new JPanel();
		add(busquedaPanel, BorderLayout.NORTH);
		GridBagLayout gbl_busquedaPanel = new GridBagLayout();
		gbl_busquedaPanel.columnWidths = new int[]{0, 40, 0, 54, 0, 56, 0, 54, 0, 102, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_busquedaPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_busquedaPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_busquedaPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		busquedaPanel.setLayout(gbl_busquedaPanel);

		JLabel emailLabel = new JLabel("Email");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 1;
		gbc_emailLabel.gridy = 0;
		busquedaPanel.add(emailLabel, gbc_emailLabel);

		JLabel telefonoLabel = new JLabel("Teléfono");
		GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();
		gbc_telefonoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoLabel.gridx = 3;
		gbc_telefonoLabel.gridy = 0;
		busquedaPanel.add(telefonoLabel, gbc_telefonoLabel);

		JLabel nombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 5;
		gbc_nombreLabel.gridy = 0;
		busquedaPanel.add(nombreLabel, gbc_nombreLabel);

		JLabel apellidoLabel = new JLabel("Apellido");
		GridBagConstraints gbc_apellidoLabel = new GridBagConstraints();
		gbc_apellidoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_apellidoLabel.gridx = 7;
		gbc_apellidoLabel.gridy = 0;
		busquedaPanel.add(apellidoLabel, gbc_apellidoLabel);

		JLabel fechaRegistroDesdeLabel = new JLabel("Fecha Registro Desde");
		GridBagConstraints gbc_fechaRegistroDesdeLabel = new GridBagConstraints();
		gbc_fechaRegistroDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaRegistroDesdeLabel.gridx = 9;
		gbc_fechaRegistroDesdeLabel.gridy = 0;
		busquedaPanel.add(fechaRegistroDesdeLabel, gbc_fechaRegistroDesdeLabel);
		
		JLabel fechRegistroHastaLabel = new JLabel("Fecha Registro Hasta");
		GridBagConstraints gbc_fechRegistroHastaLabel = new GridBagConstraints();
		gbc_fechRegistroHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechRegistroHastaLabel.gridx = 11;
		gbc_fechRegistroHastaLabel.gridy = 0;
		busquedaPanel.add(fechRegistroHastaLabel, gbc_fechRegistroHastaLabel);

		activoCheckBox = new JCheckBox("Activo");
		GridBagConstraints gbc_activoCheckBox = new GridBagConstraints();
		gbc_activoCheckBox.anchor = GridBagConstraints.WEST;
		gbc_activoCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_activoCheckBox.gridx = 13;
		gbc_activoCheckBox.gridy = 0;
		busquedaPanel.add(activoCheckBox, gbc_activoCheckBox);

		emailTF = new JTextField();
		GridBagConstraints gbc_emailTF = new GridBagConstraints();
		gbc_emailTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTF.insets = new Insets(0, 0, 5, 5);
		gbc_emailTF.gridx = 1;
		gbc_emailTF.gridy = 1;
		busquedaPanel.add(emailTF, gbc_emailTF);
		emailTF.setColumns(10);

		telefonoTF = new JTextField();
		GridBagConstraints gbc_telefonoTF = new GridBagConstraints();
		gbc_telefonoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonoTF.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoTF.gridx = 3;
		gbc_telefonoTF.gridy = 1;
		busquedaPanel.add(telefonoTF, gbc_telefonoTF);
		telefonoTF.setColumns(10);

		nombreTF = new JTextField();
		GridBagConstraints gbc_nombreTF = new GridBagConstraints();
		gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTF.gridx = 5;
		gbc_nombreTF.gridy = 1;
		busquedaPanel.add(nombreTF, gbc_nombreTF);
		nombreTF.setColumns(10);

		apellidoTF = new JTextField();
		GridBagConstraints gbc_apellidoTF = new GridBagConstraints();
		gbc_apellidoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellidoTF.insets = new Insets(0, 0, 5, 5);
		gbc_apellidoTF.gridx = 7;
		gbc_apellidoTF.gridy = 1;
		busquedaPanel.add(apellidoTF, gbc_apellidoTF);
		apellidoTF.setColumns(10);

		registroDesdeDC = new JDateChooser();
		GridBagConstraints gbc_registroDesdeDC = new GridBagConstraints();
		gbc_registroDesdeDC.insets = new Insets(0, 0, 5, 5);
		gbc_registroDesdeDC.fill = GridBagConstraints.BOTH;
		gbc_registroDesdeDC.gridx = 9;
		gbc_registroDesdeDC.gridy = 1;
		busquedaPanel.add(registroDesdeDC, gbc_registroDesdeDC);
		
		registroHastaDC = new JDateChooser();
		GridBagConstraints gbc_registroHastaDC = new GridBagConstraints();
		gbc_registroHastaDC.insets = new Insets(0, 0, 5, 5);
		gbc_registroHastaDC.fill = GridBagConstraints.BOTH;
		gbc_registroHastaDC.gridx = 11;
		gbc_registroHastaDC.gridy = 1;
		busquedaPanel.add(registroHastaDC, gbc_registroHastaDC);

		verificadoCheckBox = new JCheckBox("Verificado");
		GridBagConstraints gbc_verificadoCheckBox = new GridBagConstraints();
		gbc_verificadoCheckBox.anchor = GridBagConstraints.WEST;
		gbc_verificadoCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_verificadoCheckBox.gridx = 13;
		gbc_verificadoCheckBox.gridy = 1;
		busquedaPanel.add(verificadoCheckBox, gbc_verificadoCheckBox);

		limpiarCamposButton = new JButton("Limpiar Campos");
		GridBagConstraints gbc_limpiarCamposButton = new GridBagConstraints();
		gbc_limpiarCamposButton.anchor = GridBagConstraints.EAST;
		gbc_limpiarCamposButton.insets = new Insets(0, 0, 0, 5);
		gbc_limpiarCamposButton.gridx = 15;
		gbc_limpiarCamposButton.gridy = 2;
		busquedaPanel.add(limpiarCamposButton, gbc_limpiarCamposButton);

		buscarButton = new JButton("Buscar");
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.anchor = GridBagConstraints.WEST;
		gbc_buscarButton.insets = new Insets(0, 0, 0, 5);
		gbc_buscarButton.gridx = 16;
		gbc_buscarButton.gridy = 2;
		busquedaPanel.add(buscarButton, gbc_buscarButton);

		resultadosTable = new JTable();
		add(resultadosTable, BorderLayout.CENTER);
	}
	
	private void postInitialize () {
		// controladores 
		ClienteSearchController clienteSearchController = new ClienteSearchController(this);
		buscarButton.setAction(clienteSearchController);
	}
	
	public ClienteCriteria getCriteria() {
		ClienteCriteria criteria = new ClienteCriteria();
		criteria.setEmail(emailTF.getText());
		criteria.setNombre(nombreTF.getText());
		criteria.setApellido(apellidoTF.getText());
		criteria.setTelefono(telefonoTF.getText());
		
		criteria.setActivo(activoCheckBox.isSelected()); 
		criteria.setVerificado(verificadoCheckBox.isSelected()); 
		
//		criteria.setCreadoDesde(registroDesdeDC.getDate()); 
//		criteria.setCreadoHasta(registroHastaDC.getDate()); 
		return criteria;
	}
	
	public void limpiarCampos () {
		
	}

}
