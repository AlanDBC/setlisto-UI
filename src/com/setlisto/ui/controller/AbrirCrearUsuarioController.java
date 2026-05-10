package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.UsuariosView;

public class AbrirCrearUsuarioController extends AbstractController implements ActionListener {

	private UsuariosView view;

	public AbrirCrearUsuarioController() {
		super("Crear", new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
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
