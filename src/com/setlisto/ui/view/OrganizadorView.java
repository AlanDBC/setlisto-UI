package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;
import java.time.ZoneId;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.setlisto.criteria.OrganizadorCriteria;
import com.setlisto.model.Organizador;
import com.setlisto.ui.controller.OrganizadorViewController;
import com.setlisto.ui.model.OrganizadorTableModel;
import com.toedter.calendar.JDateChooser;

public class OrganizadorView extends AbstractView {

	private static final long serialVersionUID = 1L;

	private JTextField nombreComercialTF;
	private JTextField emailTF;
	private JTextField telefonoTF;
	private JTextField nombreTF;
	private JTextField apellido1TF;
	private JComboBox<String> verificadoCB;
	private JDateChooser nacimientoDesdeDC;
	private JDateChooser nacimientoHastaDC;
	private JButton limpiarButton;
	private JButton buscarButton;
	private JButton nuevoButton;
	private JButton editarButton;
	private JButton eliminarButton;
	private JButton verificarButton;
	private JLabel totalResultadosLabel;
	private JTable resultadosTable;
	private OrganizadorTableModel tableModel;

	public OrganizadorView() {
		initialize();
		postInitialize();
		setName("Organizadores");
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JPanel busquedaPanel = new JPanel();
		add(busquedaPanel, BorderLayout.NORTH);
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 0, 130, 120, 100, 100, 100, 90, 130, 130, 0 };
		layout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		busquedaPanel.setLayout(layout);

		addLabel(busquedaPanel, "Nombre comercial", 1, 0);
		addLabel(busquedaPanel, "Email", 2, 0);
		addLabel(busquedaPanel, "Telefono", 3, 0);
		addLabel(busquedaPanel, "Nombre", 4, 0);
		addLabel(busquedaPanel, "Apellido", 5, 0);
		addLabel(busquedaPanel, "Verificado", 6, 0);
		addLabel(busquedaPanel, "Nacimiento desde", 7, 0);
		addLabel(busquedaPanel, "Nacimiento hasta", 8, 0);

		nombreComercialTF = addTextField(busquedaPanel, 1);
		emailTF = addTextField(busquedaPanel, 2);
		telefonoTF = addTextField(busquedaPanel, 3);
		nombreTF = addTextField(busquedaPanel, 4);
		apellido1TF = addTextField(busquedaPanel, 5);

		verificadoCB = new JComboBox<String>(new String[] { "Todos", "Si", "No" });
		addComponent(busquedaPanel, verificadoCB, 6, 1);

		nacimientoDesdeDC = new JDateChooser();
		addComponent(busquedaPanel, nacimientoDesdeDC, 7, 1);

		nacimientoHastaDC = new JDateChooser();
		addComponent(busquedaPanel, nacimientoHastaDC, 8, 1);

		JPanel accionesBusquedaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		GridBagConstraints gbcAccionesBusqueda = new GridBagConstraints();
		gbcAccionesBusqueda.anchor = GridBagConstraints.EAST;
		gbcAccionesBusqueda.gridwidth = 8;
		gbcAccionesBusqueda.insets = new Insets(5, 0, 5, 5);
		gbcAccionesBusqueda.gridx = 1;
		gbcAccionesBusqueda.gridy = 2;
		busquedaPanel.add(accionesBusquedaPanel, gbcAccionesBusqueda);

		limpiarButton = new JButton("Limpiar");
		accionesBusquedaPanel.add(limpiarButton);

		buscarButton = new JButton("Buscar");
		accionesBusquedaPanel.add(buscarButton);

		tableModel = new OrganizadorTableModel();
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

		verificarButton = new JButton("Verificar/Desverificar");
		accionesPanel.add(verificarButton);

		totalResultadosLabel = new JLabel("Total de resultados: 0");
		accionesPanel.add(totalResultadosLabel);
	}

	private void postInitialize() {
		OrganizadorViewController controller = new OrganizadorViewController(this);
		buscarButton.setAction(controller);
		nuevoButton.setAction(controller.getNuevoAction());
		editarButton.setAction(controller.getEditarAction());
		eliminarButton.setAction(controller.getEliminarAction());
		verificarButton.setAction(controller.getVerificarAction());
		limpiarButton.addActionListener(e -> limpiarCampos());
		controller.doAction();
	}

	public OrganizadorCriteria getCriteria() {
		OrganizadorCriteria criteria = new OrganizadorCriteria();
		criteria.setNombreComercial(toNull(nombreComercialTF.getText()));
		criteria.setEmail(toNull(emailTF.getText()));
		criteria.setTelefono(toNull(telefonoTF.getText()));
		criteria.setNombre(toNull(nombreTF.getText()));
		criteria.setApellido1(toNull(apellido1TF.getText()));
		criteria.setVerificado(toBooleanFilter((String) verificadoCB.getSelectedItem()));
		criteria.setFechaNacimientoDesde(toSqlDate(nacimientoDesdeDC.getDate()));
		criteria.setFechaNacimientoHasta(toSqlDate(nacimientoHastaDC.getDate()));
		return criteria;
	}

	public void setResults(List<Organizador> organizadores) {
		tableModel.setOrganizadores(organizadores);
		totalResultadosLabel.setText("Total de resultados: " + tableModel.getRowCount());
	}

	public Organizador getSelectedOrganizador() {
		int selectedRow = resultadosTable.getSelectedRow();
		if (selectedRow < 0) {
			return null;
		}
		return tableModel.getOrganizadorAt(resultadosTable.convertRowIndexToModel(selectedRow));
	}

	public void limpiarCampos() {
		nombreComercialTF.setText("");
		emailTF.setText("");
		telefonoTF.setText("");
		nombreTF.setText("");
		apellido1TF.setText("");
		verificadoCB.setSelectedIndex(0);
		nacimientoDesdeDC.setDate(null);
		nacimientoHastaDC.setDate(null);
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

	private Date toSqlDate(java.util.Date date) {
		if (date == null) {
			return null;
		}
		return Date.valueOf(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
}
