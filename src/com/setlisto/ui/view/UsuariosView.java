package com.setlisto.ui.view;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;

// Clase contenedora de las vistas (ClienteSearchView y OrganizadorSearchView) para la gestión de usuarios
public class UsuariosView extends AbstractView {

	private static final long serialVersionUID = 1L;

	private JTabbedPane usuariosTabbedPane;
	private ClienteView clienteView;
	private OrganizadorView organizadorView;

	public UsuariosView() {
		initialize();
		setName("Usuarios");
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		usuariosTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(usuariosTabbedPane, BorderLayout.CENTER);

		clienteView = new ClienteView();
		organizadorView = new OrganizadorView();

		usuariosTabbedPane.addTab("Clientes", clienteView);
		usuariosTabbedPane.addTab("Organizadores", organizadorView);
	}

	public void mostrarClientes() {
		usuariosTabbedPane.setSelectedComponent(clienteView);
	}

	public void mostrarOrganizadores() {
		usuariosTabbedPane.setSelectedComponent(organizadorView);
	}
}
