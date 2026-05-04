package com.setlisto.ui.main;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import com.setlisto.ui.controller.LoginController;
import javax.swing.JCheckBox;

public class LoginWindow extends JFrame {
	
	private static LoginWindow instance = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton accederButton;
	private JTextField usuarioTF;
	private JTextField contrasenaTF;
	private JCheckBox rolOrganizadorCheckBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = LoginWindow.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		initialize();
		postInitialize();
	}
	
	public static LoginWindow getInstance() {
		if (instance == null) {
			instance = new LoginWindow();
		}
		return instance;
	}

	public void initialize () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel setlistoLabel = new JLabel("Setlisto");
		GridBagConstraints gbc_setlistoLabel = new GridBagConstraints();
		gbc_setlistoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_setlistoLabel.gridx = 1;
		gbc_setlistoLabel.gridy = 1;
		contentPane.add(setlistoLabel, gbc_setlistoLabel);

		JLabel usuarioLabel = new JLabel("Usuario");
		GridBagConstraints gbc_usuarioLabel = new GridBagConstraints();
		gbc_usuarioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usuarioLabel.gridx = 1;
		gbc_usuarioLabel.gridy = 3;
		contentPane.add(usuarioLabel, gbc_usuarioLabel);

		usuarioTF = new JTextField();
		GridBagConstraints gbc_usuarioTF = new GridBagConstraints();
		gbc_usuarioTF.insets = new Insets(0, 0, 5, 5);
		gbc_usuarioTF.gridx = 1;
		gbc_usuarioTF.gridy = 4;
		contentPane.add(usuarioTF, gbc_usuarioTF);
		usuarioTF.setColumns(10);

		JLabel contrasenaLabel = new JLabel("Contrasena");
		GridBagConstraints gbc_contrasenaLabel = new GridBagConstraints();
		gbc_contrasenaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_contrasenaLabel.gridx = 1;
		gbc_contrasenaLabel.gridy = 6;
		contentPane.add(contrasenaLabel, gbc_contrasenaLabel);

		contrasenaTF = new JTextField();
		GridBagConstraints gbc_contrasenaTF = new GridBagConstraints();
		gbc_contrasenaTF.insets = new Insets(0, 0, 5, 5);
		gbc_contrasenaTF.gridx = 1;
		gbc_contrasenaTF.gridy = 7;
		contentPane.add(contrasenaTF, gbc_contrasenaTF);
		contrasenaTF.setColumns(10);
		
		rolOrganizadorCheckBox = new JCheckBox("Soy Organizador");
		GridBagConstraints gbc_rolOrganizadorCheckBox = new GridBagConstraints();
		gbc_rolOrganizadorCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_rolOrganizadorCheckBox.gridx = 1;
		gbc_rolOrganizadorCheckBox.gridy = 8;
		contentPane.add(rolOrganizadorCheckBox, gbc_rolOrganizadorCheckBox);

		accederButton = new JButton("Acceder");
		GridBagConstraints gbc_accederButton = new GridBagConstraints();
		gbc_accederButton.insets = new Insets(0, 0, 0, 5);
		gbc_accederButton.gridx = 1;
		gbc_accederButton.gridy = 9;
		contentPane.add(accederButton, gbc_accederButton);
		
		// Bloquear que se pueda cambiar el tamaño y maximizar

	    this.setResizable(false);

	    // Establece el tamaño exacto 

	    this.setSize(400, 350); 

	    // Centrar la ventana en la pantalla
	    // Se pasa 'null', Java la centra respecto a la pantalla del monitor.
	    this.setLocationRelativeTo(null);
	}

	public void postInitialize() {
		LoginController LoginController = new LoginController();
		accederButton.setAction(LoginController);	
	}

	public String getCorreo () {
		String correo = StringUtils.trimToNull(usuarioTF.getText());
		return correo;
	}

	public String getContrasena () {
		String contrasena = StringUtils.trimToNull(contrasenaTF.getText());
		return contrasena;
	}
	
	public boolean isRolOrganizador() {
		return rolOrganizadorCheckBox.isSelected();
	}

}
