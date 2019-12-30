package com.setupteam.buscaminas.view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.setupteam.buscaminas.connection.ConnectionCommands;
import com.setupteam.buscaminas.controller.ConnectionListener;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class HostDialog extends JDialog {
	private NewHostDialog newHostDialog;
	private LobbyDialog lobbyDialog;
	private JTable table;
	private JTextField textField;
	private JTextField hostField;
	private JTextField portField;

	public HostDialog() {
		this.newHostDialog = new NewHostDialog();
		this.lobbyDialog = new LobbyDialog();
		this.portField = new JTextField("3333");
		this.hostField = new JTextField("localhost");
		this.textField = new JTextField();
		this.init();
	}

	private void init() {
		ConnectionListener.getInstance().setHostDialog(this);
		ConnectionListener.getInstance().setNewHostDialog(this.newHostDialog);
		ConnectionListener.getInstance().setLobbyDialog(this.lobbyDialog);
		
		this.lobbyDialog.setLocationRelativeTo(this);
		this.newHostDialog.setLocationRelativeTo(this);
		
		this.setModal(true);
		this.setTitle("Salas");
		this.setSize(new Dimension(480, 300));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(panel);

		JPanel controlPanel = new JPanel();
		panel.add(controlPanel, BorderLayout.NORTH);
		GridBagLayout gb_controlPanel = new GridBagLayout();
		gb_controlPanel.columnWidths = new int[] { 70, 130, 70, 70, 0, 0 };
		gb_controlPanel.rowHeights = new int[] { 35, 35, 0 };
		gb_controlPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gb_controlPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		controlPanel.setLayout(gb_controlPanel);

		JLabel nicknameLabel = new JLabel("Nickname : ");
		nicknameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_nicknameLabel = new GridBagConstraints();
		gbc_nicknameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nicknameLabel.anchor = GridBagConstraints.EAST;
		gbc_nicknameLabel.gridx = 0;
		gbc_nicknameLabel.gridy = 0;
		controlPanel.add(nicknameLabel, gbc_nicknameLabel);

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		controlPanel.add(textField, gbc_textField);

		JButton createButton = new JButton("Crear sala");
		createButton.setEnabled(false);
		createButton.addActionListener(ConnectionListener.getInstance());
		createButton.setActionCommand(ConnectionCommands.HOST.toString());
		createButton.setForeground(Color.WHITE);
		createButton.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnCrearSala = new GridBagConstraints();
		gbc_btnCrearSala.insets = new Insets(0, 0, 5, 0);
		gbc_btnCrearSala.fill = GridBagConstraints.BOTH;
		gbc_btnCrearSala.gridx = 4;
		gbc_btnCrearSala.gridy = 0;
		controlPanel.add(createButton, gbc_btnCrearSala);

		JLabel hostLabel = new JLabel("Host : ");
		hostLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblHost = new GridBagConstraints();
		gbc_lblHost.insets = new Insets(0, 0, 0, 5);
		gbc_lblHost.gridx = 0;
		gbc_lblHost.gridy = 1;
		controlPanel.add(hostLabel, gbc_lblHost);

		GridBagConstraints gbc_txtHost = new GridBagConstraints();
		gbc_txtHost.insets = new Insets(0, 0, 0, 5);
		gbc_txtHost.fill = GridBagConstraints.BOTH;
		gbc_txtHost.gridx = 1;
		gbc_txtHost.gridy = 1;
		controlPanel.add(hostField, gbc_txtHost);

		JLabel portLabel = new JLabel("Puerto : ");
		portLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.insets = new Insets(0, 0, 0, 5);
		gbc_lblPort.gridx = 2;
		gbc_lblPort.gridy = 1;
		controlPanel.add(portLabel, gbc_lblPort);

		GridBagConstraints gbc_portField = new GridBagConstraints();
		gbc_portField.insets = new Insets(0, 0, 0, 5);
		gbc_portField.fill = GridBagConstraints.BOTH;
		gbc_portField.gridx = 3;
		gbc_portField.gridy = 1;
		controlPanel.add(portField, gbc_portField);

		JButton joinButton = new JButton("Entrar");
		joinButton.setForeground(Color.WHITE);
		joinButton.setBackground(Color.BLACK);
		GridBagConstraints gbc_joinButton = new GridBagConstraints();
		gbc_joinButton.fill = GridBagConstraints.BOTH;
		gbc_joinButton.gridx = 4;
		gbc_joinButton.gridy = 1;
		controlPanel.add(joinButton, gbc_joinButton);

		joinButton.addActionListener(ConnectionListener.getInstance());
		joinButton.setActionCommand(ConnectionCommands.JOIN.toString());

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable();
		table.setModel(model);
		table.setEnabled(false);

		model.setColumnIdentifiers(new String[] { "Sala", "Dificultad", "Modo", "Creador" });

		scrollPane.setViewportView(table);
	}

	public String[] getData() {
		return new String[] { this.hostField.getText(), this.portField.getText() };
	}

	public String getNickname() {
		return this.textField.getText();
	}
}
