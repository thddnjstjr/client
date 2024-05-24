package ch06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// 2단계 - 상속 활용 리팩토링 단계
public abstract class AbstractClient {
	
	private Socket socket;
	private BufferedReader readerStream;
	private PrintWriter printWriter;
	private BufferedReader keyboardReader;
	
	// set 메소드
	// 메소드 의존 주입(멤버 변수에 파라미터변수 할당)
	protected void setSocket (Socket socket) {
		this.socket = socket;
	}
	
	// get 메소드 
	protected Socket getSocket () {
		return socket;
	}
	
	public final void run() {
		try {
			setUpSocket();
			setupStream();
			startService();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanup();
		}
	}
	
	protected abstract void setUpSocket() throws IOException;
	
	private void setupStream() throws IOException {
		readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		printWriter = new PrintWriter(socket.getOutputStream(), true);
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private void startService() {
		Thread readThread = createReadThread();
		Thread sendThread = createWriteThread();
		
		readThread.start();
		sendThread.start();
		
		try {
			readThread.join();
			sendThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// 캡슐화
	private Thread createReadThread() {
		return new Thread(() -> {
			try {
				String msg;
				while( (msg = readerStream.readLine()) != null) {
					System.out.println("서버에서 보낸 메세지 : " + msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private Thread createWriteThread() {
		return new Thread(() -> {
			try {
				String sendmsg;
				while( (sendmsg = keyboardReader.readLine()) != null) {
					printWriter.println(sendmsg);
					printWriter.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private void cleanup() {
		try {
			if(socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
