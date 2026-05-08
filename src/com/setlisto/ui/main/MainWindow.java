package com.setlisto.ui.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.setlisto.model.Cliente;
import com.setlisto.model.Organizador;
import com.setlisto.ui.controller.AbrirBuscarEMController;
import com.setlisto.ui.controller.AbrirCrearEMController;
import com.setlisto.ui.controller.LogoutController;
import com.setlisto.ui.view.AbstractView;
import com.setlisto.ui.view.View;

public class MainWindow extends JFrame {

	private static MainWindow instance;
	private JTabbedPane tabbedPane;
	private Object usuarioLogueado;
	private JLabel bienvenidaLabel;
	private JButton buscarUsuarioButton;
	private JButton crearUsuarioButton;
	private JButton buscarEventoButton;
	private JButton crearEventoButton;
	private JButton reservasButton;
	private JButton resenasButton;
	private JButton estadisticasButton;
	private JMenuItem editarMI;
	private JMenuItem cerrarSesionMI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatDarculaLaf());
					getInstance().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	private MainWindow() {
		initialize();
		postInitialize();
		
	}

	public static MainWindow getInstance () {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	} 
/*
	 public MainWindow(Object usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
		initialize();
		postInitialize();
		configurarSegunRol();
	} */

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 465, 310);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		this.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel usuariosFunctionsPanel = new JPanel();
		northPanel.add(usuariosFunctionsPanel);
		usuariosFunctionsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel usuariosFunctionsNorth = new JPanel();
		usuariosFunctionsPanel.add(usuariosFunctionsNorth, BorderLayout.NORTH);
		
		JLabel usuariosLabel = new JLabel("Usuarios");
		usuariosFunctionsNorth.add(usuariosLabel);
		
		JPanel usuariosFunctionsCenter = new JPanel();
		usuariosFunctionsPanel.add(usuariosFunctionsCenter, BorderLayout.SOUTH);
		
		buscarUsuarioButton = new JButton("Buscar");
		buscarUsuarioButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1467_xmag_xmag.png")));
		usuariosFunctionsCenter.add(buscarUsuarioButton);
		
		crearUsuarioButton = new JButton("Crear");
		crearUsuarioButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		usuariosFunctionsCenter.add(crearUsuarioButton);
		
		JPanel eventosFunctionsPanel = new JPanel();
		northPanel.add(eventosFunctionsPanel);
		eventosFunctionsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel eventosFunctionsNorth = new JPanel();
		eventosFunctionsPanel.add(eventosFunctionsNorth, BorderLayout.NORTH);
		
		JLabel eventosLabel = new JLabel("Eventos");
		eventosFunctionsNorth.add(eventosLabel);
		
		JPanel eventosFunctionsCenter = new JPanel();
		eventosFunctionsPanel.add(eventosFunctionsCenter);
		
		buscarEventoButton = new JButton("Buscar");
		buscarEventoButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1467_xmag_xmag.png")));
		eventosFunctionsCenter.add(buscarEventoButton);
		
		crearEventoButton = new JButton("Crear");
		crearEventoButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		eventosFunctionsCenter.add(crearEventoButton);
		
		JPanel estadisticasFunctionsPanel = new JPanel();
		northPanel.add(estadisticasFunctionsPanel);
		estadisticasFunctionsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel estadisticasFunctionsNorth = new JPanel();
		estadisticasFunctionsPanel.add(estadisticasFunctionsNorth, BorderLayout.NORTH);
		
		JLabel gestionLabel = new JLabel("Gestión");
		estadisticasFunctionsNorth.add(gestionLabel);
		
		JPanel estadisticasFunctionsCenter = new JPanel();
		estadisticasFunctionsPanel.add(estadisticasFunctionsCenter);
		
		reservasButton = new JButton("Reservas");
		estadisticasFunctionsCenter.add(reservasButton);
		
		resenasButton = new JButton("Reseñas");
		estadisticasFunctionsCenter.add(resenasButton);
		
		estadisticasButton = new JButton("Estadisticas");
		estadisticasFunctionsCenter.add(estadisticasButton);
		
		JPanel usuarioPanel = new JPanel();
		northPanel.add(usuarioPanel);
		
		JMenuBar perfilUsuarioMenuBar = new JMenuBar();
		usuarioPanel.add(perfilUsuarioMenuBar);
		
		JMenu perfilUsuarioMenu = new JMenu("");
		perfilUsuarioMenu.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1447_man_male_male_man_user_employee_manager_employee_operator_manager_personal_operator_administrator_administrator_personal_user.png")));
		perfilUsuarioMenuBar.add(perfilUsuarioMenu);
		
		bienvenidaLabel = new JLabel("Hola ");
		perfilUsuarioMenu.add(bienvenidaLabel);
		
		editarMI = new JMenuItem("Editar...");
		perfilUsuarioMenu.add(editarMI);
		
		cerrarSesionMI = new JMenuItem("Cerrar Sesion");
		perfilUsuarioMenu.add(cerrarSesionMI);

		JPanel centerPanel = new JPanel();
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centerPanel.add(tabbedPane);
		JPanel panel = new JPanel();
		centerPanel.add(panel, BorderLayout.NORTH);

	}

	private void postInitialize() {
		setAllControllers();
	}

	public void addTab(AbstractView view) { 
		tabbedPane.addTab(view.getName(), view);
		int index = tabbedPane.getTabCount() - 1;
	    tabbedPane.setTabComponentAt(index, new ClosableTabComponent(tabbedPane, view.getName()));
	    tabbedPane.setSelectedIndex(index);
	}
	
	public void removeTab(AbstractView view) {
	    int index = tabbedPane.indexOfComponent(view);
	    if (index != -1) {
	        tabbedPane.remove(index);
	    }
	}
	
	public void setUsuarioLogueado(Object usuario) {
		this.usuarioLogueado = usuario;
		configurarSegunRol();
	}
	
	public Object getUsuarioLogueado() {
		return usuarioLogueado;
	}
	
	private void configurarSegunRol() {
		// 1. Caso de seguridad: nadie logueado
		if (usuarioLogueado == null) {
			bienvenidaLabel.setText("Invitado");
			return;
		}

		// 2. Lógica por roles
		if (usuarioLogueado instanceof Organizador) {
			Organizador org = (Organizador) usuarioLogueado;
			bienvenidaLabel.setText("Hola, " + org.getNombre());
		}
		else if (usuarioLogueado instanceof Cliente) {
			Cliente cli = (Cliente) usuarioLogueado;
			bienvenidaLabel.setText("Hola, " + cli.getNombre());
		}
	}
	
	private void setAllControllers() {
		// Añadimos todos los controladores iniciales
//		TODO Controllers para componentes comentados 	
//		buscarUsuarioButton.setAction(new AbrirBuscarUsuarioController); 
//		crearUsuarioButton.setAction(new AbrirCrearUsuarioController); 
		buscarEventoButton.setAction(new AbrirBuscarEMController());
		crearEventoButton.setAction(new AbrirCrearEMController());
//		reservasButton.setAction(new AbrirReservasController);
//		resenasButton.setAction(new AbrirResenasController);
//		estadisticasButton.setAction(new AbrirEstadisticasController);
		cerrarSesionMI.setAction(new LogoutController());
	}
}


