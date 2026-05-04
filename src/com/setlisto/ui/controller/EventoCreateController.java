package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.setlisto.model.EventoMusical;
import com.setlisto.service.EventoMusicalService;
import com.setlisto.service.impl.EventoMusicalServiceImpl;
import com.setlisto.ui.view.EventoCreateView;

public class EventoCreateController extends AbstractController {
	
	private EventoMusicalService eventoMusicalService = null;
	private EventoCreateView view = null;
	
	
	public EventoCreateController (EventoCreateView view) {
		super("Crear", new ImageIcon(EventoCreateView.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		eventoMusicalService = new EventoMusicalServiceImpl();
		this.view = view;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
	
	public void doAction() {
		EventoMusical evento = view.getEvento();
		EventoMusical creado = eventoMusicalService.create(evento);
		// view... dependiendo de el exito o no de la operacion, se muestra un mensaje y se limpia el formulario
		// JoptionPane informando de la insercion
		
		if (creado != null) {
	        
	        // Mostramos el mensaje de éxito
	        JOptionPane.showMessageDialog(
	        		view, 
	            "¡Evento '" + creado.getNombre() + "' creado con éxito!", 
	            "Operación Exitosa", 
	            JOptionPane.INFORMATION_MESSAGE
	        );

	        // Limpiamos los campos mientras el usuario cierra el diálogo
	        view.limpiarCampos();
	        
	    } else {
	        // Opcional: Si el evento es null es porque validarCampos() falló (bordes rojos)
	        // No hace falta un OptionPane de error si ya resaltaste los campos en rojo.
	    }
	}

}
