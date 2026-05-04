package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.setlisto.criteria.ClienteCriteria;
import com.setlisto.model.Cliente;
import com.setlisto.model.Results;
import com.setlisto.service.ClienteService;
import com.setlisto.service.impl.ClienteServiceImpl;
import com.setlisto.ui.view.ClienteSearchView;

public class ClienteSearchController extends AbstractController implements ActionListener{

	private ClienteService clienteService = null;
	private ClienteSearchView view = null;
	
	public ClienteSearchController(ClienteSearchView view) {
		super ("Buscar"); // poner icono
		clienteService = new ClienteServiceImpl();
		this.view = view; 
	}

	public void doAction() {
		ClienteCriteria criteria = view.getCriteria();
		Results<Cliente> clientes = clienteService.findByCriteria(criteria, 1, 10);
		List<Cliente> list = clientes.getPage();
//		view.setResults(list);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
		
	}

}
