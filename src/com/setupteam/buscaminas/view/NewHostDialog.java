package com.setupteam.buscaminas.view;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.setupteam.buscaminas.connection.ConnectionCommands;
import com.setupteam.buscaminas.controller.ConnectionListener;

import javax.swing.JComboBox;
import javax.swing.JButton;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class NewHostDialog extends JDialog {
	private JTextField hostnameField;

	public NewHostDialog() {
		this.hostnameField = new JTextField();
		this.init();
	}

	private void init() {
		this.setModal(true);
		this.setTitle("Crear sala");
		this.setSize(new Dimension(250, 180));
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		getContentPane().add(panel);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel hostnameLabel = new JLabel("Sala");
		
		dataPanel.add(hostnameLabel);
		dataPanel.add(hostnameField);

		panel.add(dataPanel, BorderLayout.CENTER);
		
		JLabel difficultyLabel = new JLabel("Dificultad");
		dataPanel.add(difficultyLabel);
		
		JComboBox<String> difficultyBox = new JComboBox<>();
		dataPanel.add(difficultyBox);
		
		JLabel modeLabel = new JLabel("Modo");
		dataPanel.add(modeLabel);
		
		JComboBox<String> modeBox = new JComboBox<>();
		dataPanel.add(modeBox);
		
		JLabel headerLabel = new JLabel("Completa los siguientes datos");
		panel.add(headerLabel, BorderLayout.NORTH);
		
		JPanel controlPanel = new JPanel();
		panel.add(controlPanel, BorderLayout.SOUTH);
		controlPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton createButton = new JButton("Crear");
		controlPanel.add(createButton);
		
		createButton.addActionListener(ConnectionListener.getInstance());
		createButton.setActionCommand(ConnectionCommands.CREATE_HOST.toString());
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(ConnectionListener.getInstance());
		cancelButton.setActionCommand(ConnectionCommands.CANCEL_HOST.toString());
		
		controlPanel.add(cancelButton);
	}

}
