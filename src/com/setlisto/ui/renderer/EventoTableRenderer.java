package com.setlisto.ui.renderer;

import java.awt.Component;
import java.time.format.DateTimeFormatter; // Cambia el import

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.setlisto.model.EventoMusicalDTO;

public class EventoTableRenderer implements TableCellRenderer {

    // Cambiamos SimpleDateFormat por DateTimeFormatter
    private static final DateTimeFormatter FECHA_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        EventoMusicalDTO e = (EventoMusicalDTO) value;
        JLabel label = new JLabel(); // Usamos una sola label para optimizar
        
        if (e != null) {
            switch (column) {
                case 0: label.setText(e.getId().toString()); break;
                case 1: label.setText(e.getNombre()); break;
                case 2: label.setText(e.getEstadoNombre()); break;
                case 3: label.setText(e.getOrganizadorNombre()); break;
                case 4: 
                    // Usamos el nuevo formateador aquí
                    label.setText(e.getFechaInicio() != null ? e.getFechaInicio().format(FECHA_FORMATTER) : ""); 
                    break;
                case 5: 
                    label.setText(e.getFechaFin() != null ? e.getFechaFin().format(FECHA_FORMATTER) : ""); 
                    break;
                case 6: label.setText(e.getLugarNombre()); break;
                case 7: label.setText(e.getCiudadNombre()); break;
                case 8: label.setText(e.getCapacidad() != null ? e.getCapacidad().toString() : "0"); break;
                default: label.setText("N/A");
            }
        }

        // Toque visual: Si la fila está seleccionada, que se note
        if (isSelected) {
            label.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
            label.setOpaque(true);
        }

        return label;
    }
}