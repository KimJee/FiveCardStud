package cardPackage;

import java.util.*;

//Rank.java - John K. Estell - 8 May 2003
//last modified: 16 Febraury 2004
//Implementation of the "rank" value for a playing card.

// Yes I am borrowing his implementation of Ranks / Cards / Deck / But the implementation of the cardgame idea is mine 

public class Rank implements Comparable {
	
	private String name;
	private String symbol;
	private int value;
	
	public static final Rank ACE = new Rank("Ace", "a");
	public static final Rank TWO = new Rank("Two", "2");
	public static final Rank THREE = new Rank("Three", "3");
	public static final Rank FOUR = new Rank("Four", "4");
	public static final Rank FIVE = new Rank("Five", "5");
	public static final Rank SIX = new Rank("Six", "6");
	public static final Rank SEVEN = new Rank("Seven", "7");
	public static final Rank EIGHT = new Rank("Eight", "8");
	public final static Rank NINE = new Rank( "Nine", "9" );
	public final static Rank TEN = new Rank( "Ten", "t" );	
    public final static Rank JACK = new Rank( "Jack", "j" );
	public final static Rank QUEEN = new Rank( "Queen", "q" );
	public final static Rank KING = new Rank( "King", "k" );
	
	private final static java.util.List VALUES_ACE_HIGH =
		      Collections.unmodifiableList( 
		         Arrays.asList( new Rank[] { TWO, THREE, FOUR, FIVE, SIX, SEVEN,
		                                     EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE } ) );


	public final static java.util.List VALUES = Collections.unmodifiableList( VALUES_ACE_HIGH );
	public Rank(String nameValue, String symbolValue)
	{
		name = nameValue;
		symbol = symbolValue;
		// TODO Auto-generated constructor stub
	}
	
	public int compareTo(Object otherRankObject)
	{
		Rank otherRank = (Rank) otherRankObject; 
		return this.getValue() - otherRank.getValue(); 
	}
	
	String getName()
	{
		return name;
	}
	
	String getSymbol()
	{
		return symbol;
	}

	
	public String toString()
	{
		return name;
	}
	
	public int getValue() {
		
		int value = 0;
		if (this.getSymbol().equals("a"))
		{
			value = 14;
		}
		else if (this.getSymbol().equals("2"))
		{
			value = 2;
		}
		else if (this.getSymbol().equals("3"))
		{
			value = 3;
		}
		else if (this.getSymbol().equals("4"))
		{
			value = 4;
		}
		else if (this.getSymbol().equals("5"))
		{
			value = 5;
		}
		else if (this.getSymbol().equals("6"))
		{
			value = 6;
		}
		else if (this.getSymbol().equals("7"))
		{
			value = 7;
		}
		else if (this.getSymbol().equals("8"))
		{
			value = 8;
		}
		else if (this.getSymbol().equals("9"))
		{
			value = 9;
		}
		else if (this.getSymbol().equals("t"))
		{
			value = 10;
		}
		else if (this.getSymbol().equals("j"))
		{
			value = 11;
		}
		else if (this.getSymbol().equals("q"))
		{
			value = 12;
		}
		else if (this.getSymbol().equals("k"))
		{
			value = 13;
		}
		
		return value; 
		
	}
}
