package com.setupteam.buscaminas.connection.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.setupteam.buscaminas.connection.ConnectionCommands;
import com.setupteam.buscaminas.connection.Server;
import com.setupteam.buscaminas.model.entity.User;
import com.setupteam.buscaminas.utilities.Utilities;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class Client implements Runnable {
	private Thread thread;
	private Server server;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private User user;

	public Client(Socket socket, Server server) throws IOException {
		this.thread = new Thread(this);
		this.server = server;
		this.socket = socket;
		this.dis = new DataInputStream(this.socket.getInputStream());
		this.dos = new DataOutputStream(this.socket.getOutputStream());

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
				this.server.receive(ConnectionCommands.valueOf(command), data, this);
			} catch (IOException e) {
				this.server.getClients().remove(this);
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

	public void interrumpt() throws IOException {
		this.dis.close();
		this.dos.close();
		this.socket.close();
		this.thread.interrupt();
	}
	
	public void initUser(String nickname){
		this.user= new User(nickname);
	}
	
	public User getUser() {
		return user;
	}
}