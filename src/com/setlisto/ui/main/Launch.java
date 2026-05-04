package com.setlisto.ui.main;

import javax.swing.SwingUtilities;

import com.setlisto.model.Organizador;
import com.setlisto.service.OrganizadorService;
import com.setlisto.service.impl.OrganizadorServiceImpl;

public class Launch {

    public static void main(String[] args) {
        // Ejecutamos en el Event Dispatch Thread de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                OrganizadorService authService = new OrganizadorServiceImpl();
                
                // 1. Intentamos el login programático
                Organizador user = authService.login("contacto@producciones.test", "admin123");
                
                if (user != null) {
                    System.out.println("Login de prueba exitoso: " + user.getNombre());
                    
                    // 2. Aquí es vital que tu Singleton de MainWindow (o tu SessionManager si tienes uno) 
                    // sepa quién es el usuario. Si MainWindow.getInstance() ya gestiona el usuario:
                    MainWindow main = MainWindow.getInstance();
                    
                    // Si tienes un método para setear el usuario en la sesión, úsalo aquí:
                    main.setUsuarioLogueado(user); 
                    main.setVisible(true);
                    main.setLocationRelativeTo(null);
                } else {
                    System.err.println("Error: Credenciales de prueba incorrectas.");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}