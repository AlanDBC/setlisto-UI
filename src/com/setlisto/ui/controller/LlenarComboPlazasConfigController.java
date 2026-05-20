package com.setlisto.ui.controller;

import javax.swing.JOptionPane;

import com.setlisto.model.CategoriaAsiento;
import com.setlisto.service.CategoriaPlazaService;
import com.setlisto.service.impl.CategoriaPlazaServiceImpl;
import com.setlisto.ui.utils.UIUtils;
import com.setlisto.ui.view.PlazasConfigView;

public class LlenarComboPlazasConfigController {
	private PlazasConfigView view;
	
	private CategoriaPlazaService categoriaService;
	
	public LlenarComboPlazasConfigController(PlazasConfigView view) {
		this.view = view;
		try {
			this.categoriaService = new CategoriaPlazaServiceImpl();
			cargarDatos();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "No fue posible cargar categorias: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cargarDatos() throws Exception {
		view.getCategoriaCB().setModel(UIUtils.crearModelo(categoriaService.findAll(), new CategoriaAsiento(null, "Seleccionar")));
	}

}
