package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.setlisto.ui.view.PlazasConfigView;

public class AdjuntarImagenButtonController extends AbstractController implements ActionListener{

	private PlazasConfigView view;
	
	public AdjuntarImagenButtonController(PlazasConfigView view) {
		super("Adjuntar Imagen");
		this.view = view;
	}
	
	@Override
	public void doAction() {
		JFileChooser chooser = new JFileChooser();
		
		// Filtro para que el usuario solo pueda elegir imágenes
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif");
        chooser.setFileFilter(filtro);
        
		if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedImage img = ImageIO.read(chooser.getSelectedFile());
				view.getMapaPanel().setBackgroundImage(img);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
}
