package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.setlisto.criteria.LugarCriteria;
import com.setlisto.model.Ciudad;
import com.setlisto.model.Lugar;
import com.setlisto.model.LugarDTO;
import com.setlisto.model.Pais;
import com.setlisto.model.Region;
import com.setlisto.model.ZonaHoraria;
import com.setlisto.ui.controller.AceptarLugarSeleccionadoController;
import com.setlisto.ui.controller.CancelarLugarSeleccionadoController;
import com.setlisto.ui.controller.LlenarCombosLugarController;
import com.setlisto.ui.controller.LugarCreateController;
import com.setlisto.ui.controller.LugarSearchController;
import com.setlisto.ui.model.LugarTableModel;
import com.setlisto.ui.renderer.CiudadCBRenderer;
import com.setlisto.ui.renderer.LugarTableRenderer;
import com.setlisto.ui.renderer.PaisCBRenderer;
import com.setlisto.ui.renderer.RegionCBRenderer;
import com.setlisto.ui.renderer.ZonaHorariaCBRenderer;

public class LugarSelectView extends JDialog {

	private static final long serialVersionUID = 1L;

	private LugarDTO lugarSeleccionado;
	private JPanel contentPane;
	private JPanel searchPanel;
	private JLabel buscarLabel;
	private JLabel paisLabel;
	private JLabel regionLabel;
	private JLabel ciudadLabel;
	private JComboBox paisCB;
	private JComboBox regionCB;
	private JComboBox ciudadCB;
	private JComboBox zonaHorariaCB;
	private JLabel direccionLabel;
	private JLabel lugarLabel;
	private JLabel zonaHorariaLabel;
	private JTextField nombreTF;
	private JTextField direccionTF;
	private Component verticalStrut;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private JPanel tablePanel;
	private JTable resultadosTable;
	private JButton buscarButton;
	private JButton crearButton;
	private JLabel preguntaLabel;
	private JButton limpiarButton;
	private List<LugarDTO> model;
	private JPanel southPanel;
	private JLabel totalResultadosLabel;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private JLabel lugarSeleccionadoLabel;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private JButton cancelarButton;
	private JButton aceptarButton;
	private Component horizontalGlue;
	private EventoCreateView receptor;
	private Component verticalStrut_3;

	public LugarSelectView(JFrame parent, boolean modal, EventoCreateView receptor) {
		super(parent, modal);
		this.receptor = receptor;
		initialize();
		postInitialize();
	}

	public void initialize () {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		searchPanel = new JPanel();
		contentPane.add(searchPanel, BorderLayout.NORTH);
		GridBagLayout gbl_searchPanel = new GridBagLayout();
		gbl_searchPanel.columnWidths = new int[]{0, 72, 77, 83, 169, 135, 135, 0, 0};
		gbl_searchPanel.rowHeights = new int[]{28, 0, 17, 20, 0, 0, 0, 0, 0, 0, 0};
		gbl_searchPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_searchPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		searchPanel.setLayout(gbl_searchPanel);

		buscarLabel = new JLabel("Busqueda de Lugares");
		GridBagConstraints gbc_buscarLabel = new GridBagConstraints();
		gbc_buscarLabel.gridwidth = 6;
		gbc_buscarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_buscarLabel.gridx = 1;
		gbc_buscarLabel.gridy = 0;
		searchPanel.add(buscarLabel, gbc_buscarLabel);
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 4;
		gbc_verticalStrut_3.gridy = 1;
		searchPanel.add(verticalStrut_3, gbc_verticalStrut_3);

		paisLabel = new JLabel("Pais");
		GridBagConstraints gbc_paisLabel = new GridBagConstraints();
		gbc_paisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paisLabel.gridx = 1;
		gbc_paisLabel.gridy = 2;
		searchPanel.add(paisLabel, gbc_paisLabel);

		regionLabel = new JLabel("Region");
		GridBagConstraints gbc_regionLabel = new GridBagConstraints();
		gbc_regionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_regionLabel.gridx = 2;
		gbc_regionLabel.gridy = 2;
		searchPanel.add(regionLabel, gbc_regionLabel);

		ciudadLabel = new JLabel("Ciudad");
		GridBagConstraints gbc_ciudadLabel = new GridBagConstraints();
		gbc_ciudadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ciudadLabel.gridx = 3;
		gbc_ciudadLabel.gridy = 2;
		searchPanel.add(ciudadLabel, gbc_ciudadLabel);

		lugarLabel = new JLabel("Lugar (nombre)");
		GridBagConstraints gbc_lugarLabel = new GridBagConstraints();
		gbc_lugarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lugarLabel.gridx = 4;
		gbc_lugarLabel.gridy = 2;
		searchPanel.add(lugarLabel, gbc_lugarLabel);

		direccionLabel = new JLabel("Lugar (dirección)");
		GridBagConstraints gbc_direccionLabel = new GridBagConstraints();
		gbc_direccionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_direccionLabel.gridx = 5;
		gbc_direccionLabel.gridy = 2;
		searchPanel.add(direccionLabel, gbc_direccionLabel);

		zonaHorariaLabel = new JLabel("Zona Horaria");
		GridBagConstraints gbc_zonaHorariaLabel = new GridBagConstraints();
		gbc_zonaHorariaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaLabel.gridx = 6;
		gbc_zonaHorariaLabel.gridy = 2;
		searchPanel.add(zonaHorariaLabel, gbc_zonaHorariaLabel);

		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 3;
		searchPanel.add(horizontalStrut, gbc_horizontalStrut);

		paisCB = new JComboBox();
		GridBagConstraints gbc_paisCB = new GridBagConstraints();
		gbc_paisCB.insets = new Insets(0, 0, 5, 5);
		gbc_paisCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_paisCB.gridx = 1;
		gbc_paisCB.gridy = 3;
		searchPanel.add(paisCB, gbc_paisCB);

		regionCB = new JComboBox();
		GridBagConstraints gbc_regionCB = new GridBagConstraints();
		gbc_regionCB.insets = new Insets(0, 0, 5, 5);
		gbc_regionCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_regionCB.gridx = 2;
		gbc_regionCB.gridy = 3;
		searchPanel.add(regionCB, gbc_regionCB);

		ciudadCB = new JComboBox();
		GridBagConstraints gbc_ciudadCB = new GridBagConstraints();
		gbc_ciudadCB.insets = new Insets(0, 0, 5, 5);
		gbc_ciudadCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_ciudadCB.gridx = 3;
		gbc_ciudadCB.gridy = 3;
		searchPanel.add(ciudadCB, gbc_ciudadCB);

		nombreTF = new JTextField();
		nombreTF.setColumns(10);
		GridBagConstraints gbc_nombreTF = new GridBagConstraints();
		gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTF.gridx = 4;
		gbc_nombreTF.gridy = 3;
		searchPanel.add(nombreTF, gbc_nombreTF);

		direccionTF = new JTextField();
		direccionTF.setColumns(10);
		GridBagConstraints gbc_direccionTF = new GridBagConstraints();
		gbc_direccionTF.insets = new Insets(0, 0, 5, 5);
		gbc_direccionTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_direccionTF.gridx = 5;
		gbc_direccionTF.gridy = 3;
		searchPanel.add(direccionTF, gbc_direccionTF);

		zonaHorariaCB = new JComboBox();
		GridBagConstraints gbc_zonaHorariaCB = new GridBagConstraints();
		gbc_zonaHorariaCB.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_zonaHorariaCB.gridx = 6;
		gbc_zonaHorariaCB.gridy = 3;
		searchPanel.add(zonaHorariaCB, gbc_zonaHorariaCB);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 7;
		gbc_horizontalStrut_1.gridy = 3;
		searchPanel.add(horizontalStrut_1, gbc_horizontalStrut_1);

		preguntaLabel = new JLabel("¿El lugar no existe?");
		GridBagConstraints gbc_preguntaLabel = new GridBagConstraints();
		gbc_preguntaLabel.anchor = GridBagConstraints.SOUTH;
		gbc_preguntaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_preguntaLabel.gridx = 3;
		gbc_preguntaLabel.gridy = 5;
		searchPanel.add(preguntaLabel, gbc_preguntaLabel);

		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 4;
		gbc_verticalStrut.gridy = 5;
		searchPanel.add(verticalStrut, gbc_verticalStrut);

		limpiarButton = new JButton("Limpiar campos");
		limpiarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		GridBagConstraints gbc_limpiarButton = new GridBagConstraints();
		gbc_limpiarButton.insets = new Insets(0, 0, 5, 5);
		gbc_limpiarButton.gridx = 1;
		gbc_limpiarButton.gridy = 6;
		searchPanel.add(limpiarButton, gbc_limpiarButton);

		crearButton = new JButton("Crear nuevo lugar");
		GridBagConstraints gbc_crearButton = new GridBagConstraints();
		gbc_crearButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_crearButton.insets = new Insets(0, 0, 5, 5);
		gbc_crearButton.gridx = 3;
		gbc_crearButton.gridy = 6;
		searchPanel.add(crearButton, gbc_crearButton);

		buscarButton = new JButton("Buscar");
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.insets = new Insets(0, 0, 5, 5);
		gbc_buscarButton.gridx = 5;
		gbc_buscarButton.gridy = 6;
		searchPanel.add(buscarButton, gbc_buscarButton);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 4;
		gbc_verticalStrut_2.gridy = 7;
		searchPanel.add(verticalStrut_2, gbc_verticalStrut_2);
		
		lugarSeleccionadoLabel = new JLabel("(Sin lugar seleccionado)");
		GridBagConstraints gbc_lugarSeleccionadoLabel = new GridBagConstraints();
		gbc_lugarSeleccionadoLabel.gridwidth = 5;
		gbc_lugarSeleccionadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lugarSeleccionadoLabel.gridx = 1;
		gbc_lugarSeleccionadoLabel.gridy = 8;
		searchPanel.add(lugarSeleccionadoLabel, gbc_lugarSeleccionadoLabel);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_1.gridx = 4;
		gbc_verticalStrut_1.gridy = 9;
		searchPanel.add(verticalStrut_1, gbc_verticalStrut_1);

		tablePanel = new JPanel();
		contentPane.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));

		resultadosTable = new JTable();
		resultadosTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane scrollPane = new JScrollPane(resultadosTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		tablePanel.add(scrollPane);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		scrollPane.setRowHeaderView(horizontalStrut_2);
		
		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		totalResultadosLabel = new JLabel("Total de resultados: 0");
		southPanel.add(totalResultadosLabel);
		
		horizontalGlue = Box.createHorizontalGlue();
		southPanel.add(horizontalGlue);
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setIcon(new ImageIcon(LugarSelectView.class.getResource("/nuvola/16x16/1250_delete_delete.png")));
		southPanel.add(cancelarButton);
		
		aceptarButton = new JButton("Aceptar");
		aceptarButton.setIcon(new ImageIcon(LugarSelectView.class.getResource("/nuvola/16x16/1710_ok_yes_accept_green_ok_green_accept_yes.png")));
		southPanel.add(aceptarButton);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut_3, BorderLayout.EAST);
	}

	public void postInitialize() {
		resultadosTable.setModel(new LugarTableModel());

		setAllRenderers();

		setAllControllers();
		
		buscarButton.doClick(); // para cargar la tabla con todos los resultados al abrir el dialog.

	}

	private void setAllRenderers() {
		resultadosTable.setDefaultRenderer(Object.class, new LugarTableRenderer()); 

		paisCB.setRenderer(new PaisCBRenderer());
		regionCB.setRenderer(new RegionCBRenderer());
		ciudadCB.setRenderer(new CiudadCBRenderer());
		zonaHorariaCB.setRenderer(new ZonaHorariaCBRenderer());
	}

	private void setAllControllers() {
		LlenarCombosLugarController llenarCombosController = new LlenarCombosLugarController(this);

		LugarSearchController searchController = new LugarSearchController(this, this.receptor);
		buscarButton.setAction(searchController);
		crearButton.setAction(new LugarCreateController(this));
		aceptarButton.setAction(new AceptarLugarSeleccionadoController(this, this.receptor));
		cancelarButton.setAction(new CancelarLugarSeleccionadoController(this, this.receptor));
		
		paisCB.setAction(searchController);
		regionCB.setAction(searchController);
		ciudadCB.setAction(searchController);
		
		nombreTF.setAction(searchController);
		direccionTF.setAction(searchController);
		
		resultadosTable.addMouseListener(searchController);
	}

	public void limpiarCampos () {
		nombreTF.setText("");
		direccionTF.setText("");

		paisCB.setSelectedIndex(0);
		
		// buscamos sin filtros para refrescar la tabla con todos los resultados.
		buscarButton.doClick();
	}

	public JComboBox getPaisCB() {
		return paisCB;
	}

	public JComboBox getRegionCB() {
		return regionCB;
	}

	public JComboBox getCiudadCB() {
		return ciudadCB;
	}

	public JComboBox getZonaHorariaCB() {
		return zonaHorariaCB;
	}

	public LugarDTO getLugarSeleccionado() {
		return lugarSeleccionado;
	}

	private void seleccionarLugar(LugarDTO lugar) {
		this.lugarSeleccionado = lugar;
		this.dispose(); // Cierra el diálogo y devuelve el control al padre
	}

	public LugarCriteria getCriteria () {
		LugarCriteria criteria = new LugarCriteria();
		// haremos validacion para el caso que el id sea null (esta seleccionado el placeHolder).

		Pais p = (Pais) paisCB.getSelectedItem();
		if (p != null && p.getId() != null) {
			criteria.setPaisId(p.getId());
		} else {
			criteria.setPaisId(null);
		}

		Region r = (Region) regionCB.getSelectedItem();
		if (r != null && r.getId() != null) {
			criteria.setRegionId(r.getId());
		} else {
			criteria.setRegionId(null);
		}

		Ciudad c = (Ciudad) ciudadCB.getSelectedItem();
		if (c != null && c.getId() != null) {
			criteria.setCiudadId(c.getId());
		} else {
			criteria.setCiudadId(null);
		}

		criteria.setLugarNombre(nombreTF.getText().trim().isEmpty() ? null : nombreTF.getText().trim());
		criteria.setLugarDireccion(direccionTF.getText().trim().isEmpty() ? null : direccionTF.getText().trim());

		return criteria;
	}
	
	public void setModel (List<LugarDTO> lugares) {
		this.model = lugares;
		updateView();
	}
	
	public void updateView() {		
		LugarTableModel tableModel = new LugarTableModel(model);
		resultadosTable.setModel(tableModel);
		totalResultadosLabel.setText("Total de resultados: " + model.size());
	
	}
	
	public Lugar getLugar() {

		Lugar lugar = new Lugar();
		
		lugar.setNombre(nombreTF.getText().trim());
		lugar.setDireccion(direccionTF.getText().trim());

		Ciudad c = (Ciudad) ciudadCB.getSelectedItem();
		if (c != null && c.getId() != null) {
			lugar.setCiudadId(c.getId());
		}

		ZonaHoraria zh = (ZonaHoraria) zonaHorariaCB.getSelectedItem();
		if (zh != null && zh.getId() != null) {
			lugar.setIdZonaHoraria(zh.getId());
		}
		
		return lugar;
	}
	
	// para traer el lugar seleccionado desde el controlador (LugarCreateController o el lugar seleccionado de la tabla).
	public void setLugarSeleccionado(LugarDTO lugar) {
		this.lugarSeleccionado = lugar;
		setLabelSeleccionado();
	}
	
	// desde aqui cambiamos el texto de una label que muestre el lugar seleccionado, para que el usuario sepa que se ha seleccionado correctamente.
	public void setLabelSeleccionado () {
		if (lugarSeleccionado != null) {
			String zona = lugarSeleccionado.getZonaHorariaNombre() == null ? "zona horaria no informada" : lugarSeleccionado.getZonaHorariaNombre();
			lugarSeleccionadoLabel.setText("Lugar seleccionado: " + lugarSeleccionado.getNombre() + ", " + lugarSeleccionado.getDireccion() + " (" + zona + ")");
		} else {
			lugarSeleccionadoLabel.setText("(Sin lugar seleccionado)");
		}
	}
	
	public JTable getTabla() {
		return resultadosTable;
	}
	
	public LugarDTO getLugarAt(int row) {
        if (model != null && row >= 0 && row < model.size()) {
            return model.get(row);
        }
        return null;
    }
	
	public void cancelarSeleccion() {
		this.lugarSeleccionado = null;
		this.dispose();
	}
	
	public void aceptarSeleccion() {
		if (lugarSeleccionado != null) {
			this.dispose();
		} else {
			// Aquí podrías mostrar un mensaje de error al usuario indicando que debe seleccionar un lugar antes de aceptar.
			JOptionPane.showMessageDialog(this, "Por favor, seleccione un lugar antes de aceptar.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
