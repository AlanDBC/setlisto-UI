package com.setlisto.ui.mapper;

import java.util.List;

import com.setlisto.model.Artista;
import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.model.GeneroMusical;
import com.setlisto.model.SubGeneroMusical;
import com.setlisto.ui.selectable.ListSeleccionableModel;

/**
 * Convierte de DTO a listaSeleccionable y viceversa
 */

public class EventoMusicalMapper {

    /**
     * Sincroniza el estado de las listas de la UI hacia el objeto DTO.
     * Implementa el flujo de creación: Construcción del DTO.
     */
    public void mapUItoDTO(EventoMusicalDTO dto, 
                          ListSeleccionableModel<GeneroMusical> generosModel, 
                          ListSeleccionableModel<SubGeneroMusical> subgenerosModel, 
                          ListSeleccionableModel<Artista> artistasModel) {
        
        // Se extraen los ítems marcados de cada modelo y se asignan a las listas del DTO
        dto.setGeneros(generosModel.getSeleccionados());
        dto.setSubGeneros(subgenerosModel.getSeleccionados());
        dto.setArtistas(artistasModel.getSeleccionados());
    }
	
	/**
     * Sincroniza de la UI hacia el DTO (Usado en el flujo de CREACIÓN/GUARDADO)
     * Recibe las listas que ya extrajiste con obtenerSeleccionados()
     */
    public void mapUItoDTO(EventoMusicalDTO dto, 
                           List<GeneroMusical> generos, 
                           List<SubGeneroMusical> subGeneros, 
                           List<Artista> artistas) {
        dto.setGeneros(generos);
        dto.setSubGeneros(subGeneros);
        dto.setArtistas(artistas);
    }

    /**
     * Sincroniza del DTO hacia la UI (Usado en el flujo de EDICIÓN)
     * Toma lo que viene del DTO y marca los checks correspondientes en los modelos
     */
    public void mapDTOtoUI(EventoMusicalDTO dto,
                           ListSeleccionableModel<GeneroMusical> modeloGeneros,
                           ListSeleccionableModel<SubGeneroMusical> modeloSubGeneros,
                           ListSeleccionableModel<Artista> modeloArtistas) {
        
        // Primero resetea las listas para que no haya checks previos
        modeloGeneros.clear();
        modeloSubGeneros.clear();
        modeloArtistas.clear();

        // Marca como 'seleccionado' lo que viene en el DTO
        if (dto.getGeneros() != null) {
            for (GeneroMusical g : dto.getGeneros()) {
                modeloGeneros.marcarItem(g); // Esto dispara fireContentsChanged visualmente
            }
        }

        if (dto.getSubGeneros() != null) {
            for (SubGeneroMusical sg : dto.getSubGeneros()) {
                modeloSubGeneros.marcarItem(sg);
            }
        }

        if (dto.getArtistas() != null) {
            for (Artista a : dto.getArtistas()) {
                modeloArtistas.marcarItem(a);
            }
        }
    }
}