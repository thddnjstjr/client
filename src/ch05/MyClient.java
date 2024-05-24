package ch05;

import java.io.IOException;
import java.net.Socket;

// 2 -1 상속을 활용한 구현 클래스 설계 하기
public class MyClient extends AbstractClient{

	@Override
	protected void setUpSocket() throws IOException {
		super.setSocket(new Socket("192.168.0.117", 5000));
		System.out.println("--- 클라이언트 접속 --- ");
	}
	
	public static void main(String[] args) {
		MyClient myClient = new MyClient();
		myClient.run();
	}

}
