package com.setlisto.ui.view;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.setlisto.model.EventoMusicalDTO;
import com.toedter.calendar.JDateChooser;

/**
 * Esta vista servira tanto para ver el detalle de un evento, com opara modificarlo. 
 * Es instanciada desde AbrirDetalleEMController llamado al hacer doble clic sobre la tabla de resultadosd de AdminEventoSearchView.
 * TODO construir la vista.
 */
public class EventoView extends AbstractView {
	
	private static EventoView instance = null;
	
	private static final long serialVersionUID = 1L;
	private JLabel nombreLabel;
	private JComboBox organizadorCB;
	private JComboBox estadoCB;
	private JTextArea descripcionTA;
	private JDateChooser fechaInicioDC;
	private JFormattedTextField horaInicioFTF;
	private JDateChooser fechaFinDC;
	private JFormattedTextField horaFinFTF;
	private JComboBox zonaHorariaEventoCB;
	private JComboBox zonaHorariaActualCB;
	private JComboBox lugarCB;
	private JFormattedTextField capacidadFTF;
	private JFormattedTextField entradasRestantesFTF;
	private JList generosList;
	private JList subgenerosList;
	private JList artistasList;
	private JComboBox tipoCB;
	private JComboBox subtipoCB;
	private JLabel direccionLugarLabel;

	/**
	 * Create the panel.
	 */
	private EventoView() {
		initialize();
		postInitialize();
	}
	
	public static EventoView getInstance() {
		if (instance == null) {
			instance = new EventoView();
		}
		return instance;
	}
	
	private void initialize () {
		setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		FlowLayout fl_headerPanel = (FlowLayout) headerPanel.getLayout();
		fl_headerPanel.setAlignment(FlowLayout.RIGHT);
		centerPanel.add(headerPanel, BorderLayout.NORTH);
		
		JLabel imagenPortadaLabel = new JLabel("IMAGEN");
		headerPanel.add(imagenPortadaLabel);
		
		JPanel nombrePanel = new JPanel();
		headerPanel.add(nombrePanel);
		nombrePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel nombreOrganizadorPanel = new JPanel();
		nombrePanel.add(nombreOrganizadorPanel, BorderLayout.NORTH);
		nombreOrganizadorPanel.setLayout(new BoxLayout(nombreOrganizadorPanel, BoxLayout.Y_AXIS));
		
		Box nombreHB = Box.createHorizontalBox();
		nombreOrganizadorPanel.add(nombreHB);
		
		nombreLabel = new JLabel("Nombre del Evento");
		nombreHB.add(nombreLabel);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		nombreHB.add(horizontalGlue_3);
		
		Box organizadorHB = Box.createHorizontalBox();
		nombreOrganizadorPanel.add(organizadorHB);
		
		organizadorCB = new JComboBox();
		organizadorHB.add(organizadorCB);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		organizadorHB.add(horizontalGlue_4);
		
		Box labelsHB = Box.createHorizontalBox();
		nombreOrganizadorPanel.add(labelsHB);
		
		estadoCB = new JComboBox();
		labelsHB.add(estadoCB);
		
		JPanel descripcionPanel = new JPanel();
		nombrePanel.add(descripcionPanel, BorderLayout.SOUTH);
		
		descripcionTA = new JTextArea();
		descripcionTA.setText("Esta es la descripcion del evento");
		descripcionTA.setEditable(false);
		descripcionPanel.add(descripcionTA);
		
		JPanel informacionPanel = new JPanel();
		centerPanel.add(informacionPanel, BorderLayout.CENTER);
		GridBagLayout gbl_informacionPanel = new GridBagLayout();
		gbl_informacionPanel.columnWidths = new int[]{0, 0, 0, 89, 0, 0, 0, 0, 0};
		gbl_informacionPanel.rowHeights = new int[]{11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0};
		gbl_informacionPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_informacionPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		informacionPanel.setLayout(gbl_informacionPanel);
		
		JLabel informacionLabel = new JLabel("Informacion del evento");
		GridBagConstraints gbc_informacionLabel = new GridBagConstraints();
		gbc_informacionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_informacionLabel.anchor = GridBagConstraints.WEST;
		gbc_informacionLabel.gridx = 3;
		gbc_informacionLabel.gridy = 0;
		informacionPanel.add(informacionLabel, gbc_informacionLabel);
		
		JLabel fechaHoraInicioLabel = new JLabel("Fecha y hora de inicio");
		GridBagConstraints gbc_fechaHoraInicioLabel = new GridBagConstraints();
		gbc_fechaHoraInicioLabel.gridwidth = 2;
		gbc_fechaHoraInicioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHoraInicioLabel.gridx = 3;
		gbc_fechaHoraInicioLabel.gridy = 1;
		informacionPanel.add(fechaHoraInicioLabel, gbc_fechaHoraInicioLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridwidth = 3;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 2;
		informacionPanel.add(horizontalStrut, gbc_horizontalStrut);
		
		fechaInicioDC = new JDateChooser();
		GridBagConstraints gbc_fechaInicioDC = new GridBagConstraints();
		gbc_fechaInicioDC.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaInicioDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioDC.gridx = 3;
		gbc_fechaInicioDC.gridy = 2;
		informacionPanel.add(fechaInicioDC, gbc_fechaInicioDC);
		
		horaInicioFTF = new JFormattedTextField();
		horaInicioFTF.setText("00 : 00");
		horaInicioFTF.setHorizontalAlignment(SwingConstants.CENTER);
		horaInicioFTF.setFont(new Font("Monospaced", Font.PLAIN, 14));
		GridBagConstraints gbc_horaInicioFTF = new GridBagConstraints();
		gbc_horaInicioFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_horaInicioFTF.insets = new Insets(0, 0, 5, 5);
		gbc_horaInicioFTF.gridx = 4;
		gbc_horaInicioFTF.gridy = 2;
		informacionPanel.add(horaInicioFTF, gbc_horaInicioFTF);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.gridwidth = 3;
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 5;
		gbc_horizontalStrut_1.gridy = 2;
		informacionPanel.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		JLabel fechaHoraFinLabel = new JLabel("Fecha y hora de fin");
		GridBagConstraints gbc_fechaHoraFinLabel = new GridBagConstraints();
		gbc_fechaHoraFinLabel.gridwidth = 2;
		gbc_fechaHoraFinLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHoraFinLabel.gridx = 3;
		gbc_fechaHoraFinLabel.gridy = 3;
		informacionPanel.add(fechaHoraFinLabel, gbc_fechaHoraFinLabel);
		
		fechaFinDC = new JDateChooser();
		GridBagConstraints gbc_fechaFinDC = new GridBagConstraints();
		gbc_fechaFinDC.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaFinDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaFinDC.gridx = 3;
		gbc_fechaFinDC.gridy = 4;
		informacionPanel.add(fechaFinDC, gbc_fechaFinDC);
		
		horaFinFTF = new JFormattedTextField();
		horaFinFTF.setText("00 : 00");
		horaFinFTF.setHorizontalAlignment(SwingConstants.CENTER);
		horaFinFTF.setFont(new Font("Monospaced", Font.PLAIN, 14));
		GridBagConstraints gbc_horaFinFTF = new GridBagConstraints();
		gbc_horaFinFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_horaFinFTF.insets = new Insets(0, 0, 5, 5);
		gbc_horaFinFTF.gridx = 4;
		gbc_horaFinFTF.gridy = 4;
		informacionPanel.add(horaFinFTF, gbc_horaFinFTF);
		
		JLabel zonaHorariaLabel = new JLabel(" Zona horaria del evento");
		GridBagConstraints gbc_zonaHorariaLabel = new GridBagConstraints();
		gbc_zonaHorariaLabel.anchor = GridBagConstraints.WEST;
		gbc_zonaHorariaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaLabel.gridx = 3;
		gbc_zonaHorariaLabel.gridy = 5;
		informacionPanel.add(zonaHorariaLabel, gbc_zonaHorariaLabel);
		
		JLabel zonaHorariaActualLabel = new JLabel("Tu zona horaria actual");
		GridBagConstraints gbc_zonaHorariaActualLabel = new GridBagConstraints();
		gbc_zonaHorariaActualLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaActualLabel.gridx = 4;
		gbc_zonaHorariaActualLabel.gridy = 5;
		informacionPanel.add(zonaHorariaActualLabel, gbc_zonaHorariaActualLabel);
		
		zonaHorariaEventoCB = new JComboBox();
		GridBagConstraints gbc_zonaHorariaEventoCB = new GridBagConstraints();
		gbc_zonaHorariaEventoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_zonaHorariaEventoCB.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaEventoCB.gridx = 3;
		gbc_zonaHorariaEventoCB.gridy = 6;
		informacionPanel.add(zonaHorariaEventoCB, gbc_zonaHorariaEventoCB);
		
		zonaHorariaActualCB = new JComboBox();
		GridBagConstraints gbc_zonaHorariaActualCB = new GridBagConstraints();
		gbc_zonaHorariaActualCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_zonaHorariaActualCB.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaActualCB.gridx = 4;
		gbc_zonaHorariaActualCB.gridy = 6;
		informacionPanel.add(zonaHorariaActualCB, gbc_zonaHorariaActualCB);
		
		JLabel lugarLabel = new JLabel("Lugar");
		GridBagConstraints gbc_lugarLabel = new GridBagConstraints();
		gbc_lugarLabel.anchor = GridBagConstraints.WEST;
		gbc_lugarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lugarLabel.gridx = 3;
		gbc_lugarLabel.gridy = 7;
		informacionPanel.add(lugarLabel, gbc_lugarLabel);
		
		JLabel direccionLabel = new JLabel("Direccion");
		GridBagConstraints gbc_direccionLabel = new GridBagConstraints();
		gbc_direccionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_direccionLabel.gridx = 4;
		gbc_direccionLabel.gridy = 7;
		informacionPanel.add(direccionLabel, gbc_direccionLabel);
		
		lugarCB = new JComboBox();
		GridBagConstraints gbc_lugarCB = new GridBagConstraints();
		gbc_lugarCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_lugarCB.insets = new Insets(0, 0, 5, 5);
		gbc_lugarCB.gridx = 3;
		gbc_lugarCB.gridy = 8;
		informacionPanel.add(lugarCB, gbc_lugarCB);
		
		direccionLugarLabel = new JLabel("avenida test #2");
		GridBagConstraints gbc_direccionLugarLabel = new GridBagConstraints();
		gbc_direccionLugarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_direccionLugarLabel.gridx = 4;
		gbc_direccionLugarLabel.gridy = 8;
		informacionPanel.add(direccionLugarLabel, gbc_direccionLugarLabel);
		
		JLabel capacidadLabel = new JLabel("Capacidad");
		GridBagConstraints gbc_capacidadLabel = new GridBagConstraints();
		gbc_capacidadLabel.anchor = GridBagConstraints.WEST;
		gbc_capacidadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capacidadLabel.gridx = 3;
		gbc_capacidadLabel.gridy = 9;
		informacionPanel.add(capacidadLabel, gbc_capacidadLabel);
		
		JLabel entradasRestantesLabel = new JLabel("Entradas Restantes");
		GridBagConstraints gbc_entradasRestantesLabel = new GridBagConstraints();
		gbc_entradasRestantesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_entradasRestantesLabel.gridx = 4;
		gbc_entradasRestantesLabel.gridy = 9;
		informacionPanel.add(entradasRestantesLabel, gbc_entradasRestantesLabel);
		
		capacidadFTF = new JFormattedTextField();
		GridBagConstraints gbc_capacidadFTF = new GridBagConstraints();
		gbc_capacidadFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_capacidadFTF.insets = new Insets(0, 0, 5, 5);
		gbc_capacidadFTF.gridx = 3;
		gbc_capacidadFTF.gridy = 10;
		informacionPanel.add(capacidadFTF, gbc_capacidadFTF);
		
		entradasRestantesFTF = new JFormattedTextField();
		GridBagConstraints gbc_entradasRestantesFTF = new GridBagConstraints();
		gbc_entradasRestantesFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_entradasRestantesFTF.insets = new Insets(0, 0, 5, 5);
		gbc_entradasRestantesFTF.gridx = 4;
		gbc_entradasRestantesFTF.gridy = 10;
		informacionPanel.add(entradasRestantesFTF, gbc_entradasRestantesFTF);
		
		JLabel categoriaLabel = new JLabel("Categoria del evento");
		GridBagConstraints gbc_categoriaLabel = new GridBagConstraints();
		gbc_categoriaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_categoriaLabel.gridx = 3;
		gbc_categoriaLabel.gridy = 11;
		informacionPanel.add(categoriaLabel, gbc_categoriaLabel);
		
		JLabel subcategoriaLabel = new JLabel("SubCategoria");
		GridBagConstraints gbc_subcategoriaLabel = new GridBagConstraints();
		gbc_subcategoriaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_subcategoriaLabel.gridx = 4;
		gbc_subcategoriaLabel.gridy = 11;
		informacionPanel.add(subcategoriaLabel, gbc_subcategoriaLabel);
		
		tipoCB = new JComboBox();
		GridBagConstraints gbc_tipoCB = new GridBagConstraints();
		gbc_tipoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoCB.insets = new Insets(0, 0, 5, 5);
		gbc_tipoCB.gridx = 3;
		gbc_tipoCB.gridy = 12;
		informacionPanel.add(tipoCB, gbc_tipoCB);
		
		subtipoCB = new JComboBox();
		GridBagConstraints gbc_subtipoCB = new GridBagConstraints();
		gbc_subtipoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_subtipoCB.insets = new Insets(0, 0, 5, 5);
		gbc_subtipoCB.gridx = 4;
		gbc_subtipoCB.gridy = 12;
		informacionPanel.add(subtipoCB, gbc_subtipoCB);
		
		JLabel generosLabel = new JLabel("Generos musicales");
		GridBagConstraints gbc_generosLabel = new GridBagConstraints();
		gbc_generosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generosLabel.gridx = 3;
		gbc_generosLabel.gridy = 13;
		informacionPanel.add(generosLabel, gbc_generosLabel);
		
		JLabel otrasEtiquetasLabel = new JLabel("Subgeneros Musicales");
		GridBagConstraints gbc_otrasEtiquetasLabel = new GridBagConstraints();
		gbc_otrasEtiquetasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_otrasEtiquetasLabel.gridx = 4;
		gbc_otrasEtiquetasLabel.gridy = 13;
		informacionPanel.add(otrasEtiquetasLabel, gbc_otrasEtiquetasLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 14;
		informacionPanel.add(scrollPane, gbc_scrollPane);
		
		generosList = new JList();
		scrollPane.setViewportView(generosList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 4;
		gbc_scrollPane_1.gridy = 14;
		informacionPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		subgenerosList = new JList();
		scrollPane_1.setViewportView(subgenerosList);
		
		JLabel artistasLabel = new JLabel("Artistas presentes");
		GridBagConstraints gbc_artistasLabel = new GridBagConstraints();
		gbc_artistasLabel.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_artistasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_artistasLabel.gridx = 3;
		gbc_artistasLabel.gridy = 15;
		informacionPanel.add(artistasLabel, gbc_artistasLabel);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 3;
		gbc_scrollPane_2.gridy = 16;
		informacionPanel.add(scrollPane_2, gbc_scrollPane_2);
		
		artistasList = new JList();
		scrollPane_2.setViewportView(artistasList);
		
		JPanel rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JLabel fotosLabel = new JLabel("Fotos del lugar");
		rightPanel.add(fotosLabel);
		
		JLabel imagenSeleccionadaLabel = new JLabel("IMAGEN");
		rightPanel.add(imagenSeleccionadaLabel);
		
		JPanel southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setAlignment(FlowLayout.LEFT);
		add(southPanel, BorderLayout.SOUTH);
		
		JButton volverButton = new JButton("Volver");
		southPanel.add(volverButton);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		southPanel.add(horizontalGlue);
		
		JButton reservarButton = new JButton("Reservar");
		southPanel.add(reservarButton);
	}
	
	private void postInitialize () {
	}
	
	public void setModel (EventoMusicalDTO em) {
		nombreLabel.setText(em.getNombre());
		
		
	}
	
	public void setEditable (boolean editable) {
		
	}
	
	public void setGuardarController () {
		
	}
	
	public void setCancellarController () {
		
	}
}
