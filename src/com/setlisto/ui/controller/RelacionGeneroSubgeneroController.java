package com.setlisto.ui.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.setlisto.model.GeneroMusical;
import com.setlisto.model.SubGeneroMusical;
import com.setlisto.model.SubGeneroMusicalDTO;
import com.setlisto.ui.selectable.ItemSeleccionable;
import com.setlisto.ui.selectable.ListSeleccionableModel;

/**
 * Gestiona la relación entre géneros y subgéneros
 * Filtra subgéneros según géneros seleccionados
 * Desmarca subgéneros inválidos
 * Notifica cambios a la lista de subgéneros
 */

public class RelacionGeneroSubgeneroController {
	private ListSeleccionableModel<GeneroMusical> modeloGeneros;
	private ListSeleccionableModel<SubGeneroMusical> modeloSubgeneros;
	private List<SubGeneroMusicalDTO> todosLosSubgeneros; // Lista maestra cargada inicialmente

	public RelacionGeneroSubgeneroController(
			ListSeleccionableModel<GeneroMusical> modeloGeneros,
			ListSeleccionableModel<SubGeneroMusical> modeloSubgeneros,
			List<SubGeneroMusicalDTO> todosLosSubgeneros) {
		this.modeloGeneros = modeloGeneros;
		this.modeloSubgeneros = modeloSubgeneros;
		this.todosLosSubgeneros = todosLosSubgeneros;
	}

	/**
	 * Ejecuta el flujo completo de dependencia: Consistencia + Filtrado.
	 * Debe llamarse cada vez que un Género cambia de estado (toggle).
	 */
	public void actualizarDependencias() {
	    List<GeneroMusical> generosSeleccionados = modeloGeneros.getSeleccionados();
	    reconstruirListaSubgeneros(generosSeleccionados);
	}
	
	private boolean esGeneroPadreSeleccionado(SubGeneroMusical sub, List<GeneroMusical> seleccionados) {
		return seleccionados.stream()
				.anyMatch(g -> g.getId().equals(sub.getGeneroMusicalId()));
	}

	private void reconstruirListaSubgeneros(List<GeneroMusical> generosSeleccionados) {
	    List<SubGeneroMusical> marcadosAnteriormente = modeloSubgeneros.getSeleccionados();

	    modeloSubgeneros.clear();
	    
	    Set<Long> idsAgregados = new HashSet<Long>();

	    for (SubGeneroMusical sg : todosLosSubgeneros) {
	        if (sg == null || sg.getId() == null) {
	            continue;
	        }

	        if (idsAgregados.contains(sg.getId())) {
	            continue;
	        }

	        if (esGeneroPadreSeleccionado(sg, generosSeleccionados)) {
	            modeloSubgeneros.addElement(new ItemSeleccionable<SubGeneroMusical>(sg));
	            idsAgregados.add(sg.getId());

	            if (estabaMarcado(sg, marcadosAnteriormente)) {
	                modeloSubgeneros.marcarItem(sg);
	            }
	        }
	    }
	}
	

	private boolean estabaMarcado(SubGeneroMusical subgenero, List<SubGeneroMusical> marcados) {
		if (subgenero == null || subgenero.getId() == null) {
			return false;
		}

		for (SubGeneroMusical marcado : marcados) {
			if (marcado != null && subgenero.getId().equals(marcado.getId())) {
				return true;
			}
		}
		return false;
	}
}
