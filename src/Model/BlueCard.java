package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BlueCard extends Cards {
	
	public BlueCard (int randomNumber)
	{
		mainID = randomNumber;
	}
	
	public void assignDescription (int mainID)
	{
		switch (mainID)
		{
			case 0 : 
			{
				typeOfCard = "LAWSUIT";
				description = "";
				toDoAction = "Pay the amount indicated.";
				break;
			}
			case 1 : 
			{
				typeOfCard = "SALARY TAX DUE";
				description = "";
				toDoAction = "Pay the tax due for current salary.";
				break;
			}
			case 2 : 
			{
				typeOfCard = "TIP THE SERVER";
				description = "";
				toDoAction = "Press for random number, then pay $1000 * generated number.";
				break;
			}
			case 3 : 
			{
				typeOfCard = "SKI ACCIDENT";
				description = "";
				toDoAction = "Pay $10000.";
				break;
			}
			case 4 : 
			{
				typeOfCard = "COMPUTER REPAIR";
				description = "";
				toDoAction = "Press for random number, then pay $5000 if the number is even and $10000 otherwise.";
				break;
			}
			case 5 : 
			{
				typeOfCard = "WORLD CUP";
				description = "";
				toDoAction = "Pay $5000 * number of players.";
				break;
			}
			case 6 : 
			{
				typeOfCard = "F1 RACE";
				description = "";
				toDoAction = "Pay 10% of current salary.";
				break;
			}
		}
	}
	
	public void doAction (int mainID, ArrayList<Player> players, int turn)
	{
		Random rand = new Random ();
		// TODO this shit
		switch (mainID)
		{
			case 0 : 
			{
				
			}
			case 1 : 
			{
				
			}
			case 2 : 
			{	
				// pay $1000 x random number (1 - 50)
				int nVal;
				nVal = rand.nextInt(50);
				nVal += 1;	// to get value from 1 - 50 instead of 0 - 49;
				players.get(turn).reduceMoneyBalance(nVal * 1000.00);
				break;
				
			}
			case 3 : 
			{	
				// pay $10000
				players.get(turn).reduceMoneyBalance(10000.00);
				break;
			}
			case 4 : 
			{	// pay $5000 if random number is even, $10000 otherwise
				int nNum;
				nNum = rand.nextInt();
				if(nNum % 2 == 0)
					players.get(turn).reduceMoneyBalance(5000.00);
				else
					players.get(turn).reduceMoneyBalance(10000.00);
			}
			case 5 : 
			{	
				// pay $5000 x number of players
				players.get(turn).reduceMoneyBalance(players.size() * 5000.00);
				break;
			}
			case 6 : 
			{	
				// pay 10% of current salary
				players.get(turn).reduceMoneyBalance(players.get(turn).getSalary() * 0.10);
				break;
			}
		
		}
		
	}
	
	
}
