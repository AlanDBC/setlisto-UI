package com.setlisto.ui.controller;

import java.awt.Rectangle;

import com.setlisto.model.CategoriaAsiento;

// Representa la zona configurada en el plano del evento, con su ubicación y dimensiones.

public class ZonaConfigurada {

	private Rectangle area; // El dibujo en el mapa
	private String seccion; // "A", "B", "C"...
	private CategoriaAsiento categoria; // Obtenida del CategoriaAsientoService
	private int cantidad; // Del JSpinner	

	public ZonaConfigurada() {
	}

	public Rectangle getArea() {
		return area;
	}

	public String getSeccion() {
		return seccion;
	}

	public CategoriaAsiento getCategoria() {
		return categoria;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setArea(Rectangle area) {
		this.area = area;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public void setCategoria(CategoriaAsiento categoria) {
		this.categoria = categoria;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
