//Bekakos Ioannis cs32495 2495
import java.util.Scanner;
public class Blackjack 
{
	
	private static void rules()
	{
		{
			System.out.println("  BLACKJACK RULES:\n");
			System.out.println("1) Each player is dealt 2 cards. The dealer is dealt 2 cards, the first is visible and the second invisible.\n");
			System.out.println("2) Cards are equal to their value with face cards (J,Q,K) being 10 and an Ace being 1 or 11.\n");
			System.out.println("3) The players cards are added up for their total.\n");
			System.out.println("4) Players Hit to gain another card from the deck. Players Stand to keep their current card total.\n");
			System.out.println("5) Dealer Hit until 17 or above total .\n");
			System.out.println("6) The goal is to have a higher card total than the dealer without going over 21.\n");
			System.out.println("7) If the player total equals the dealer total, its a tie.\n");
			System.out.println("8) Players have to bet first and then choose if they want to play with their original bet or double the bet or split.\n");
			System.out.println("9) When you Double your bet then you are dealt only one card. If your total is less than 21 and > dealer you win x2 your bet.\n");
			System.out.println("10) When you Split , you must have two of a kind cards, you can split them in 2 different hands, you can win or lose or tie in both hands.\n");
			System.out.println("11) Players win their bet if they beat the dealer.\n");
			System.out.println("12) Players win 1.5x their bet if they get Blackjack which is 21.");
		}
	}
	
	public static void main(String [] args)
	{
		int number;
		String answer;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("!~~> Welcome to Blackjack table <~~!");
		
		do
		{
			System.out.print("\nWould you like to read the rules before we start? (yes or no): ");
			answer = input.next();
		} while( !(answer.equals("yes") || answer.equals("no") ));
		if(answer.equals("yes"))
		{
			System.out.println("\nWell, i got you !\n");
			rules();
		}else if(answer.equals("no"))
		{
			System.out.println("\nOkay, as you wish !");
		}
		
		System.out.print("\nHow many players want to test their luck: ");
		
		try
		{
			number = input.nextInt();
			BlackjackTable jackTable = new BlackjackTable(number);
			jackTable.play();
		}catch(Exception e)
		{
			System.out.println("Invalid input: " + e);
		}	
	}
}