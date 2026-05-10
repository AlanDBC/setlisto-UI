package com.setlisto.ui.controller;

import com.setlisto.model.CategoriaAsiento;
import com.setlisto.service.CategoriaAsientoService;
import com.setlisto.service.impl.CategoriaAsientoServiceImpl;
import com.setlisto.ui.utils.UIUtils;
import com.setlisto.ui.view.PlazasConfigView;

public class LlenarComboPlazasConfigController {
	private PlazasConfigView view;
	
	private CategoriaAsientoService categoriaService = new CategoriaAsientoServiceImpl();
	
	public LlenarComboPlazasConfigController(PlazasConfigView view) {
		this.view = view;
		cargarDatos();
	}
	
	private void cargarDatos() {
		view.getCategoriaCB().setModel(UIUtils.crearModelo(categoriaService.findAll(), new CategoriaAsiento(null, "Seleccionar")));
	}

}
