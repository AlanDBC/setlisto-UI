package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.sql.Date;
import java.time.ZoneId;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.setlisto.model.Organizador;
import com.setlisto.ui.controller.OrganizadorFormController;
import com.toedter.calendar.JDateChooser;

public class OrganizadorFormView extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField nombreComercialTF;
	private JTextField emailTF;
	private JPasswordField contrasenaPF;
	private JTextField telefonoTF;
	private JTextField nombreTF;
	private JTextField apellido1TF;
	private JTextField apellido2TF;
	private JCheckBox verificadoCB;
	private JDateChooser fechaNacimientoDC;
	private JButton guardarButton;
	private JButton cancelarButton;
	private Organizador organizadorOriginal;

	public OrganizadorFormView(Window owner, Organizador organizador, Runnable onSaved) {
		super(owner, organizador == null ? "Crear organizador" : "Editar organizador", ModalityType.APPLICATION_MODAL);
		this.organizadorOriginal = organizador;
		initialize();
		cargarOrganizador(organizador);
		guardarButton.setAction(new OrganizadorFormController(this, onSaved));
		cancelarButton.addActionListener(e -> dispose());
	}

	private void initialize() {
		setBounds(100, 100, 520, 470);
		setLayout(new BorderLayout(0, 0));

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(formPanel, BorderLayout.CENTER);

		nombreComercialTF = addTextField(formPanel, "Nombre comercial", 0);
		emailTF = addTextField(formPanel, "Email", 1);
		contrasenaPF = addPasswordField(formPanel, "Contrasena", 2);
		telefonoTF = addTextField(formPanel, "Telefono", 3);
		nombreTF = addTextField(formPanel, "Nombre", 4);
		apellido1TF = addTextField(formPanel, "Apellido 1", 5);
		apellido2TF = addTextField(formPanel, "Apellido 2", 6);

		fechaNacimientoDC = new JDateChooser();
		addComponent(formPanel, "Fecha nacimiento", fechaNacimientoDC, 7);

		verificadoCB = new JCheckBox("Verificado");
		addComponent(formPanel, "", verificadoCB, 8);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(buttonPanel, BorderLayout.SOUTH);
		cancelarButton = new JButton("Cancelar");
		buttonPanel.add(cancelarButton);
		guardarButton = new JButton("Guardar");
		buttonPanel.add(guardarButton);
	}

	public Organizador getOrganizadorFromFields() {
		Organizador organizador = organizadorOriginal == null ? new Organizador() : organizadorOriginal;
		organizador.setNombreComercial(nombreComercialTF.getText().trim());
		organizador.setEmail(emailTF.getText().trim());
		organizador.setTelefono(telefonoTF.getText().trim());
		organizador.setNombre(nombreTF.getText().trim());
		organizador.setApellido1(apellido1TF.getText().trim());
		organizador.setApellido2(apellido2TF.getText().trim());
		organizador.setVerificado(verificadoCB.isSelected());
		organizador.setFechaNacimiento(toSqlDate(fechaNacimientoDC.getDate()));

		String password = new String(contrasenaPF.getPassword()).trim();
		if (!password.isEmpty()) {
			organizador.setContrasena(password);
		}
		return organizador;
	}

	public boolean isEditMode() {
		return organizadorOriginal != null && organizadorOriginal.getId() != null;
	}

	public boolean hasPasswordText() {
		return new String(contrasenaPF.getPassword()).trim().length() > 0;
	}

	private void cargarOrganizador(Organizador organizador) {
		if (organizador == null) {
			return;
		}
		nombreComercialTF.setText(nullToEmpty(organizador.getNombreComercial()));
		emailTF.setText(nullToEmpty(organizador.getEmail()));
		telefonoTF.setText(nullToEmpty(organizador.getTelefono()));
		nombreTF.setText(nullToEmpty(organizador.getNombre()));
		apellido1TF.setText(nullToEmpty(organizador.getApellido1()));
		apellido2TF.setText(nullToEmpty(organizador.getApellido2()));
		verificadoCB.setSelected(Boolean.TRUE.equals(organizador.getVerificado()));
		if (organizador.getFechaNacimiento() != null) {
			fechaNacimientoDC.setDate(organizador.getFechaNacimiento());
		}
	}

	private JTextField addTextField(JPanel panel, String label, int row) {
		JTextField textField = new JTextField();
		addComponent(panel, label, textField, row);
		return textField;
	}

	private JPasswordField addPasswordField(JPanel panel, String label, int row) {
		JPasswordField passwordField = new JPasswordField();
		addComponent(panel, label, passwordField, row);
		return passwordField;
	}

	private void addComponent(JPanel panel, String label, Component component, int row) {
		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.anchor = GridBagConstraints.WEST;
		gbcLabel.insets = new Insets(0, 0, 5, 5);
		gbcLabel.gridx = 0;
		gbcLabel.gridy = row;
		panel.add(new JLabel(label), gbcLabel);

		GridBagConstraints gbcComponent = new GridBagConstraints();
		gbcComponent.fill = GridBagConstraints.HORIZONTAL;
		gbcComponent.weightx = 1.0;
		gbcComponent.insets = new Insets(0, 0, 5, 0);
		gbcComponent.gridx = 1;
		gbcComponent.gridy = row;
		panel.add(component, gbcComponent);
	}

	private String nullToEmpty(String value) {
		return value == null ? "" : value;
	}

	private Date toSqlDate(java.util.Date date) {
		if (date == null) {
			return null;
		}
		return Date.valueOf(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
}
