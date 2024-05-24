package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Data;

@Data
public class MessageWriter implements Runnable, ActionListener {

	// 소켓 장치
	private Socket socket;

	// 출력 장치
	private BufferedWriter writer;

	// 출력할 대상
	private ClientFrame messageFrame;

	// 프레임 창의 MessagePanel 컴포넌트 접근
	private JButton sendMessageBtn;
	private JTextArea writeMessageBox;

	// 프레임 창의 IndexPanel 컴포넌트 접근
	private JTextField ip;
	private JTextField port;
	private JTextField id;
	private JButton connectBtn;

	public MessageWriter(Socket socket, ClientFrame messageFrame) {
		// 소켓 연결
		this.socket = socket;

		// 프레임 창 연결
		this.messageFrame = messageFrame;

		// 출력 장치
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 프레임 창의 MessagePanel 컴포넌트
		sendMessageBtn = messageFrame.getMessagePanel().getSendMessageBtn();
		writeMessageBox = messageFrame.getMessagePanel().getWriteMessageBox();

		// 프레임 창의 IndexPanel 컴포넌트 접근
		ip = messageFrame.getIndexPanel().getInputIp();
		port = messageFrame.getIndexPanel().getInputPort();
		id = messageFrame.getIndexPanel().getInputId();
		connectBtn = messageFrame.getIndexPanel().getConnectBtn();

		// 스레드
		new Thread(this).start();
	}

	@Override
	public void run() {
		// MessagePanel 컴포넌트
		sendMessageBtn.addActionListener(this);

		// IndexPanel 컴포넌트 접근
		connectBtn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == connectBtn) {
			String getip = ip.getText();

		} else if (e.getSource() == sendMessageBtn) {

			String msg = writeMessageBox.getText();

			try {
				writer.write(msg + "\n");
				writer.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void connectServer() {

	}
}
