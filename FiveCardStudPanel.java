package cardPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
public class FiveCardStudPanel extends JPanel {
	
	private final int WIDTH = 500, HEIGHT = 500;
	private final int DELAY = 150, IMAGE_SIZE = 35;
	private static ImageIcon image;
	private static int imageNum = -1;
    private static boolean soundFlag = true;
	private Timer timer;
	private int x, y, moveX, moveY;
	
	public FiveCardStudPanel() {
		// TODO Auto-generated constructor stub
		

		// What needs to be set up before we set up the game
		/*
		 * 1) Background needs to be set
		 * 2) Cards need to be dealt by the dealer and given to each player
		 * 
		 * 			Visual Guide?
		 * 
		 *          	 --- --- --- --- ---
		 *  Player 1 	 | | | | | | | | | |
		 *               --- --- --- --- ---
		 *     	         --- --- --- --- ---
		 *  Computer 1   | | | | | | | | | |  // All face down
		 *     		 --- --- --- --- ---
		 *  		 --- --- --- --- ---
		 *  Computer 2   | | | | | | | | | |
		 *     	         --- --- --- --- ---
		 *     		 --- --- --- --- ---
		 *  Computer 3   | | | | | | | | | |
		 *     		 --- --- --- --- ---
		 *     
		 * 3) Once card has been dealt we have to prompt the users how many cards to replace, and which cards (either by name or by index)
		 * 4) Translate them off screen, and replace them with cards
		 * 5) Reveal all the cards 
		 * 6) Evaluate all the Hands 
		 * 7) Determine who wins wins
		 * 8a) If win --> Win screen
		 * 8b) If lose --> Lose screen
		 * 
		 * 
		 * 
		 * 
		 */
	      x = 0;
	      y = 40;
	      moveX = 15;
	      moveY = -5;
	      setPreferredSize (new Dimension(WIDTH, HEIGHT));
	      setBackground (Color.black);
	      setOpaque(false);
	  
	}

	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		if (image !=  null)
		{
			image.paintIcon(this, page, x, y);
		}
	}
	
		private class CardListener implements ActionListener
		{
			public void actionPerformed(ActionEvent action) 
			{
				// Need to start with a "Play Button" and when that is pressed I need to deal the cards
				x += moveX;
				URL url = this.getClass().getClassLoader().getResource("cardPlace1.wav");
				moveY = -10;
				y += moveY;
				
				if (soundFlag) 
				{
					try 
					{
				         // Open an audio input stream.
						 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				         // Get a sound clip resource.
				         Clip clip = AudioSystem.getClip();
				         // Open audio clip and load samples from the audio input stream.
				         clip.open(audioIn);
				         clip.start();
				      } catch (UnsupportedAudioFileException e) {
				         e.printStackTrace();
				      } catch (IOException e) {
				         e.printStackTrace();
				      } catch (LineUnavailableException e) {
				         e.printStackTrace();
				      }
				}
				
				
		}
	}
}
	
