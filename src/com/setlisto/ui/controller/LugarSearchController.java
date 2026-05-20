package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.setlisto.criteria.LugarCriteria;
import com.setlisto.model.LugarDTO;
import com.setlisto.model.Results;
import com.setlisto.service.LugarService;
import com.setlisto.service.impl.LugarServiceImpl;
import com.setlisto.ui.view.AdminEventoSearchView;
import com.setlisto.ui.view.EventoCreateView;
import com.setlisto.ui.view.LugarSelectView;

public class LugarSearchController extends AbstractController implements ActionListener, MouseListener {

	private LugarSelectView view;
	private LugarService service;
	private EventoCreateView receptor;
	
	public LugarSearchController(LugarSelectView view, EventoCreateView receptor) {
		super("Buscar", new ImageIcon(AdminEventoSearchView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		this.view = view;
		this.receptor = receptor;
		service = new LugarServiceImpl();
	}

	@Override
	public void doAction() {
		try {
			LugarCriteria criteria = view.getCriteria();
			Results<LugarDTO> results = service.findByCriteria(criteria, 0, 20);
			List<LugarDTO> list = results.getPage();
			view.setModel(list);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "No fue posible buscar lugares: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override // Buttons
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
	
	@Override // ComboBoxes
	public void itemStateChanged(ItemEvent e) {
		doAction();
	}
	
	@Override // TextFields y FormattedTextFields
	public void keyReleased(KeyEvent e) {
		doAction();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Verificar si es doble clic
	    if (e.getClickCount() == 2) {
	        // Obtener la tabla desde la vista (necesitarás un getter en LugarSelectView)
	        JTable tabla = view.getTabla(); 
	        
	        int filaSelec = tabla.getSelectedRow();
	        if (filaSelec != -1) {

	            LugarDTO seleccionado = view.getLugarAt(tabla.convertRowIndexToModel(filaSelec));
	            
	            view.setLugarSeleccionado(seleccionado);
//	            receptor.setLugarSeleccionado(seleccionado);
	        }
	    }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
