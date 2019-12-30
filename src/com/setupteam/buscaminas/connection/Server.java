package com.setupteam.buscaminas.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import com.setupteam.buscaminas.connection.server.Client;
import com.setupteam.buscaminas.utilities.Constants;
import com.setupteam.buscaminas.utilities.Utilities;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */

public class Server extends ServerSocket implements Runnable {
	private boolean isConnected;
	private Thread thread;
	private ArrayList<Client> clients;

	public Server(int port) throws IOException {
		super(port);
		this.clients = new ArrayList<>();
		this.thread = new Thread(this);
		this.isConnected = true;

		this.thread.start();

		new Thread() {
			@Override
			public void run() {
				while (true) {
					System.out.println(Constants.prompt + "Hay " + clients.size() + " conectados");
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}.start();
	}

	@Override
	public void run() {
		while (this.isConnected) {
			if (!Constants.isStarted)
				try {
					clients.add(this.createClient());
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
		}
	}

	private Client createClient() throws IOException {
		Client client = new Client(this.accept(), this);
		client.send(ConnectionCommands.REQUEST, "Conectado a " + Constants.nickname);
		return client;
	}

	public void receive(ConnectionCommands cc, byte[] data, Client client) {
		switch (cc) {
		case START:
			if(areAllReady()) {
				Constants.isStarted = true;
				this.startGame(data);				
			}else {
				System.out.println(Constants.prompt + "Todos los jugadores deben estar listos");
			}
			break;
		case WINNER:
			Constants.isStarted = false;
			System.out.println(Constants.prompt + client.getUser().getNickname() + " gana");
			break;
		case LOSER:
			System.out.println(Constants.prompt + client.getUser().getNickname() + " pierde");
			break;
		case CONNECT:
			client.initUser(Utilities.bytesToString(data));
			System.out.println(Constants.prompt + client.getUser().getNickname() + " se ha conectado");
			break;
		case MESSAGE:
			System.out.println(
					Constants.prompt + client.getUser().getNickname() + " dice: " + Utilities.bytesToString(data));
			try {
				this.sendToAll(ConnectionCommands.MESSAGE,
						client.getUser().getNickname() + ": " + Utilities.bytesToString(data));
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			break;
		case READY:
			client.getUser().setReady(true);
			System.out.println(Constants.prompt + client.getUser().getNickname() + " está listo");
			break;
		case NOT_READY:
			client.getUser().setReady(false);
			System.out.println(Constants.prompt + client.getUser().getNickname() + " ya no está listo");
			break;
		case DISCONNECT:
			System.out.println(Constants.prompt + client.getUser().getNickname() + " se ha desconectado");
			try {
				client.interrumpt();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			break;
		default:
			System.out.println(Constants.prompt + cc + " no está definido");
			break;
		}
	}

	private void startGame(byte[] data) {
		switch (data[0]) {
		case Constants.EASY_MODE:
			Constants.minesweeper.setValues(Constants.EASY);
			break;
		}
		try {
			this.prepareField();
			this.sendToAll(ConnectionCommands.START, new byte[] {});
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void prepareField() throws IOException {
		Constants.minesweeper.generateField();
		byte[][] field = Constants.minesweeper.getField();
		this.sendToAll(ConnectionCommands.FIELD_SIZE, new byte[] { (byte) field.length, (byte) field[0].length });
		for (int i = 0; i < field.length; i++) {
			this.sendToAll(ConnectionCommands.ADD_ROW, field[i]);
		}
	}

	private void sendToAll(ConnectionCommands cc, byte[] data) throws IOException {
		for (int i = 0; i < this.clients.size(); i++) {
			this.clients.get(i).send(cc, data);
		}
	}

	private void sendToAll(ConnectionCommands cc, String data) throws IOException {
		this.sendToAll(cc, Utilities.toBytes(data));
	}

	public boolean areAllReady() {
		for (int i = 0; i < clients.size(); i++) {
			if (!clients.get(i).getUser().isReady())
				return false;
		}
		return true;
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void interrumpt() throws IOException {
		this.thread.interrupt();
		this.isConnected = false;
	}
}