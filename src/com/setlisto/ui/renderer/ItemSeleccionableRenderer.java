package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.setlisto.model.Artista;
import com.setlisto.model.GeneroMusical;
import com.setlisto.model.SubGeneroMusical;
import com.setlisto.ui.selectable.ItemSeleccionable;

public class ItemSeleccionableRenderer extends JCheckBox implements ListCellRenderer<ItemSeleccionable<?>> {

    @Override
    public Component getListCellRendererComponent(
            JList<? extends ItemSeleccionable<?>> list,
            ItemSeleccionable<?> value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        setSelected(value != null && value.isSeleccionado());
        setText(getNombre(value != null ? value.getValor() : null));

        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setFocusPainted(false);

        return this;
    }

    private String getNombre(Object valor) {
        if (valor instanceof GeneroMusical) {
            return ((GeneroMusical) valor).getNombre();
        }
        if (valor instanceof SubGeneroMusical) {
            return ((SubGeneroMusical) valor).getNombre();
        }
        if (valor instanceof Artista) {
            return ((Artista) valor).getNombre();
        }
        return valor != null ? valor.toString() : "";
    }
}
