package com.setupteam.buscaminas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import com.setupteam.buscaminas.connection.Client;
import com.setupteam.buscaminas.connection.ConnectionCommands;
import com.setupteam.buscaminas.utilities.Constants;
import com.setupteam.buscaminas.view.HostDialog;
import com.setupteam.buscaminas.view.LobbyDialog;
import com.setupteam.buscaminas.view.NewHostDialog;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class ConnectionListener implements ActionListener, ItemListener {
	private static ConnectionListener connectionListener;

	private NewHostDialog newHostDialog;
	private HostDialog hostDialog;
	private LobbyDialog lobbyDialog;

	private ConnectionListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ConnectionCommands.valueOf(e.getActionCommand())) {
		case HOST:
			this.newHostDialog.setVisible(true);
			break;
		case CANCEL_HOST:
			this.newHostDialog.dispose();
			break;
		case JOIN:
			this.join();
			break;
		case START:
			try {
				Constants.client.send(ConnectionCommands.START, new byte[] { Constants.EASY_MODE });
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
			}
			break;
		case MESSAGE:
			try {
				Constants.client.send(ConnectionCommands.MESSAGE, this.lobbyDialog.getMessage());
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
			}
			break;
		default:
			System.out.println(e.getActionCommand() + " no ha sido definido");
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JToggleButton tb = (JToggleButton) e.getSource();
		if (tb.isSelected()) {
			try {
				Constants.client.send(ConnectionCommands.READY);
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
				tb.setSelected(false);
			}
		} else {
			try {
				Constants.client.send(ConnectionCommands.NOT_READY);
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
				tb.setSelected(true);
			}
		}
	}

	private void join() {
		Constants.nickname=this.hostDialog.getNickname();
		try {
			Constants.client = new Client(this.hostDialog.getData()[0], Integer.parseInt(this.hostDialog.getData()[1]));
			Constants.client.send(ConnectionCommands.CONNECT, Constants.nickname);
			this.lobbyDialog.setVisible(true);
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			JOptionPane.showMessageDialog(hostDialog, "Nos se encuentra servidor.", "Error. Servidor",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static ConnectionListener getInstance() {
		if (connectionListener == null)
			connectionListener = new ConnectionListener();
		return connectionListener;
	}

	public void setNewHostDialog(NewHostDialog newHostDialog) {
		this.newHostDialog = newHostDialog;
	}

	public void setHostDialog(HostDialog hostDialog) {
		this.hostDialog = hostDialog;
	}

	public HostDialog getHostDialog() {
		return hostDialog;
	}

	public void setLobbyDialog(LobbyDialog lobbyDialog) {
		this.lobbyDialog = lobbyDialog;
	}
	
	public LobbyDialog getLobbyDialog() {
		return lobbyDialog;
	}
}
