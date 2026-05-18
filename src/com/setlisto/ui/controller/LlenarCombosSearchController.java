package com.setlisto.ui.controller;

import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.setlisto.model.Ciudad;
import com.setlisto.model.EstadoEvento;
import com.setlisto.model.GeneroMusical;
import com.setlisto.model.Pais;
import com.setlisto.model.Region;
import com.setlisto.model.SubGeneroMusicalDTO;
import com.setlisto.model.SubTipoEventoDTO;
import com.setlisto.model.TipoEvento;
import com.setlisto.model.ZonaHoraria;
import com.setlisto.service.CiudadService;
import com.setlisto.service.EstadoEventoService;
import com.setlisto.service.GeneroMusicalService;
import com.setlisto.service.PaisService;
import com.setlisto.service.RegionService;
import com.setlisto.service.SubGeneroMusicalService;
import com.setlisto.service.SubTipoEventoService;
import com.setlisto.service.TipoEventoService;
import com.setlisto.service.ZonaHorariaService;
import com.setlisto.service.impl.CiudadServiceImpl;
import com.setlisto.service.impl.EstadoEventoServiceImpl;
import com.setlisto.service.impl.GeneroMusicalServiceImpl;
import com.setlisto.service.impl.PaisServiceImpl;
import com.setlisto.service.impl.RegionServiceImpl;
import com.setlisto.service.impl.SubGeneroMusicalServiceImpl;
import com.setlisto.service.impl.SubTipoEventoServiceImpl;
import com.setlisto.service.impl.TipoEventoServiceImpl;
import com.setlisto.service.impl.ZonaHorariaServiceImpl;
import com.setlisto.ui.utils.UIUtils;
import com.setlisto.ui.view.AdminEventoSearchView;

public class LlenarCombosSearchController {
	private AdminEventoSearchView vista;

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

	public LlenarCombosSearchController(AdminEventoSearchView view) {
		this.vista = view;
		cargarDatosIniciales();
		initEventos();
	}

	/**
	 * Carga los datos iniciales para los combos independientes (que no dependen de otro combo) y los combos padres.
	 */
	private void cargarDatosIniciales() {
		try {
			// Combos independientes
			vista.getEstadoCB().setModel(UIUtils.crearModelo(estadoService.findAll(), new EstadoEvento(null, "Seleccionar")));
			vista.getZonaHorariaCB().setModel(UIUtils.crearModelo(zonaHorariaService.findAll(), new ZonaHoraria(null, "Seleccionar")));

			// Combos Padres iniciales
			vista.getPaisCB().setModel(UIUtils.crearModelo(paisService.findAll(), new Pais(null, "Seleccionar")));
			vista.getGeneroCB().setModel(UIUtils.crearModelo(generoService.findAll(), new GeneroMusical(null, "Seleccionar")));
			vista.getTipoCB().setModel(UIUtils.crearModelo(tipoService.findAll(), new TipoEvento(null, "Seleccionar")));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vista, "No fue posible cargar filtros: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Configura la relación entre un combo padre y su combo hijo, de modo que al seleccionar un elemento en el padre, 
	 * se ejecute la acción de carga para el hijo.
	 * @param comboPadre
	 * @param accionCarga
	 */
	private void configurarRelacion(JComboBox comboPadre, Runnable accionCarga) {
		comboPadre.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				accionCarga.run();
			}
		});
	}

	/**
	 * Inicializa los eventos para cargar los combos hijos cuando se selecciona un elemento en el combo padre.
	 */
	private void initEventos() {
		// Tipo -> SubTipo
		configurarRelacion(vista.getTipoCB(), () -> {
			TipoEvento padre = (TipoEvento) vista.getTipoCB().getSelectedItem();
			if (padre != null && padre.getId() != null) {
				try {
					List<SubTipoEventoDTO> hijos = subtipoService.findByTipoEvento(padre.getId());
					vista.getSubtipoCB().setModel(UIUtils.crearModelo(hijos, new SubTipoEventoDTO(null, "Seleccionar")));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(vista, "No fue posible cargar subtipos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				vista.getSubtipoCB().setModel(new DefaultComboBoxModel<>());
			}
		});

		// Genero -> SubGenero
		configurarRelacion(vista.getGeneroCB(), () -> {
			GeneroMusical padre = (GeneroMusical) vista.getGeneroCB().getSelectedItem();
			if (padre != null && padre.getId() != null) {
				try {
					List<SubGeneroMusicalDTO> hijos = subgeneroService.findByGenero(padre.getId());
					vista.getSubgeneroCB().setModel(UIUtils.crearModelo(hijos, new SubGeneroMusicalDTO(null, "Seleccionar")));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(vista, "No fue posible cargar subgeneros: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				vista.getSubgeneroCB().setModel(new DefaultComboBoxModel<>());
			}
		});

		// Pais -> Region
		configurarRelacion(vista.getPaisCB(), () -> {
			Pais padre = (Pais) vista.getPaisCB().getSelectedItem();
			if (padre != null && padre.getId() != null) {
				try {
					List<Region> hijos = regionService.findByPaisId(padre.getId());
					vista.getRegionCB().setModel(UIUtils.crearModelo(hijos, new Region(null, "Seleccionar")));
					vista.getCiudadCB().setModel(new DefaultComboBoxModel<>()); // Limpiar nieto
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(vista, "No fue posible cargar regiones: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				vista.getRegionCB().setModel(new DefaultComboBoxModel<>());
				vista.getCiudadCB().setModel(new DefaultComboBoxModel<>());
			}
		});

		// Region -> Ciudad
		configurarRelacion(vista.getRegionCB(), () -> {
			Region padre = (Region) vista.getRegionCB().getSelectedItem();
			if (padre != null && padre.getId() != null) {
				try {
					List<Ciudad> hijos = ciudadService.findByRegionId(padre.getId());
					vista.getCiudadCB().setModel(UIUtils.crearModelo(hijos, new Ciudad(null, "Seleccionar")));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(vista, "No fue posible cargar ciudades: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				vista.getCiudadCB().setModel(new DefaultComboBoxModel<>());
			}
		});
	}
}
