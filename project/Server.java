package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	// 프레임 창
	private ClientFrame messageFrame;

	// 소켓 장치
	private ServerSocket serverSocket;
	private Socket socket;

	// 입출력 장치
	private MessageReader reader;
	private MessageWriter writer;

	public Server() {
		initObject();
	}

	private void initObject() {
		// 프레임 창
		messageFrame = new ClientFrame("서버");
		
		// 소켓 장치
		try {
			serverSocket = new ServerSocket(10000);
			socket = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 입출력 장치
		reader = new MessageReader(socket, messageFrame);
		writer = new MessageWriter(socket, messageFrame);
	}

	public static void main(String[] args) {
		new Server();
	}
}
