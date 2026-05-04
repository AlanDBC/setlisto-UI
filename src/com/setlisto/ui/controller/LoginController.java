package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.setlisto.model.Cliente;
import com.setlisto.model.Organizador;
import com.setlisto.service.ClienteService;
import com.setlisto.service.OrganizadorService;
import com.setlisto.service.impl.ClienteServiceImpl;
import com.setlisto.service.impl.OrganizadorServiceImpl;
import com.setlisto.ui.main.LoginWindow;
import com.setlisto.ui.main.MainWindow;

/**
 * Controlador para manejar el login tanto de organizadores como clientes.
 * Esto se decedura a partir del CheckBox de rolOrganizador en la ventana de login.
 */
public class LoginController extends AbstractController implements ActionListener{

	private OrganizadorService organizadorService = null;
	private ClienteService clienteService = null;

	public LoginController () {
		super ("Acceder");

		organizadorService = new OrganizadorServiceImpl();
		clienteService = new ClienteServiceImpl();
	}

	public void doAction() {
		boolean esOrganizador = LoginWindow.getInstance().isRolOrganizador();
		String correo = LoginWindow.getInstance().getCorreo();
		String contrasena = LoginWindow.getInstance().getContrasena();

		Object usuarioLogueado = null; // Usamos Object para que pueda ser Cliente u Organizador

		if (esOrganizador) {
			usuarioLogueado = organizadorService.login(correo, contrasena);
		} else {
			usuarioLogueado = clienteService.login(correo, contrasena);
		}

		if (usuarioLogueado != null) {
			// 1. Obtenemos la instancia ÚNICA
	        MainWindow mainWindow = MainWindow.getInstance();
	        
	        // 2. Le pasamos el usuario
	        mainWindow.setUsuarioLogueado(usuarioLogueado);
	        
	        // 3. Mostramos la ventana
	        JFrame framePrincipal = mainWindow;
	        framePrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        framePrincipal.setVisible(true);
	        
	        // 4. Cerramos el Login
	        LoginWindow.getInstance().dispose();
		} else {
			String tipo = esOrganizador ? "organizador" : "cliente";
			JOptionPane.showMessageDialog(LoginWindow.getInstance(), "Credenciales de " + tipo + " incorrectas", "Error de Login", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();	
	}
} // Class
