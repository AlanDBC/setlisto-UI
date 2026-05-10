package com.setlisto.ui.view;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import com.setlisto.model.CategoriaAsiento;
import com.setlisto.ui.controller.AdjuntarImagenButtonController;
import com.setlisto.ui.controller.AceptarPlazasButtonController;
import com.setlisto.ui.controller.PlazasConfigController;
import com.setlisto.ui.controller.LlenarComboPlazasConfigController;
import com.setlisto.ui.controller.ZonaConfigurada;
import com.setlisto.ui.renderer.CategoriaAsientoCBRenderer;

/**
 *  Esta vista estara dentro de un JDialog que nace de dar clic al boton "Configurar Asientos" en la vista de "Crear Evento".
 *  Aqui se configurara el numero y ubicacion de los asientos para el evento que se esta creando.
 *  Se subira una imagen tipo plano del lugar del evento, y se podra marcar en ella la ubicacion de los asientos, 
 *  ademas de configurar el numero de asientos por cada ubicacion.
 *  Para el MVP se podra configurar un numero de asientos con su categoria a partir de un rectangulo que se dibuje sobre la imagen del plano, 
 *  Una vez que se dibuje el rectangulo, se podra configurar el numero de asientos y su categoria (VIP, General, etc) para esa ubicacion.
 */
public class PlazasConfigView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton adjuntarImgButton;
	private JButton aceptarButton;
	private JButton limpiarButton;
	private JButton cancelarButton;
	private PlazasMapaPanel mapaPanel;
	private JSpinner numeroAsientosSpinner;
	private JComboBox categoriaCB;
	private ZonaConfigurada zonaActual; // Para mantener referencia a la zona que se está editando
	private EventoCreateView receptor;
	private JLabel seccionSelectLabel;

	/**
	 * Create the panel.
	 */
	public PlazasConfigView(EventoCreateView receptor) {
		this.receptor = receptor;
		setName("Configurar Asientos"); 
		initialize();
		postInitialize();
	}

	private void initialize() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Dimensiones iniciales del dialog, se pueden ajustar segun sea necesario
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
		gbl_rightPanel.rowHeights = new int[]{13, 0, 0, 0, 0, 0, 0, 0};
		gbl_rightPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_rightPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_numeroAsientosSpinner.gridx = 0;
		gbc_numeroAsientosSpinner.gridy = 6;
		rightPanel.add(numeroAsientosSpinner, gbc_numeroAsientosSpinner);
	}

	public void postInitialize() {
		setControllers();
		setRenderers();
		// inicialmente el panel derecho esta desactivado, se activara una vez que se dibuje un 
		// rectangulo en el mapa para configurar la categoria y numero de asientos de esa seccion
		setRightPanelActive(false);
	}
	
	public void setControllers() {
		// Agregar Listener al panel del mapa para detectar pressed y released despues de deslizar el mouse y asi dibujar el
		// rectangulo que representa la seccion de asientos que se esta configurando
		LlenarComboPlazasConfigController llenarComboController = new LlenarComboPlazasConfigController(this);

		PlazasConfigController controller = new PlazasConfigController(this);
		mapaPanel.addMouseListener(controller);
		mapaPanel.addMouseMotionListener(controller);
		
		AceptarPlazasButtonController aceptarController = new AceptarPlazasButtonController(this, receptor);
		aceptarButton.setAction(aceptarController);

		AdjuntarImagenButtonController adjuntarController = new AdjuntarImagenButtonController(this);
		adjuntarImgButton.setAction(adjuntarController);
		
		// Binding en tiempo real: Actualizar zonaActual al editar los campos
	    categoriaCB.addActionListener(e -> {
	    	if (zonaActual != null && categoriaCB.getSelectedItem() instanceof CategoriaAsiento) {
	            zonaActual.setCategoria((CategoriaAsiento) categoriaCB.getSelectedItem());
	        }
	    });
	    
	    numeroAsientosSpinner.addChangeListener(e -> {
	        if (zonaActual != null) {
	            zonaActual.setCantidad((Integer) numeroAsientosSpinner.getValue());
	        }
	    });
		
		cancelarButton.addActionListener(e -> this.dispose()); // Simplemente cerrar la ventana sin guardar cambios
	}
	
	public void setRenderers() {
		categoriaCB.setRenderer(new CategoriaAsientoCBRenderer());
	}

	public PlazasMapaPanel getMapaPanel() {
		return mapaPanel;
	}

	public void setRightPanelActive(boolean active) {
		categoriaCB.setEnabled(active);
		numeroAsientosSpinner.setEnabled(active);
	}

	public JComboBox getCategoriaCB() {
		return categoriaCB;
	}

	// Actualiza los valores del panel lateral cuando se selecciona una zona
	public void setZonaActual(ZonaConfigurada zona) {
	    this.zonaActual = zona;
	    mapaPanel.setZonaSeleccionada(zona); // Feedback visual
	    
	    if (zona != null) {
	        setRightPanelActive(true);
	        // Poblar campos con los datos de la zona seleccionada
	        if (zona.getCategoria() != null) {
	            categoriaCB.setSelectedItem(zona.getCategoria());
	        } else {
	            categoriaCB.setSelectedIndex(0);
	        }
	        numeroAsientosSpinner.setValue(zona.getCantidad() > 0 ? zona.getCantidad() : 0);

	         seccionSelectLabel.setText("Seccion Seleccionada: " + zona.getSeccion()); 
	    } else {
	        setRightPanelActive(false);
	    }
	}
	
	public ZonaConfigurada cargarDatosZona() {
		if (zonaActual != null) {
			zonaActual.setCategoria((CategoriaAsiento) categoriaCB.getSelectedItem());
			zonaActual.setCantidad((Integer) numeroAsientosSpinner.getValue());
			return zonaActual;
		}
		return null;
	}
	
	public void addZona(ZonaConfigurada zona) {
		mapaPanel.agregarZona(zona);
	}

}
