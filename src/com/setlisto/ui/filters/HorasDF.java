package com.setlisto.ui.filters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class HorasDF extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        // Solo permitir números
        if (!text.matches("\\d*")) return;

        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        // El formato visual será "## : ##", total 7 caracteres (4 números, 2 espacios, 1 punto)
        
        // Si intentan escribir más de lo permitido, bloqueamos
        if ((currentText.length() - length + text.length()) > 7) return;

        fb.replace(offset, length, text, attrs);
    }
}