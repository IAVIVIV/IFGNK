package Infrastructure;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private ServerSocket serverSocket;

	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	public void start() throws IOException {
		System.out.println("Server started...");
		while (true) {
			new ClientHandler(serverSocket.accept()).start();
		}
	}
}