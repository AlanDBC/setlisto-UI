package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.setlisto.criteria.TicketCriteria;
import com.setlisto.model.Results;
import com.setlisto.model.TicketDTO;
import com.setlisto.service.TicketService;
import com.setlisto.service.impl.TicketServiceImpl;
import com.setlisto.ui.view.ReservaSearchView;

public class ReservaSearchController extends AbstractController implements ActionListener {

	private TicketService ticketService;
	private ReservaSearchView view;

	public ReservaSearchController(ReservaSearchView view) {
		super("Buscar", new ImageIcon(ReservaSearchView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		this.ticketService = new TicketServiceImpl();
		this.view = view;
	}

	public void doAction() {
	    try {
	        TicketCriteria criteria = view.getCriteria();
	        
	        Results<TicketDTO> results = ticketService.findByCriteria(criteria, 0, 100);

	        view.setModel(results.getPage());

	    } catch (NumberFormatException nfe) {
	        JOptionPane.showMessageDialog(view, "Los IDs de Cliente y Evento deben ser números válidos.", "Error de formato", JOptionPane.WARNING_MESSAGE);
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(view, "No fue posible buscar reservas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
	
	
	
	
}