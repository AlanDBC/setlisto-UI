package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.setlisto.ui.main.LoginWindow;
import com.setlisto.ui.main.MainWindow;

/**
 * Controlador para manejar el logout tanto de organizadores como clientes.
 * Esto se deducira a partir del usuarioLogueado de MainWindow.
 * Al hacer logout, se cerrará la ventana principal y se volverá a mostrar la ventana de loginWindow.
 */
public class LogoutController extends AbstractController implements ActionListener {
	
	public LogoutController() {
		super("Cerrar Sesión");
	}
	
	public void doAction() {
		// Confirmación de salida
	    int respuesta = JOptionPane.showConfirmDialog(MainWindow.getInstance(), 
	            "¿Deseas cerrar la sesión actual?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);

	    if (respuesta == JOptionPane.YES_OPTION) {
	        // Destruimos la MainWindow (se libera la memoria)
	    	MainWindow.getInstance().dispose();

	        // Creamos una INSTANCIA NUEVA de LoginWindow
	        // Como el constructor ya tiene initialize() y postInitialize(), 
	        // nace lista para usarse.
	        LoginWindow login = new LoginWindow();
	        login.setVisible(true);
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
	
	

}
