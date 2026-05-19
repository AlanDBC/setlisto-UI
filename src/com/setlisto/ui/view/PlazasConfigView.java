package com.setlisto.ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.imageio.ImageIO;

import com.setlisto.model.CategoriaAsiento;
import com.setlisto.ui.controller.AdjuntarImagenButtonController;
import com.setlisto.ui.controller.AceptarPlazasButtonController;
import com.setlisto.ui.controller.PlazasConfigController;
import com.setlisto.ui.controller.LlenarComboPlazasConfigController;
import com.setlisto.ui.controller.ZonaConfigurada;
import com.setlisto.ui.renderer.CategoriaAsientoCBRenderer;

import java.math.BigDecimal;

public class PlazasConfigView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton adjuntarImgButton;
	private JButton aceptarButton;
	private JButton limpiarButton;
	private JButton cancelarButton;
	private PlazasMapaPanel mapaPanel;
	private JSpinner numeroAsientosSpinner;
	private JComboBox categoriaCB;
	private JTextField precioTF;
	private ZonaConfigurada zonaActual;
	private EventoCreateView receptor;
	private JLabel seccionSelectLabel;
	private String rutaImagenPlano;
	
	// Bandera añadida para evitar sobrescrituras de eventos al cambiar de zonas
	private boolean isUpdating = false;

	public PlazasConfigView(EventoCreateView receptor) {
		this.receptor = receptor;
		setName("Configurar Asientos"); 
		initialize();
		postInitialize();
		cargarEstadoReceptor();
	}

	private void initialize() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1000, 800));
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);

		adjuntarImgButton = new JButton("Adjuntar Imagen");
		northPanel.add(adjuntarImgButton);

		mapaPanel = new PlazasMapaPanel();
		getContentPane().add(mapaPanel, BorderLayout.CENTER);
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);

		cancelarButton = new JButton("Cancelar");
		southPanel.add(cancelarButton);

		limpiarButton = new JButton("Limpiar");
		southPanel.add(limpiarButton);
		Component horizontalGlue = Box.createHorizontalGlue();
		southPanel.add(horizontalGlue);

		aceptarButton = new JButton("Aceptar");
		southPanel.add(aceptarButton);

		JPanel rightPanel = new JPanel();
		getContentPane().add(rightPanel, BorderLayout.EAST);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[]{173, 0};
		gbl_rightPanel.rowHeights = new int[]{13, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_rightPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_rightPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		rightPanel.setLayout(gbl_rightPanel);

		seccionSelectLabel = new JLabel("Seccion Seleccionada: (Sin seleccion)");
		GridBagConstraints gbc_seccionSelectLabel = new GridBagConstraints();
		gbc_seccionSelectLabel.insets = new Insets(0, 0, 5, 0);
		gbc_seccionSelectLabel.anchor = GridBagConstraints.WEST;
		gbc_seccionSelectLabel.gridx = 0;
		gbc_seccionSelectLabel.gridy = 0;
		rightPanel.add(seccionSelectLabel, gbc_seccionSelectLabel);

		JLabel categoriaSeccionLabel = new JLabel("Categoria de Asientos");
		GridBagConstraints gbc_categoriaSeccionLabel = new GridBagConstraints();
		gbc_categoriaSeccionLabel.insets = new Insets(0, 0, 5, 0);
		gbc_categoriaSeccionLabel.gridx = 0;
		gbc_categoriaSeccionLabel.gridy = 2;
		rightPanel.add(categoriaSeccionLabel, gbc_categoriaSeccionLabel);

		categoriaCB = new JComboBox();
		GridBagConstraints gbc_categoriaCB = new GridBagConstraints();
		gbc_categoriaCB.insets = new Insets(0, 0, 5, 0);
		gbc_categoriaCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoriaCB.gridx = 0;
		gbc_categoriaCB.gridy = 3;
		rightPanel.add(categoriaCB, gbc_categoriaCB);

		JLabel numeroAsientosLabel = new JLabel("Numero de Asientos");
		GridBagConstraints gbc_numeroAsientosLabel = new GridBagConstraints();
		gbc_numeroAsientosLabel.insets = new Insets(0, 0, 5, 0);
		gbc_numeroAsientosLabel.gridx = 0;
		gbc_numeroAsientosLabel.gridy = 5;
		rightPanel.add(numeroAsientosLabel, gbc_numeroAsientosLabel);

		numeroAsientosSpinner = new JSpinner();
		GridBagConstraints gbc_numeroAsientosSpinner = new GridBagConstraints();
		gbc_numeroAsientosSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_numeroAsientosSpinner.gridx = 0;
		gbc_numeroAsientosSpinner.gridy = 6;
		rightPanel.add(numeroAsientosSpinner, gbc_numeroAsientosSpinner);

		JLabel precioLabel = new JLabel("Precio Base");
		GridBagConstraints gbc_precioLabel = new GridBagConstraints();
		gbc_precioLabel.insets = new Insets(0, 0, 5, 0);
		gbc_precioLabel.gridx = 0;
		gbc_precioLabel.gridy = 7;
		rightPanel.add(precioLabel, gbc_precioLabel);

		precioTF = new JTextField();
		GridBagConstraints gbc_precioTF = new GridBagConstraints();
		gbc_precioTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_precioTF.gridx = 0;
		gbc_precioTF.gridy = 8;
		rightPanel.add(precioTF, gbc_precioTF);
	}

	public void postInitialize() {
		setControllers();
		setRenderers();
		setRightPanelActive(false);
	}
	
	public void setControllers() {
		LlenarComboPlazasConfigController llenarComboController = new LlenarComboPlazasConfigController(this);
		PlazasConfigController controller = new PlazasConfigController(this);
		mapaPanel.addMouseListener(controller);
		mapaPanel.addMouseMotionListener(controller);
		
		AceptarPlazasButtonController aceptarController = new AceptarPlazasButtonController(this, receptor);
		aceptarButton.setAction(aceptarController);

		AdjuntarImagenButtonController adjuntarController = new AdjuntarImagenButtonController(this);
		adjuntarImgButton.setAction(adjuntarController);
		
		// Utilizamos la bandera !isUpdating para no disparar las lógicas si es manipulación programática
	    categoriaCB.addActionListener(e -> {
	    	if (!isUpdating && zonaActual != null && categoriaCB.getSelectedItem() instanceof CategoriaAsiento) {
	            zonaActual.setCategoria((CategoriaAsiento) categoriaCB.getSelectedItem());
	        }
	    });
	    
	    numeroAsientosSpinner.addChangeListener(e -> {
	        if (!isUpdating && zonaActual != null) {
	            zonaActual.setCantidad((Integer) numeroAsientosSpinner.getValue());
	        }
	    });

	    precioTF.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { actualizarPrecioZona(); }
			public void removeUpdate(DocumentEvent e) { actualizarPrecioZona(); }
			public void changedUpdate(DocumentEvent e) { actualizarPrecioZona(); }
		});
	    
	    cancelarButton.addActionListener(e -> this.dispose());
	}
	
	public void setRenderers() {
		categoriaCB.setRenderer(new CategoriaAsientoCBRenderer());
	}

	public PlazasMapaPanel getMapaPanel() {
		return mapaPanel;
	}

	private void cargarEstadoReceptor() {
		if (receptor.getZonasConfiguradas() != null) {
			mapaPanel.setZonas(receptor.getZonasConfiguradas());
		}
		setRutaImagenPlano(receptor.getRutaImagenPlano());
	}

	public String getRutaImagenPlano() {
		return rutaImagenPlano;
	}

	public void setRutaImagenPlano(String rutaImagenPlano) {
		this.rutaImagenPlano = rutaImagenPlano;
		if (rutaImagenPlano == null || rutaImagenPlano.trim().isEmpty()) {
			mapaPanel.setBackgroundImage(null);
			return;
		}
		try {
			mapaPanel.setBackgroundImage(ImageIO.read(new File(rutaImagenPlano)));
		} catch (Exception ignored) {
			mapaPanel.setBackgroundImage(null);
		}
	}

	public void setRightPanelActive(boolean active) {
		categoriaCB.setEnabled(active);
		numeroAsientosSpinner.setEnabled(active);
		precioTF.setEnabled(active);
	}

	public JComboBox getCategoriaCB() {
		return categoriaCB;
	}

	// Actualiza los valores del panel lateral cuando se selecciona una zona
	public void setZonaActual(ZonaConfigurada zona) {
	    // Hacemos commit seguro por si se ha introducido un número por teclado pero no se le dio al enter o clickó otra cosa
	    if (this.zonaActual != null) {
	        try {
	            numeroAsientosSpinner.commitEdit();
	        } catch (Exception ignored) {}
	    }
	    
	    this.zonaActual = zona;
	    mapaPanel.setZonaSeleccionada(zona); // Feedback visual
	    
	    // Activamos bandera para no alterar el modelo a medida que se carga el formulario visual
	    isUpdating = true;
	    
	    if (zona != null) {
	        setRightPanelActive(true);
	        if (zona.getCategoria() != null) {
	            categoriaCB.setSelectedItem(zona.getCategoria());
	        } else {
	            categoriaCB.setSelectedIndex(0);
	        }
	        numeroAsientosSpinner.setValue(zona.getCantidad() > 0 ? zona.getCantidad() : 0);
	        precioTF.setText(zona.getPrecio() != null ? zona.getPrecio().toPlainString() : "");

	        seccionSelectLabel.setText("Seccion Seleccionada: " + zona.getSeccion());
	    } else {
	        setRightPanelActive(false);
	        seccionSelectLabel.setText("Seccion Seleccionada: (Sin seleccion)");
	        categoriaCB.setSelectedIndex(0);
	        numeroAsientosSpinner.setValue(0);
	        precioTF.setText("");
	    }
	    
	    // Apagamos bandera, ya podemos volver a escuchar acciones de usuario.
	    isUpdating = false;
	}
	
	public ZonaConfigurada cargarDatosZona() {
		if (zonaActual != null) {
			zonaActual.setCategoria((CategoriaAsiento) categoriaCB.getSelectedItem());
			zonaActual.setCantidad((Integer) numeroAsientosSpinner.getValue());
			zonaActual.setPrecio(getPrecioActual());
			return zonaActual;
		}
		return null;
	}
	
	public void addZona(ZonaConfigurada zona) {
		mapaPanel.agregarZona(zona);
	}

	private BigDecimal getPrecioActual() {
		String precio = precioTF.getText().trim().replace(",", ".");
		return precio.isEmpty() ? null : new BigDecimal(precio);
	}

	private void actualizarPrecioZona() {
		if (!isUpdating && zonaActual != null) {
			try {
				zonaActual.setPrecio(getPrecioActual());
			} catch (NumberFormatException ignored) {
				zonaActual.setPrecio(null);
			}
		}
	}
}
