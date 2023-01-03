import java.util.Random;
public class River
{
	private Card [] deck;                                                                      
	private static String [] cards = {"1","2","3","4","5","6","7","8","9","10","J","K","Q"}; 	//static array with all figures in a deck
	private int numberOfCards = cards.length * 4;  											 //  13 figures(cards.length) * 4 to make the 52 card deck
                          
	private int cardsLeft = 52;                  // cards not used yet , it's 52 because cards  haven't dealt yet
	
	public River(int numberOfDecks) 
	{
		
		deck = new Card[ numberOfDecks * numberOfCards]; // initialize  deck after the number of decks passed in
		for(int i = 0; i < deck.length; i++)            
		{
			String element = cards[i % 13];            //element gets mod i value from String cards array, for example 12mod13 = 12 -> Q and resets
			deck[i] = new Card(element); 
		}		 
	}
	
	public Card nextCard()
	{
		Random rand = new Random();
		if(cardsLeft == 0) 
		{
			return null;
		}                    
		int x = rand.nextInt(cardsLeft);          // creates a random number between 0 and cardsLeft-1 	
		for(int i = 0; i < deck.length; i++)           
		{
			Card temp = deck[x]; 
			deck[x] = deck[cardsLeft-1];          // swap random x with cardsLeft-1
			deck[cardsLeft-1] = temp;
		}
		cardsLeft-- ;              
		return deck[x];
	}
	public boolean shouldRestart()
	{
		if(cardsLeft < (numberOfCards/4) )    
		{
			return true;
		}
		return false;
	}
	public void restart()
	{
		cardsLeft = numberOfCards ;        
		for(int i = 0; i < cardsLeft; i++)
		{
			Random r = new Random();
			int x = r.nextInt(cardsLeft);
			Card temp = deck[x];
			deck[x] = deck[cardsLeft-1];
			deck[cardsLeft-1] = temp;
		}
	}
	
	public String toString()  // i use this extra toString method,to avoid River@5ca881b5 error when i print only object river
	{
		String cardList = " ";
		for(Card c : deck)
		{
			cardList += "\n" + c.toString();
		}
		return cardList;
	}
	
	public static void main(String [] args) 
	{
		River river = new River(1);
		
		while(river.shouldRestart() != true)
		{
			System.out.println(river.nextCard());
		}
		
		System.out.println("\n");
		
		river.restart();
		System.out.println("Cards are shuffled !\n");
		
		while(river.nextCard() != null)
		{
			System.out.println(river.nextCard());
		}
	}
}