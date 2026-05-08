package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.setlisto.model.Artista;
import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.model.GeneroMusical;
import com.setlisto.model.SubGeneroMusical;
import com.setlisto.service.ArtistaService;
import com.setlisto.service.EventoMusicalService;
import com.setlisto.service.GeneroMusicalService;
import com.setlisto.service.SubGeneroMusicalService;
import com.setlisto.service.impl.ArtistaServiceImpl;
import com.setlisto.service.impl.EventoMusicalServiceImpl;
import com.setlisto.service.impl.GeneroMusicalServiceImpl;
import com.setlisto.service.impl.SubGeneroMusicalServiceImpl;
import com.setlisto.ui.selectable.ListSeleccionableModel;
import com.setlisto.ui.view.EventoCreateView;

public class EventoCreateController extends AbstractController {
	
	private EventoMusicalService eventoService = null;
	private GeneroMusicalService generoService = null;
	private SubGeneroMusicalService subgeneroService = null;
	private ArtistaService artistaService = null;
	private ListSeleccionableModel<GeneroMusical> generoSeleccionable = null;
	private ListSeleccionableModel<SubGeneroMusical> subGeneroSeleccionable = null;
	private ListSeleccionableModel<Artista> artistaSeleccionable = null;
	
	
	private EventoCreateView view = null;
	
	
	public EventoCreateController (EventoCreateView view) {
		super("Crear", new ImageIcon(EventoCreateView.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		eventoService = new EventoMusicalServiceImpl();
		generoService = new GeneroMusicalServiceImpl();
		subgeneroService = new SubGeneroMusicalServiceImpl();
		artistaService = new ArtistaServiceImpl();
		this.view = view;
	}

	private void initialize() {
		List<GeneroMusical> generos = generoService.findAll();
		this.generoSeleccionable.cargarItems(generos);
		view.setGenerosListModel(generoSeleccionable);
		
//		List<SubGeneroMusical> subgeneros = subgeneroService.findByGenero(); TODO implementar
		
		
		List<Artista> artistas = artistaService.findAll();
		this.artistaSeleccionable.cargarItems(artistas);
		view.setArtistasListModel(artistaSeleccionable);
	}
	
	
	public void doAction() {
		EventoMusicalDTO evento = view.getEvento();
		EventoMusicalDTO creado = eventoService.create(evento);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

}
