package com.setlisto.ui.controller;

import com.setlisto.model.CategoriaAsiento;
import com.setlisto.service.CategoriaAsientoService;
import com.setlisto.service.impl.CategoriaAsientoServiceImpl;
import com.setlisto.ui.utils.UIUtils;
import com.setlisto.ui.view.PlazasConfigView;
import javax.swing.JOptionPane;

public class LlenarComboPlazasConfigController {
	private PlazasConfigView view;
	
	private CategoriaAsientoService categoriaService;
	
	public LlenarComboPlazasConfigController(PlazasConfigView view) {
		this.view = view;
		try {
			this.categoriaService = new CategoriaAsientoServiceImpl();
			cargarDatos();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "No fue posible cargar categorias: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cargarDatos() throws Exception {
		view.getCategoriaCB().setModel(UIUtils.crearModelo(categoriaService.findAll(), new CategoriaAsiento(null, "Seleccionar")));
	}

}
