package com.setlisto.ui.selectable;

/**
 * Clase genérica para representar un ítem seleccionable en la interfaz de usuario.
 * @param <T> El tipo de dato que representa el valor del ítem seleccionable.
 * @param boolean seleccionado Indica si el ítem está seleccionado o no.
 * Modifica el estado y evita modificar directamente la entidad real
 */

public class ItemSeleccionable<T> {
	
	private T valor;
	private boolean seleccionado;

	public ItemSeleccionable(T valor) {
		this.valor = valor;
		this.seleccionado = false; 
	}

	public T getValor() {
		return valor;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
}
