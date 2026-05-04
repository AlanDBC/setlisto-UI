package com.setlisto.ui.view;
import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class SwingXTestView extends AbstractView {

	private static final long serialVersionUID = 1L;
	private JComboBox paisCB;

	/**
	 * Create the panel.
	 */
	public SwingXTestView() {
		initialize();
		postInitialize();

	}

	public void initialize () {
		setLayout(new BorderLayout(0, 0));

		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);

		paisCB = new JComboBox(new String [] {"Ruben","Juan","Paula","Toñin","Sabic","Alan" });
		centerPanel.add(paisCB);
	}

	public void postInitialize () {
		AutoCompleteDecorator.decorate(paisCB);

	}

}
