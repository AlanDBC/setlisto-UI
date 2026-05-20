package com.setlisto.ui.controller;

import java.awt.Rectangle;
import java.math.BigDecimal;

import com.setlisto.model.CategoriaAsiento;

// Representa la zona configurada en el plano del evento, con su ubicación y dimensiones.

public class ZonaConfigurada {

	private Rectangle area; // El dibujo en el mapa
	private String seccion; // "1", "2", "3"
	private CategoriaAsiento categoria; // Obtenida del CategoriaAsientoService
	private int cantidad; // Del JSpinner	
	private BigDecimal precio;
	private Long id;
	private Integer disponibles;

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

	public BigDecimal getPrecio() {
		return precio;
	}

	public Long getId() {
		return id;
	}

	public Integer getDisponibles() {
		return disponibles;
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

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDisponibles(Integer disponibles) {
		this.disponibles = disponibles;
	}
}
