package com.setupteam.buscaminas.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.setupteam.buscaminas.controller.ConnectionListener;
import com.setupteam.buscaminas.controller.MenuBarListener;
import com.setupteam.buscaminas.utilities.Constants;
import com.setupteam.buscaminas.utilities.Utilities;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */

public class Client extends Socket implements Runnable {
	private static Client client;
	private Thread thread;
	private DataInputStream dis;
	private DataOutputStream dos;

	private Client() throws UnknownHostException, IOException {
		this(Constants.host, Constants.port);
	}

	public Client(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
		this.thread = new Thread(this);
		this.dis = new DataInputStream(this.getInputStream());
		this.dos = new DataOutputStream(this.getOutputStream());

		this.thread.start();
	}

	@Override
	public void run() {
		byte[] data;
		String command;
		while (true) {
			try {
				command = this.dis.readUTF();
				data = new byte[this.dis.readInt()];
				this.dis.read(data);
				this.receive(ConnectionCommands.valueOf(command), data);
			} catch (IOException e) {
			}
		}
	}

	public void send(ConnectionCommands cc, byte[] data) throws IOException {
		this.dos.writeUTF(cc.toString());
		this.dos.writeInt(data.length);
		this.dos.write(data);
	}

	public void send(ConnectionCommands cc, String data) throws IOException {
		this.send(cc, Utilities.toBytes(data));
	}

	public void send(ConnectionCommands cc) throws IOException {
		this.send(cc, new byte[] {});
	}

	private void receive(ConnectionCommands cc, byte[] data) {
		switch (cc) {
		case REQUEST:
			System.out.println(Utilities.bytesToString(data));
			break;
		case FIELD_SIZE:
			Constants.field = new byte[data[0]][data[1]];
			Constants.rowCount = 0;
			break;
		case ADD_ROW:
			Constants.field[Constants.rowCount] = data;
			Constants.rowCount++;
			break;
		case START:
			Constants.minesweeper.setField(Constants.field);
			ConnectionListener.getInstance().getHostDialog().dispose();
			ConnectionListener.getInstance().getLobbyDialog().dispose();
			MenuBarListener.getInstance().getGameAreaPanel().chargeCellMap();
			break;
		case MESSAGE:
			ConnectionListener.getInstance().getLobbyDialog().addMessage(Utilities.bytesToString(data));
			break;
		default:
			System.out.println(cc + " no ha sido definido");
			break;
		}
	}

	public void interrumt() throws IOException {
		this.thread.interrupt();
		this.dis.close();
		this.dos.close();
		this.close();
		client = null;
	}

	public static Client getInstance() throws UnknownHostException, IOException {
		if (client == null)
			client = new Client();
		return client;
	}
}
