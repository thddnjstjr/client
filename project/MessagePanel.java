package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import lombok.Data;

@Data
public class MessagePanel extends JPanel {

	private JPanel mainPanel;
	private JPanel bottomPanel;
	
	private JTextArea mainMessageBox;
	private JTextArea writeMessageBox;
	private JButton sendMessageBtn;
	
	private String messageText;
	
	public MessagePanel() {
		initObject();
		initSetting();
		initListener();
	}

	private void initObject() {
		mainPanel = new JPanel();
		bottomPanel = new JPanel();

		mainMessageBox = new JTextArea("[ 채팅 ]\n", 30, 30);
		writeMessageBox = new JTextArea(2, 23);
		sendMessageBtn = new JButton("전송");
	}

	private void initSetting() {
		setSize(getWidth(), getHeight());
		setLayout(new BorderLayout());

		mainMessageBox.setEnabled(false);
		sendMessageBtn.setBackground(Color.WHITE);

		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		mainPanel.add(mainMessageBox);
		bottomPanel.add(writeMessageBox);
		bottomPanel.add(sendMessageBtn);
	}

	private void initListener() {
		sendMessageBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				writeMessageBox.setText("");
			}
		});
	}
}
