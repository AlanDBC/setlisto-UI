package com.setlisto.ui.controller;
import javax.swing.ImageIcon;

import com.setlisto.ui.view.PlazasConfigView;
import com.setlisto.ui.view.EventoCreateView;

public class AceptarPlazasButtonController extends AbstractController {

    private PlazasConfigView view;
    private EventoCreateView receptor;

    public AceptarPlazasButtonController(PlazasConfigView view, EventoCreateView receptor) {
    	super("Aceptar", new ImageIcon(EventoCreateView.class.getResource("/nuvola/16x16/1710_ok_yes_accept_green_ok_green_accept_yes.png")));
        this.view = view;
        this.receptor = receptor;
    }

    @Override
    public void doAction() {
        // Enviar la lista de zonas mapeadas al formulario de creación del evento
        receptor.setZonasConfiguradas(view.getMapaPanel().getZonas());
        receptor.setRutaImagenPlano(view.getRutaImagenPlano());
        // Cerrar el JDialog
        view.dispose();
    }
}
