package project;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	// 프레임 창
	private ClientFrame messageFrame;

	// 소켓 장치
	private Socket socket;

	// 입출력 장치
	private MessageReader reader;
	private MessageWriter writer;

	// 연결 주소
	private String ip = "127.0.0.1";
	private int port = 10000;

	public Client() {
		initObject();
	}

	private void initObject() {
		// 프레임 창
		messageFrame = new ClientFrame("클라이언트");
		
		// 소켓 장치
		try {
			socket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 입출력 장치
		reader = new MessageReader(socket, messageFrame);
		writer = new MessageWriter(socket, messageFrame);
	}

	public static void main(String[] args) {
		new Client();
	}
}
