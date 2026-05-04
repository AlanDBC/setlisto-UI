package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;

import com.setlisto.criteria.LugarCriteria;
import com.setlisto.model.LugarDTO;
import com.setlisto.model.Results;
import com.setlisto.service.LugarService;
import com.setlisto.service.impl.LugarServiceImpl;
import com.setlisto.ui.view.AdminEventoSearchView;
import com.setlisto.ui.view.LugarSelectView;

public class LugarSearchController extends AbstractController implements ActionListener {

	private LugarSelectView view;
	private LugarService service;
	
	public LugarSearchController(LugarSelectView view) {
		super("Buscar", new ImageIcon(AdminEventoSearchView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		this.view = view;
		service = new LugarServiceImpl();
	}

	@Override
	public void doAction() {
		LugarCriteria criteria = view.getCriteria();
		Results<LugarDTO> results = service.findByCriteria(criteria, 0, 10);
		List<LugarDTO> list = results.getPage();
		view.setModel(list);
	}
	
	@Override // Buttons
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
	
	@Override // ComboBoxes
	public void itemStateChanged(ItemEvent e) {
		doAction();
	}
	
	@Override // TextFields y FormattedTextFields
	public void keyReleased(KeyEvent e) {
		doAction();
	}

}
