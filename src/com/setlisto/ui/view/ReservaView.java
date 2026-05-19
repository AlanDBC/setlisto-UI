package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.setlisto.model.CategoriaAsiento;
import com.setlisto.model.EventZone;
import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.model.TicketDTO;
import com.setlisto.service.EventZoneService;
import com.setlisto.service.ReservaService;
import com.setlisto.service.impl.EventZoneServiceImpl;
import com.setlisto.service.impl.ReservaServiceImpl;
import com.setlisto.ui.controller.ZonaConfigurada;

public class ReservaView extends AbstractView {

	private static final long serialVersionUID = 1L;
	private static final Long PAYMENT_METHOD_VISA = 1L;
	private static final Long CURRENCY_EUR = 1L;

	private EventoMusicalDTO evento;
	private PlazasMapaPanel mapaPanel;
	private JTextField clienteIdTF;
	private JSpinner cantidadSpinner;
	private JLabel zonaSeleccionadaLabel;
	private JButton comprarButton;
	private ZonaConfigurada zonaSeleccionada;
	private EventZoneService eventZoneService = new EventZoneServiceImpl();
	private ReservaService reservaService = new ReservaServiceImpl();

	public ReservaView(EventoMusicalDTO evento) {
		this.evento = evento;
		setName("Reservar - " + evento.getNombre());
		initialize();
		cargarZonas();
	}

	private void initialize() {
		setLayout(new BorderLayout(8, 8));
		mapaPanel = new PlazasMapaPanel();
		add(mapaPanel, BorderLayout.CENTER);

		JPanel right = new JPanel(new GridBagLayout());
		add(right, BorderLayout.EAST);

		clienteIdTF = new JTextField(12);
		cantidadSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
		zonaSeleccionadaLabel = new JLabel("(sin zona)");
		comprarButton = new JButton("Comprar");

		addRow(right, 0, "Cliente id", clienteIdTF);
		addRow(right, 1, "Cantidad", cantidadSpinner);
		addRow(right, 2, "Zona", zonaSeleccionadaLabel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(5, 5, 5, 5);
		right.add(comprarButton, gbc);

		mapaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionarZona(e);
			}
		});
		comprarButton.addActionListener(e -> comprar());
	}

	private void cargarZonas() {
		try {
			List<EventZone> zonas = eventZoneService.findByEventId(evento.getId());
			List<ZonaConfigurada> configuradas = new ArrayList<ZonaConfigurada>();
			for (EventZone zona : zonas) {
				ZonaConfigurada zc = new ZonaConfigurada();
				zc.setId(zona.getId());
				zc.setSeccion(zona.getSectionName());
				zc.setCantidad(zona.getTotalCapacity() != null ? zona.getTotalCapacity() : 0);
				zc.setDisponibles(zona.getAvailableCapacity());
				zc.setPrecio(zona.getBasePrice());
				zc.setCategoria(new CategoriaAsiento(zona.getSeatCategoryId(), zona.getSeatCategoryName()));
				zc.setArea(new Rectangle(nvl(zona.getPosX()), nvl(zona.getPosY()), nvl(zona.getWidth()), nvl(zona.getHeight())));
				configuradas.add(zc);
			}
			mapaPanel.setZonas(configuradas);
			cargarImagenPlano();
			mapaPanel.repaint();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "No fue posible cargar las zonas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarImagenPlano() {
		String rutaImagen = evento.getRutaImagenPlano();
		if (rutaImagen == null || rutaImagen.trim().isEmpty()) {
			mapaPanel.setBackgroundImage(null);
			return;
		}
		try {
			mapaPanel.setBackgroundImage(ImageIO.read(new File(rutaImagen)));
		} catch (Exception ignored) {
			mapaPanel.setBackgroundImage(null);
		}
	}

	private int nvl(Integer value) {
		return value == null ? 0 : value.intValue();
	}

	private void seleccionarZona(MouseEvent e) {
		for (ZonaConfigurada zona : mapaPanel.getZonas()) {
			if (zona.getArea() != null && zona.getArea().contains(e.getPoint())) {
				zonaSeleccionada = zona;
				mapaPanel.setZonaSeleccionada(zona);
				zonaSeleccionadaLabel.setText(zona.getSeccion() + " - precio: " + zona.getPrecio()
						+ " - disponibilidad: " + zona.getDisponibles());
				return;
			}
		}
		zonaSeleccionada = null;
		mapaPanel.setZonaSeleccionada(null);
		zonaSeleccionadaLabel.setText("(sin zona)");
	}

	private void comprar() {
		try {
			if (zonaSeleccionada == null) {
				JOptionPane.showMessageDialog(this, "Selecciona una zona del mapa.", "Reserva", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Long clienteId = Long.valueOf(clienteIdTF.getText().trim());
			int cantidad = ((Integer) cantidadSpinner.getValue()).intValue();
			List<TicketDTO> tickets = reservaService.comprarZona(zonaSeleccionada.getId(), clienteId, cantidad, PAYMENT_METHOD_VISA, CURRENCY_EUR);
			JOptionPane.showMessageDialog(this, "Compra simulada correctamente. Tickets emitidos: " + tickets.size(), "Reserva", JOptionPane.INFORMATION_MESSAGE);
			cargarZonas();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "No fue posible completar la reserva: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addRow(JPanel panel, int y, String label, java.awt.Component component) {
		GridBagConstraints l = new GridBagConstraints();
		l.gridx = 0;
		l.gridy = y;
		l.insets = new Insets(5, 5, 5, 5);
		l.anchor = GridBagConstraints.WEST;
		panel.add(new JLabel(label), l);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = y;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(component, c);
	}
}
