package com.setlisto.ui.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

public class UsuariosSearchView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public UsuariosSearchView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel filtrosPanel = new JPanel();
		add(filtrosPanel, BorderLayout.NORTH);
		GridBagLayout gbl_filtrosPanel = new GridBagLayout();
		gbl_filtrosPanel.columnWidths = new int[]{0};
		gbl_filtrosPanel.rowHeights = new int[]{0};
		gbl_filtrosPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_filtrosPanel.rowWeights = new double[]{Double.MIN_VALUE};
		filtrosPanel.setLayout(gbl_filtrosPanel);
		
		JPanel resultadosPanel = new JPanel();
		add(resultadosPanel, BorderLayout.CENTER);

	}

}
