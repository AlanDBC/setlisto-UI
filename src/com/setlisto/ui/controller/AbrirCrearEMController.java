package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.EventoCreateView;

public class AbrirCrearEMController extends AbstractController implements ActionListener{
	
	private EventoCreateView view = null;
	
	public AbrirCrearEMController() {
		super ("Crear", new ImageIcon(EventoCreateView.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		view = new EventoCreateView();
	}
	
	public void doAction() {
		MainWindow.getInstance().addTab(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
}