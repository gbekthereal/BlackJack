public class Card
 {
	private String figure;
	private int value ; 
	
	public Card(String figure)
	{
		this.figure = figure;
	}
	
	public int getValue()
	{                      							
		switch(figure)
		{	
			case "J":									
				value = 10;	
				break;
			case "K":
				value = 10;
				break;
			case "Q":
				value = 10;
				break;
			case "1":
				value = Integer.parseInt(figure);
				break;
			case "2":
				value = Integer.parseInt(figure);
				break;
			case "3":
				value = Integer.parseInt(figure);
				break;
			case "4":
				value = Integer.parseInt(figure);
				break;
			case "5":
				value = Integer.parseInt(figure);
				break;
			case "6":
				value = Integer.parseInt(figure);
				break;
			case "7":
				value = Integer.parseInt(figure);
				break;
			case "8":
				value = Integer.parseInt(figure);
				break;
			case "9":
				value = Integer.parseInt(figure);
				break;
			case "10":
				value = Integer.parseInt(figure);
				break;
			default:
				try
				{
					System.out.println("~~> Check again the playable cards <~~");
	                throw new RuntimeException("Blackjack playable cards are 1 - 10 and J,K,Q");
				}catch(Exception e)
				{
					System.out.println(e);
				}
		}
		return value;
	}	
	public boolean isAce()
	{
		if(figure != "1")
		{
			return false;
		}
		return true;
	}
	public boolean equals(Card other)
	{
		return this.figure.equals(other.figure) && this.getValue() == other.getValue();
	}
	
	public String toString()
	{
		return this.figure;   //  + " " + getValue(),to get the invalid card
	}
	
}