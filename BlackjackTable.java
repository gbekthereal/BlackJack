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
			while(!tableArray[i].isBroke())
			{
				Round tableRound = new Round(tableRiver);
				
				for(int j = 0; j < tableArray.length; j ++)
				{
					if(!tableArray[j].isBroke())
					{
						tableRound.addPlayer(tableArray[j]);
					}
				}
				
				System.out.println("\n---- NEW ROUND! ---- \n");
				
				if(tableRiver.shouldRestart())
				{
					tableRiver.restart();
				}
				
				tableRound.playRound();
				
				if(tableArray[i].isBroke())
				{
					break;
				}
			}
			System.out.println(tableArray[i] +", leaves the table.\n");
		}
	}
}