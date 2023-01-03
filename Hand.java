import java.util.ArrayList;
public class Hand 
{
	private ArrayList<Card> hand = new ArrayList<Card>();  									
	
	public void addCard(Card card) 
	{
		if(card == null)
		{
			try
			{
	            throw new RuntimeException("You can't add a null card ");
				
			}catch(Exception NullPointerException)
			{
				System.out.println(NullPointerException);
			}
		}else
		{
			hand.add(card);
		}
	}
	public int score()
	{
		boolean aceFlag = false;       // hand has no aces yet
		int score = 0;				   // stores the score of cards
		int aceCounter = 0;            // counts how many aces you got
		int P = 0;                     // counts the total points of hand and reset when it's called again
		for(int i = 0; i < hand.size(); i++) 
		{
			int valueHand = hand.get(i).getValue();
			if(valueHand == 10)
			{
				score += 10;
			}else if(valueHand == 1)
			{
				aceFlag = true;
				aceCounter ++;
			}else
			{
				score += valueHand;
			}
		}
		P += score;
		if(aceFlag)             // if you have at least 1 ace, calculates what is best for the hand
		{
			if(aceCounter == 1 && ( (P + 10) < 21) )
			{
				P += 11;
			}else if(aceCounter == 1 && ( (P + 10 ) >= 21) )
			{
				P += aceCounter;
			}else if(aceCounter > 1 && ( ( P + aceCounter - 1) < 11) )
			{
				P += 10 + aceCounter;
			}else if( aceCounter > 1 && ( ( P + aceCounter - 1) >= 11) )
			{
				P += aceCounter;
			}
		}
		return P;
	}
	public boolean canSplit()
	{
		if((hand.size() != 2))   // you can't split if you have more or less than 2 cards
		{
			return false;
		}
		for(int i = 0; i < hand.size() - 1; i++)
		{
			for(int j = i+1; j < hand.size(); j++)
			{
				if(hand.get(0).equals(hand.get(1)))
				{
					return true;
				}
			}
		}
		 return false;
	}
	public Hand [] split()
	{
		if(!canSplit())
		{
			return null;
		}
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();
		
		hand1.addCard(hand.get(0));
		hand2.addCard(hand.get(0));
		
		Hand [] splited = new Hand[2];
		
		splited[0] = hand1;
		splited[1] = hand2;
		
        return  splited ;
	}
	public  boolean isBlackjack()
	{
		if(hand.size() != 2)  // if you have more or less than 2 cards there is no blackjack
		{
			return false;
		}
		
		return ((hand.get(0).isAce() && hand.get(1).getValue() == 10) ||
		        (hand.get(0).getValue() == 10 && hand.get(1).isAce())) ;
	}
	public boolean isBust( )
	{
		if(score() <= 21)
		{
			return false;
		}
		return true;
	}
	
	public String toString()
	{
		String handStr = " ";
		for(int i = 0; i < hand.size(); i++)
		{
			handStr +=  hand.get(i) + " " ;   // you can use getValue method so cards will appear as numbers only
		}
		return handStr;
	}
	
	public static void main(String [] args)   
	{
		Hand hand = new Hand();
		Card card1 = new Card("1");    
		Card card2 = new Card("1");
		
		hand.addCard(card1);
		hand.addCard(card2);
		
		System.out.println(hand);
		System.out.println(hand.score());
		System.out.println(hand.canSplit());
		
		
		Hand [] splitHand = hand.split();
		
		System.out.println(splitHand[0]);
		System.out.println(splitHand[1]);
		
		Card splitedCard1 = new Card("K");            //creating "K" card,adding "K" to first hand,prints following
		splitHand[0].addCard(splitedCard1);
		 
		System.out.println(splitHand[0]);
		System.out.println(splitHand[0].score());
		System.out.println(splitHand[0].isBlackjack());
		
		Card splitedCard2 = new Card("1");      // creating "1" card,adding "1" to first Hand,prints following
		splitHand[0].addCard(splitedCard2);
		
		System.out.println(splitHand[0]);
		System.out.println(splitHand[0].score());
		
		Card splitedCard3 = new Card("10");     	// creating "10" card,adding "10" to first Hand, prints following
		splitHand[0].addCard(splitedCard3);
		
		System.out.println(splitHand[0]);
		System.out.println(splitHand[0].score());
		System.out.println(splitHand[0].isBust());
	}
}