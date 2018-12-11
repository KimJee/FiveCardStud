package cardPackage;

import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JApplet;

public class Driver{

	public Driver() {
		// TODO Auto-generated constructor stub
	}

	private final static String directory = "/CardGame/CardImages";
	private final static int NUMBER_OF_PLAYERS = 4;
	public static Deck carddeck = new Deck();
	public static void main(String[] args) {
		
		PokerHand myHand = new PokerHand();
		PokerHand Player2 = new PokerHand();
		PokerHand Player3 = new PokerHand();
		PokerHand Player4 = new PokerHand();
		
		
		
//		insertSpecificCard("t", "s");
//		insertSpecificCard("a", "s");
//		insertSpecificCard("k", "c");
//		insertSpecificCard("q", "h");
//		insertSpecificCard("j", "c");
//		
//
		//insertSpecificCard("3", "s");
		//insertSpecificCard("5", "h");
		//insertSpecificCard("k", "d");
		//insertSpecificCard("7", "s");
		//insertSpecificCard("a", "c");
		
		populateDeck();
		carddeck.shuffle();
		
		System.out.println("Number of cards in deck: " + carddeck.getNumberOfCardsRemaining());
		
		while (carddeck.getNumberOfCardsRemaining() > (5*NUMBER_OF_PLAYERS - 1))
		{
			for (int i = 0; i < 5; i++)
			{
				myHand.addCard(carddeck.dealCard());
				Player2.addCard(carddeck.dealCard());
				Player3.addCard(carddeck.dealCard());
				Player4.addCard(carddeck.dealCard());
			}
			
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
			System.out.println("The amount of cards in the deck is: " + carddeck.getNumberOfCardsRemaining());
			System.out.println("-----------------------------------------------------------------------");
			
			System.out.println();
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("Cards in Player 3's hand are: ");
			System.out.println("Value of Player 3 hand is: " + Player3.evaluateHand());
			System.out.println("The amount of cards in the deck is: " + carddeck.getNumberOfCardsRemaining());
			System.out.println("-----------------------------------------------------------------------");
			
			System.out.println();
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("Cards in Player 4's hand are: ");
			System.out.println("Value of Player 4 hand is: " + Player4.evaluateHand());
			System.out.println("The amount of cards in the deck is: " + carddeck.getNumberOfCardsRemaining());
			System.out.println("-----------------------------------------------------------------------");
			
			Player4.discardHand();
			Player3.discardHand();
			Player2.discardHand();
			myHand.discardHand();
		}
	}
	
	public static void populateDeck() {
		
		Iterator suitIterator = Suit.VALUES.iterator();
		while (suitIterator.hasNext())
		{
		    Suit suit = (Suit) suitIterator.next();
		    Iterator rankIterator = Rank.VALUES.iterator();
		    while ( rankIterator.hasNext() ) 
		    {
		        Rank rank = (Rank) rankIterator.next();
		        String imageFile = directory + Card.getFilename( suit, rank ) + ".gif";
		        ImageIcon cardImage = new ImageIcon(imageFile);
		        Card card = new Card( suit, rank, cardImage );
		        carddeck.addCard( card );
		    }
		}
	}

	public static void insertSpecificCard(String ranksymbol, String suitsymbol) 
	{
		Rank rank = new Rank(rankSymbolToRankNameValue(ranksymbol), ranksymbol);
		Suit suit = new Suit(suitSymboltoSuitName(suitsymbol), suitsymbol);
		
		String imageFile = directory + Card.getFilename(suit, rank) + ".gif";
		ImageIcon cardImage = new ImageIcon(imageFile); 
		
		Card card = new Card(suit, rank, cardImage);
		carddeck.addCard(card);
	}
	
	public static String rankSymbolToRankNameValue(String rankSymbol) 
	{
		String rankname = null;
		if (rankSymbol.equals("a"))
		{
			rankname = "Ace";
		}
		else if (rankSymbol.equals("2"))
		{
			rankname = "Two";
		}
		else if (rankSymbol.equals("3"))
		{
			rankname = "Three";
		}
		else if (rankSymbol.equals("4"))
		{
			rankname = "Four";
		}
		else if (rankSymbol.equals("5"))
		{
			rankname = "Five";
		}
		else if (rankSymbol.equals("6"))
		{
			rankname = "Six";
		}
		else if (rankSymbol.equals("7"))
		{
			rankname = "Seven";
		}
		else if (rankSymbol.equals("8"))
		{
			rankname = "Eight";
		}
		else if (rankSymbol.equals("9"))
		{
			rankname = "Nine";
		}
		else if (rankSymbol.equals("t"))
		{
			rankname = "Ten";
		}
		else if (rankSymbol.equals("j"))
		{
			rankname = "Jack";
		}
		else if (rankSymbol.equals("q"))
		{
			rankname = "Queen";
		}
		else if (rankSymbol.equals("k"))
		{
			rankname = "King";
		}
		
		return rankname; 
		
	}
	
	public static String suitSymboltoSuitName(String suitsymbol)
	{
		String suitname = "";
		
		if (suitsymbol.equals("c"))
		{
			suitname = "Clubs";
		}
		else if (suitsymbol.equals("d"))
		{
			suitname = "Diamonds";
		}
		else if (suitsymbol.equals("h"))
		{
			suitname = "Hearts";
		}
		else
		{
			suitname = "Spades";
		}
		return suitname;
	}
	
	
}

	
	
