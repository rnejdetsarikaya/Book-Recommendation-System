package model;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.icepdf.ri.common.MyAnnotationCallback;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class PDFReader {

	String bookPDFPath;
	
	public PDFReader(String bookPDFPath) {
		this.bookPDFPath=bookPDFPath;
	}
	public void readPDF() {
		SwingController controller=new SwingController();
		JPanel panel =new SwingViewBuilder(controller).buildViewerPanel();
		controller.getDocumentViewController().setAnnotationCallback(new MyAnnotationCallback(controller.getDocumentViewController()));
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(panel);
		controller.openDocument(bookPDFPath);
		frame.pack();
		frame.setVisible(true);
	}
}
