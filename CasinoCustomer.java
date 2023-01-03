import java.util.Scanner;
public class CasinoCustomer 
{
	private String playerName;
	private double playerMoney;   
	
	Scanner input = new Scanner(System.in);   // helps with insertMoney method
	
	public CasinoCustomer(String playerName,double playerMoney)
	{
		this.playerName = playerName;
		this.playerMoney = playerMoney;
	}
	
	public void payBet(double x)
	{
		playerMoney -= x;            // x represents the player's lost bet
		System.out.println("Pay: $ " + x);
	}
	public void collectBet(double y)
	{
		playerMoney += y;           // y represents the player's won bet
		System.out.println("Collect: $ " + y);
	}
	public boolean canCover(double z)
	{
		return playerMoney >=  z;    // z represents the amount of bet
	}
	public boolean isBroke()
	{
		if(playerMoney < 1)
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		return playerName;
	}
	
	public void insertMoney()      // extra class from BlackjackTable
	{
		String answer;
		do
		{
			System.out.print(playerName + ", you can add some extra money, at least > $ 1.\nWould you like to add some extra money ?(yes or no):");
			answer = input.next();
		} while( !(answer.equals("yes") || answer.equals("no") ));
		if(answer.equals("yes"))
		{
			double addMoney = input.nextDouble();
			
			if(addMoney >= 1)
			{
				playerMoney += addMoney;
				System.out.println("\nAlright, " + playerName + ", adds $ " + addMoney + ".\n");
			}else
			{
				System.out.println("\nPlease, dont fool me.\n");
			}
		}else
		{
			System.out.println("\nAs you wish,  no additions.\n");
		}			
	}
	
	public void printState()
	{
		System.out.println(playerName + " has $ " + playerMoney + " left.\n");
	}
}