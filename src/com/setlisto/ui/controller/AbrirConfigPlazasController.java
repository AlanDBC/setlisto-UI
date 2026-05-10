package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.PlazasConfigView;
import com.setlisto.ui.view.EventoCreateView;

public class AbrirConfigPlazasController extends AbstractController implements ActionListener {

	private EventoCreateView view;
	
	public AbrirConfigPlazasController(EventoCreateView view) {
		super ("Configurar Asientos", new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1819_pencil_pencil.png")));
		this.view = view;
	}
	
	@Override
	public void doAction() {
		PlazasConfigView configView = new PlazasConfigView(this.view);
		configView.setModal(true);
		configView.pack();
		configView.setLocationRelativeTo(view);
		configView.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

}
