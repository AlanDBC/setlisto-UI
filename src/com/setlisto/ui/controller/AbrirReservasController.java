package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;

import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.ReservaSearchView;

public class AbrirReservasController extends AbstractController {

	public AbrirReservasController() {
		super("Reservas");
	}

	@Override
	public void doAction() {
		MainWindow.getInstance().addTab(new ReservaSearchView());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
}
