package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.setlisto.criteria.TicketCriteria;
import com.setlisto.model.Results;
import com.setlisto.model.TicketDTO;
import com.setlisto.service.TicketService;
import com.setlisto.service.impl.TicketServiceImpl;

public class ReservaSearchView extends AbstractView {

	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	private JTextField clienteIdTF;
	private JTextField eventoIdTF;
	private JTextField codigoTF;
	private JTable table;
	private DefaultTableModel tableModel;
	private TicketService ticketService = new TicketServiceImpl();

	public ReservaSearchView() {
		setName("Reservas");
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(8, 8));
		JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));
		clienteIdTF = new JTextField(8);
		eventoIdTF = new JTextField(8);
		codigoTF = new JTextField(10);
		JButton buscarButton = new JButton("Buscar");
		search.add(new JLabel("Cliente id"));
		search.add(clienteIdTF);
		search.add(new JLabel("Evento id"));
		search.add(eventoIdTF);
		search.add(new JLabel("Codigo"));
		search.add(codigoTF);
		search.add(buscarButton);
		add(search, BorderLayout.NORTH);

		tableModel = new DefaultTableModel(new String[] { "Id", "Codigo", "Cliente", "Evento", "Zona", "Precio", "Compra", "Pago" }, 0);
		table = new JTable(tableModel);
		add(new JScrollPane(table), BorderLayout.CENTER);

		buscarButton.addActionListener(e -> buscar());
	}

	private void buscar() {
		try {
			tableModel.setRowCount(0);
			String code = codigoTF.getText().trim();
			if (!code.isEmpty()) {
				TicketDTO ticket = ticketService.findByCode(code);
				if (ticket != null) {
					addTicket(ticket);
				}
				return;
			}

			TicketCriteria criteria = new TicketCriteria();
			if (!clienteIdTF.getText().trim().isEmpty()) {
				criteria.setClienteId(Long.valueOf(clienteIdTF.getText().trim()));
			}
			if (!eventoIdTF.getText().trim().isEmpty()) {
				criteria.setEventoId(Long.valueOf(eventoIdTF.getText().trim()));
			}
			Results<TicketDTO> results = ticketService.findByCriteria(criteria, 1, 100);
			List<TicketDTO> tickets = results.getPage();
			for (TicketDTO ticket : tickets) {
				addTicket(ticket);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "No fue posible buscar reservas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addTicket(TicketDTO ticket) {
		tableModel.addRow(new Object[] {
				ticket.getId(),
				ticket.getCodigo(),
				ticket.getClienteNombre(),
				ticket.getEventoNombre(),
				ticket.getEventZoneSectionName() != null ? ticket.getEventZoneSectionName() : ticket.getPlazaFila() + "-" + ticket.getPlazaNumero(),
				ticket.getPrecio(),
				ticket.getFechaCompra() != null ? ticket.getFechaCompra().format(DATE_FORMAT) : "",
				ticket.getPagoId()
		});
	}
}
