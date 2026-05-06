package com.setlisto.ui.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;

import com.setlisto.mapper.EventoMusicalMapper;
import com.setlisto.model.Artista;
import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.model.GeneroMusical;
import com.setlisto.model.LugarDTO;
import com.setlisto.model.Organizador;
import com.setlisto.model.SubGeneroMusical;
import com.setlisto.model.SubGeneroMusicalDTO;
import com.setlisto.model.SubTipoEventoDTO;
import com.setlisto.model.ZonaHoraria;
import com.setlisto.service.SubGeneroMusicalService;
import com.setlisto.service.impl.SubGeneroMusicalServiceImpl;
import com.setlisto.ui.controller.AbrirLugarSelectController;
import com.setlisto.ui.controller.CancelarController;
import com.setlisto.ui.controller.EventoCreateController;
import com.setlisto.ui.controller.ListaSeleccionableController;
import com.setlisto.ui.controller.LlenarCombosCreateController;
import com.setlisto.ui.controller.RelacionGeneroSubgeneroController;
import com.setlisto.ui.filters.HorasDF;
import com.setlisto.ui.renderer.OrganizadorCBRenderer;
import com.setlisto.ui.renderer.SubtipoEventoCBRenderer;
import com.setlisto.ui.renderer.TipoEventoCBRenderer;
import com.setlisto.ui.renderer.ZonaHorariaCBRenderer;
import com.setlisto.ui.selectable.ItemSeleccionable;
import com.setlisto.ui.selectable.ListSeleccionableModel;
import com.toedter.calendar.JDateChooser;

/* TODO: Implementar JOptionPane para mostrar mensajes de error si la creacion del evento 
		 falla por alguna razon (validaciones, error en la base de datos, etc).
 */
public class EventoCreateView extends AbstractView {

	private static final long serialVersionUID = 1L;
	private JTextField nombreTF;
	private JDateChooser fechaInicioDC;
	private JTextArea descripcionTA;
	private JTextArea ubicacionTA;
	private JTextArea entradasTA;
	private JButton crearButton;
	private JDateChooser fechaFinDC;
	private JFormattedTextField horaInicioFTF;
	private JFormattedTextField horaFinFTF;
	private JLabel zonaHorariaLabel;
	private JComboBox zonaHorariaCB;
	private JButton limpiarButton;
	private final Border BORDE_ERROR = BorderFactory.createLineBorder(Color.RED, 1);
	private final Border BORDE_OK = new JTextField().getBorder(); // El borde estándar de Swing
	private JButton cancelarButton;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private JLabel generosLabel;
	private JComboBox generoCB;
	private JLabel subgenerosLabel;
	private JComboBox subgeneroCB;
	private JLabel lblNewLabel;
	private JComboBox tipoCB;
	private JLabel subtipoLabel;
	private JComboBox subtipoCB;
	private JLabel organizadorLabel;
	private JComboBox organizadorCB;
	private JLabel artistasLabel;
	private JButton elegirLugarButton;
	private JLabel lugarSeleccionadoLabel;
	private JFormattedTextField capacidadFTF;
	private JLabel capacidadLabel;
	private JButton configPlazasButton;
	
	
	private JList<ItemSeleccionable<SubGeneroMusical>> subgenerosList;
	private JList<ItemSeleccionable<GeneroMusical>> generosList;
	private JList<ItemSeleccionable<Artista>> artistasList;
	private LugarDTO lugarSeleccionado; // Para almacenar el lugar elegido desde LugarSelectView
	private List<EventoMusicalDTO> model;
	private ListSeleccionableModel<GeneroMusical> generosModel;
	private ListSeleccionableModel<SubGeneroMusical> subgenerosModel;
	private ListSeleccionableModel<Artista> artistasModel;
	private EventoMusicalMapper mapper;
	private RelacionGeneroSubgeneroController relacionGeneroSubgeneroController;
	private SubGeneroMusicalService subgeneroService;
	

	/**
	 * Create the panel.
	 */
	public EventoCreateView() {
		initialize();
		postInitialize();
		setName("Crear Evento Musical");
	}

	public void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { -82, 95, 95, 95, 0, 116, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 44, 0, 0, 38, 31, 19, 0, 26, 28, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 4;
		gbc_verticalStrut_1.gridy = 0;
		add(verticalStrut_1, gbc_verticalStrut_1);

		JLabel nombreLabel = new JLabel("Nombre del Evento");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 1;
		gbc_nombreLabel.gridy = 1;
		add(nombreLabel, gbc_nombreLabel);
		
		lblNewLabel = new JLabel("Tipo Evento");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		subtipoLabel = new JLabel("Subtipo Evento");
		GridBagConstraints gbc_subtipoLabel = new GridBagConstraints();
		gbc_subtipoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_subtipoLabel.gridx = 3;
		gbc_subtipoLabel.gridy = 1;
		add(subtipoLabel, gbc_subtipoLabel);
		
		organizadorLabel = new JLabel("Organizador");
		GridBagConstraints gbc_organizadorLabel = new GridBagConstraints();
		gbc_organizadorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_organizadorLabel.gridx = 4;
		gbc_organizadorLabel.gridy = 1;
		add(organizadorLabel, gbc_organizadorLabel);
		
		lugarSeleccionadoLabel = new JLabel("(sin lugar seleccionado)");
		GridBagConstraints gbc_lugarSeleccionadoLabel = new GridBagConstraints();
		gbc_lugarSeleccionadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lugarSeleccionadoLabel.gridx = 5;
		gbc_lugarSeleccionadoLabel.gridy = 1;
		add(lugarSeleccionadoLabel, gbc_lugarSeleccionadoLabel);
		
		capacidadLabel = new JLabel("Capacidad");
		GridBagConstraints gbc_capacidadLabel = new GridBagConstraints();
		gbc_capacidadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capacidadLabel.gridx = 6;
		gbc_capacidadLabel.gridy = 1;
		add(capacidadLabel, gbc_capacidadLabel);

		nombreTF = new JTextField();
		nombreTF.setColumns(10);
		GridBagConstraints gbc_nombreTF = new GridBagConstraints();
		gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTF.gridx = 1;
		gbc_nombreTF.gridy = 2;
		add(nombreTF, gbc_nombreTF);
		
		tipoCB = new JComboBox();
		GridBagConstraints gbc_tipoCB = new GridBagConstraints();
		gbc_tipoCB.insets = new Insets(0, 0, 5, 5);
		gbc_tipoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoCB.gridx = 2;
		gbc_tipoCB.gridy = 2;
		add(tipoCB, gbc_tipoCB);
		
		subtipoCB = new JComboBox();
		GridBagConstraints gbc_subtipoCB = new GridBagConstraints();
		gbc_subtipoCB.insets = new Insets(0, 0, 5, 5);
		gbc_subtipoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_subtipoCB.gridx = 3;
		gbc_subtipoCB.gridy = 2;
		add(subtipoCB, gbc_subtipoCB);
		
		organizadorCB = new JComboBox();
		GridBagConstraints gbc_organizadorCB = new GridBagConstraints();
		gbc_organizadorCB.insets = new Insets(0, 0, 5, 5);
		gbc_organizadorCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_organizadorCB.gridx = 4;
		gbc_organizadorCB.gridy = 2;
		add(organizadorCB, gbc_organizadorCB);
		
		elegirLugarButton = new JButton("Elegir Lugar");
		GridBagConstraints gbc_elegirLugarButton = new GridBagConstraints();
		gbc_elegirLugarButton.insets = new Insets(0, 0, 5, 5);
		gbc_elegirLugarButton.gridx = 5;
		gbc_elegirLugarButton.gridy = 2;
		add(elegirLugarButton, gbc_elegirLugarButton);
		
		capacidadFTF = new JFormattedTextField();
		GridBagConstraints gbc_capacidadFTF = new GridBagConstraints();
		gbc_capacidadFTF.insets = new Insets(0, 0, 5, 5);
		gbc_capacidadFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_capacidadFTF.gridx = 6;
		gbc_capacidadFTF.gridy = 2;
		add(capacidadFTF, gbc_capacidadFTF);
		
		configPlazasButton = new JButton("Configurar Plazas");
		GridBagConstraints gbc_configPlazasButton = new GridBagConstraints();
		gbc_configPlazasButton.insets = new Insets(0, 0, 5, 5);
		gbc_configPlazasButton.gridx = 7;
		gbc_configPlazasButton.gridy = 2;
		add(configPlazasButton, gbc_configPlazasButton);
		
		generosLabel = new JLabel("Generos Musicales");
		GridBagConstraints gbc_generosLabel = new GridBagConstraints();
		gbc_generosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generosLabel.gridx = 1;
		gbc_generosLabel.gridy = 4;
		add(generosLabel, gbc_generosLabel);
		
		subgenerosLabel = new JLabel("SubGeneros Musicales");
		GridBagConstraints gbc_subgenerosLabel = new GridBagConstraints();
		gbc_subgenerosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_subgenerosLabel.gridx = 2;
		gbc_subgenerosLabel.gridy = 4;
		add(subgenerosLabel, gbc_subgenerosLabel);
		
		artistasLabel = new JLabel("Artistas");
		GridBagConstraints gbc_artistasLabel = new GridBagConstraints();
		gbc_artistasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_artistasLabel.gridx = 3;
		gbc_artistasLabel.gridy = 4;
		add(artistasLabel, gbc_artistasLabel);
		
		generosList = new JList();
		GridBagConstraints gbc_generosList = new GridBagConstraints();
		gbc_generosList.insets = new Insets(0, 0, 5, 5);
		gbc_generosList.fill = GridBagConstraints.BOTH;
		gbc_generosList.gridx = 1;
		gbc_generosList.gridy = 5;
		add(generosList, gbc_generosList);
		
		subgenerosList = new JList();
		GridBagConstraints gbc_subgenerosList = new GridBagConstraints();
		gbc_subgenerosList.insets = new Insets(0, 0, 5, 5);
		gbc_subgenerosList.fill = GridBagConstraints.BOTH;
		gbc_subgenerosList.gridx = 2;
		gbc_subgenerosList.gridy = 5;
		add(subgenerosList, gbc_subgenerosList);
		
		artistasList = new JList();
		GridBagConstraints gbc_artistasList = new GridBagConstraints();
		gbc_artistasList.insets = new Insets(0, 0, 5, 5);
		gbc_artistasList.fill = GridBagConstraints.BOTH;
		gbc_artistasList.gridx = 3;
		gbc_artistasList.gridy = 5;
		add(artistasList, gbc_artistasList);
		
		generoCB = new JComboBox();
		GridBagConstraints gbc_generoCB = new GridBagConstraints();
		gbc_generoCB.insets = new Insets(0, 0, 5, 5);
		gbc_generoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoCB.gridx = 1;
		gbc_generoCB.gridy = 6;
		add(generoCB, gbc_generoCB);
		
		subgeneroCB = new JComboBox();
		GridBagConstraints gbc_subgeneroCB = new GridBagConstraints();
		gbc_subgeneroCB.insets = new Insets(0, 0, 5, 5);
		gbc_subgeneroCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_subgeneroCB.gridx = 2;
		gbc_subgeneroCB.gridy = 6;
		add(subgeneroCB, gbc_subgeneroCB);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 7;
		add(horizontalStrut, gbc_horizontalStrut);

		JLabel descripcionLabel = new JLabel("Descripción Breve");
		GridBagConstraints gbc_descripcionLabel = new GridBagConstraints();
		gbc_descripcionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionLabel.gridx = 1;
		gbc_descripcionLabel.gridy = 7;
		add(descripcionLabel, gbc_descripcionLabel);

		JLabel ubicacionLabel = new JLabel("Ubicación");
		GridBagConstraints gbc_ubicacionLabel = new GridBagConstraints();
		gbc_ubicacionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ubicacionLabel.gridx = 2;
		gbc_ubicacionLabel.gridy = 7;
		add(ubicacionLabel, gbc_ubicacionLabel);

		JLabel entradasLabel = new JLabel("Información Entradas");
		GridBagConstraints gbc_entradasLabel = new GridBagConstraints();
		gbc_entradasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_entradasLabel.gridx = 3;
		gbc_entradasLabel.gridy = 7;
		add(entradasLabel, gbc_entradasLabel);

		JLabel fechaHoraInicioLabel = new JLabel("Fecha y hora de inicio");
		GridBagConstraints gbc_fechaHoraInicioLabel = new GridBagConstraints();
		gbc_fechaHoraInicioLabel.gridwidth = 2;
		gbc_fechaHoraInicioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHoraInicioLabel.gridx = 4;
		gbc_fechaHoraInicioLabel.gridy = 7;
		add(fechaHoraInicioLabel, gbc_fechaHoraInicioLabel);

		zonaHorariaLabel = new JLabel("Zona Horaria del Evento");
		GridBagConstraints gbc_zonaHorariaLabel = new GridBagConstraints();
		gbc_zonaHorariaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaLabel.gridx = 6;
		gbc_zonaHorariaLabel.gridy = 7;
		add(zonaHorariaLabel, gbc_zonaHorariaLabel);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 7;
		gbc_horizontalStrut_1.gridy = 7;
		add(horizontalStrut_1, gbc_horizontalStrut_1);

		descripcionTA = new JTextArea();
		descripcionTA.setToolTipText("");
		GridBagConstraints gbc_descripcionTA = new GridBagConstraints();
		gbc_descripcionTA.gridheight = 3;
		gbc_descripcionTA.insets = new Insets(0, 0, 5, 5);
		gbc_descripcionTA.fill = GridBagConstraints.BOTH;
		gbc_descripcionTA.gridx = 1;
		gbc_descripcionTA.gridy = 8;
		add(descripcionTA, gbc_descripcionTA);

		ubicacionTA = new JTextArea();
		ubicacionTA.setToolTipText("");
		GridBagConstraints gbc_ubicacionTA = new GridBagConstraints();
		gbc_ubicacionTA.gridheight = 3;
		gbc_ubicacionTA.insets = new Insets(0, 0, 5, 5);
		gbc_ubicacionTA.fill = GridBagConstraints.BOTH;
		gbc_ubicacionTA.gridx = 2;
		gbc_ubicacionTA.gridy = 8;
		add(ubicacionTA, gbc_ubicacionTA);

		entradasTA = new JTextArea();
		GridBagConstraints gbc_entradasTA = new GridBagConstraints();
		gbc_entradasTA.gridheight = 3;
		gbc_entradasTA.insets = new Insets(0, 0, 5, 5);
		gbc_entradasTA.fill = GridBagConstraints.BOTH;
		gbc_entradasTA.gridx = 3;
		gbc_entradasTA.gridy = 8;
		add(entradasTA, gbc_entradasTA);

		fechaInicioDC = new JDateChooser();
		GridBagConstraints gbc_fechaInicioDC = new GridBagConstraints();
		gbc_fechaInicioDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioDC.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaInicioDC.gridx = 4;
		gbc_fechaInicioDC.gridy = 8;
		add(fechaInicioDC, gbc_fechaInicioDC);

		limpiarButton = new JButton("Limpiar Campos");
		limpiarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});

		horaInicioFTF = new JFormattedTextField();
		GridBagConstraints gbc_horaInicioFTF = new GridBagConstraints();
		gbc_horaInicioFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_horaInicioFTF.insets = new Insets(0, 0, 5, 5);
		gbc_horaInicioFTF.gridx = 5;
		gbc_horaInicioFTF.gridy = 8;
		add(horaInicioFTF, gbc_horaInicioFTF);

		zonaHorariaCB = new JComboBox();
		GridBagConstraints gbc_zonaHorariaCB = new GridBagConstraints();
		gbc_zonaHorariaCB.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_zonaHorariaCB.gridx = 6;
		gbc_zonaHorariaCB.gridy = 8;
		add(zonaHorariaCB, gbc_zonaHorariaCB);

		JLabel fechaHoraFinLabel = new JLabel("Fecha y hora de fin");
		GridBagConstraints gbc_fechaHoraFinLabel = new GridBagConstraints();
		gbc_fechaHoraFinLabel.anchor = GridBagConstraints.SOUTH;
		gbc_fechaHoraFinLabel.gridwidth = 2;
		gbc_fechaHoraFinLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHoraFinLabel.gridx = 4;
		gbc_fechaHoraFinLabel.gridy = 9;
		add(fechaHoraFinLabel, gbc_fechaHoraFinLabel);

		fechaFinDC = new JDateChooser();
		GridBagConstraints gbc_fechaFinDC = new GridBagConstraints();
		gbc_fechaFinDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaFinDC.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaFinDC.gridx = 4;
		gbc_fechaFinDC.gridy = 10;
		add(fechaFinDC, gbc_fechaFinDC);

		horaFinFTF = new JFormattedTextField();
		GridBagConstraints gbc_horaFinFTF = new GridBagConstraints();
		gbc_horaFinFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_horaFinFTF.insets = new Insets(0, 0, 5, 5);
		gbc_horaFinFTF.gridx = 5;
		gbc_horaFinFTF.gridy = 10;
		add(horaFinFTF, gbc_horaFinFTF);
		GridBagConstraints gbc_limpiarButton = new GridBagConstraints();
		gbc_limpiarButton.insets = new Insets(0, 0, 5, 5);
		gbc_limpiarButton.gridx = 1;
		gbc_limpiarButton.gridy = 12;
		add(limpiarButton, gbc_limpiarButton);

		cancelarButton = new JButton("New button");


		GridBagConstraints gbc_cancelarButton = new GridBagConstraints();
		gbc_cancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelarButton.gridx = 2;
		gbc_cancelarButton.gridy = 12;
		add(cancelarButton, gbc_cancelarButton);

		crearButton = new JButton("Crear");
		GridBagConstraints gbc_crearButton = new GridBagConstraints();
		gbc_crearButton.insets = new Insets(0, 0, 5, 5);
		gbc_crearButton.gridx = 6;
		gbc_crearButton.gridy = 12;
		add(crearButton, gbc_crearButton);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 4;
		gbc_verticalStrut.gridy = 13;
		add(verticalStrut, gbc_verticalStrut);

	}

	private void postInitialize() {
		// configurar campos de horaInicio y horaFin
		horaInicioFTF.setHorizontalAlignment(JTextField.CENTER);
		horaInicioFTF.setText("00 : 00"); // Valor inicial para que se vea el formato
		horaFinFTF.setHorizontalAlignment(JTextField.CENTER);
		horaFinFTF.setText("00 : 00");

		// Aplicar filtro
		((AbstractDocument) horaInicioFTF.getDocument()).setDocumentFilter(new HorasDF());
		((AbstractDocument) horaFinFTF.getDocument()).setDocumentFilter(new HorasDF());

		// Cambiar la fuente a una Monospaced (como Consolas)
		// Esto hace que los números siempre ocupen lo mismo y no "bailen" al escribir
		horaInicioFTF.setFont(new Font("Monospaced", Font.PLAIN, 14));
		horaFinFTF.setFont(new Font("Monospaced", Font.PLAIN, 14));

		setAllRenderers();

		setAllControllers();		
		
		mapper = new EventoMusicalMapper();
		subgeneroService = new SubGeneroMusicalServiceImpl();

	}

	public void limpiarCampos() {
		// Textos normales
		nombreTF.setText("");
		entradasTA.setText("");
		ubicacionTA.setText("");
		descripcionTA.setText("");
		capacidadFTF.setText("");

		// Horas (Resetear al formato visual inicial)
		horaInicioFTF.setText("00 : 00");
		horaFinFTF.setText("00 : 00");

		// Combos Principales (Solo resetear índice, NO borrar el modelo)
		generoCB.setSelectedIndex(0);
		tipoCB.setSelectedIndex(0);
		organizadorCB.setSelectedIndex(0);

		if (zonaHorariaCB.getItemCount() > 0) {
			zonaHorariaCB.setSelectedIndex(0);
		}

		// Combos Dependientes (Estos sí se limpian porque dependen de los de arriba)
		subgeneroCB.setModel(new DefaultComboBoxModel<>());
		subtipoCB.setModel(new DefaultComboBoxModel<>());

		// Calendarios
		fechaInicioDC.setDate(null);
		fechaFinDC.setDate(null);

		// Bordes
		nombreTF.setBorder(BORDE_OK);
		organizadorCB.setBorder(BORDE_OK);
	}

	public EventoMusicalDTO getEvento() {
	    if (!validarCampos()) {
	        return null; // No seguimos con la creación
	    }

	    EventoMusicalDTO em = new EventoMusicalDTO();
	    
	    // 1. Datos básicos
	    em.setNombre(nombreTF.getText().trim());
	    em.setDescripcion(descripcionTA.getText().trim());
	    em.setFechaInicio(combinarFechaHora(fechaInicioDC, horaInicioFTF));
	    em.setFechaFin(combinarFechaHora(fechaFinDC, horaFinFTF));
	    
	    // 2. Validación de Organizador
	    Organizador org = (Organizador) organizadorCB.getSelectedItem();
	    if (org != null && org.getId() != null) {
	        em.setIdOrganizador(org.getId());
	    }
	    
	    // 3. Lugar
	    LugarDTO lugSelect = getLugarSeleccionado();
	    if (lugSelect != null && lugSelect.getId() != null) {
	        em.setIdLugar(lugSelect.getId());
	    }
	    
	    // 4. Capacidad y Subtipo
	    String capText = capacidadFTF.getText().trim();
	    em.setCapacidad(capText.isEmpty() ? null : Integer.parseInt(capText));

	    SubTipoEventoDTO sub = (SubTipoEventoDTO) subtipoCB.getSelectedItem();
	    if (sub != null && sub.getId() != null) {
	        em.setIdSubtipo(sub.getId());
	    }
	    
	    // 5. Zona Horaria
	    ZonaHoraria zh = (ZonaHoraria) zonaHorariaCB.getSelectedItem();
	    if (zh != null && zh.getId() != null) {
	        em.setIdZonaHoraria(zh.getId());
	    }
	    
	    // 6. Sincronización de listas (UI -> DTO) usando el Mapper
	    // El mapper encapsula la extracción y asignación de Géneros, Subgéneros y Artistas
	    mapper.mapUItoDTO(em, generosModel, subgenerosModel, artistasModel);

	    return em;
	}

	/**
	 * Método auxiliar para fusionar JDateChooser y JFormattedTextField (HH : mm)
	 */
	private LocalDateTime combinarFechaHora(JDateChooser chooser, JFormattedTextField horaFTF) {
		Date date = chooser.getDate();
		if (date == null)
			return null;
		// 1. Convertir Date a LocalDate
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		// 2. Extraer hora y minutos del texto "HH : mm"
		// Eliminamos espacios y puntos para quedarnos con "HHmm"
		String cleanTime = horaFTF.getText().replace(" ", "").replace(":", "");
		int h = Integer.parseInt(cleanTime.substring(0, 2));
		int m = Integer.parseInt(cleanTime.substring(2, 4));

		// 3. Unir en LocalDateTime
		return LocalDateTime.of(fecha, LocalTime.of(h, m));
	}

	private void setAllRenderers() {
		zonaHorariaCB.setRenderer(new ZonaHorariaCBRenderer());
		organizadorCB.setRenderer(new OrganizadorCBRenderer());
		tipoCB.setRenderer(new TipoEventoCBRenderer());
		subtipoCB.setRenderer(new SubtipoEventoCBRenderer());
	}

	private void setAllControllers() {
		// Instancias el controlador de relación (que gestiona la lógica entre las dos listas)
		List<SubGeneroMusicalDTO> subgeneros = subgeneroService.findAll();
		relacionGeneroSubgeneroController = new RelacionGeneroSubgeneroController(generosModel, subgenerosModel, subgeneros);
		
		// Conecta la lista de géneros con su controlador y le pasa el de relación
		ListaSeleccionableController<GeneroMusical> listaGenerosController = new ListaSeleccionableController<GeneroMusical>(generosList, generosModel, relacionGeneroSubgeneroController);
		
		// Conectas la lista de subgéneros (esta no suele necesitar el controlador de relación como parámetro)
		ListaSeleccionableController<SubGeneroMusical> listaSubgenerosController = new ListaSeleccionableController<SubGeneroMusical>(subgenerosList, subgenerosModel, null);
		
		
		// Llenado de comboboxes con controller
		LlenarCombosCreateController llenado = new LlenarCombosCreateController(this);
		
		crearButton.setAction(new EventoCreateController(this));
		cancelarButton.setAction(new CancelarController(this));
		elegirLugarButton.setAction(new AbrirLugarSelectController(this));
		
		
		
	}
	// TODO terminar validacion para todos los componentes
	private boolean validarCampos() {
		boolean valido = true;

		// Validar Nombre
		if (nombreTF.getText().trim().isEmpty()) {
			nombreTF.setBorder(BORDE_ERROR);
			valido = false;
		} else {
			nombreTF.setBorder(BORDE_OK);
		}

		// Validar Organizador (Combo)
		if (organizadorCB.getSelectedIndex() <= 0) {
			organizadorCB.setBorder(BORDE_ERROR);
			valido = false;
		} else {
			organizadorCB.setBorder(BORDE_OK);
		}

		return valido;
	}
		
	public JComboBox getGeneroCB() {
		return generoCB;
	}
	
	public JComboBox getSubgeneroCB() {
		return subgeneroCB;
	}
	
	public JComboBox getTipoCB() {
		return tipoCB;
	}
	
	public JComboBox getSubtipoCB() {
		return subtipoCB;
	}
	
	public JComboBox getOrganizadorCB() {
		return organizadorCB;
	}
	
	public JComboBox getZonaHorariaCB() {
		return zonaHorariaCB;
	}
	
	public JLabel getLugarSeleccionadoLabel() {
		return lugarSeleccionadoLabel;
	}
	
	public void setLugarSeleccionado(LugarDTO lugar) {
		this.lugarSeleccionado = lugar;
		setLabelSeleccionado();
	}
	
	public void setLabelSeleccionado() {
		if (lugarSeleccionado != null) {
			lugarSeleccionadoLabel.setText(lugarSeleccionado.getNombre());
		} else {
			lugarSeleccionadoLabel.setText("(sin lugar seleccionado)");
		}
	}
	
	public LugarDTO getLugarSeleccionado() {
		return lugarSeleccionado;
	}
	
	public void setGenerosListModel(ListSeleccionableModel<GeneroMusical> model) {
		this.generosModel = model;
		generosList.setModel(model);
	}
	
	public void setSubgenerosListModel(ListSeleccionableModel<SubGeneroMusical> model) {
		this.subgenerosModel = model;
		subgenerosList.setModel(model);
	}
	
	public void setArtistasListModel(ListSeleccionableModel<Artista> model) {
		this.artistasModel = model;
		artistasList.setModel(model);
	}
}
