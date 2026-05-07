package com.setlisto.ui.selectable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

/**
 * Gestiona una colección de ItemSeleccionable
 * usando directamente DefaultListModel como fuente de verdad.
 */
public class ListSeleccionableModel<T> extends DefaultListModel<ItemSeleccionable<T>> {

	/**
	 * Carga los datos iniciales en el modelo
	 */
	public void cargarItems(List<T> datos) {
	    clear();

	    if (datos == null) {
	        return;
	    }

	    for (T dato : datos) {
	        addElement(new ItemSeleccionable<>(dato));
	    }
	}

	/**
	 * Marca un item como seleccionado
	 */
	public void marcarItem(T valor) {
		for (int i = 0; i < getSize(); i++) {
			ItemSeleccionable<T> item = getElementAt(i);
			if (item.getValor().equals(valor)) {
				item.setSeleccionado(true);
				// Notifica a la vista que este elemento cambió
				fireContentsChanged(this, i, i);
				break;
			}
		}
	}

	/**
	 * Desmarca un item
	 */
	public void desmarcarItem(T valor) {
		for (int i = 0; i < getSize(); i++) {
			ItemSeleccionable<T> item = getElementAt(i);
			if (item.getValor().equals(valor)) {
				item.setSeleccionado(false);
				fireContentsChanged(this, i, i);
				break;
			}
		}
	}

	/**
	 * Alterna el estado (toggle)
	 */
	public void toggleItem(int index) {
		ItemSeleccionable<T> item = getElementAt(index);
		item.setSeleccionado(!item.isSeleccionado());
		fireContentsChanged(this, index, index);
	}

	/**
	 * Obtiene solo los elementos seleccionados
	 */
	public List<T> getSeleccionados() {
		List<T> seleccionados = new ArrayList<>();
		for (int i = 0; i < getSize(); i++) {
			ItemSeleccionable<T> item = getElementAt(i);
			if (item.isSeleccionado()) {
				seleccionados.add(item.getValor());
			}
		}
		return seleccionados;
	}

	/**
	 * Resetea todos los items (los desmarca)
	 */
	public void resetear() {
		for (int i = 0; i < getSize(); i++) {
			ItemSeleccionable<T> item = getElementAt(i);
			item.setSeleccionado(false);
		}
		// Notifica cambio global
		if (getSize() > 0) {
			fireContentsChanged(this, 0, getSize() - 1);
		}
	}

	/**
	 * Obtener item por índice
	 */
	public ItemSeleccionable<T> getItem(int index) {
		return getElementAt(index);
	}
}