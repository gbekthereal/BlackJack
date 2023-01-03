import java.util.Scanner;
public class Player 
{
	private CasinoCustomer playerCustomer;
	private Hand playerHand = new Hand();
	private double playerBet;
	
	Scanner answer = new Scanner(System.in);
	
	public Player(CasinoCustomer playerCustomer)
	{
		this.playerCustomer = playerCustomer;
	}
	public Player(CasinoCustomer playerCustomer,Hand playerHand,double playerBet)
	{
		this.playerCustomer = playerCustomer;
		this.playerHand = playerHand;
		this.playerBet = playerBet;
	}
	
	public CasinoCustomer getPlayerCustomer()
	{
		return playerCustomer;
	}
	public Hand getPlayerHand()
	{
		return playerHand;
	}
	public double getPlayerBet()
	{
		return playerBet;
	}
	
	public void wins()
	{
		System.out.print("You win! ");
		playerCustomer.collectBet(playerBet);
	}
	public void winsBlackJack()
	{
		System.out.println("\n~> "+  playerCustomer + ", you win with Blackjack <~\n");
		playerCustomer.collectBet(playerBet * 1.5);
	}
	public void loses()
	{
		System.out.print("Sorry, you lose! ");
		playerCustomer.payBet(playerBet);
	}
	public void placeBet()
	{
		playerCustomer.printState();
		
		while (true) 
		{
			System.out.print(playerCustomer + ", please place your bet:");
			try
			{
				double input = Double.parseDouble(answer.next());
				if(input >= 1)
				{
					if(playerCustomer.canCover(input) && !playerCustomer.isBroke())
					{
						playerBet += input;
						break;       // will only get to here if input is a number > 1
					}else
					{
						System.out.println("$ Not enough money $\n");
					}
				}
				if(input < 1)
				{
					System.out.println("Please don't fool me. Bet again.\n");
				}
				
			}catch (Exception e) 
			{
				System.out.println( e + " -> Bet counts only as a number > 0 <-\n");
			}
		}
	}	
	public void doubleBet()
	{
		if(playerCustomer.canCover(playerBet * 2))
		{
			playerBet *= 2;
		}
	}
	public boolean wantsToDouble()
	{
		String choice;
		if(playerCustomer.canCover(playerBet * 2))
		{
			do
			{
			   System.out.print(playerCustomer + ", would you like to double ? (yes or no): ");
			   choice = answer.next();
			} while( !(choice.equals("yes") || choice.equals("no") ));
			if(choice.equals("yes") && playerCustomer.canCover(playerBet) && !playerCustomer.isBroke())
			{
				doubleBet();
				return true;
			}
		}	
		return false;
	}
	public boolean wantsToSplit()
	{
		String choice;
		if(playerCustomer.canCover(playerBet * 2))
		{
			do
			{
			   System.out.print(playerCustomer + ", would you like to split ? (yes or no): ");
			   choice = answer.next();
			} while( !(choice.equals("yes") || choice.equals("no") ));
			if(playerHand.canSplit() && choice.equals("yes"))
			{
				return true;
			}
		}
		return false;
	}
	
	public String toString()
	{
		return playerCustomer + "" + playerHand;
	}
	
	public static void main(String [] args)
	{
		CasinoCustomer customer = new CasinoCustomer("Bekakos",50);
		Player player = new Player(customer);

		player.placeBet();
		
		player.wantsToSplit();
		player.getPlayerCustomer().printState();
		
		player.wantsToDouble();
		player.getPlayerCustomer().printState();
		
		player.wins();
		player.getPlayerCustomer().printState();
		
		player.winsBlackJack();
		player.getPlayerCustomer().printState();
		
		player.loses();
		player.getPlayerCustomer().printState();
	}
}