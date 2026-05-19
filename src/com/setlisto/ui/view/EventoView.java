package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import com.setlisto.model.Artista;
import com.setlisto.model.EstadoEvento;
import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.model.GeneroMusical;
import com.setlisto.model.Organizador;
import com.setlisto.model.SubGeneroMusical;
import com.setlisto.model.SubGeneroMusicalDTO;
import com.setlisto.model.SubTipoEventoDTO;
import com.setlisto.model.TipoEvento;
import com.setlisto.service.ArtistaService;
import com.setlisto.service.EstadoEventoService;
import com.setlisto.service.EventoMusicalService;
import com.setlisto.service.GeneroMusicalService;
import com.setlisto.service.OrganizadorService;
import com.setlisto.service.SubGeneroMusicalService;
import com.setlisto.service.SubTipoEventoService;
import com.setlisto.service.TipoEventoService;
import com.setlisto.service.impl.ArtistaServiceImpl;
import com.setlisto.service.impl.EstadoEventoServiceImpl;
import com.setlisto.service.impl.EventoMusicalServiceImpl;
import com.setlisto.service.impl.GeneroMusicalServiceImpl;
import com.setlisto.service.impl.OrganizadorServiceImpl;
import com.setlisto.service.impl.SubGeneroMusicalServiceImpl;
import com.setlisto.service.impl.SubTipoEventoServiceImpl;
import com.setlisto.service.impl.TipoEventoServiceImpl;
import com.setlisto.ui.controller.AbrirReservaController;
import com.setlisto.ui.controller.ListaSeleccionableController;
import com.setlisto.ui.controller.RelacionGeneroSubgeneroController;
import com.setlisto.ui.filters.HorasDF;
import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.mapper.EventoMusicalMapper;
import com.setlisto.ui.renderer.EstadoCBRenderer;
import com.setlisto.ui.renderer.ItemSeleccionableRenderer;
import com.setlisto.ui.renderer.NamedListCellRenderer;
import com.setlisto.ui.renderer.OrganizadorCBRenderer;
import com.setlisto.ui.renderer.SubtipoEventoCBRenderer;
import com.setlisto.ui.renderer.TipoEventoCBRenderer;
import com.setlisto.ui.selectable.ListSeleccionableModel;
import com.setlisto.ui.utils.UIUtils;
import com.toedter.calendar.JDateChooser;

public class EventoView extends AbstractView {

	private static final long serialVersionUID = 1L;

	private JTextField nombreTF;
	private JTextArea descripcionTA;
	private JComboBox organizadorCB;
	private JComboBox estadoCB;
	private JComboBox tipoCB;
	private JComboBox subtipoCB;
	private JDateChooser fechaInicioDC;
	private JFormattedTextField horaInicioFTF;
	private JDateChooser fechaFinDC;
	private JFormattedTextField horaFinFTF;
	private JLabel lugarLabel;
	private JLabel direccionLabel;
	private JLabel zonaHorariaLabel;
	private JLabel capacidadLabel;
	private JLabel entradasRestantesLabel;
	private JList generosList;
	private JList subgenerosList;
	private JList artistasList;
	private JButton volverButton;
	private JButton editarButton;
	private JButton reservarButton;

	private EventoMusicalDTO model;
	private boolean editable;

	private EventoMusicalService eventoService = new EventoMusicalServiceImpl();
	private OrganizadorService organizadorService = new OrganizadorServiceImpl();
	private EstadoEventoService estadoService = new EstadoEventoServiceImpl();
	private TipoEventoService tipoService = new TipoEventoServiceImpl();
	private SubTipoEventoService subtipoService = new SubTipoEventoServiceImpl();
	private GeneroMusicalService generoService = new GeneroMusicalServiceImpl();
	private SubGeneroMusicalService subgeneroService = new SubGeneroMusicalServiceImpl();
	private ArtistaService artistaService = new ArtistaServiceImpl();
	private EventoMusicalMapper mapper = new EventoMusicalMapper();
	private ListSeleccionableModel<GeneroMusical> generosModel;
	private ListSeleccionableModel<SubGeneroMusical> subgenerosModel;
	private ListSeleccionableModel<Artista> artistasModel;
	private MouseListener generosMouseListener;
	private MouseListener subgenerosMouseListener;
	private MouseListener artistasMouseListener;

	public EventoView() {
		initialize();
		postInitialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(10, 10));

		JPanel form = new JPanel(new GridBagLayout());
		add(new JScrollPane(form), BorderLayout.CENTER);

		int y = 0;
		nombreTF = new JTextField(30);
		descripcionTA = new JTextArea(4, 30);
		organizadorCB = new JComboBox();
		estadoCB = new JComboBox();
		tipoCB = new JComboBox();
		subtipoCB = new JComboBox();
		fechaInicioDC = new JDateChooser();
		horaInicioFTF = new JFormattedTextField();
		fechaFinDC = new JDateChooser();
		horaFinFTF = new JFormattedTextField();
		lugarLabel = new JLabel();
		direccionLabel = new JLabel();
		zonaHorariaLabel = new JLabel();
		capacidadLabel = new JLabel();
		entradasRestantesLabel = new JLabel();
		generosList = new JList();
		subgenerosList = new JList();
		artistasList = new JList();

		addRow(form, y++, "Nombre", nombreTF);
		addRow(form, y++, "Organizador", organizadorCB);
		addRow(form, y++, "Estado", estadoCB);
		addRow(form, y++, "Descripcion", new JScrollPane(descripcionTA));
		addRow(form, y++, "Inicio", pair(fechaInicioDC, horaInicioFTF));
		addRow(form, y++, "Fin", pair(fechaFinDC, horaFinFTF));
		addRow(form, y++, "Lugar", lugarLabel);
		addRow(form, y++, "Direccion", direccionLabel);
		addRow(form, y++, "Zona horaria", zonaHorariaLabel);
		addRow(form, y++, "Capacidad", capacidadLabel);
		addRow(form, y++, "Entradas restantes", entradasRestantesLabel);
		addRow(form, y++, "Tipo", tipoCB);
		addRow(form, y++, "Subtipo", subtipoCB);
		addRow(form, y++, "Generos", new JScrollPane(generosList));
		addRow(form, y++, "Subgeneros", new JScrollPane(subgenerosList));
		addRow(form, y++, "Artistas", new JScrollPane(artistasList));

		JPanel south = new JPanel();
		volverButton = new JButton("Volver");
		editarButton = new JButton("Editar");
		reservarButton = new JButton("Reservar");
		south.add(volverButton);
		south.add(editarButton);
		south.add(reservarButton);
		add(south, BorderLayout.SOUTH);
	}

	private void postInitialize() {
		horaInicioFTF.setText("00 : 00");
		horaFinFTF.setText("00 : 00");
		((AbstractDocument) horaInicioFTF.getDocument()).setDocumentFilter(new HorasDF());
		((AbstractDocument) horaFinFTF.getDocument()).setDocumentFilter(new HorasDF());

		organizadorCB.setRenderer(new OrganizadorCBRenderer());
		estadoCB.setRenderer(new EstadoCBRenderer());
		tipoCB.setRenderer(new TipoEventoCBRenderer());
		subtipoCB.setRenderer(new SubtipoEventoCBRenderer());
		generosList.setCellRenderer(new NamedListCellRenderer());
		subgenerosList.setCellRenderer(new NamedListCellRenderer());
		artistasList.setCellRenderer(new NamedListCellRenderer());

		volverButton.addActionListener(e -> MainWindow.getInstance().removeTab(this));
		editarButton.addActionListener(e -> {
			if (editable) {
				guardarCambios();
			} else {
				setEditable(true);
				editarButton.setText("Guardar");
			}
		});

		reservarButton.setAction(new AbrirReservaController(this));

		setEditable(false);
	}

	public void setModel(EventoMusicalDTO em) {
		this.model = em;
		setName(em.getNombre());
		nombreTF.setText(em.getNombre());
		descripcionTA.setText(em.getDescripcion());
		fechaInicioDC.setDate(em.getFechaInicio() != null ? Date.valueOf(em.getFechaInicio().toLocalDate()) : null);
		horaInicioFTF.setText(em.getFechaInicio() != null ? String.format("%02d : %02d", em.getFechaInicio().getHour(), em.getFechaInicio().getMinute()) : "00 : 00");
		fechaFinDC.setDate(em.getFechaFin() != null ? Date.valueOf(em.getFechaFin().toLocalDate()) : null);
		horaFinFTF.setText(em.getFechaFin() != null ? String.format("%02d : %02d", em.getFechaFin().getHour(), em.getFechaFin().getMinute()) : "00 : 00");
		lugarLabel.setText(em.getLugarNombre() != null ? em.getLugarNombre() : "");
		direccionLabel.setText(em.getLugarDireccion() != null ? em.getLugarDireccion() : "");
		zonaHorariaLabel.setText(em.getZonaHorariaNombre() != null ? em.getZonaHorariaNombre() : "");
		capacidadLabel.setText(em.getCapacidad() != null ? em.getCapacidad().toString() : "0");
		entradasRestantesLabel.setText(em.getEntradasRestantes() != null ? em.getEntradasRestantes().toString() : "0");
		generosList.setListData(em.getGeneros() != null ? em.getGeneros().toArray() : new Object[0]);
		subgenerosList.setListData(em.getSubGeneros() != null ? em.getSubGeneros().toArray() : new Object[0]);
		artistasList.setListData(em.getArtistas() != null ? em.getArtistas().toArray() : new Object[0]);
		cargarCombos(em);
	}

	public EventoMusicalDTO getModel() {
		return this.model;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		nombreTF.setEditable(editable);
		descripcionTA.setEditable(editable);
		organizadorCB.setEnabled(editable);
		estadoCB.setEnabled(editable);
		tipoCB.setEnabled(editable);
		subtipoCB.setEnabled(editable);
		fechaInicioDC.setEnabled(editable);
		horaInicioFTF.setEditable(editable);
		fechaFinDC.setEnabled(editable);
		horaFinFTF.setEditable(editable);
		generosList.setEnabled(editable);
		subgenerosList.setEnabled(editable);
		artistasList.setEnabled(editable);
		if (model != null) {
			if (editable) {
				activarListasEditables();
			} else {
				mostrarListasDetalle();
			}
		}
	}

	private void mostrarListasDetalle() {
		desconectarListasEditables();
		generosList.setCellRenderer(new NamedListCellRenderer());
		subgenerosList.setCellRenderer(new NamedListCellRenderer());
		artistasList.setCellRenderer(new NamedListCellRenderer());
		generosList.setListData(model.getGeneros() != null ? model.getGeneros().toArray() : new Object[0]);
		subgenerosList.setListData(model.getSubGeneros() != null ? model.getSubGeneros().toArray() : new Object[0]);
		artistasList.setListData(model.getArtistas() != null ? model.getArtistas().toArray() : new Object[0]);
	}

	private void activarListasEditables() {
		try {
			desconectarListasEditables();
			generosModel = new ListSeleccionableModel<GeneroMusical>();
			subgenerosModel = new ListSeleccionableModel<SubGeneroMusical>();
			artistasModel = new ListSeleccionableModel<Artista>();

			List<SubGeneroMusicalDTO> subgeneros = subgeneroService.findAll();
			generosModel.cargarItems(generoService.findAll());
			artistasModel.cargarItems(artistaService.findAll());

			marcarGeneros(model.getGeneros());
			RelacionGeneroSubgeneroController relacion = new RelacionGeneroSubgeneroController(generosModel, subgenerosModel, subgeneros);
			relacion.actualizarDependencias();
			marcarSubgeneros(model.getSubGeneros());
			marcarArtistas(model.getArtistas());

			generosList.setModel(generosModel);
			subgenerosList.setModel(subgenerosModel);
			artistasList.setModel(artistasModel);
			generosList.setCellRenderer(new ItemSeleccionableRenderer());
			subgenerosList.setCellRenderer(new ItemSeleccionableRenderer());
			artistasList.setCellRenderer(new ItemSeleccionableRenderer());

			generosMouseListener = new ListaSeleccionableController<GeneroMusical>(generosList, generosModel, relacion);
			subgenerosMouseListener = new ListaSeleccionableController<SubGeneroMusical>(subgenerosList, subgenerosModel, null);
			artistasMouseListener = new ListaSeleccionableController<Artista>(artistasList, artistasModel, null);
			generosList.addMouseListener(generosMouseListener);
			subgenerosList.addMouseListener(subgenerosMouseListener);
			artistasList.addMouseListener(artistasMouseListener);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "No fue posible preparar las listas editables: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void desconectarListasEditables() {
		if (generosMouseListener != null) {
			generosList.removeMouseListener(generosMouseListener);
			generosMouseListener = null;
		}
		if (subgenerosMouseListener != null) {
			subgenerosList.removeMouseListener(subgenerosMouseListener);
			subgenerosMouseListener = null;
		}
		if (artistasMouseListener != null) {
			artistasList.removeMouseListener(artistasMouseListener);
			artistasMouseListener = null;
		}
	}

	private void marcarGeneros(List<GeneroMusical> generos) {
		if (generos == null) {
			return;
		}
		for (GeneroMusical genero : generos) {
			generosModel.marcarItem(genero);
		}
	}

	private void marcarSubgeneros(List<SubGeneroMusical> subgeneros) {
		if (subgeneros == null) {
			return;
		}
		for (SubGeneroMusical subgenero : subgeneros) {
			subgenerosModel.marcarItem(subgenero);
		}
	}

	private void marcarArtistas(List<Artista> artistas) {
		if (artistas == null) {
			return;
		}
		for (Artista artista : artistas) {
			artistasModel.marcarItem(artista);
		}
	}

	private void cargarCombos(EventoMusicalDTO em) {
		try {
			organizadorCB.setModel(UIUtils.crearModelo(organizadorService.findAll(), new Organizador(null, "Seleccionar")));
			estadoCB.setModel(UIUtils.crearModelo(estadoService.findAll(), new EstadoEvento(null, "Seleccionar")));
			tipoCB.setModel(UIUtils.crearModelo(tipoService.findAll(), new TipoEvento(null, "Seleccionar")));
			selectById(organizadorCB, em.getIdOrganizador());
			selectById(estadoCB, em.getIdEstado());
			selectById(tipoCB, em.getIdTipo());

			if (em.getIdTipo() != null) {
				subtipoCB.setModel(UIUtils.crearModelo(subtipoService.findByTipoEvento(em.getIdTipo()), new SubTipoEventoDTO(null, "Seleccionar")));
			} else {
				subtipoCB.setModel(new DefaultComboBoxModel());
			}
			selectById(subtipoCB, em.getIdSubtipo());

			tipoCB.addActionListener(e -> {
				TipoEvento tipo = (TipoEvento) tipoCB.getSelectedItem();
				if (tipo != null && tipo.getId() != null) {
					try {
						subtipoCB.setModel(UIUtils.crearModelo(subtipoService.findByTipoEvento(tipo.getId()), new SubTipoEventoDTO(null, "Seleccionar")));
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "No fue posible cargar subtipos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "No fue posible cargar el evento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void guardarCambios() {
		try {
			model.setNombre(nombreTF.getText().trim());
			model.setDescripcion(descripcionTA.getText().trim());
			model.setFechaInicio(combinarFechaHora(fechaInicioDC, horaInicioFTF));
			model.setFechaFin(combinarFechaHora(fechaFinDC, horaFinFTF));
			Organizador org = (Organizador) organizadorCB.getSelectedItem();
			EstadoEvento estado = (EstadoEvento) estadoCB.getSelectedItem();
			SubTipoEventoDTO subtipo = (SubTipoEventoDTO) subtipoCB.getSelectedItem();
			if (org != null) model.setIdOrganizador(org.getId());
			if (estado != null) model.setIdEstado(estado.getId());
			if (subtipo != null) model.setIdSubtipo(subtipo.getId());
			if (generosModel != null && subgenerosModel != null && artistasModel != null) {
				mapper.mapUItoDTO(model, generosModel, subgenerosModel, artistasModel);
			}
			eventoService.update(model);
			setModel(eventoService.findById(model.getId()));
			setEditable(false);
			editarButton.setText("Editar");
			JOptionPane.showMessageDialog(this, "Evento actualizado correctamente.", "Operacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "No fue posible guardar el evento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JPanel pair(Component left, Component right) {
		JPanel panel = new JPanel();
		panel.add(left);
		panel.add(right);
		return panel;
	}

	private void addRow(JPanel panel, int y, String label, Component component) {
		GridBagConstraints labelGbc = new GridBagConstraints();
		labelGbc.gridx = 0;
		labelGbc.gridy = y;
		labelGbc.insets = new Insets(4, 8, 4, 8);
		labelGbc.anchor = GridBagConstraints.WEST;
		panel.add(new JLabel(label), labelGbc);

		GridBagConstraints componentGbc = new GridBagConstraints();
		componentGbc.gridx = 1;
		componentGbc.gridy = y;
		componentGbc.weightx = 1.0;
		componentGbc.fill = GridBagConstraints.HORIZONTAL;
		componentGbc.insets = new Insets(4, 8, 4, 8);
		panel.add(component, componentGbc);
	}

	private void selectById(JComboBox combo, Long id) {
		if (id == null) return;
		for (int i = 0; i < combo.getItemCount(); i++) {
			Object item = combo.getItemAt(i);
			Long itemId = getId(item);
			if (id.equals(itemId)) {
				combo.setSelectedIndex(i);
				return;
			}
		}
	}

	private Long getId(Object item) {
		if (item instanceof Organizador) return ((Organizador) item).getId();
		if (item instanceof EstadoEvento) return ((EstadoEvento) item).getId();
		if (item instanceof TipoEvento) return ((TipoEvento) item).getId();
		if (item instanceof SubTipoEventoDTO) return ((SubTipoEventoDTO) item).getId();
		return null;
	}

	private LocalDateTime combinarFechaHora(JDateChooser chooser, JFormattedTextField horaFTF) {
		if (chooser.getDate() == null) return null;
		LocalDate fecha = chooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String cleanTime = horaFTF.getText().replace(" ", "").replace(":", "");
		return LocalDateTime.of(fecha, LocalTime.of(Integer.parseInt(cleanTime.substring(0, 2)), Integer.parseInt(cleanTime.substring(2, 4))));
	}
}
