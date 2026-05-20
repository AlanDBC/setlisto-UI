package com.setlisto.ui.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import com.setlisto.ui.selectable.ItemSeleccionable;
import com.setlisto.ui.selectable.ListSeleccionableModel;

/**
 * Controla una lista especifica (Jlist)
 * conecta UI con el modelo
 * Maneja clicks
 * Actualiza la vista
 * Sincroniza con el modelo
 * @param <T>
 */

public class ListaSeleccionableController<T> extends AbstractController implements MouseListener {
	
    private JList<ItemSeleccionable<T>> listaUI;
    private ListSeleccionableModel<T> modelo;
    private RelacionGeneroSubgeneroController relacionController;
    

    public ListaSeleccionableController(JList<ItemSeleccionable<T>> listaUI, ListSeleccionableModel<T> modelo, RelacionGeneroSubgeneroController relacionController) {
        this.listaUI = listaUI;
        this.modelo = modelo;
        this.relacionController = relacionController;
    }        

	@Override
	public void doAction() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		 // Obtener el índice basado en la posición del puntero
	    int index = listaUI.locationToIndex(e.getPoint());
	    
	    if (index != -1) {
	        // Ejecutar el toggle en el modelo
	        // Este método cambia el boolean y dispara fireContentsChanged automáticamente
	        modelo.toggleItem(index);
	        
	        if (relacionController != null) {
	            relacionController.actualizarDependencias(); // Actualiza dependencias si es necesario
	        }
	        
	        // Limpiar la selección visual de la JList (opcional)
	        // Esto refuerza la idea de que es un checklist y no una lista de selección simple
	        listaUI.clearSelection();
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
