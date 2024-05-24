package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import lombok.Data;

/**
 * 상대방이 보낸 메세지를 소켓통신을 이용해서 읽어들인다.<br>
 * 
 * @author 김현아
 *
 */
@Data
public class MessageReader implements Runnable {

	// 소켓 장치
	private Socket socket;

	// 입력 장치
	private BufferedReader reader;

	// 입력 대상
	private ClientFrame messageFrame;

	//
	private String msg;

	public MessageReader(Socket socket, ClientFrame messageFrame) {
		// 소켓 연결
		this.socket = socket;

		// 프레임 창 연결
		this.messageFrame = messageFrame;

		// 입력 장치
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 스레드
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				msg = reader.readLine();
				readMessage();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readMessage() {
		messageFrame.getMessagePanel().getMainMessageBox().append("상대가 보낸 메세지 : " + msg + "\n");
	}
}
