package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;

import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.AbstractView;

public class CancelarController extends AbstractController  {
	
	public CancelarController(AbstractView view) {
		super ("Cancelar");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

	@Override
	public void doAction() {
		MainWindow.getInstance().removeTab((AbstractView)getView());
	}

}
