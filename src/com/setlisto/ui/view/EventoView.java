package com.setlisto.ui.view;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;

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
import javax.swing.text.AbstractDocument;

import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.ui.filters.HorasDF;
import com.setlisto.ui.filters.SoloNumerosDF;
import com.toedter.calendar.JDateChooser;

/**
 * Esta vista servira tanto para ver el detalle de un evento, com opara modificarlo. 
 * Es instanciada desde AbrirDetalleEMController llamado al hacer doble clic sobre la tabla de resultadosd de AdminEventoSearchView.
 * TODO construir la vista.
 */
public class EventoView extends AbstractView {


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
	private JList generosList;
	private JList subgenerosList;
	private JList artistasList;
	private JComboBox tipoCB;
	private JComboBox subtipoCB;
	private JLabel direccionLugarLabel;
	private JButton editarButton;
	private JButton reservarButton;
	private JLabel zonaHorariaRellenaLabel;
	private JButton lugarSelectButton;
	private JLabel entradasRestantesRellenaLabel;
	private JButton volverButton;
	private JLabel capacidadRellenaLabel;

	/**
	 * Create the panel.
	 */
	public EventoView() {
		initialize();
		postInitialize();
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
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
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
		
		zonaHorariaRellenaLabel = new JLabel("(tu zona horaria actual)");
		GridBagConstraints gbc_zonaHorariaRellenaLabel = new GridBagConstraints();
		gbc_zonaHorariaRellenaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaRellenaLabel.gridx = 4;
		gbc_zonaHorariaRellenaLabel.gridy = 6;
		informacionPanel.add(zonaHorariaRellenaLabel, gbc_zonaHorariaRellenaLabel);

		JLabel lugarLabel = new JLabel("Lugar");
		GridBagConstraints gbc_lugarLabel = new GridBagConstraints();
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
		
		lugarSelectButton = new JButton("(sin lugar seleccionado)");
		GridBagConstraints gbc_lugarSelectButton = new GridBagConstraints();
		gbc_lugarSelectButton.insets = new Insets(0, 0, 5, 5);
		gbc_lugarSelectButton.gridx = 3;
		gbc_lugarSelectButton.gridy = 8;
		informacionPanel.add(lugarSelectButton, gbc_lugarSelectButton);

		direccionLugarLabel = new JLabel("(sin direccion actual)");
		GridBagConstraints gbc_direccionLugarLabel = new GridBagConstraints();
		gbc_direccionLugarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_direccionLugarLabel.gridx = 4;
		gbc_direccionLugarLabel.gridy = 8;
		informacionPanel.add(direccionLugarLabel, gbc_direccionLugarLabel);

		JLabel capacidadLabel = new JLabel("Capacidad");
		GridBagConstraints gbc_capacidadLabel = new GridBagConstraints();
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
		
		capacidadRellenaLabel = new JLabel("(sin capacidad)");
		GridBagConstraints gbc_capacidadRellenaLabel = new GridBagConstraints();
		gbc_capacidadRellenaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capacidadRellenaLabel.gridx = 3;
		gbc_capacidadRellenaLabel.gridy = 10;
		informacionPanel.add(capacidadRellenaLabel, gbc_capacidadRellenaLabel);
		
		entradasRestantesRellenaLabel = new JLabel("(sin entradas restantes)");
		GridBagConstraints gbc_entradasRestantesRellenaLabel = new GridBagConstraints();
		gbc_entradasRestantesRellenaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_entradasRestantesRellenaLabel.gridx = 4;
		gbc_entradasRestantesRellenaLabel.gridy = 10;
		informacionPanel.add(entradasRestantesRellenaLabel, gbc_entradasRestantesRellenaLabel);

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
		add(southPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_southPanel = new GridBagLayout();
		gbl_southPanel.columnWidths = new int[]{0, 61, 0, 1, 73, 59, 0};
		gbl_southPanel.rowHeights = new int[]{21, 21, 0};
		gbl_southPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_southPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		southPanel.setLayout(gbl_southPanel);
						
						Component horizontalStrut_2 = Box.createHorizontalStrut(20);
						GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
						gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 5);
						gbc_horizontalStrut_2.gridx = 0;
						gbc_horizontalStrut_2.gridy = 0;
						southPanel.add(horizontalStrut_2, gbc_horizontalStrut_2);
				
						volverButton = new JButton("Volver");
						GridBagConstraints gbc_volverButton = new GridBagConstraints();
						gbc_volverButton.anchor = GridBagConstraints.NORTHWEST;
						gbc_volverButton.insets = new Insets(0, 0, 5, 5);
						gbc_volverButton.gridx = 1;
						gbc_volverButton.gridy = 0;
						southPanel.add(volverButton, gbc_volverButton);
				
						editarButton = new JButton("Editar");
						GridBagConstraints gbc_editarButton = new GridBagConstraints();
						gbc_editarButton.insets = new Insets(0, 0, 5, 5);
						gbc_editarButton.anchor = GridBagConstraints.NORTHWEST;
						gbc_editarButton.gridx = 2;
						gbc_editarButton.gridy = 0;
						southPanel.add(editarButton, gbc_editarButton);
				
				Component horizontalGlue = Box.createHorizontalGlue();
				GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
				gbc_horizontalGlue.anchor = GridBagConstraints.WEST;
				gbc_horizontalGlue.insets = new Insets(0, 0, 5, 5);
				gbc_horizontalGlue.gridx = 3;
				gbc_horizontalGlue.gridy = 0;
				southPanel.add(horizontalGlue, gbc_horizontalGlue);
		
				reservarButton = new JButton("Reservar");
				GridBagConstraints gbc_reservarButton = new GridBagConstraints();
				gbc_reservarButton.anchor = GridBagConstraints.NORTHWEST;
				gbc_reservarButton.insets = new Insets(0, 0, 5, 5);
				gbc_reservarButton.gridx = 4;
				gbc_reservarButton.gridy = 0;
				southPanel.add(reservarButton, gbc_reservarButton);
				
				Component verticalStrut = Box.createVerticalStrut(20);
				GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
				gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
				gbc_verticalStrut.gridx = 2;
				gbc_verticalStrut.gridy = 1;
				southPanel.add(verticalStrut, gbc_verticalStrut);
	}

	private void postInitialize () {
		setControllers();
		setFilters();
		setEditable(false); // Por defecto, la vista se muestra en modo lectura.
	}
	
	private void setControllers() {
//		editarButton.setAction(new AbrirEditarEventoController(this));
//		reservarButton.setAction(new AbrirReservarController(this));
	}

	public void setFilters() {
		// DocumentFilters para campos numéricos (capacidadFTF, entradasRestantesFTF) y de hora (horaInicioFTF, horaFinFTF)
		horaInicioFTF.setHorizontalAlignment(JTextField.CENTER);
		horaInicioFTF.setText("00 : 00"); // Valor inicial para que se vea el formato
		horaFinFTF.setHorizontalAlignment(JTextField.CENTER);
		horaFinFTF.setText("00 : 00");

		((AbstractDocument) horaInicioFTF.getDocument()).setDocumentFilter(new HorasDF());
		((AbstractDocument) horaFinFTF.getDocument()).setDocumentFilter(new HorasDF());

		// Esto hace que los números siempre ocupen lo mismo y no "bailen" al escribir
		horaInicioFTF.setFont(new Font("Monospaced", Font.PLAIN, 14));
		horaFinFTF.setFont(new Font("Monospaced", Font.PLAIN, 14));
	}

	public void setModel(EventoMusicalDTO em) {
		// Establecemos el nombre de la vista para que el Tab coja este título
		this.setName(em.getNombre());         
		nombreLabel.setText(em.getNombre());
		descripcionTA.setText(em.getDescripcion() != null ? em.getDescripcion() : "");
		horaInicioFTF.setText(em.getFechaInicio() != null ? String.format("%02d : %02d", em.getFechaInicio().getHour(), em.getFechaInicio().getMinute()) : "00 : 00");
		horaFinFTF.setText(em.getFechaFin() != null ? String.format("%02d : %02d", em.getFechaFin().getHour(), em.getFechaFin().getMinute()) : "00 : 00");
		fechaInicioDC.setDate(em.getFechaInicio() != null ? Date.valueOf(em.getFechaInicio().toLocalDate()) : null);
		fechaFinDC.setDate(em.getFechaFin() != null ? Date.valueOf(em.getFechaFin().toLocalDate()) : null);
		capacidadRellenaLabel.setText(em.getCapacidad() != null ? em.getCapacidad().toString() : "0");
		entradasRestantesRellenaLabel.setText(em.getEntradasRestantes() != null ? em.getEntradasRestantes().toString() : "0");
		direccionLugarLabel.setText(em.getLugarDireccion() != null ? em.getLugarDireccion() : "Sin dirección");

		// Aquí deberías setear los índices de los ComboBoxes (organizadorCB, estadoCB, lugarCB) TODO 
		// recorriendo sus modelos y buscando el ID que coincida con el del DTO.
	}

	public void setEditable(boolean editable) {
		// Cambiamos el estado de los componentes según si estamos en modo lectura o edición
		descripcionTA.setEditable(editable);
		organizadorCB.setEnabled(editable);
		estadoCB.setEnabled(editable);
		fechaInicioDC.setEnabled(editable);
		horaInicioFTF.setEditable(editable);
		fechaFinDC.setEnabled(editable);
		horaFinFTF.setEditable(editable);
		zonaHorariaEventoCB.setEnabled(editable);
		tipoCB.setEnabled(editable);
		subtipoCB.setEnabled(editable);
		generosList.setEnabled(editable);
		subgenerosList.setEnabled(editable);
		artistasList.setEnabled(editable);
		lugarSelectButton.setEnabled(editable);
		
	}

	// Cambia el boton editarButton por guardarButton
	public void setGuardarController() {
//		editarButton.setAction(new EventoGuardarCambiosController(this));
	}

	// Cambia guardarButton por editarButton 
	public void setEditarController() {
//		editarButton.setAction(new EditarEventoController(this));
	}

	// Cambia el boton volverButton por cancelarButton
	public void setCancellarController() {
//		volverButton.setAction(new EventoCancelarCambiosController(this));
	}

	// Cambia cancelarButton por volverButton
	public void setReservarController() {
//		volverButton.setAction(new VolverController(this));
	}
}
