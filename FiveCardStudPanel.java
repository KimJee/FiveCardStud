package cardPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.net.URL;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.Iterator;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalInternalFrameTitlePane;

import java.awt.event.*;

import javax.imageio.stream.IIOByteBuffer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;

public class FiveCardStudPanel extends JComponent {

	private final static int NUMBER_OF_PLAYERS = 4;
	private final static String directory = "/CardGame/CardImages";

	public static Deck carddeck = new Deck();
	public static PokerHand myHand = new PokerHand();
	public static PokerHand Player2 = new PokerHand();
	public static PokerHand Player3 = new PokerHand();
	public static PokerHand Player4 = new PokerHand();

	private final int WIDTH = 500, HEIGHT = 500;

	private int counter = 0;

	private static ImageIcon currentImage;
	private int x = 10, y = 10;
	
	final int RECT_X = 400;
	final int RECT_Y = 50;
	final int RECT_WIDTH = 400;
	final int RECT_HEIGHT = 97;
	final int RECT_HEIGHT_SPACER = 150;
	final int CARD_WIDTH_SPACER = 80;

	public static ImageIcon back = new ImageIcon("b.gif");
	
	public FiveCardStudPanel() {

		
		// TODO Auto-generated constructor stub

		// What needs to be set up before we set up the game
		/*
		 * 1) Background needs to be set
		 * 2) Cards need to be dealt by the dealer and
		 * given to each player
		 * 
		 * Visual Guide?
		 * 
		 * --- --- --- --- --- Player 1 | | | | | | | | | | --- --- --- --- --- --- ---
		 * --- --- --- Computer 1 | | | | | | | | | | // All face down --- --- --- ---
		 * --- --- --- --- --- --- Computer 2 | | | | | | | | | | --- --- --- --- ---
		 * --- --- --- --- --- Computer 3 | | | | | | | | | | --- --- --- --- ---
		 * 
		 * 3) Once card has been dealt we have to prompt the users how many cards to
		 * replace, and which cards (either by name or by index) 
		 * 4) Translate them off
		 * screen, and replace them with cards 
		 * 5) Reveal all the cards 
		 * 6) Evaluate all
		 * the Hands 
		 * 7) Determine who wins wins 
		 * 8a) If win --> Win screen 
		 * 8b) If lose
		 * --> Lose screen
		 */

		initGame();


		for (int i = 0; i < myHand.getNumberOfCards(); i++)
		{
			currentImage = myHand.getCard(i).getCardImage();
			x = RECT_X  + i*CARD_WIDTH_SPACER;
			y = RECT_Y;
			repaint();
		}
		
		for (int i = 0; i < 5; i++) 
		{
			currentImage = back;
			x = RECT_X + i * CARD_WIDTH_SPACER;
			y = RECT_Y + RECT_HEIGHT_SPACER;
			//page.drawImage(back.getImage(), RECT_X + i * CARD_WIDTH_SPACER, RECT_Y + 2 * RECT_HEIGHT_SPACER, null);
			//page.drawImage(back.getImage(), RECT_X + i * CARD_WIDTH_SPACER, RECT_Y + 3 * RECT_HEIGHT_SPACER, null);
		}
		
	
		

		endGame();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.black);
		setOpaque(false);

	}

	public void paintComponent(Graphics page) {

		

		super.paintComponent(page);

		page.drawString("You", RECT_X - 100, RECT_Y + 50);
		page.drawString("Computer 1", RECT_X - 100, RECT_Y + 50 + RECT_HEIGHT_SPACER);
		page.drawString("Computer 2", RECT_X - 100, RECT_Y + 50 + 2 * RECT_HEIGHT_SPACER);
		page.drawString("Computer 3", RECT_X - 100, RECT_Y + 50 + 3 * RECT_HEIGHT_SPACER);

		
		
		
		
//		page.drawImage(myHandCard0.getImage(), RECT_X, RECT_Y, null);
//		page.drawImage(myHandCard1.getImage(), RECT_X + CARD_WIDTH_SPACER, RECT_Y, null);
//		page.drawImage(myHandCard2.getImage(), RECT_X + 2 * CARD_WIDTH_SPACER, RECT_Y, null);
//		page.drawImage(myHandCard3.getImage(), RECT_X + 3 * CARD_WIDTH_SPACER, RECT_Y, null);
//		page.drawImage(myHandCard4.getImage(), RECT_X + 4 * CARD_WIDTH_SPACER, RECT_Y, null);
//
//		
//		// Would you like to get rid of some cards
//		int answer = JOptionPane.showConfirmDialog(Driver.frame, "Would you like to cheat?");
//
//		if (answer == JOptionPane.YES_OPTION) 
//		{
//			for (int i = 0; i < 5; i++) {
//				page.drawImage(Player2.getCard(i).getCardImage().getImage(), RECT_X + i * CARD_WIDTH_SPACER,
//						RECT_Y + RECT_HEIGHT_SPACER, null);
//				page.drawImage(Player3.getCard(i).getCardImage().getImage(), RECT_X + i * CARD_WIDTH_SPACER,
//						RECT_Y + 2 * RECT_HEIGHT_SPACER, null);
//				page.drawImage(Player4.getCard(i).getCardImage().getImage(), RECT_X + i * CARD_WIDTH_SPACER,
//						RECT_Y + 3 * RECT_HEIGHT_SPACER, null);
//
//			}
//		}
//		else {
//			System.exit(-1);
//		}
		
		currentImage.paintIcon(this, page, x, y);

		
	}

	public static void initGame() {

		populateDeck();
		carddeck.shuffle();

		for (int i = 0; i < 5; i++) {
			myHand.addCard(carddeck.dealCard());
			Player2.addCard(carddeck.dealCard());
			Player3.addCard(carddeck.dealCard());
			Player4.addCard(carddeck.dealCard());
		}

	
	}

	public static void endGame() {
		
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Cards in my hand are: ");
		System.out.println("Value of my hand is: " + myHand.evaluateHand());
		System.out.println("The amount of cards in the deck is: " + carddeck.getNumberOfCardsRemaining());
		System.out.println("-----------------------------------------------------------------------");

		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Cards in Player 2's hand are: ");
		System.out.println("Value of Player 2 hand is: " + Player2.evaluateHand());
		System.out.println("-----------------------------------------------------------------------");

		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Cards in Player 3's hand are: ");
		System.out.println("Value of Player 3 hand is: " + Player3.evaluateHand());
		System.out.println("-----------------------------------------------------------------------");

		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Cards in Player 4's hand are: ");
		System.out.println("Value of Player 4 hand is: " + Player4.evaluateHand());
		System.out.println("-----------------------------------------------------------------------");

	
		
		myHand.discardHand();
		Player2.discardHand();
		Player3.discardHand();
		Player4.discardHand();
		carddeck.restoreDeck();
	}

	public static void populateDeck() {

		Iterator suitIterator = Suit.VALUES.iterator();
		while (suitIterator.hasNext()) {
			Suit suit = (Suit) suitIterator.next();
			Iterator rankIterator = Rank.VALUES.iterator();
			while (rankIterator.hasNext()) {
				Rank rank = (Rank) rankIterator.next();
				String imageFile = Card.getFilename(suit, rank);
				ImageIcon cardImage = new ImageIcon(imageFile);
				Card card = new Card(suit, rank, cardImage);
				carddeck.addCard(card);
			}
		}
	}

	public static void insertSpecificCard(String ranksymbol, String suitsymbol) {
		Rank rank = new Rank(rankSymbolToRankNameValue(ranksymbol), ranksymbol);
		Suit suit = new Suit(suitSymboltoSuitName(suitsymbol), suitsymbol);

		String imageFile = directory + Card.getFilename(suit, rank) + ".gif";
		ImageIcon cardImage = new ImageIcon(imageFile);

		Card card = new Card(suit, rank, cardImage);
		carddeck.addCard(card);
	}

	public static String rankSymbolToRankNameValue(String rankSymbol) {
		String rankname = null;
		if (rankSymbol.equals("a")) {
			rankname = "Ace";
		} else if (rankSymbol.equals("2")) {
			rankname = "Two";
		} else if (rankSymbol.equals("3")) {
			rankname = "Three";
		} else if (rankSymbol.equals("4")) {
			rankname = "Four";
		} else if (rankSymbol.equals("5")) {
			rankname = "Five";
		} else if (rankSymbol.equals("6")) {
			rankname = "Six";
		} else if (rankSymbol.equals("7")) {
			rankname = "Seven";
		} else if (rankSymbol.equals("8")) {
			rankname = "Eight";
		} else if (rankSymbol.equals("9")) {
			rankname = "Nine";
		} else if (rankSymbol.equals("t")) {
			rankname = "Ten";
		} else if (rankSymbol.equals("j")) {
			rankname = "Jack";
		} else if (rankSymbol.equals("q")) {
			rankname = "Queen";
		} else if (rankSymbol.equals("k")) {
			rankname = "King";
		}

		return rankname;

	}

	public static String suitSymboltoSuitName(String suitsymbol) {
		String suitname = "";

		if (suitsymbol.equals("c")) {
			suitname = "Clubs";
		} else if (suitsymbol.equals("d")) {
			suitname = "Diamonds";
		} else if (suitsymbol.equals("h")) {
			suitname = "Hearts";
		} else {
			suitname = "Spades";
		}
		return suitname;
	}

}
