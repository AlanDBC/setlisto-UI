package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.setlisto.model.LugarDTO;
import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.EventoCreateView;
import com.setlisto.ui.view.LugarSelectView;

public class AbrirLugarSelectController extends AbstractController implements ActionListener {

	private EventoCreateView view;
	
	public AbrirLugarSelectController(EventoCreateView view) {
		super("Buscar lugar", new ImageIcon(EventoCreateView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		this.view = view;
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        LugarSelectView dialog = new LugarSelectView(null, true, this.view);
        dialog.setLocationRelativeTo(this.view);
        dialog.setVisible(true);	
	}
	
	

}
