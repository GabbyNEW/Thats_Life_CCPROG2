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
	
	public String doAction (Player[] players, int turn, int nRand) {
		boolean isCase2 = false;
		int matchedPlayerTurn = -1;
		
		double payment = 0;
		String message = null;
		
		switch (mainID)
		{
			case 0 : // LAWSUIT
			{
				if (players[turn].getCareer().contentEquals("LAWYER")) { // Case 1 : Current player matches blue card
					players[turn].addMoneyBalance(15000.00);
					message = "Same career! Paid yourself.";
				}
				else {
					// Check if other players match the blue card
					for (Player e : players)
						if (e.getCareer().contentEquals("LAWYER")) {
							isCase2 = true;
							matchedPlayerTurn = e.getPlayerNumber();
						}
					
					payment = nRand * 10000 + 50000;
					
					
					if (isCase2) { // Case 2 : There is another player matching the blue card
						players[matchedPlayerTurn].addMoneyBalance(payment);
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Player" + (matchedPlayerTurn + 1) + ".";
					}
					
					else { // Case 3 : No matching occurred. Pay to Bank.
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Bank [" + typeOfCard;
					}
				}
			} break;
			
			case 1 : // SALARY TAX DUE
			{
				if (players[turn].getCareer().contentEquals("ACCOUNTANT")) { // Case 1 : Current player matches blue card
					players[turn].addMoneyBalance(15000.00);
					message = "Same career! Paid yourself.";
				}
				else {
					// Check if other players match the blue card
					for (Player e : players)
						if (e.getCareer().contentEquals("ACCOUNTANT")) {
							isCase2 = true;
							matchedPlayerTurn = e.getPlayerNumber();
						}
					
					payment = players[turn].getSalary();
					
					
					if (isCase2) { // Case 2 : There is another player matching the blue card
						players[matchedPlayerTurn].addMoneyBalance(payment);
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Player" + (matchedPlayerTurn + 1) + ".";
					}
					
					else { // Case 3 : No matching occurred. Pay to Bank.
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Bank [" + typeOfCard;
					}
				}
			} break;
			
			case 2 : // TIP THE SERVER
			{	
				if (players[turn].getCareer().contentEquals("SERVER")) { // Case 1 : Current player matches blue card
					players[turn].addMoneyBalance(15000.00);
					message = "Same career! Paid yourself.";
				}
				else {
					// Check if other players match the blue card
					for (Player e : players)
						if (e.getCareer().contentEquals("SERVER")) {
							isCase2 = true;
							matchedPlayerTurn = e.getPlayerNumber();
						}
					
					payment = nRand * 1000.00;
					
					
					if (isCase2) { // Case 2 : There is another player matching the blue card
						players[matchedPlayerTurn].addMoneyBalance(payment);
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Player" + (matchedPlayerTurn + 1) + ".";
					}
					
					else { // Case 3 : No matching occurred. Pay to Bank.
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Bank [" + typeOfCard;
					}
				}
			} break;
			
			case 3 :  // SKI ACCIDENT
			{	
				if (players[turn].getCareer().contentEquals("DOCTOR")) { // Case 1 : Current player matches blue card
					players[turn].addMoneyBalance(15000.00);
					message = "Same career! Paid yourself.";
				}
				else {
					// Check if other players match the blue card
					for (Player e : players)
						if (e.getCareer().contentEquals("DOCTOR")) {
							isCase2 = true;
							matchedPlayerTurn = e.getPlayerNumber();
						}
					
					payment = 10000.00;
					
					
					if (isCase2) { // Case 2 : There is another player matching the blue card
						players[matchedPlayerTurn].addMoneyBalance(payment);
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Player" + (matchedPlayerTurn + 1) + ".";
					}
					
					else { // Case 3 : No matching occurred. Pay to Bank.
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Bank [" + typeOfCard;
					}
				}

			} break;
			case 4 : // COMPUTER REPAIR
			{	// pay $5000 if random number is even, $10000 otherwise
				if (players[turn].getCareer().contentEquals("COMPUTER CONSULTANT")) { // Case 1 : Current player matches blue card
					players[turn].addMoneyBalance(15000.00);
					message = "Same career! Paid yourself.";
				}
				else {
					// Check if other players match the blue card
					for (Player e : players)
						if (e.getCareer().contentEquals("COMPUTER CONSULTANT")) {
							isCase2 = true;
							matchedPlayerTurn = e.getPlayerNumber();
						}
					
					payment = (nRand % 2 == 0) ? 5000.00 : 10000.00;
					
					
					if (isCase2) { // Case 2 : There is another player matching the blue card
						players[matchedPlayerTurn].addMoneyBalance(payment);
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Player" + (matchedPlayerTurn + 1) + ".";
					}
					
					else { // Case 3 : No matching occurred. Pay to Bank.
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Bank [" + typeOfCard;
					}
				}
			}
			case 5 : // WORLD CUP
			{	
				if (players[turn].getCareer().contentEquals("ATHLETE")) { // Case 1 : Current player matches blue card
					players[turn].addMoneyBalance(15000.00);
					message = "Same career! Paid yourself.";
				}
				else {
					// Check if other players match the blue card
					for (Player e : players)
						if (e.getCareer().contentEquals("ATHLETE")) {
							isCase2 = true;
							matchedPlayerTurn = e.getPlayerNumber();
						}
					
					payment = players.length * 5000.00;
					
					
					if (isCase2) { // Case 2 : There is another player matching the blue card
						players[matchedPlayerTurn].addMoneyBalance(payment);
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Player" + (matchedPlayerTurn + 1) + ".";
					}
					
					else { // Case 3 : No matching occurred. Pay to Bank.
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Bank [" + typeOfCard;
					}
				}
			}
			case 6 : // F1 RACE
			{	
				if (players[turn].getCareer().contentEquals("RACECAR DRIVER")) { // Case 1 : Current player matches blue card
					players[turn].addMoneyBalance(15000.00);
					message = "Same career! Paid yourself.";
				}
				else {
					// Check if other players match the blue card
					for (Player e : players)
						if (e.getCareer().contentEquals("RACECAR DRIVER")) {
							isCase2 = true;
							matchedPlayerTurn = e.getPlayerNumber();
						}
					
					payment = players[turn].getSalary() * 0.10;
					
					
					if (isCase2) { // Case 2 : There is another player matching the blue card
						players[matchedPlayerTurn].addMoneyBalance(payment);
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Player" + (matchedPlayerTurn + 1) + ".";
					}
					
					else { // Case 3 : No matching occurred. Pay to Bank.
						players[turn].reduceMoneyBalance(payment);
						message = "Paid to Bank [" + typeOfCard;
					}
				}
			}
		}
		
		return message;
	}
		

	
	
}
