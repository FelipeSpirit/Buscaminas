/**
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 17/09/2019
 */

package com.setupteam.buscaminas.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import com.setupteam.buscaminas.connection.ConnectionCommands;
import com.setupteam.buscaminas.controller.ConnectionListener;
import com.setupteam.buscaminas.utilities.Constants;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;

public class LobbyDialog extends JDialog {
	private JTextArea textArea;
	private JTextArea chatArea;
	private DefaultTableModel dtm;

	public LobbyDialog() {
		this.init();
		this.dtm = new DefaultTableModel();
	}

	private void init() {
		this.setModal(true);
		this.setSize(new Dimension(540, 400));
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel chatPanel = new JPanel();
		GridBagLayout gbl_chatPanel = new GridBagLayout();
		gbl_chatPanel.rowHeights = new int[] { 0, 270, 114 };
		gbl_chatPanel.columnWidths = new int[] { 10, 360, 130, 10 };
		gbl_chatPanel.rowWeights = new double[] { 0.0, 0.0, 1.0 };
		gbl_chatPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
		chatPanel.setLayout(gbl_chatPanel);

		JScrollPane chatScroll = new JScrollPane();
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatScroll.setViewportView(chatArea);

		GridBagConstraints gbc_chatArea = new GridBagConstraints();
		gbc_chatArea.fill = GridBagConstraints.BOTH;
		gbc_chatArea.insets = new Insets(0, 0, 5, 5);
		gbc_chatArea.gridx = 1;
		gbc_chatArea.gridy = 1;
		chatPanel.add(chatScroll, gbc_chatArea);
		panel.add(chatPanel, BorderLayout.CENTER);

		JScrollPane playersScroll = new JScrollPane();
		JTable playersTable = new JTable(this.dtm);
		playersScroll.setViewportView(playersTable);

		GridBagConstraints gbc_playersScroll = new GridBagConstraints();
		gbc_playersScroll.insets = new Insets(0, 0, 5, 5);
		gbc_playersScroll.gridx = 2;
		gbc_playersScroll.gridy = 1;
		gbc_playersScroll.fill = GridBagConstraints.BOTH;
		chatPanel.add(playersScroll, gbc_playersScroll);

		textArea = new JTextArea();
		textArea.setRows(1);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 2;
		chatPanel.add(textArea, gbc_textArea);

		JButton sendButton = new JButton("Enviar");
		GridBagConstraints gbc_sendButton = new GridBagConstraints();
		gbc_sendButton.insets = new Insets(0, 0, 0, 5);
		gbc_sendButton.fill = GridBagConstraints.BOTH;
		gbc_sendButton.gridx = 2;
		gbc_sendButton.gridy = 2;
		chatPanel.add(sendButton, gbc_sendButton);

		JPanel controlPanel = new JPanel();
		JToggleButton readyButton = new JToggleButton();
		readyButton.setText("Listo");
		controlPanel.add(readyButton);
		panel.add(controlPanel, BorderLayout.SOUTH);

		sendButton.setActionCommand(ConnectionCommands.MESSAGE.toString());
		sendButton.addActionListener(ConnectionListener.getInstance());
		readyButton.addItemListener(ConnectionListener.getInstance());

		getContentPane().add(panel);

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Constants.client.send(ConnectionCommands.DISCONNECT);
				} catch (IOException e1) {
					System.err.println(e1.getMessage());
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}

	public String getMessage() {
		return this.textArea.getText();
	}

	public void addMessage(String message) {
		this.chatArea.setText(this.chatArea.getText() + "\n" + message);
	}
}
