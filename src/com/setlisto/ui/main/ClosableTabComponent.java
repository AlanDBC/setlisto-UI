package com.setlisto.ui.main;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ClosableTabComponent extends JPanel {

	public ClosableTabComponent(JTabbedPane tabbedPane, String title) {
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		JLabel titleLabel = new JLabel(title) {
			public String getText() {
				int i = tabbedPane.indexOfTabComponent(ClosableTabComponent.this);
				return (i != -1) ? tabbedPane.getTitleAt(i) : "";
			}
		};

		JButton closeButton = new JButton("  X");
		closeButton.setBorder(null);
		closeButton.setFocusable(false);
		closeButton.setContentAreaFilled(false);

		closeButton.addActionListener(e -> {
			int i = tabbedPane.indexOfTabComponent(ClosableTabComponent.this);
			if (i != -1) tabbedPane.remove(i);
		});

		add(titleLabel);
		add(closeButton);
	}
}