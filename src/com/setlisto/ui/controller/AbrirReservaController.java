package com.setlisto.ui.controller;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.EventoView;
import com.setlisto.ui.view.ReservaView;

public class AbrirReservaController extends AbstractController {

    private EventoView view;

    public AbrirReservaController(EventoView view) {
        super("Reservar", new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
        this.view = view;
    }

    @Override
    public void doAction() {
        // Pedimos el modelo en el momento del clic
        EventoMusicalDTO model = view.getModel();

        if (model == null) {
            JOptionPane.showMessageDialog(view, "No hay un evento cargado para reservar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Levantamos el JDialog modal (Plan B)
        Window parentWindow = SwingUtilities.getWindowAncestor(MainWindow.getInstance());
        JDialog dialog = new JDialog((Frame) parentWindow, "Selección de Zonas y Reserva", true);
        dialog.setLayout(new BorderLayout());

        ReservaView reservaView = new ReservaView(model);
        dialog.add(reservaView, BorderLayout.CENTER);

        // 3. Tamaño fijo para asegurar que la imagen de las zonas encaja perfecta
        dialog.setSize(1000, 800); // Pon aquí las medidas exactas que usas en PlazasConfigView
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(MainWindow.getInstance());

        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}