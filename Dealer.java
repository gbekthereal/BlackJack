public class Dealer 
{
	private River dealerRiver;
	private Hand dealerHand = new Hand();
	
	public Dealer(River dealerRiver)
	{
		this.dealerRiver = dealerRiver;
	}
	
	public Hand getDealerHand()
	{
		return dealerHand;
	}
	
	public void draw()
	{
		dealerHand.addCard(dealerRiver.nextCard());
	}
	public void deal(Player player)
	{
		player.getPlayerHand().addCard(dealerRiver.nextCard());
	}
	public void play()
	{
		while(dealerHand.score() <= 17)
		{
			dealerHand.addCard(dealerRiver.nextCard());
		}
	}
	
	public void settle(Player player)
	{
		if(dealerHand.isBlackjack() && !player.getPlayerHand().isBlackjack())
		{
			System.out.println("\n~> Dealer got blackjack <~\n");
			System.out.println("~> " + player.getPlayerCustomer() + " has " + player.getPlayerHand().score() + " points");
			player.loses();
		}else if(!dealerHand.isBlackjack() && player.getPlayerHand().isBlackjack()) 
		{
			System.out.println("~> " + player.getPlayerCustomer() + " has " + player.getPlayerHand().score() + " points");
			player.winsBlackJack();
		}else if(dealerHand.isBlackjack() && player.getPlayerHand().isBlackjack())
		{
			System.out.println("~> " + player.getPlayerCustomer() + " has " + player.getPlayerHand().score() + " points");
			System.out.println("Its a tie ! Both dealer and " + player.getPlayerCustomer() + " got  blackjack");
		}else if(dealerHand.isBust() && !player.getPlayerHand().isBust())   
		{
			System.out.println("~> " + player.getPlayerCustomer() + " has " + player.getPlayerHand().score() + " points");
			player.wins();
		}else if(!dealerHand.isBust() && player.getPlayerHand().isBust())
		{
			System.out.println("~> " + player.getPlayerCustomer() + " got busted with " + player.getPlayerHand().score() + " points");
			player.loses();
		}else if((!dealerHand.isBust() && !player.getPlayerHand().isBust()) && (dealerHand.score() > player.getPlayerHand().score()))
		{
			System.out.println("~> " + player.getPlayerCustomer() + " has " + player.getPlayerHand().score() + " points");
			player.loses();
		}else if((!dealerHand.isBust() && !player.getPlayerHand().isBust() )&& dealerHand.score() < player.getPlayerHand().score())
		{
			System.out.println("~> " + player.getPlayerCustomer() + " has " + player.getPlayerHand().score() + " points");
			player.wins();
		}else
		{
			System.out.println("~> " + player.getPlayerCustomer() + " has " + player.getPlayerHand().score() + " points");
			System.out.println("Wow. None wins, its a tie !\n");
		}
		player.getPlayerCustomer().printState();
	}
	
	public String toString()
	{
		return "Dealer:" + dealerHand;  
	}
	
	public static void main(String [] args)
	{
		River river = new River(1);
		Dealer dealer = new Dealer(river);
		
		dealer.play();
		System.out.println(dealer);
		
		CasinoCustomer customer = new CasinoCustomer("Bekakos",80);
		Player player = new Player(customer);
		
		dealer.deal(player);
		dealer.deal(player);
		
		System.out.println(player);
		
		dealer.settle(player);
	}
}