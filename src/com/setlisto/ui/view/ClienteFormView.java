package com.setlisto.ui.view;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.setlisto.model.Cliente;
import com.setlisto.ui.controller.ClienteFormController;
import com.toedter.calendar.JDateChooser;

public class ClienteFormView extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField emailTF;
	private JPasswordField contrasenaPF;
	private JTextField nombreTF;
	private JTextField apellidoTF;
	private JTextField telefonoTF;
	private JTextArea preferenciasTA;
	private JCheckBox activoCB;
	private JCheckBox verificadoCB;
	private JDateChooser fechaNacimientoDC;
	private JButton guardarButton;
	private JButton cancelarButton;
	private Cliente clienteOriginal;

	public ClienteFormView(Window owner, Cliente cliente, Runnable onSaved) {
		super(owner, cliente == null ? "Crear cliente" : "Editar cliente", ModalityType.APPLICATION_MODAL);
		this.clienteOriginal = cliente;
		initialize();
		cargarCliente(cliente);
		guardarButton.setAction(new ClienteFormController(this, onSaved));
		postInitialize();
	}

	private void initialize() {
		setBounds(100, 100, 520, 430);
		setLayout(new BorderLayout(0, 0));

		JPanel formPanel = new JPanel(new GridBagLayout());
		formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(formPanel, BorderLayout.CENTER);

		emailTF = addTextField(formPanel, "Email", 0);
		contrasenaPF = addPasswordField(formPanel, "Contrasena", 1);
		nombreTF = addTextField(formPanel, "Nombre", 2);
		apellidoTF = addTextField(formPanel, "Apellido", 3);
		telefonoTF = addTextField(formPanel, "Telefono", 4);

		fechaNacimientoDC = new JDateChooser();
		addComponent(formPanel, "Fecha nacimiento", fechaNacimientoDC, 5);

		activoCB = new JCheckBox("Activo");
		addComponent(formPanel, "", activoCB, 6);

		verificadoCB = new JCheckBox("Verificado");
		addComponent(formPanel, "", verificadoCB, 7);

		preferenciasTA = new JTextArea(4, 20);
		addComponent(formPanel, "Preferencias", new JScrollPane(preferenciasTA), 8);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		add(buttonPanel, BorderLayout.SOUTH);
		cancelarButton = new JButton("Cancelar");
		buttonPanel.add(cancelarButton);
		guardarButton = new JButton("Guardar");
		buttonPanel.add(guardarButton);
	}
	
	public void postInitialize() {
		
		cancelarButton.addActionListener(e -> dispose());
	}

	public Cliente getClienteFromFields() {
		Cliente cliente = clienteOriginal == null ? new Cliente() : clienteOriginal;
		cliente.setEmail(emailTF.getText().trim());
		cliente.setNombre(nombreTF.getText().trim());
		cliente.setApellido(apellidoTF.getText().trim());
		cliente.setTelefono(telefonoTF.getText().trim());
		cliente.setPreferencias(preferenciasTA.getText().trim());
		cliente.setActivo(activoCB.isSelected());
		cliente.setVerificado(verificadoCB.isSelected());
		cliente.setFechaNacimiento(toSqlDate(fechaNacimientoDC.getDate()));

		String password = new String(contrasenaPF.getPassword()).trim();
		if (!password.isEmpty()) {
			cliente.setContrasena(password);
		}
		return cliente;
	}

	public boolean isEditMode() {
		return clienteOriginal != null && clienteOriginal.getId() != null;
	}

	public boolean hasPasswordText() {
		return new String(contrasenaPF.getPassword()).trim().length() > 0;
	}

	private void cargarCliente(Cliente cliente) {
		if (cliente == null) {
			activoCB.setSelected(true);
			return;
		}
		emailTF.setText(nullToEmpty(cliente.getEmail()));
		nombreTF.setText(nullToEmpty(cliente.getNombre()));
		apellidoTF.setText(nullToEmpty(cliente.getApellido()));
		telefonoTF.setText(nullToEmpty(cliente.getTelefono()));
		preferenciasTA.setText(nullToEmpty(cliente.getPreferencias()));
		activoCB.setSelected(Boolean.TRUE.equals(cliente.getActivo()));
		verificadoCB.setSelected(Boolean.TRUE.equals(cliente.getVerificado()));
		if (cliente.getFechaNacimiento() != null) {
			fechaNacimientoDC.setDate(cliente.getFechaNacimiento());
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

	private void addComponent(JPanel panel, String label, java.awt.Component component, int row) {
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
