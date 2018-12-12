package cardPackage;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Driver{

	public Driver() {
		// TODO Auto-generated constructor stub
	}

	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 500;
	
	
	
	
	static JLabel background;
	static ImageIcon bg;
	public static JFrame frame = new JFrame("FiveCardStud");

	
	public static void main(String[] args) throws InterruptedException {
	
		
		
		
		
		
		bg = new ImageIcon("green_felt.jpg");
 		background = new JLabel();
 		background.setIcon(bg);
		background.setLayout(new BorderLayout());
		
		

		
		
		
		
//		insertSpecificCard("t", "s");
//		insertSpecificCard("a", "s");
//    	insertSpecificCard("k", "c");
//		insertSpecificCard("q", "h");
//		insertSpecificCard("j", "c");
		

		//insertSpecificCard("3", "s");
		//insertSpecificCard("5", "h");
		//insertSpecificCard("k", "d");
		//insertSpecificCard("7", "s");
		//insertSpecificCard("a", "c");
		
		
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(background);
		frame.getContentPane().add(new FiveCardStudPanel());

		frame.setLocation(450, 200);
		//frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.pack();
		frame.setVisible(true);
		
		
		

	}

}
	

	
	
