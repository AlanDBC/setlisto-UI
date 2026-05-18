package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import org.apache.commons.lang3.StringUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.setlisto.criteria.EventoMusicalCriteria;
import com.setlisto.model.Ciudad;
import com.setlisto.model.EstadoEvento;
import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.model.GeneroMusical;
import com.setlisto.model.Pais;
import com.setlisto.model.SubGeneroMusicalDTO;
import com.setlisto.model.SubTipoEventoDTO;
import com.setlisto.model.TipoEvento;
import com.setlisto.model.ZonaHoraria;
import com.setlisto.model.Region;
import com.setlisto.service.CiudadService;
import com.setlisto.service.EventoMusicalService;
import com.setlisto.service.GeneroMusicalService;
import com.setlisto.service.PaisService;
import com.setlisto.service.RegionService;
import com.setlisto.service.SubGeneroMusicalService;
import com.setlisto.service.TipoEventoService;
import com.setlisto.service.ZonaHorariaService;
import com.setlisto.service.impl.EstadoEventoServiceImpl;
import com.setlisto.service.impl.SubTipoEventoServiceImpl;
import com.setlisto.ui.controller.AbrirDetalleEMController;
import com.setlisto.ui.controller.EventoSearchController;
import com.setlisto.ui.controller.LlenarCombosSearchController;
import com.setlisto.ui.filters.SoloNumerosDF;
import com.setlisto.ui.model.EventoTableModel;
import com.setlisto.ui.renderer.CiudadCBRenderer;
import com.setlisto.ui.renderer.EstadoCBRenderer;
import com.setlisto.ui.renderer.EventoTableRenderer;
import com.setlisto.ui.renderer.GeneroCBRenderer;
import com.setlisto.ui.renderer.PaisCBRenderer;
import com.setlisto.ui.renderer.RegionCBRenderer;
import com.setlisto.ui.renderer.SubgeneroCBRenderer;
import com.setlisto.ui.renderer.SubtipoEventoCBRenderer;
import com.setlisto.ui.renderer.TipoEventoCBRenderer;
import com.setlisto.ui.renderer.ZonaHorariaCBRenderer;
import com.toedter.calendar.JDateChooser;

public class AdminEventoSearchView extends AbstractView {

	private static final long serialVersionUID = 1L;
	private JTextField nombreTF;
	private JTextField artistaTF;
	private JTextField organizadorTF;
	private JTextField lugarTF;
	private JFormattedTextField idEventoFTF;
	private JFormattedTextField precioDesdeFTF;
	private JFormattedTextField precioHastaFTF;
	private JFormattedTextField capacidadDesdeFTF;
	private JFormattedTextField capacidadHastaFTF;
	private JFormattedTextField idArtistaFTF;
	private JFormattedTextField idOrganizadorFTF;
	private JFormattedTextField idLugarFTF;
	private JComboBox paisCB;
	private JComboBox regionCB;
	private JComboBox ciudadCB;
	private JComboBox generoCB;
	private JComboBox subgeneroCB;
	private JComboBox tipoEventoCB; 
	private JComboBox subtipoCB;
	private PaisService paisService;
	private RegionService regionService;
	private CiudadService ciudadService;
	private EventoMusicalService eventoMusicalService;
	private GeneroMusicalService generoMusicalService;
	private TipoEventoService tipoEventoService;
	private SubGeneroMusicalService subGeneroMusicalService;
	private SubTipoEventoServiceImpl subTipoEventoService;
	private JDateChooser fechaHastaDC;
	private JDateChooser fechaDesdeDC;
	private JComboBox estadoCB;
	private EstadoEventoServiceImpl estadoEventoService;
	private List<EventoMusicalDTO> model;
	private JLabel totalResultadosLabel;
	private JButton buscarButton;
	private JLabel zonaHorariaLabel;
	private JComboBox zonaHorariaCB;
	private ZonaHorariaService zonaHorariaService;
	private JPanel centerPanel;
	private JTable resultadosTable;
	private Component horizontalStrut;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component horizontalStrut_1;
	private JPanel tableRightPanel;
	private JPanel leftTablePanel;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;

	/**
	 * Create the panel.
	 */
	public AdminEventoSearchView() {
//		initServices(); 
		initialize();
		postInitialize();
		setName("Buscar Eventos Musicales");
	}

	private void initialize() {

		setLayout(new BorderLayout(0, 0));

		JPanel busquedaPanel = new JPanel();
		add(busquedaPanel, BorderLayout.NORTH);
		GridBagLayout gbl_busquedaPanel = new GridBagLayout();
		gbl_busquedaPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 0, 0, 0};
		gbl_busquedaPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_busquedaPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_busquedaPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		busquedaPanel.setLayout(gbl_busquedaPanel);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 7;
		gbc_verticalStrut_1.gridy = 0;
		busquedaPanel.add(verticalStrut_1, gbc_verticalStrut_1);

		JLabel idEventoLabel = new JLabel("ID del Evento");
		GridBagConstraints gbc_idEventoLabel = new GridBagConstraints();
		gbc_idEventoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idEventoLabel.gridx = 1;
		gbc_idEventoLabel.gridy = 1;
		busquedaPanel.add(idEventoLabel, gbc_idEventoLabel);

		JLabel eventoLabel = new JLabel("Evento");
		GridBagConstraints gbc_eventoLabel = new GridBagConstraints();
		gbc_eventoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_eventoLabel.gridx = 2;
		gbc_eventoLabel.gridy = 1;
		busquedaPanel.add(eventoLabel, gbc_eventoLabel);

		JLabel fechaDesdeLabel = new JLabel("Fecha Desde");
		GridBagConstraints gbc_fechaDesdeLabel = new GridBagConstraints();
		gbc_fechaDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDesdeLabel.gridx = 4;
		gbc_fechaDesdeLabel.gridy = 1;
		busquedaPanel.add(fechaDesdeLabel, gbc_fechaDesdeLabel);

		JLabel fechaHastaLabel = new JLabel("Fecha Hasta");
		GridBagConstraints gbc_fechaHastaLabel = new GridBagConstraints();
		gbc_fechaHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHastaLabel.gridx = 5;
		gbc_fechaHastaLabel.gridy = 1;
		busquedaPanel.add(fechaHastaLabel, gbc_fechaHastaLabel);

		JLabel precioDesdeLabel = new JLabel("Precio Desde");
		GridBagConstraints gbc_precioDesdeLabel = new GridBagConstraints();
		gbc_precioDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_precioDesdeLabel.gridx = 7;
		gbc_precioDesdeLabel.gridy = 1;
		busquedaPanel.add(precioDesdeLabel, gbc_precioDesdeLabel);

		JLabel precioHastaLabel = new JLabel("Precio Hasta");
		GridBagConstraints gbc_precioHastaLabel = new GridBagConstraints();
		gbc_precioHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_precioHastaLabel.gridx = 8;
		gbc_precioHastaLabel.gridy = 1;
		busquedaPanel.add(precioHastaLabel, gbc_precioHastaLabel);

		JLabel capacidadDesdeLabel = new JLabel("Capacidad Desde");
		GridBagConstraints gbc_capacidadDesdeLabel = new GridBagConstraints();
		gbc_capacidadDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capacidadDesdeLabel.gridx = 10;
		gbc_capacidadDesdeLabel.gridy = 1;
		busquedaPanel.add(capacidadDesdeLabel, gbc_capacidadDesdeLabel);

		JLabel capacidadHastaLabel = new JLabel("Capacidad Hasta");
		GridBagConstraints gbc_capacidadHastaLabel = new GridBagConstraints();
		gbc_capacidadHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_capacidadHastaLabel.gridx = 11;
		gbc_capacidadHastaLabel.gridy = 1;
		busquedaPanel.add(capacidadHastaLabel, gbc_capacidadHastaLabel);

		idEventoFTF = new JFormattedTextField();
		GridBagConstraints gbc_idEventoFTF = new GridBagConstraints();
		gbc_idEventoFTF.insets = new Insets(0, 0, 5, 5);
		gbc_idEventoFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_idEventoFTF.gridx = 1;
		gbc_idEventoFTF.gridy = 2;
		busquedaPanel.add(idEventoFTF, gbc_idEventoFTF);

		nombreTF = new JTextField();
		nombreTF.setColumns(10);
		GridBagConstraints gbc_nombreTF = new GridBagConstraints();
		gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTF.gridx = 2;
		gbc_nombreTF.gridy = 2;
		busquedaPanel.add(nombreTF, gbc_nombreTF);

		fechaDesdeDC = new JDateChooser();
		GridBagConstraints gbc_fechaDesdeDC = new GridBagConstraints();
		gbc_fechaDesdeDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDesdeDC.fill = GridBagConstraints.BOTH;
		gbc_fechaDesdeDC.gridx = 4;
		gbc_fechaDesdeDC.gridy = 2;
		busquedaPanel.add(fechaDesdeDC, gbc_fechaDesdeDC);

		fechaHastaDC = new JDateChooser();
		GridBagConstraints gbc_fechaHastaDC = new GridBagConstraints();
		gbc_fechaHastaDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHastaDC.fill = GridBagConstraints.BOTH;
		gbc_fechaHastaDC.gridx = 5;
		gbc_fechaHastaDC.gridy = 2;
		busquedaPanel.add(fechaHastaDC, gbc_fechaHastaDC);

		precioDesdeFTF = new JFormattedTextField();
		GridBagConstraints gbc_precioDesdeFTF = new GridBagConstraints();
		gbc_precioDesdeFTF.insets = new Insets(0, 0, 5, 5);
		gbc_precioDesdeFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioDesdeFTF.gridx = 7;
		gbc_precioDesdeFTF.gridy = 2;
		busquedaPanel.add(precioDesdeFTF, gbc_precioDesdeFTF);

		precioHastaFTF = new JFormattedTextField();
		GridBagConstraints gbc_precioHastaFTF = new GridBagConstraints();
		gbc_precioHastaFTF.insets = new Insets(0, 0, 5, 5);
		gbc_precioHastaFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioHastaFTF.gridx = 8;
		gbc_precioHastaFTF.gridy = 2;
		busquedaPanel.add(precioHastaFTF, gbc_precioHastaFTF);

		capacidadDesdeFTF = new JFormattedTextField();
		GridBagConstraints gbc_capacidadDesdeFTF = new GridBagConstraints();
		gbc_capacidadDesdeFTF.insets = new Insets(0, 0, 5, 5);
		gbc_capacidadDesdeFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_capacidadDesdeFTF.gridx = 10;
		gbc_capacidadDesdeFTF.gridy = 2;
		busquedaPanel.add(capacidadDesdeFTF, gbc_capacidadDesdeFTF);

		capacidadHastaFTF = new JFormattedTextField();
		GridBagConstraints gbc_capacidadHastaFTF = new GridBagConstraints();
		gbc_capacidadHastaFTF.insets = new Insets(0, 0, 5, 5);
		gbc_capacidadHastaFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_capacidadHastaFTF.gridx = 11;
		gbc_capacidadHastaFTF.gridy = 2;
		busquedaPanel.add(capacidadHastaFTF, gbc_capacidadHastaFTF);

		JLabel idArtistaLabel = new JLabel("ID del Artista");
		GridBagConstraints gbc_idArtistaLabel = new GridBagConstraints();
		gbc_idArtistaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idArtistaLabel.gridx = 1;
		gbc_idArtistaLabel.gridy = 3;
		busquedaPanel.add(idArtistaLabel, gbc_idArtistaLabel);

		JLabel artistaLabel = new JLabel("Artista");
		GridBagConstraints gbc_artistaLabel = new GridBagConstraints();
		gbc_artistaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_artistaLabel.gridx = 2;
		gbc_artistaLabel.gridy = 3;
		busquedaPanel.add(artistaLabel, gbc_artistaLabel);

		JLabel generoMusicalLabel = new JLabel("Genero Musical");
		GridBagConstraints gbc_generoMusicalLabel = new GridBagConstraints();
		gbc_generoMusicalLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generoMusicalLabel.gridx = 4;
		gbc_generoMusicalLabel.gridy = 3;
		busquedaPanel.add(generoMusicalLabel, gbc_generoMusicalLabel);

		JLabel subgeneroMusicalLabel = new JLabel("Subgenero Musical");
		GridBagConstraints gbc_subgeneroMusicalLabel = new GridBagConstraints();
		gbc_subgeneroMusicalLabel.insets = new Insets(0, 0, 5, 5);
		gbc_subgeneroMusicalLabel.gridx = 5;
		gbc_subgeneroMusicalLabel.gridy = 3;
		busquedaPanel.add(subgeneroMusicalLabel, gbc_subgeneroMusicalLabel);

		JLabel tipoEventoLabel = new JLabel("Tipo Evento");
		GridBagConstraints gbc_tipoEventoLabel = new GridBagConstraints();
		gbc_tipoEventoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tipoEventoLabel.gridx = 7;
		gbc_tipoEventoLabel.gridy = 3;
		busquedaPanel.add(tipoEventoLabel, gbc_tipoEventoLabel);

		JLabel subtipoLabel = new JLabel("Subtipo Evento");
		GridBagConstraints gbc_subtipoLabel = new GridBagConstraints();
		gbc_subtipoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_subtipoLabel.gridx = 8;
		gbc_subtipoLabel.gridy = 3;
		busquedaPanel.add(subtipoLabel, gbc_subtipoLabel);

		JLabel estadoLabel = new JLabel("Estado");
		GridBagConstraints gbc_estadoLabel = new GridBagConstraints();
		gbc_estadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_estadoLabel.gridx = 10;
		gbc_estadoLabel.gridy = 3;
		busquedaPanel.add(estadoLabel, gbc_estadoLabel);
		
		zonaHorariaLabel = new JLabel("Zona Horaria");
		GridBagConstraints gbc_zonaHorariaLabel = new GridBagConstraints();
		gbc_zonaHorariaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaLabel.gridx = 11;
		gbc_zonaHorariaLabel.gridy = 3;
		busquedaPanel.add(zonaHorariaLabel, gbc_zonaHorariaLabel);

		idArtistaFTF = new JFormattedTextField();
		GridBagConstraints gbc_idArtistaFTF = new GridBagConstraints();
		gbc_idArtistaFTF.insets = new Insets(0, 0, 5, 5);
		gbc_idArtistaFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_idArtistaFTF.gridx = 1;
		gbc_idArtistaFTF.gridy = 4;
		busquedaPanel.add(idArtistaFTF, gbc_idArtistaFTF);

		artistaTF = new JTextField();
		artistaTF.setColumns(10);
		GridBagConstraints gbc_artistaTF = new GridBagConstraints();
		gbc_artistaTF.insets = new Insets(0, 0, 5, 5);
		gbc_artistaTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_artistaTF.gridx = 2;
		gbc_artistaTF.gridy = 4;
		busquedaPanel.add(artistaTF, gbc_artistaTF);

		generoCB = new JComboBox();
		GridBagConstraints gbc_generoCB = new GridBagConstraints();
		gbc_generoCB.insets = new Insets(0, 0, 5, 5);
		gbc_generoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoCB.gridx = 4;
		gbc_generoCB.gridy = 4;
		busquedaPanel.add(generoCB, gbc_generoCB);

		subgeneroCB = new JComboBox();
		GridBagConstraints gbc_subgeneroCB = new GridBagConstraints();
		gbc_subgeneroCB.insets = new Insets(0, 0, 5, 5);
		gbc_subgeneroCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_subgeneroCB.gridx = 5;
		gbc_subgeneroCB.gridy = 4;
		busquedaPanel.add(subgeneroCB, gbc_subgeneroCB);

		tipoEventoCB = new JComboBox();
		GridBagConstraints gbc_tipoEventoCB = new GridBagConstraints();
		gbc_tipoEventoCB.insets = new Insets(0, 0, 5, 5);
		gbc_tipoEventoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoEventoCB.gridx = 7;
		gbc_tipoEventoCB.gridy = 4;
		busquedaPanel.add(tipoEventoCB, gbc_tipoEventoCB);

		subtipoCB = new JComboBox();
		GridBagConstraints gbc_subtipoCB = new GridBagConstraints();
		gbc_subtipoCB.insets = new Insets(0, 0, 5, 5);
		gbc_subtipoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_subtipoCB.gridx = 8;
		gbc_subtipoCB.gridy = 4;
		busquedaPanel.add(subtipoCB, gbc_subtipoCB);

		estadoCB = new JComboBox();
		GridBagConstraints gbc_estadoCB = new GridBagConstraints();
		gbc_estadoCB.insets = new Insets(0, 0, 5, 5);
		gbc_estadoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_estadoCB.gridx = 10;
		gbc_estadoCB.gridy = 4;
		busquedaPanel.add(estadoCB, gbc_estadoCB);
		
		zonaHorariaCB = new JComboBox();
		GridBagConstraints gbc_zonaHorariaCB = new GridBagConstraints();
		gbc_zonaHorariaCB.insets = new Insets(0, 0, 5, 5);
		gbc_zonaHorariaCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_zonaHorariaCB.gridx = 11;
		gbc_zonaHorariaCB.gridy = 4;
		busquedaPanel.add(zonaHorariaCB, gbc_zonaHorariaCB);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 13;
		gbc_horizontalStrut_1.gridy = 4;
		busquedaPanel.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 5;
		busquedaPanel.add(horizontalStrut, gbc_horizontalStrut);

		JLabel idOrganizadorLabel = new JLabel("ID del Organizador");
		GridBagConstraints gbc_idOrganizadorLabel = new GridBagConstraints();
		gbc_idOrganizadorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idOrganizadorLabel.gridx = 1;
		gbc_idOrganizadorLabel.gridy = 5;
		busquedaPanel.add(idOrganizadorLabel, gbc_idOrganizadorLabel);

		JLabel organizadorLabel = new JLabel("Organizador");
		GridBagConstraints gbc_organizadorLabel = new GridBagConstraints();
		gbc_organizadorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_organizadorLabel.gridx = 2;
		gbc_organizadorLabel.gridy = 5;
		busquedaPanel.add(organizadorLabel, gbc_organizadorLabel);

		JLabel idLugarLabel = new JLabel("ID del Lugar");
		GridBagConstraints gbc_idLugarLabel = new GridBagConstraints();
		gbc_idLugarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLugarLabel.gridx = 4;
		gbc_idLugarLabel.gridy = 5;
		busquedaPanel.add(idLugarLabel, gbc_idLugarLabel);

		JLabel lugarLabel = new JLabel("Lugar");
		GridBagConstraints gbc_lugarLabel = new GridBagConstraints();
		gbc_lugarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lugarLabel.gridx = 5;
		gbc_lugarLabel.gridy = 5;
		busquedaPanel.add(lugarLabel, gbc_lugarLabel);

		JLabel paisLabel = new JLabel("Pais");
		GridBagConstraints gbc_paisLabel = new GridBagConstraints();
		gbc_paisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paisLabel.gridx = 7;
		gbc_paisLabel.gridy = 5;
		busquedaPanel.add(paisLabel, gbc_paisLabel);

		JLabel regionLabel = new JLabel("Region");
		GridBagConstraints gbc_regionLabel = new GridBagConstraints();
		gbc_regionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_regionLabel.gridx = 8;
		gbc_regionLabel.gridy = 5;
		busquedaPanel.add(regionLabel, gbc_regionLabel);

		JLabel ciudadLabel = new JLabel("Ciudad");
		GridBagConstraints gbc_ciudadLabel = new GridBagConstraints();
		gbc_ciudadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ciudadLabel.gridx = 9;
		gbc_ciudadLabel.gridy = 5;
		busquedaPanel.add(ciudadLabel, gbc_ciudadLabel);

		idOrganizadorFTF = new JFormattedTextField();
		GridBagConstraints gbc_idOrganizadorFTF = new GridBagConstraints();
		gbc_idOrganizadorFTF.insets = new Insets(0, 0, 5, 5);
		gbc_idOrganizadorFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_idOrganizadorFTF.gridx = 1;
		gbc_idOrganizadorFTF.gridy = 6;
		busquedaPanel.add(idOrganizadorFTF, gbc_idOrganizadorFTF);

		organizadorTF = new JTextField();
		GridBagConstraints gbc_organizadorTF = new GridBagConstraints();
		gbc_organizadorTF.insets = new Insets(0, 0, 5, 5);
		gbc_organizadorTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_organizadorTF.gridx = 2;
		gbc_organizadorTF.gridy = 6;
		busquedaPanel.add(organizadorTF, gbc_organizadorTF);
		organizadorTF.setColumns(10);

		idLugarFTF = new JFormattedTextField();
		GridBagConstraints gbc_idLugarFTF = new GridBagConstraints();
		gbc_idLugarFTF.insets = new Insets(0, 0, 5, 5);
		gbc_idLugarFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_idLugarFTF.gridx = 4;
		gbc_idLugarFTF.gridy = 6;
		busquedaPanel.add(idLugarFTF, gbc_idLugarFTF);

		lugarTF = new JTextField();
		GridBagConstraints gbc_lugarTF = new GridBagConstraints();
		gbc_lugarTF.insets = new Insets(0, 0, 5, 5);
		gbc_lugarTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_lugarTF.gridx = 5;
		gbc_lugarTF.gridy = 6;
		busquedaPanel.add(lugarTF, gbc_lugarTF);
		lugarTF.setColumns(10);

		paisCB = new JComboBox();
		GridBagConstraints gbc_paisCB = new GridBagConstraints();
		gbc_paisCB.insets = new Insets(0, 0, 5, 5);
		gbc_paisCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_paisCB.gridx = 7;
		gbc_paisCB.gridy = 6;
		busquedaPanel.add(paisCB, gbc_paisCB);

		regionCB = new JComboBox();
		GridBagConstraints gbc_regionCB = new GridBagConstraints();
		gbc_regionCB.insets = new Insets(0, 0, 5, 5);
		gbc_regionCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_regionCB.gridx = 8;
		gbc_regionCB.gridy = 6;
		busquedaPanel.add(regionCB, gbc_regionCB);

		ciudadCB = new JComboBox();
		GridBagConstraints gbc_ciudadCB = new GridBagConstraints();
		gbc_ciudadCB.insets = new Insets(0, 0, 5, 5);
		gbc_ciudadCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_ciudadCB.gridx = 9;
		gbc_ciudadCB.gridy = 6;
		busquedaPanel.add(ciudadCB, gbc_ciudadCB);

		buscarButton = new JButton("Buscar");
		buscarButton.setIcon(new ImageIcon(AdminEventoSearchView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.insets = new Insets(0, 0, 5, 5);
		gbc_buscarButton.gridx = 11;
		gbc_buscarButton.gridy = 6;
		busquedaPanel.add(buscarButton, gbc_buscarButton);

		JButton limpiarButton = new JButton("Limpiar Campos");
		limpiarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		limpiarButton.setIcon(new ImageIcon(AdminEventoSearchView.class.getResource("/nuvola/16x16/1909_restart_tool_restart_refresh_tool_refresh.png")));
		GridBagConstraints gbc_limpiarButton = new GridBagConstraints();
		gbc_limpiarButton.insets = new Insets(0, 0, 5, 5);
		gbc_limpiarButton.gridx = 12;
		gbc_limpiarButton.gridy = 6;
		busquedaPanel.add(limpiarButton, gbc_limpiarButton);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut.gridx = 7;
		gbc_verticalStrut.gridy = 7;
		busquedaPanel.add(verticalStrut, gbc_verticalStrut);
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		resultadosTable = new JTable();
		resultadosTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scrollPane = new JScrollPane(resultadosTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		centerPanel.add(scrollPane);
		
		tableRightPanel = new JPanel();
		centerPanel.add(tableRightPanel, BorderLayout.WEST);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		tableRightPanel.add(horizontalStrut_2);
		
		leftTablePanel = new JPanel();
		centerPanel.add(leftTablePanel, BorderLayout.EAST);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		leftTablePanel.add(horizontalStrut_3);

		JPanel footerPanel = new JPanel();
		FlowLayout fl_footerPanel = (FlowLayout) footerPanel.getLayout();
		fl_footerPanel.setAlignment(FlowLayout.LEFT);
		add(footerPanel, BorderLayout.SOUTH);

		totalResultadosLabel = new JLabel("");
		footerPanel.add(totalResultadosLabel);

	}

	private void postInitialize() {
		// desactivar TF aun no implementados
		artistaTF.setEnabled(false);
		lugarTF.setEnabled(false);
		organizadorTF.setEnabled(false);
		
		setDocumentFilters();

		setAllRenderers();		

//		llenarCB();
		
		setModel(new ArrayList<EventoMusicalDTO>());

		setControllers();
		
		AutoCompleteDecorator.decorate(paisCB, new PaisToStringConverter()); 
		
		
		buscarButton.doClick(); // para que al abrir la vista ya se muestren resultados, aunque sea el resultado de una búsqueda sin criterios, para que no quede la vista vacía sin tabla ni nada
		
	}

	/*private void initServices() {
		estadoEventoService = new EstadoEventoServiceImpl();
		subTipoEventoService = new SubTipoEventoServiceImpl();
		subGeneroMusicalService = new SubGeneroMusicalServiceImpl();
		tipoEventoService = new TipoEventoServiceImpl();
		paisService = new PaisServiceImpl();
		regionService= new RegionServiceImpl();
		ciudadService = new CiudadServiceImpl();
		eventoMusicalService = new EventoMusicalServiceImpl();
		generoMusicalService = new GeneroMusicalServiceImpl();
		zonaHorariaService = new ZonaHorariaServiceImpl();
	}
	*/

	private void limpiarCampos() {
		// Limpiar JTextFields
		nombreTF.setText("");
		artistaTF.setText("");
		lugarTF.setText("");
		organizadorTF.setText(""); 
		// Limpiar JFormattedTextFields
		idEventoFTF.setText("");
		precioDesdeFTF.setText("");
		precioHastaFTF.setText("");
		capacidadDesdeFTF.setText("");
		capacidadHastaFTF.setText("");
		idArtistaFTF.setText("");
		idOrganizadorFTF.setText("");
		idLugarFTF.setText("");
		// Limpiar JDateChoosers
		fechaDesdeDC.setDate(null);
		fechaHastaDC.setDate(null);	
		// Resetear ComboBoxes (Al índice 0, que es "Seleccionar")
		paisCB.setSelectedIndex(0);
		generoCB.setSelectedIndex(0);
		tipoEventoCB.setSelectedIndex(0);
		// Para los ComboBoxes dependientes, limpiamos sus modelos para que no queden opciones que no correspondan al criterio actual 
		// ni que salgan errores de out of bounds al setear el índice 0 después de haber cargado opciones dinámicamente
		subgeneroCB.setModel(new DefaultComboBoxModel<>());
		subtipoCB.setModel(new DefaultComboBoxModel<>());
		regionCB.setModel(new DefaultComboBoxModel<>());
		ciudadCB.setModel(new DefaultComboBoxModel<>());
		// Limpiar el área de resultados
		resultadosTable.setModel(new DefaultTableModel());
		// Limpiar el label de total de resultados
		totalResultadosLabel.setText("");
		
		// Volver a filtrar por nada, para que no quede la vista sin tabla
		buscarButton.doClick();
	}

	public EventoMusicalCriteria getCriteria() {
		EventoMusicalCriteria criteria = new EventoMusicalCriteria();
		// trimear los campos de texto (TextField) para evitar problemas con espacios en blanco
		// TODO: implementar completado de Id, despues de escribir el nombre del artista, lugar, etc. para mejorar la usabilidad y siempre pasar IDs al backend
		criteria.setNombre(StringUtils.trimToNull(nombreTF.getText()));
		criteria.setId(idEventoFTF.getText().isEmpty() ? null : Long.parseLong(idEventoFTF.getText()));
		if (fechaDesdeDC.getDate() != null) {
	        LocalDate fechaDesde = fechaDesdeDC.getDate().toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDate();
	        // Buscamos desde el primer segundo de ese día
	        criteria.setFechaInicio(fechaDesde.atStartOfDay()); 
	    }

	    if (fechaHastaDC.getDate() != null) {
	        LocalDate fechaHasta = fechaHastaDC.getDate().toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDate();
	        // Buscamos hasta el último suspiro de ese día
	        criteria.setFechaFin(fechaHasta.atTime(LocalTime.MAX)); 
	    }
		criteria.setPrecioDesde(precioDesdeFTF.getText().isEmpty() ? null : Integer.parseInt(precioDesdeFTF.getText()));
		criteria.setPrecioHasta(precioHastaFTF.getText().isEmpty() ? null : Integer.parseInt(precioHastaFTF.getText()));
		criteria.setCapacidadDesde(capacidadDesdeFTF.getText().isEmpty() ? null : Integer.parseInt(capacidadDesdeFTF.getText()));
		criteria.setCapacidadHasta(capacidadHastaFTF.getText().isEmpty() ? null : Integer.parseInt(capacidadHastaFTF.getText()));
		criteria.setArtistaId(idArtistaFTF.getText().isEmpty() ? null : Long.parseLong(idArtistaFTF.getText()));				
		GeneroMusical seleccionadoGenero = (GeneroMusical) generoCB.getSelectedItem();
		criteria.setGeneroMusicalId((seleccionadoGenero != null && seleccionadoGenero.getId() != null) ? Long.valueOf(seleccionadoGenero.getId()) : null);
		SubGeneroMusicalDTO seleccionadoSubgenero = (SubGeneroMusicalDTO) subgeneroCB.getSelectedItem();
		criteria.setSubGeneroMusicalId((seleccionadoSubgenero != null && seleccionadoSubgenero.getId() != null) ? Long.valueOf(seleccionadoSubgenero.getId()) : null);
		TipoEvento seleccionadoTipo = (TipoEvento) tipoEventoCB.getSelectedItem();
		criteria.setTipoEventoId((seleccionadoTipo != null && seleccionadoTipo.getId() != null) ? Long.valueOf(seleccionadoTipo.getId()) : null);
		SubTipoEventoDTO seleccionadoSubtipo = (SubTipoEventoDTO) subtipoCB.getSelectedItem();
		criteria.setSubtipoEventoId((seleccionadoSubtipo != null && seleccionadoSubtipo.getId() != null) ? Long.valueOf(seleccionadoSubtipo.getId()) : null);
		criteria.setOrganizadorId(idOrganizadorFTF.getText().isEmpty() ? null : Long.parseLong(idOrganizadorFTF.getText()));
		criteria.setLugarId(idLugarFTF.getText().isEmpty() ? null : Long.parseLong(idLugarFTF.getText()));
		Pais seleccionadoPais = (Pais) paisCB.getSelectedItem();
		criteria.setPaisId((seleccionadoPais != null && seleccionadoPais.getId() != null) ? Long.valueOf(seleccionadoPais.getId()) : null);
		Region seleccionadaRegion = (Region) regionCB.getSelectedItem();
		criteria.setRegionId((seleccionadaRegion != null && seleccionadaRegion.getId() != null) ? Long.valueOf(seleccionadaRegion.getId()) : null);
		Ciudad seleccionadaCiudad = (Ciudad) ciudadCB.getSelectedItem();
		criteria.setCiudadId((seleccionadaCiudad != null && seleccionadaCiudad.getId() != null) ? Long.valueOf(seleccionadaCiudad.getId()) : null);
		EstadoEvento seleccionadoEstado = (EstadoEvento) estadoCB.getSelectedItem();
		criteria.setEstadoId((seleccionadoEstado != null && seleccionadoEstado.getId() != null) ? Long.valueOf(seleccionadoEstado.getId()) : null);
		ZonaHoraria seleccionadaZona = (ZonaHoraria) zonaHorariaCB.getSelectedItem();
		criteria.setZonaHorariaId((seleccionadaZona != null && seleccionadaZona.getId() != null) ? Long.valueOf(seleccionadaZona.getId()) : null);
		return criteria;
	}

	public void setModel(List<EventoMusicalDTO> model) {
		this.model = model;
		updateView();
	}
	
	public void updateView() {
		EventoTableModel tableModel = new EventoTableModel(model);
		resultadosTable.setModel(tableModel);
		totalResultadosLabel.setText("Total de resultados: " + model.size());
	}
	
	public void setDocumentFilters() {
		((AbstractDocument) idEventoFTF.getDocument()).setDocumentFilter(new SoloNumerosDF());
		((AbstractDocument) precioDesdeFTF.getDocument()).setDocumentFilter(new SoloNumerosDF());
		((AbstractDocument) precioHastaFTF.getDocument()).setDocumentFilter(new SoloNumerosDF());
		((AbstractDocument) capacidadDesdeFTF.getDocument()).setDocumentFilter(new SoloNumerosDF());
		((AbstractDocument) capacidadHastaFTF.getDocument()).setDocumentFilter(new SoloNumerosDF());
		((AbstractDocument) idArtistaFTF.getDocument()).setDocumentFilter(new SoloNumerosDF());
		((AbstractDocument) idOrganizadorFTF.getDocument()).setDocumentFilter(new SoloNumerosDF());
		((AbstractDocument) idLugarFTF.getDocument()).setDocumentFilter(new SoloNumerosDF());
	}
	
	public void setAllRenderers() {
		paisCB.setRenderer(new PaisCBRenderer());
		regionCB.setRenderer(new RegionCBRenderer());
		ciudadCB.setRenderer(new CiudadCBRenderer()); 
		generoCB.setRenderer(new GeneroCBRenderer());
		subgeneroCB.setRenderer(new SubgeneroCBRenderer());
		tipoEventoCB.setRenderer(new TipoEventoCBRenderer());
		subtipoCB.setRenderer(new SubtipoEventoCBRenderer());
		estadoCB.setRenderer(new EstadoCBRenderer());
		zonaHorariaCB.setRenderer(new ZonaHorariaCBRenderer());
		// renderer de tabla de resultados
		resultadosTable.setDefaultRenderer(Object.class, new EventoTableRenderer());
	}
	
	public void setControllers() {
		// Llenado de comboboxes con controller
		LlenarCombosSearchController llenado = new LlenarCombosSearchController(this);

		// Acciones de búsqueda
		EventoSearchController searchAction = new EventoSearchController(this);
		buscarButton.setAction(searchAction);
		
		// Permitir búsqueda al presionar Enter en los campos de texto (KeyTyped) en campos de Texto y FormattedTextField
		nombreTF.setAction(searchAction);
		precioDesdeFTF.setAction(searchAction);
		precioHastaFTF.setAction(searchAction);
		capacidadDesdeFTF.setAction(searchAction);
		capacidadHastaFTF.setAction(searchAction);
		idEventoFTF.setAction(searchAction);
		idArtistaFTF.setAction(searchAction);
		idOrganizadorFTF.setAction(searchAction);
		idLugarFTF.setAction(searchAction);
		
		// Permitir búsqueda al cambiar selección en ComboBoxes
		paisCB.addActionListener(searchAction);
		regionCB.addActionListener(searchAction);
		ciudadCB.addActionListener(searchAction);
		generoCB.addActionListener(searchAction);
		subgeneroCB.addActionListener(searchAction);
		tipoEventoCB.addActionListener(searchAction);
		subtipoCB.addActionListener(searchAction);
		estadoCB.addActionListener(searchAction);		
		// Permitir búsqueda al cambiar fecha en JDateChoosers
		fechaDesdeDC.getDateEditor().addPropertyChangeListener(searchAction);
		fechaHastaDC.getDateEditor().addPropertyChangeListener(searchAction);
		
		AbrirDetalleEMController detalleAction = new AbrirDetalleEMController(this);
		resultadosTable.addMouseListener(detalleAction);
	}
	
	public JTable getTable () {
		return resultadosTable;
	}
	
	// Metodos get para comboBoxes (utilizados en LlenarCombosController)
	public JComboBox getEstadoCB () {
		return estadoCB;
	}
	
	public JComboBox getGeneroCB() {
		return generoCB;
	}
	
	public JComboBox getSubgeneroCB() {
		return subgeneroCB;
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
	
	public JComboBox getTipoCB() {
		return tipoEventoCB;
	}
	
	public JComboBox getSubtipoCB() {
		return subtipoCB;
	}
	
	public JComboBox getZonaHorariaCB() {
		return zonaHorariaCB;
	} 
	
} // class
