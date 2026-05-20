package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.setlisto.criteria.TicketCriteria;
import com.setlisto.model.TicketDTO;
import com.setlisto.ui.controller.ReservaSearchController;
import com.setlisto.ui.model.ReservaTableModel;

public class ReservaSearchView extends AbstractView {

	private static final long serialVersionUID = 1L;

	private JTextField clienteIdTF;
	private JTextField eventoIdTF;
	private JTextField codigoTF;
	private JTable table;
	private ReservaTableModel tableModel;
	private JButton buscarButton;
	private JButton limpiarButton;

	public ReservaSearchView() {
		setName("Reservas");
		initialize();
		postInitialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(8, 8));
		JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		clienteIdTF = new JTextField(8);
		eventoIdTF = new JTextField(8);
		codigoTF = new JTextField(10);
		buscarButton = new JButton("Buscar");
		limpiarButton = new JButton ("Limpiar");
		
		search.add(new JLabel("Cliente id"));
		search.add(clienteIdTF);
		search.add(new JLabel("Evento id"));
		search.add(eventoIdTF);
		search.add(new JLabel("Codigo"));
		search.add(codigoTF);
		search.add(buscarButton);
		search.add(limpiarButton);
		add(search, BorderLayout.NORTH);

		table = new JTable(tableModel);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	private void postInitialize() {

	    this.tableModel = new ReservaTableModel(); 
	    
	    table.setModel(this.tableModel);
	    		
	    buscarButton.addActionListener(new ReservaSearchController(this));
	    limpiarButton.addActionListener(e -> {
	        limpiarCampos();
	        clienteIdTF.requestFocus();
	    });
	}
	
	public void limpiarCampos() {
		clienteIdTF.setText("");
		eventoIdTF.setText("");
		codigoTF.setText("");
	    
	    tableModel.setTickets(new ArrayList<>());
	    buscarButton.doClick();
	}
	
	public String getClienteId() {
		return clienteIdTF.getText().trim();
	}

	public String getEventoId() {
		return eventoIdTF.getText().trim();
	}

	public String getCodigo() {
		return codigoTF.getText().trim();
	}
	
	public TicketCriteria getCriteria() {
	    TicketCriteria criteria = new TicketCriteria();

	    String code = getCodigo();
	    if (!code.isEmpty()) {
	        criteria.setCodigo(code);
	    }

	    String clienteIdStr = getClienteId();
	    if (!clienteIdStr.isEmpty()) {
	        criteria.setClienteId(Long.valueOf(clienteIdStr));
	    }

	    String eventoIdStr = getEventoId();
	    if (!eventoIdStr.isEmpty()) {
	        criteria.setEventoId(Long.valueOf(eventoIdStr));
	    }

	    return criteria;
	}

	public void setModel(List<TicketDTO> tickets) {
		tableModel.setTickets(tickets);
	}
}