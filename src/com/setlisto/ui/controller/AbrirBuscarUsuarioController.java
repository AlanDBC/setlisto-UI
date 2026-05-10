package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.UsuariosView;

public class AbrirBuscarUsuarioController extends AbstractController implements ActionListener {

	private UsuariosView view;

	public AbrirBuscarUsuarioController() {
		super("Gestion de Usuarios", new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1312_kdmconfig_kdmconfig.png")));
	}

	@Override
	public void doAction() {
		if (view == null) {
			view = new UsuariosView();
		}
		view.mostrarClientes();
		MainWindow.getInstance().addTab(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
}
