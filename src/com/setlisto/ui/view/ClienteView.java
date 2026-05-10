package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.setlisto.criteria.ClienteCriteria;
import com.setlisto.model.Cliente;
import com.setlisto.ui.controller.ClienteViewController;
import com.setlisto.ui.model.ClienteTableModel;
import com.toedter.calendar.JDateChooser;

public class ClienteView extends AbstractView {

	private static final long serialVersionUID = 1L;

	private JTable resultadosTable;
	private JTextField emailTF;
	private JTextField telefonoTF;
	private JTextField nombreTF;
	private JTextField apellidoTF;
	private JButton limpiarCamposButton;
	private JButton buscarButton;
	private JButton nuevoButton;
	private JButton editarButton;
	private JButton eliminarButton;
	private JButton activarButton;
	private JButton verificarButton;
	private JButton refrescarButton;
	private JDateChooser registroDesdeDC;
	private JDateChooser registroHastaDC;
	private JComboBox<String> verificadoCB;
	private JComboBox<String> activoCB;
	private JLabel totalResultadosLabel;
	private ClienteTableModel tableModel;

	public ClienteView() {
		initialize();
		postInitialize();
		setName("Clientes");
	}

	public void initialize() {
		setLayout(new BorderLayout(0, 0));

		JPanel busquedaPanel = new JPanel();
		add(busquedaPanel, BorderLayout.NORTH);
		GridBagLayout gbl_busquedaPanel = new GridBagLayout();
		gbl_busquedaPanel.columnWidths = new int[] { 0, 110, 110, 110, 110, 130, 130, 90, 90, 0 };
		gbl_busquedaPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_busquedaPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_busquedaPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		busquedaPanel.setLayout(gbl_busquedaPanel);

		addLabel(busquedaPanel, "Email", 1, 0);
		addLabel(busquedaPanel, "Telefono", 2, 0);
		addLabel(busquedaPanel, "Nombre", 3, 0);
		addLabel(busquedaPanel, "Apellido", 4, 0);
		addLabel(busquedaPanel, "Registro desde", 5, 0);
		addLabel(busquedaPanel, "Registro hasta", 6, 0);
		addLabel(busquedaPanel, "Activo", 7, 0);
		addLabel(busquedaPanel, "Verificado", 8, 0);

		emailTF = addTextField(busquedaPanel, 1);
		telefonoTF = addTextField(busquedaPanel, 2);
		nombreTF = addTextField(busquedaPanel, 3);
		apellidoTF = addTextField(busquedaPanel, 4);

		registroDesdeDC = new JDateChooser();
		addComponent(busquedaPanel, registroDesdeDC, 5, 1);

		registroHastaDC = new JDateChooser();
		addComponent(busquedaPanel, registroHastaDC, 6, 1);

		activoCB = new JComboBox<String>(new String[] { "Todos", "Si", "No" });
		addComponent(busquedaPanel, activoCB, 7, 1);

		verificadoCB = new JComboBox<String>(new String[] { "Todos", "Si", "No" });
		addComponent(busquedaPanel, verificadoCB, 8, 1);

		JPanel accionesBusquedaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		GridBagConstraints gbc_accionesBusquedaPanel = new GridBagConstraints();
		gbc_accionesBusquedaPanel.anchor = GridBagConstraints.EAST;
		gbc_accionesBusquedaPanel.gridwidth = 8;
		gbc_accionesBusquedaPanel.insets = new Insets(5, 0, 5, 5);
		gbc_accionesBusquedaPanel.gridx = 1;
		gbc_accionesBusquedaPanel.gridy = 2;
		busquedaPanel.add(accionesBusquedaPanel, gbc_accionesBusquedaPanel);

		limpiarCamposButton = new JButton("Limpiar");
		accionesBusquedaPanel.add(limpiarCamposButton);

		buscarButton = new JButton("Buscar");
		accionesBusquedaPanel.add(buscarButton);

		tableModel = new ClienteTableModel();
		resultadosTable = new JTable(tableModel);
		add(new JScrollPane(resultadosTable), BorderLayout.CENTER);

		JPanel accionesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		add(accionesPanel, BorderLayout.SOUTH);

		nuevoButton = new JButton("Nuevo");
		accionesPanel.add(nuevoButton);

		editarButton = new JButton("Editar");
		accionesPanel.add(editarButton);

		eliminarButton = new JButton("Eliminar");
		accionesPanel.add(eliminarButton);

		activarButton = new JButton("Activar/Desactivar");
		accionesPanel.add(activarButton);

		verificarButton = new JButton("Verificar/Desverificar");
		accionesPanel.add(verificarButton);

		refrescarButton = new JButton("Refrescar");
		accionesPanel.add(refrescarButton);

		totalResultadosLabel = new JLabel("Total de resultados: 0");
		accionesPanel.add(totalResultadosLabel);
	}

	private void postInitialize() {
		ClienteViewController controller = new ClienteViewController(this);
		// filtrado en tiempo real para componentes de búsqueda
		
		
		
		
		buscarButton.setAction(controller);
		refrescarButton.setAction(controller);
		nuevoButton.setAction(controller.getNuevoAction());
		editarButton.setAction(controller.getEditarAction());
		eliminarButton.setAction(controller.getEliminarAction());
		activarButton.setAction(controller.getActivarAction());
		verificarButton.setAction(controller.getVerificarAction());
		limpiarCamposButton.addActionListener(e -> limpiarCampos());
		controller.doAction();
	}

	public ClienteCriteria getCriteria() {
		ClienteCriteria criteria = new ClienteCriteria();
		criteria.setEmail(toNull(emailTF.getText()));
		criteria.setNombre(toNull(nombreTF.getText()));
		criteria.setApellido(toNull(apellidoTF.getText()));
		criteria.setTelefono(toNull(telefonoTF.getText()));
		criteria.setActivo(toBooleanFilter((String) activoCB.getSelectedItem()));
		criteria.setVerificado(toBooleanFilter((String) verificadoCB.getSelectedItem()));
		criteria.setCreadoDesde(toDateTime(registroDesdeDC.getDate(), false));
		criteria.setCreadoHasta(toDateTime(registroHastaDC.getDate(), true));
		return criteria;
	}

	public void setResults(List<Cliente> clientes) {
		tableModel.setClientes(clientes);
		totalResultadosLabel.setText("Total de resultados: " + tableModel.getRowCount());
	}

	public Cliente getSelectedCliente() {
		int selectedRow = resultadosTable.getSelectedRow();
		if (selectedRow < 0) {
			return null;
		}
		return tableModel.getClienteAt(resultadosTable.convertRowIndexToModel(selectedRow));
	}

	public void limpiarCampos() {
		emailTF.setText("");
		telefonoTF.setText("");
		nombreTF.setText("");
		apellidoTF.setText("");
		registroDesdeDC.setDate(null);
		registroHastaDC.setDate(null);
		activoCB.setSelectedIndex(0);
		verificadoCB.setSelectedIndex(0);
	}

	private void addLabel(JPanel panel, String text, int gridx, int gridy) {
		JLabel label = new JLabel(text);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 0, 5, 5);
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		panel.add(label, gbc);
	}

	private JTextField addTextField(JPanel panel, int gridx) {
		JTextField textField = new JTextField();
		textField.setColumns(10);
		addComponent(panel, textField, gridx, 1);
		return textField;
	}

	private void addComponent(JPanel panel, java.awt.Component component, int gridx, int gridy) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		panel.add(component, gbc);
	}

	private String toNull(String value) {
		return value == null || value.trim().isEmpty() ? null : value.trim();
	}

	private Boolean toBooleanFilter(String value) {
		if ("Si".equals(value)) {
			return Boolean.TRUE;
		}
		if ("No".equals(value)) {
			return Boolean.FALSE;
		}
		return null;
	}

	private LocalDateTime toDateTime(Date date, boolean endOfDay) {
		if (date == null) {
			return null;
		}
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return LocalDateTime.of(localDate, endOfDay ? LocalTime.MAX : LocalTime.MIN);
	}
}
