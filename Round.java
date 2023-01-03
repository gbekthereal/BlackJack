import java.util.ArrayList;
import java.util.Scanner;
public class Round 
{
	private Dealer roundDealer;
	private ArrayList<Player> roundPlayers = new ArrayList<Player>();
	private ArrayList<Player> dealerSettle = new ArrayList<Player>();
	
	Scanner input = new Scanner(System.in);
	
	public Round(River river)
	{
		Dealer createDealer = new Dealer(river);
		roundDealer = createDealer;
	}
	public void addPlayer(CasinoCustomer customer)
	{
		Player roundPlayer = new Player(customer);
		roundPlayers.add(roundPlayer);
	}
		
	public void playRound()
	{
		for(int i = 0; i < roundPlayers.size(); i++)
		{
			roundPlayers.get(i).placeBet();				// players bet
			System.out.println();
		}
		
		for(int i = 0; i < roundPlayers.size(); i++)
		{
			roundDealer.deal(roundPlayers.get(i));  	// each player gets a first card
		}
		
		roundDealer.draw();
		System.out.println("\nDealers first card: " + roundDealer.getDealerHand());    // dealer draw first card visible
		
		for(int i = 0; i < roundPlayers.size(); i++)
		{
			roundDealer.deal(roundPlayers.get(i)); 			// each player gets a second card
		}
		
		roundDealer.draw();	       // dealer draw second card invisible
		
		for(int i = 0; i < roundPlayers.size(); i++)
		{
			System.out.println(roundPlayers.get(i).getPlayerCustomer() + " starts with: " + roundPlayers.get(i).getPlayerHand());   //printing players hand 
		}
		
		for(int i = 0; i < roundPlayers.size(); i++)
		{
			if(roundDealer.getDealerHand().isBlackjack() && !roundPlayers.get(i).getPlayerHand().isBlackjack())   // conditions dealer - player
			{
				System.out.println("\nDealers hand: " + roundDealer.getDealerHand() + "\n~> Dealer got blackjack <~\n");
				roundPlayers.get(i).loses();
				roundPlayers.get(i).getPlayerCustomer().printState();
			}else if(roundPlayers.get(i).getPlayerHand().isBlackjack() && !roundDealer.getDealerHand().isBlackjack())
			{
				System.out.println("\nDealers hand: " + roundDealer.getDealerHand());
				roundPlayers.get(i).winsBlackJack();
				roundPlayers.get(i).getPlayerCustomer().printState();
			}else
			{
				playPlayer(roundPlayers.get(i));
			}
		}
		
		if(dealerSettle.size() > 0)
		{
			roundDealer.play();
			if(roundDealer.getDealerHand().isBust())
			{
				System.out.println("Dealers hand: " + roundDealer.getDealerHand() + "\n~> Dealer got busted with " + roundDealer.getDealerHand().score() + " points\n");
			}else
			{
				System.out.println("Dealers hand: " + roundDealer.getDealerHand() + "\n~> Dealer has " + roundDealer.getDealerHand().score() + " points\n");
			}
			for(int j = 0; j < dealerSettle.size(); j++)
			{
				roundDealer.settle(dealerSettle.get(j));
			}
		}
		for(int i = 0; i < roundPlayers.size(); i++)     // extra for loop, helps in BlackjackTable
		{
			roundPlayers.get(i).getPlayerCustomer().insertMoney();
		}
	}
	private void playNormalHand(Player player)
	{
		String answer;
		char c;
		do {
			do {
				System.out.print(player.getPlayerCustomer() + ", would you like to (H)it or (S)tand: ");
				answer = input.next();
				c = answer.toUpperCase().charAt(0);
			} while ( ! ( c == 'H' || c == 'S' ) );
			if ( c == 'H' ) {
				roundDealer.deal(player);
				System.out.println("\n-->Your hand now is: " + player.getPlayerHand() + "\n");
			}
		}while(c != 'S');
		
		System.out.println("\n! Player " + player.getPlayerCustomer() + " stand with: " + player.getPlayerHand() + "\n");
		
		if(!player.getPlayerHand().isBust())
		{
			dealerSettle.add(player);
		}else 	 //if u are busted,dealer holds 2 cards so the value its < 21
		{
			System.out.println("~> " + player.getPlayerCustomer() + " got busted with " + player.getPlayerHand().score() + " points\n");
			player.loses();
			player.getPlayerCustomer().printState();
		}
	}
	private void playDoubleHand(Player player)
	{
		roundDealer.deal(player);
		
		System.out.println("\n! Player " + player.getPlayerCustomer() + " double with: " + player.getPlayerHand() + "\n");
		
		if(!player.getPlayerHand().isBust())
		{
			dealerSettle.add(player);
		}else     // if u are busted,dealer holds 2 cards so the value its < 21
		{
			System.out.println("~> " + player.getPlayerCustomer() + " got busted with " + player.getPlayerHand().score() + " points\n");
			player.loses();
			player.getPlayerCustomer().printState();
		}
	}
	private void playSplitHand(Player player)
	{
		Hand [] playSplit = player.getPlayerHand().split();
		
		Player player1 = new Player(player.getPlayerCustomer(),playSplit[0],player.getPlayerBet());
		Player player2 = new Player(player.getPlayerCustomer(),playSplit[1],player.getPlayerBet());
		
		System.out.println("~> Playing with first hand <~\n");
		
		playNormalHand(player1);
		
		System.out.println("~> Playing with second hand <~\n");
		
		playNormalHand(player2);
	}
	private void playPlayer(Player player)
	{
		if(player.getPlayerHand().canSplit() && player.wantsToSplit())
		{
			playSplitHand(player);
		}else if(player.wantsToDouble())
		{
			playDoubleHand(player);
		}else
		{
			playNormalHand(player);
		}
	}
	
	public static void main(String [] args)
	{
		River aRiver = new River(6);
		Round aRound = new Round(aRiver);
		CasinoCustomer aCustomer = new CasinoCustomer("Bekakos",100);
		
		aRound.addPlayer(aCustomer);
		
		aRound.playRound();	
	}
}