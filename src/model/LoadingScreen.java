package model;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bookProject.SingleObject;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollBar;
import java.awt.Window.Type;
import java.lang.management.RuntimeMXBean;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoadingScreen extends JFrame {

	private JPanel contentPane;
	static SingleObject obj;

	public LoadingScreen() {
		setType(Type.UTILITY);
		setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("loading.gif"));
		label.setBounds(0, 0, 382, 253);
		contentPane.add(label);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				obj = SingleObject.getSingleObject();
				Thread thread = new Thread() {
					@Override
					public void run() {
						obj.getListBook().setLocationRelativeTo(null);
						obj.getListBook().setVisible(true);
					}
				};
				thread.start();
			}
		});
		// setUndecorated(true);
	}

}
