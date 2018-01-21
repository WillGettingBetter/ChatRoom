package net_qunliao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerFrame extends JFrame {

	private JPanel contentPane;

	private TCPServer server;
	private JButton btnStart;
	private JButton btnStop;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame frame = new ServerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerFrame() {
		setTitle("\u670D\u52A1\u5668\u7AEF");
		// 实例化TCPServer对象
		server = new TCPServer(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 424, 276);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		btnStart = new JButton("\u542F\u52A8\u76D1\u542C");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 启动监听的单机事件
				server.startListener();// 启动监听
				if (server.isStart()) {
					btnStart.setEnabled(false);// 让启动监听按钮禁用
					btnStop.setEnabled(true);// 让停止监听按钮启用
					LogUtils.showLog("启动监听成功", textArea);
				} else {
					LogUtils.showLog("停止监听失败", textArea);
				}

			}
		});
		btnStart.setBounds(10, 306, 93, 23);
		contentPane.add(btnStart);

		btnStop = new JButton("\u505C\u6B62\u76D1\u542C");
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 停止监听
				// 启动监听的单机事件
				server.stopListener();// 启动监听
				if (!server.isStart()) {
					btnStart.setEnabled(true);// 让启动监听按钮启用
					btnStop.setEnabled(false);// 让停止监听按钮禁用
					LogUtils.showLog("停止监听成功", textArea);

				} else {
					LogUtils.showLog("停止监听失败", textArea);
				}
			}
		});
		btnStop.setBounds(128, 306, 93, 23);
		contentPane.add(btnStop);

		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 退出
				System.exit(0);
			}
		});
		btnExit.setBounds(331, 306, 93, 23);
		contentPane.add(btnExit);
	}

	/************* getter and setter ************************************/
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

}
