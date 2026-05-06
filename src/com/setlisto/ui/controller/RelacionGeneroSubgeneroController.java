package com.setlisto.ui.controller;

import java.util.List;

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
        
        // Si un subgénero estaba marcado pero su género padre ya no lo está, lo desmarcamos.
        for (int i = 0; i < modeloSubgeneros.getSize(); i++) {
            ItemSeleccionable<SubGeneroMusical> item = modeloSubgeneros.getItem(i);
            if (item.isSeleccionado() && !esGeneroPadreSeleccionado(item.getValor(), generosSeleccionados)) {
                item.setSeleccionado(false);
            }
        }

        // Reconstruimos la lista de subgéneros visibles.
        reconstruirListaSubgeneros(generosSeleccionados);
    }

    private boolean esGeneroPadreSeleccionado(SubGeneroMusical sub, List<GeneroMusical> seleccionados) {
        return seleccionados.stream()
                .anyMatch(g -> g.getId().equals(sub.getGeneroMusicalId()));
    }

    private void reconstruirListaSubgeneros(List<GeneroMusical> generosSeleccionados) {
        // Guardamos temporalmente cuáles estaban marcados para no perder la selección al filtrar
        List<SubGeneroMusical> marcadosAnteriormente = modeloSubgeneros.getSeleccionados();
        
        modeloSubgeneros.clear(); // Limpiamos la vista actual

        for (SubGeneroMusical sg : todosLosSubgeneros) {
            if (esGeneroPadreSeleccionado(sg, generosSeleccionados)) {
                modeloSubgeneros.addElement(new ItemSeleccionable<>(sg));
                // Si ya estaba marcado antes de filtrar, lo mantenemos marcado
                if (marcadosAnteriormente.contains(sg)) {
                    modeloSubgeneros.marcarItem(sg);
                }
            }
        }
    }
}
