package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 1 단계 - 함수로 분리 해서 리펙토링 진행
public class MultiTreadClient {

	public static void main(String[] args) {
		
		System.out.println(">>> 클라이언트 실행<<<");
		
		try(Socket socket = new Socket("localhost",5000)){
			System.out.println("---connected to server---");
			
			PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader socketReader = 
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader keyboardReader = 
					new BufferedReader(new InputStreamReader(System.in));
			
			// 스레드 시작
			startSendThread(socketWriter, keyboardReader);
			startReadThread(socketReader);
			
			System.out.println("메인 스레드 작업 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	} // end of main
	
	// 서버로부터 데이터를 읽는 메소드
	private static void startReadThread(BufferedReader bufferedReader) {
		Thread readThread = new Thread(() -> {
			try {
				String serverMessage;
				while( (serverMessage = bufferedReader.readLine()) != null) {
					// 서버에서 보낸 메세지를 읽음
					System.out.println("서버에서 온 메세지 : " + serverMessage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		readThread.start();
		
	}
	
	// 서버로 데이터를 보내는 메소드
	private static void startSendThread(PrintWriter printWriter,BufferedReader keyboardReader) {
		Thread sendThread = new Thread(() -> {
			try {
				String sendMessage;
				while( (sendMessage = keyboardReader.readLine()) != null) {
					printWriter.println(sendMessage);
					printWriter.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		sendThread.start();
	}
	
	// 스레드 종료까지 기다리는 메소드
	private static void waitForThreadToEnd(Thread thread) {
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
} // end of class
