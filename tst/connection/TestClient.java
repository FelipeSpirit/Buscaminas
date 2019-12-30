package connection;

import java.io.IOException;
import java.net.UnknownHostException;

import com.setupteam.buscaminas.connection.Client;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class TestClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Cliente");
		Client c=new Client("localhost", 3333);
		c.close();
	}
}
