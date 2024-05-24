package project;

import javax.swing.JFrame;

import lombok.Data;

@Data
public class ClientFrame extends JFrame {

	private String name;

	// 로그인 창
	private IndexPanel indexPanel;
	
	// 메세지 창
	private MessagePanel messagePanel;

	public ClientFrame(String name) {
		this.name = name;
		initObject();
		initSetting();
		initListener();
	}

	private void initObject() {
		indexPanel = new IndexPanel();
		messagePanel = new MessagePanel();
	}

	private void initSetting() {
		setTitle("[ " + name + " ] 님의 메세지 톡");
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(indexPanel);
		
//		add(messagePanel);

		setVisible(true);
	}

	private void initListener() {
		
	}
	
	public static void main(String[] args) {
		new ClientFrame("");
	}

}
