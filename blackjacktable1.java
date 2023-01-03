import java.util.Scanner;
public class BlackjackTable {
	private River tableRiver = new River(6);
	private CasinoCustomer [] tableArray;
	private int tableCount;
	
	Scanner input = new Scanner(System.in);
	
	public BlackjackTable(int tableCount)
	{
		if(tableCount < 1)
		{
			System.out.println("No ghosts allowed. I need a number > 0.");
		}
		this.tableCount = tableCount;
		tableArray = new CasinoCustomer[this.tableCount];
		
		for(int i = 0; i < tableArray.length; i++)
		{
			
			tableArray[i] = createCasinoCustomer();
		}
	}
	private CasinoCustomer createCasinoCustomer()
	{
		String customerIn;
		Double amountIn;
		
		System.out.print("-->Enter your name: ");
		customerIn = input.nextLine();
		
		System.out.print("amount of money: ");
		amountIn = input.nextDouble();
		input.nextLine();
		
		CasinoCustomer createCustomer = new CasinoCustomer(customerIn,amountIn);
		
		return createCustomer;	
	}
	public void play()
	{
		String answer;
		for(int i = 0; i < tableArray.length; i ++)
		{
			if(tableArray[i].isBroke())
			{
				System.out.println(tableArray[i] + ", if you want to play you will need some money first.\n");
			}else
			{
				System.out.println("\nWelcome to the table , " + tableArray[i] + ".\n");
			}
		}
		for(int i = 0; i < tableArray.length; i ++)
		{
			if(!tableArray[i].isBroke())
			{
				do
			 	{
			 		System.out.print(tableArray[i] +", let's play a round ?(yes or no): ");
			 		answer = input.next();
			 	}while( !(answer.equals("yes") || answer.equals("no") ));
				
				while(answer.equals("yes"))
				{
					Round tableRound = new Round(tableRiver);
					tableRound.addPlayer(tableArray[i]);
					
					if(tableRiver.shouldRestart())
					{
						tableRiver.restart();
					}
					
					tableRound.playRound();
					
					tableArray[i].insertMoney();
					
					if(tableArray[i].isBroke())
					{
						System.out.println("Sorry, " + tableArray[i] + ", you have no money left for another round.");
						break;
					}
					do
					{
						System.out.print(tableArray[i] +", another round ?(yes or no): ");
				 		answer = input.next();
					}while( !(answer.equals("yes") || answer.equals("no") ));
					
				}
				System.out.print("\n" + tableArray[i] +", leaves the table.\n");
			}
		}
	}
}