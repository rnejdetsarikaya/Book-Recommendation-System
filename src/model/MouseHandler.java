package model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bookProject.SingleObject;

public class MouseHandler {

	static SingleObject obj = SingleObject.getSingleObject();

	public static void mouseListener(JTextArea textArea) {
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
			}
		});
	}

	public static void mouseListener(JTextField textField) {
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
			}
		});
	}

	public static void mouseListener(JButton button, String name, List<Book> books) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				obj = SingleObject.getSingleObject();
				obj.getReadBook().setVisible(true);
				obj.getReadBook().name = name;
				obj.getReadBook().books = books;
			}
		});
	}

	public static void mouseListener(JButton button) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Random rand = new Random();
				int random = rand.nextInt(11);
				PDFReader reader = new PDFReader(
						// C:\Users\ali
						// haydar\Desktop\YazLab-1.zip_expanded2018.10.31-01.48\YazLab-1.zip_expanded\YazLab-1\books
						"books\\" + random + ".pdf");
				reader.readPDF();
			}
		});
	}
}
