package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.setlisto.model.LugarDTO;
import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.EventoCreateView;
import com.setlisto.ui.view.LugarSelectView;

public class AbrirLugarSelectController extends AbstractController implements ActionListener {

	private EventoCreateView view;
	
	public AbrirLugarSelectController(EventoCreateView view) {
		this.view = view;
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        LugarSelectView dialog = new LugarSelectView(MainWindow.getInstance(), true);
        
        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true); 
        
        LugarDTO lugar = dialog.getLugarSeleccionado();
        
        if (lugar != null) {
            view.getLugarSeleccionadoLabel().setText(lugar.getNombre());
        }	
	}
	
	

}
