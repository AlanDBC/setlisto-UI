package com.setlisto.ui.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.setlisto.model.TicketDTO;

public class ReservaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	private static final String[] COLUMN_NAMES = {
			"Id", "Codigo", "Cliente", "Evento", "Zona", "Precio", "Compra", "Pago"
	};

	private List<TicketDTO> tickets = new ArrayList<>();

	public ReservaTableModel() {
	}

	public ReservaTableModel(List<TicketDTO> tickets) {
		setTickets(tickets);
	}

	@Override
	public int getRowCount() {
		return tickets.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TicketDTO ticket = getTicketAt(rowIndex);
		if (ticket == null) {
			return null;
		}

		switch (columnIndex) {
		case 0:
			return ticket.getId();
		case 1:
			return ticket.getCodigo();
		case 2:
			return ticket.getClienteNombre();
		case 3:
			return ticket.getEventoNombre();
		case 4:
			return ticket.getZonaEventoNombre() != null ? ticket.getZonaEventoNombre() : ticket.getPlazaFila() + "-" + ticket.getPlazaNumero();
		case 5:
			return ticket.getPrecio();
		case 6:
			return ticket.getFechaCompra() != null ? DATE_TIME_FORMATTER.format(ticket.getFechaCompra()) : "";
		case 7:
			return ticket.getPagoId();
		default:
			return "";
		}
	}

	public void setTickets(List<TicketDTO> tickets) {
		if (tickets == null) {
			this.tickets = new ArrayList<>();
		} else {
			this.tickets = tickets;
		}
		fireTableDataChanged();
	}

	public TicketDTO getTicketAt(int row) {
		if (row < 0 || row >= tickets.size()) {
			return null;
		}
		return tickets.get(row);
	}
}