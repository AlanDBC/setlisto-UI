package com.setlisto.ui.controller;

import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.setlisto.model.GeneroMusical;
import com.setlisto.model.Organizador;
import com.setlisto.model.SubGeneroMusicalDTO;
import com.setlisto.model.SubTipoEventoDTO;
import com.setlisto.model.TipoEvento;
import com.setlisto.model.ZonaHoraria;
import com.setlisto.service.ArtistaService;
import com.setlisto.service.CiudadService;
import com.setlisto.service.EstadoEventoService;
import com.setlisto.service.GeneroMusicalService;
import com.setlisto.service.OrganizadorService;
import com.setlisto.service.PaisService;
import com.setlisto.service.RegionService;
import com.setlisto.service.SubGeneroMusicalService;
import com.setlisto.service.SubTipoEventoService;
import com.setlisto.service.TipoEventoService;
import com.setlisto.service.ZonaHorariaService;
import com.setlisto.service.impl.ArtistaServiceImpl;
import com.setlisto.service.impl.CiudadServiceImpl;
import com.setlisto.service.impl.EstadoEventoServiceImpl;
import com.setlisto.service.impl.GeneroMusicalServiceImpl;
import com.setlisto.service.impl.OrganizadorServiceImpl;
import com.setlisto.service.impl.PaisServiceImpl;
import com.setlisto.service.impl.RegionServiceImpl;
import com.setlisto.service.impl.SubGeneroMusicalServiceImpl;
import com.setlisto.service.impl.SubTipoEventoServiceImpl;
import com.setlisto.service.impl.TipoEventoServiceImpl;
import com.setlisto.service.impl.ZonaHorariaServiceImpl;
import com.setlisto.ui.utils.UIUtils;
import com.setlisto.ui.view.EventoCreateView;

public class InicializarEventoCreateController {
	private EventoCreateView vista;
	
	// Servicios
	private SubTipoEventoService subtipoService = new SubTipoEventoServiceImpl();
	private SubGeneroMusicalService subgeneroService = new SubGeneroMusicalServiceImpl();
	private RegionService regionService = new RegionServiceImpl();
	private CiudadService ciudadService = new CiudadServiceImpl();
	private PaisService paisService = new PaisServiceImpl();
	private EstadoEventoService estadoService = new EstadoEventoServiceImpl();
	private ZonaHorariaService zonaHorariaService = new ZonaHorariaServiceImpl();
	private GeneroMusicalService generoService = new GeneroMusicalServiceImpl();
	private TipoEventoService tipoService = new TipoEventoServiceImpl();
	private OrganizadorService organizadorService = new OrganizadorServiceImpl();
	private ArtistaService artistaService= new ArtistaServiceImpl();

	public InicializarEventoCreateController(EventoCreateView view) {
		this.vista = view;
		cargarDatosIniciales();
		initEventos();
	}

	private void cargarDatosIniciales() {
		// combo independiente
		vista.getZonaHorariaCB().setModel(UIUtils.crearModelo(zonaHorariaService.findAll(), new ZonaHoraria(null, "Seleccionar")));
		vista.getOrganizadorCB().setModel(UIUtils.crearModelo(organizadorService.findAll(), new Organizador(null, "Seleccionar")));

		// combos padres
		vista.getTipoCB().setModel(UIUtils.crearModelo(tipoService.findAll(), new TipoEvento(null, "Seleccionar")));
		
		// Llenado de las listas seleccionables
		vista.getGenerosModel().cargarItems(generoService.findAll());
		vista.getArtistasModel().cargarItems(artistaService.findAll());
	}

	private void configurarRelacion(JComboBox comboPadre, Runnable accionCarga) {
		comboPadre.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				accionCarga.run();
			}
		});
	}

	private void initEventos() {
		configurarRelacion(vista.getTipoCB(), () -> {
			TipoEvento padre = (TipoEvento) vista.getTipoCB().getSelectedItem();
			if (padre != null && padre.getId() != null) {
				List<SubTipoEventoDTO> hijos = subtipoService.findByTipoEvento(padre.getId());
				vista.getSubtipoCB().setModel(UIUtils.crearModelo(hijos, new SubTipoEventoDTO(null, "Seleccionar")));
			} else {
				vista.getSubtipoCB().setModel(new DefaultComboBoxModel<>());
			}
		});
	}
}
