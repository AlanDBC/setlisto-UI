package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.AdminEventoSearchView;

public class AbrirBuscarEMController extends AbstractController implements ActionListener{

	private AdminEventoSearchView view = null;

	public AbrirBuscarEMController()  {
		super ("Buscar", new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1467_xmag_xmag.png")));
		view = new AdminEventoSearchView();
	}

	public void doAction() { 
		MainWindow.getInstance().addTab(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
}