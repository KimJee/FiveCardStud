package cardPackage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.xml.ws.AsyncHandler;

public class PokerHand extends Hand {



	public static boolean SortByRank = false;
	
	public static int ROYAL_FLUSH =    9000000;
	public static int STRAIGHT_FLUSH = 8000000;
	public static int FOUR_OF_A_KIND = 7000000;
	public static int FULL_HOUSE =     6000000;
	public static int FLUSH =          5000000;
	public static int STRAIGHT =       4000000;
	public static int SET =            3000000;
	public static int TWO_PAIRS =      2000000;
	public static int ONE_PAIR =       1000000;
	
	
	
	
	public PokerHand() {
		
		// TODO Auto-generated constructor stub
		
	}


	@Override	
	public int evaluateHand() {
		sortByRank(this);
		System.out.println(this);
		  if (isRoyalFlush(this))
			 return valueRoyalFlush(this);
	      else if (isStraightFlush(this) )
	         return valueStraightFlush(this);
	      else if ( isPoker(this))
	         return valueFourOfAKind(this);
	      else if ( isFullHouse(this) )
	         return valueFullHouse(this);
	      else if ( isFlush(this) )
	         return valueFlush(this);
	      else if ( isStraight(this) )
	         return valueStraight(this);
	      else if ( isThreeOfAKind(this))
	         return valueSet(this);
	      else if ( isTwoPair(this) )
	         return valueTwoPairs(this);
	      else if ( isOnePair(this) )
	         return valueOnePair(this);
	      else
	         return valueHighCard(this);
	}
	
	/*
	 * Below are methods for judging the hand
	 * 
	 * 
	 */
	
		public static int valueRoyalFlush (Hand h)
		{
			System.out.println("You have a Royal Flush!");	
			return ROYAL_FLUSH + valueHighCard(h);
		}
		public static int valueStraightFlush( Hand h )
	    {
		  System.out.println("You have a Straight Flush!");
	      return STRAIGHT_FLUSH + valueHighCard(h);
	    }

	   /* -----------------------------------------------------
	      valueFlush(): return value of a Flush hand

	            value = FLUSH + valueHighCard()
	      ----------------------------------------------------- */
	   public static int valueFlush(Hand h )
	   {
		  System.out.println("You have a Flush!");
	      return FLUSH + valueHighCard(h);
	   }

	   /* -----------------------------------------------------
	      valueStraight(): return value of a Straight hand

	            value = STRAIGHT + valueHighCard()
	      ----------------------------------------------------- */
	   public static int valueStraight( Hand h )
	   {
		  System.out.println("You have a Straight!");	
	      return STRAIGHT + valueHighCard(h);
	   }

	   /* ---------------------------------------------------------
	      valueFourOfAKind(): return value of a 4 of a kind hand

	            value = FOUR_OF_A_KIND + 4sCardRank

	      Trick: card h.get(2) is always a card that is part of 
	             the 4-of-a-kind hand
		     There is ONLY ONE hand with a quads of a given rank.
	      --------------------------------------------------------- */
	   public static int valueFourOfAKind(Hand h )
	   {
		  System.out.println("You have a Poker!");
	      return FOUR_OF_A_KIND + (h.getCard(2).getRank().getValue());
	   }

	   /* -----------------------------------------------------------
	      valueFullHouse(): return value of a Full House hand

	            value = FULL_HOUSE + SetCardRank

	      Trick: card h.get(2) is always a card that is part of
	             the 3-of-a-kind in the full house hand
		     There is ONLY ONE hand with a FH of a given set.
	      ----------------------------------------------------------- */
	   public static int valueFullHouse(Hand h)
	   {
	      System.out.println("You have a Full House!");
	      return FULL_HOUSE + (h.getCard(2).getRank().getValue());
	   }

	   /* ---------------------------------------------------------------
	      valueSet(): return value of a Set hand

	            value = SET + SetCardRank

	      Trick: card h.get(2) is always a card that is part of the set hand
		     There is ONLY ONE hand with a set of a given rank.
	      --------------------------------------------------------------- */
	   public static int valueSet( Hand h )
	   {
	      System.out.println("You have a Triple!");
	      return SET + (h.getCard(2).getRank().getValue());
	   }

	   /* -----------------------------------------------------
	      valueTwoPairs(): return value of a Two-Pairs hand

	            value = TWO_PAIRS
	                   + 14*14*HighPairCard
	                   + 14*LowPairCard
	                   + UnmatchedCard
	      ----------------------------------------------------- */
	   public static int valueTwoPairs(Hand h )
	   {
	      int val = 0;

	      sortByRank(h);

	      if ( h.getCard(0).getRank().getValue() == h.getCard(1).getRank().getValue() && h.getCard(2).getRank().getValue() == h.getCard(3).getRank().getValue() )
	         val = 14*14*(h.getCard(2).getRank().getValue()) + 14*(h.getCard(0).getRank().getValue()) + (h.getCard(4).getRank().getValue());
	      else if ( h.getCard(0).getRank().getValue() == h.getCard(1).getRank().getValue() && h.getCard(3).getRank().getValue() == h.getCard(4).getRank().getValue() )
	         val = 14*14*(h.getCard(3).getRank().getValue()) + 14*(h.getCard(0).getRank().getValue()) + (h.getCard(2).getRank().getValue());
	      else 
	         val = 14*14*(h.getCard(3).getRank().getValue()) + 14*(h.getCard(1).getRank().getValue()) + (h.getCard(0).getRank().getValue());

	      System.out.println("You have Two Pairs!");
	      return TWO_PAIRS + val;
	      
	   }

	   /* -----------------------------------------------------
	      valueOnePair(): return value of a One-Pair hand

	            value = ONE_PAIR 
	                   + 14^3*PairCard
	                   + 14^2*HighestCard
	                   + 14*MiddleCard
	                   + LowestCard
	      ----------------------------------------------------- */
	   public static int valueOnePair(Hand h )
	   {
	      int val = 0;

	      sortByRank(h);

	      if ( h.getCard(0).getRank().getValue() == h.getCard(1).getRank().getValue() )
	         val = 14*14*14*(h.getCard(0).getRank().getValue()) + (h.getCard(2).getRank().getValue()) + 14*(h.getCard(3).getRank().getValue()) + 14*14*(h.getCard(4).getRank().getValue());
	      else if ( h.getCard(1).getRank().getValue() == h.getCard(2).getRank().getValue())
	         val = 14*14*14*(h.getCard(1).getRank().getValue()) + (h.getCard(0).getRank().getValue()) + 14*(h.getCard(3).getRank().getValue()) + 14*14*(h.getCard(4).getRank().getValue());
	      else if ( h.getCard(2).getRank().getValue() == h.getCard(3).getRank().getValue())
	         val = 14*14*14*(h.getCard(2).getRank().getValue()) + (h.getCard(0).getRank().getValue()) + 14*(h.getCard(1).getRank().getValue()) + 14*14*(h.getCard(4).getRank().getValue());
	      else
	         val = 14*14*14*(h.getCard(3).getRank().getValue()) + (h.getCard(0).getRank().getValue()) + 14*(h.getCard(1).getRank().getValue()) + 14*14*(h.getCard(2).getRank().getValue());
	      System.out.println("You have a pair!");
	      return ONE_PAIR + val;
	   }

	   /* -----------------------------------------------------
	      valueHighCard(): return value of a high card hand

	            value =  14^4*highestCard 
	                   + 14^3*2ndHighestCard
	                   + 14^2*3rdHighestCard
	                   + 14^1*4thHighestCard
	                   + LowestCard
	      ----------------------------------------------------- */
	   public static int valueHighCard(Hand h )
	   {
	      int val;

	      sortByRank(h);

	      val = (h.getCard(0).getRank().getValue()) + 14*(h.getCard(1).getRank().getValue()) + 14*14*(h.getCard(2).getRank().getValue()) 
	            + 14*14*14*(h.getCard(3).getRank().getValue()) + 14*14*14*14*(h.getCard(4).getRank().getValue());
	      return val;
	   }
	
	
/*
 *  Below are methods that define what the Hand is
 * 
 */
	
	public static boolean isFlush(Hand h)
	{ 		
		if (h.getNumberOfCards() != 5)
		{
			System.err.println("The hand does not have 5 cards");
			return false;
		}
	
		
		boolean isFlush = false;
		sortBySuit(h);
		
		if (h.getCard(0).getSuit().getSymbol().equals(h.getCard(4).getSuit().getSymbol()))
		{
			isFlush = true;
		}
		return isFlush;
	}
	
	public static boolean isStraight(Hand h)
	{
		boolean isStraight = false; 
		
		if (h.getNumberOfCards() != 5)
		{
			System.err.println("The hand does not have 5 cards");
			return false;
		}
		
		
		//System.out.println("Before Sort" + h);
		sortByRank(h);
		//System.out.println("After Sort" + h);
		
	
	
			
		if (h.getCard(4).getRank().getSymbol().equals("a")) 
		{
			if (h.getCard(0).getRank().getSymbol().equals("2") && h.getCard(1).getRank().getSymbol().equals("3") && h.getCard(2).getRank().getSymbol().equals("4") && h.getCard(3).getRank().getSymbol() .equals("5"))
			{
				isStraight = true;
			}
			if (h.getCard(0).getRank().getSymbol().equals("t") && h.getCard(1).getRank().getSymbol().equals("j") && h.getCard(2).getRank().getSymbol().equals("q") && h.getCard(3).getRank().getSymbol().equals("k"))
			{
				isStraight = true;
			}
		}
		else
		{
			//System.out.println("The value in the first card is: " + h.get(0).getRank().getValue());
			int nextRank = (h.getCard(0).getRank().getValue()) + 1;
			//System.out.println("The value of nextRank is: " + nextRank);
			
			
			isStraight = true;
			for (int a = 1; a < h.getNumberOfCards(); a++)
			{
				if ((h.getCard(a).getRank().getValue()) != nextRank)
				{
					//System.out.println("On this index: " + a + " isStraight was set to false");
					isStraight = false;
					break; 
				}
				nextRank++;
				//System.out.println("The value of nextRank is: " + nextRank);
			}
			
		}
		return isStraight;
	}
	
	public static boolean isStraightFlush(Hand h)
	{
		if (h.getNumberOfCards() != 5)
		{
			System.err.println("The hand does not have 5 cards");
			return false;
		}
		
		if(isRoyalFlush(h))
		{
			return false;
		}
		
		boolean isStraightFlush = false;
		sortByRank(h);
		if(isStraight(h) && isFlush(h))
		{
			isStraightFlush = true;
		}
		return isStraightFlush;	
	}
	
	public static boolean isRoyalFlush(Hand h) 
	{
		boolean isRoyalFlush = false;
		//System.out.println("Before Sorting a Royal Flush:" + h);
		sortByRank(h);
		//System.out.println("After Sorting a Royal Flush:" + h);
		if (isStraight(h) && isFlush(h) && h.getCard(4).getRank().getName().equals("Ace"))
		{
			isRoyalFlush = true;
		}
		return isRoyalFlush;
	}
	
	public static boolean isPoker(Hand h)
	{
		boolean isPoker = false;
		if (h.getNumberOfCards() != 5)
		{
			System.err.println("The hand does not have 5 cards");
			return false;
		}
	
		
		sortByRank(h);

		if (h.getCard(0).getRank().getValue() == h.getCard(1).getRank().getValue() && h.getCard(1).getRank().getValue() == h.getCard(2).getRank().getValue() && h.getCard(2).getRank().getValue() == (h.getCard(3).getRank().getValue()))
		{
			isPoker = true;
		}
		
		if (h.getCard(1).getRank().getValue() == h.getCard(2).getRank().getValue() && h.getCard(2).getRank().getValue() == h.getCard(3).getRank().getValue() && h.getCard(3).getRank().getValue() == h.getCard(4).getRank().getValue())
		{
			isPoker = true;
		}
		return isPoker;
	}
	
	public static boolean isFullHouse(Hand h) 
	{
		boolean isFullHouse = false;
		
		if (h.getNumberOfCards() != 5)
		{
			System.err.println("The hand does not have 5 cards");
			return false;
		}
		
		sortByRank(h);
		
		if (h.getCard(0).getRank().getValue() == h.getCard(1).getRank().getValue() && h.getCard(1).getRank().getValue() == h.getCard(2).getRank().getValue() && h.getCard(3).getRank().getValue() == h.getCard(4).getRank().getValue())
		{
			isFullHouse = true;
		}
		
		if (h.getCard(2).getRank().getValue() == h.getCard(3).getRank().getValue() && h.getCard(3).getRank().getValue() == h.getCard(4).getRank().getValue() && h.getCard(0).getRank().getValue() == h.getCard(1).getRank().getValue())
		{
			isFullHouse = true;
		}
		return isFullHouse;
	}
	
	public static boolean isThreeOfAKind(Hand h) 
	{
		boolean isThreeOfAKind = false;
		
		if (h.getNumberOfCards() != 5)
		{
			System.err.println("The hand does not have 5 cards");
			return false;
		}
		
		if (isPoker(h) || isFullHouse(h)) {
			return false;
		}
		
		sortByRank(h);
		//  x x x a b
		if (h.getCard(0).getRank() == h.getCard(1).getRank() && h.getCard(1).getRank() == h.getCard(2).getRank() && h.getCard(0).getRank() != h.getCard(3).getRank() && h.getCard(0).getRank() != h.getCard(4).getRank() && h.getCard(3).getRank() != h.getCard(4).getRank())
		{
			isThreeOfAKind = true;
		}
		// a x x x b
		if (h.getCard(2).getRank() == h.getCard(1).getRank() && h.getCard(3).getRank() == h.getCard(2).getRank() && h.getCard(0).getRank() != h.getCard(4).getRank() && h.getCard(1).getRank() != h.getCard(0).getRank() && h.getCard(1).getRank() != h.getCard(4).getRank())
		{
			isThreeOfAKind = true;
		}
		
		if (h.getCard(2).getRank() == h.getCard(3).getRank() && h.getCard(3).getRank() == h.getCard(4).getRank() && h.getCard(0).getRank() != h.getCard(2).getRank() && h.getCard(1).getRank() != h.getCard(2).getRank() && h.getCard(0).getRank() != h.getCard(1).getRank())
		{
			isThreeOfAKind = true;
		} 
		
		return isThreeOfAKind;
	}
	
	public static boolean isTwoPair(Hand h) 
	{
		boolean isTwoPair = false;
		
		if (h.getNumberOfCards() != 5)
		{
			System.err.println("The hand does not have 5 cards");
			return false;
		}
		
		if(isPoker(h) || isFullHouse(h) || isThreeOfAKind(h))
		{
			return isTwoPair;
		}

		sortByRank(h);
		
		if (h.getCard(0).getRank() == h.getCard(1).getRank() && h.getCard(2).getRank() == h.getCard(3).getRank())
		{
			isTwoPair = true;
		}
		
		 if (h.getCard(1).getRank() == h.getCard(2).getRank() && h.getCard(3).getRank() == h.getCard(4).getRank())
		{
			isTwoPair = true;
		}
		
		 if (h.getCard(0).getRank() == h.getCard(1).getRank() && h.getCard(3).getRank() == h.getCard(4).getRank())
		{
			isTwoPair = true;
		}
		return isTwoPair;
		
	}
	
	public static boolean isOnePair(Hand h) 
	{
		boolean isOnePair = false;
		
		if (h.getNumberOfCards() != 5)
		{
			System.err.println("The hand does not have 5 cards");
			return false;
		}
		
		if (isPoker(h) || isFullHouse(h) || isThreeOfAKind(h) || isTwoPair(h))
		{
			return isOnePair;
		}
		
		sortByRank(h);
		
		// a a x y z
		
		if( h.getCard(0).getRank().getValue() == h.getCard(1).getRank().getValue())
		{
			isOnePair = true;
		}
		
		if (h.getCard(1).getRank().getValue() == h.getCard(2).getRank().getValue())
		{
			isOnePair = true;
		}
		
		if (h.getCard(2).getRank().getValue() == h.getCard(3).getRank().getValue())
		{
			isOnePair = true;
		}
		
		if (h.getCard(3).getRank().getValue() == h.getCard(4).getRank().getValue())
		{
			isOnePair = true;
		}
		
		return isOnePair;
	}
	
	
	public static void sortBySuit(Hand h)
	{
		Card.setSuitMajorSort();
		h.sort();
	}
	
	public static void sortByRank(Hand h)
	{
		Card.setRankMajorSort();
		h.sort();
	}
	

	


}
