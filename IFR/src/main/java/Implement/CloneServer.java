package Implement;

import java.io.IOException;

import Infrastructure.Server;

public class CloneServer {
	public static String IPServer = "192.168.1.3";
	public static int portServer = 12345;

	public static void main(String[] args) throws IOException {
		Server server = new Server(portServer);
		server.start();
	}
}