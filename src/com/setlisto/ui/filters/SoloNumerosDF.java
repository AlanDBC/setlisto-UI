package com.setlisto.ui.filters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class SoloNumerosDF extends DocumentFilter {
	
	public SoloNumerosDF() {
		
	}
	
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
            throws BadLocationException {
        
        // Solo permite si el texto es numérico
        if (text.matches("\\d*")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}