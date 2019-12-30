package connection;

import java.io.IOException;
import java.util.Scanner;

import com.setupteam.buscaminas.connection.ConnectionCommands;
import com.setupteam.buscaminas.connection.Server;
import com.setupteam.buscaminas.utilities.Constants;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class TestServer {
	public static void main(String[] args) {
		System.out.println("Servidor " + Constants.nickname);
		Server s=null;
		try {
			s = new Server(3333);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		Scanner sc = new Scanner(System.in);
		String command = "";
		while (!command.equals("OUT")) {
			System.out.print(Constants.prompt);
			command= sc.nextLine().toUpperCase();
			
			switch(command) {
			case "START":
				s.receive(ConnectionCommands.START, new byte[] {Constants.EASY_MODE}, null);
				break;
			}
		}
		
		try {
			s.close();
			s.interrumpt();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		sc.close();
	}
}
