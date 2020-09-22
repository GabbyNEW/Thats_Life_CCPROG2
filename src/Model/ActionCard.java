package Model;
import java.util.*;

/** JAVADOC
 * This class is for action cards. 
 * It includes the mainID which is used to identify a card (see below).
 * It also includes the type of card and description details for each card.<P>
 * All action cards are stored using stack implementation. The head always starts at tne very last index 49 (to represent the top card in real life).
 * Whenever an Action Card is drawn, return the top most card, then decrement head.
 * If the deck of Action cards run out, shuffle, then reset head back to the last index 49. Below are the mainID details<P>
 * 0 - Collect from the Bank<P>
 * 1 - Pay the Bank<P>
 * 2 - Pay the Player<P>
 * 3 - Collect From Player<P>
 * Each Action Card also has a subID that is used to further identify an action card (see below). <P>
 * If ID = 0 (Collect from bank) -> subID 0 - Tax refund, 1 - Sell an Item, 2 - Bonus Payday, 3 - Setup school <P>
 * If ID = 1 (Pay the Bank) -> subID 0 - Buy an Item, 1 - Visit a Place, 2 - Hiking, 3 - Watch a Show, 4 - Win a Competiton, 5 - Traffic Violation<P>
 * If ID = 2 (Pay the Player) -> subID 0 - Lawsuit (choose a Player), 1 - Christmas Bonus (pay all players) <P>
 * If ID = 3 (Collect from Player)-> subID 0 - File a Lawsuit (choose a player), 1 - It's Your Birthday (collect from all players) <P>
 * @author gabby
 */

public class ActionCard extends Cards {
	
	private Scanner input = new Scanner(System.in);
	
	/**
	 * Whenever an Action Card is created, always provide a random number from 0-3.
	 * Assign the random number to an Action Card's mainID.
	 * mainID will be used to identify what kind of Action Card it is
	 * There are a total of 4 different kinds of Action Cards that have their own instructions.<P>
	 * 0 - Collect from the Bank<P>
	 * 1 - Pay the Bank<P>
	 * 2 - Pay the Player<P>
	 * 3 - Collect From Player<P>
	 * @param randomNumber the mainID to assign to an Action Card
	 */
	ActionCard(int randomNumber) {
		mainID = randomNumber;
	}
	
	/**
	 * This method automatically assigns the type of card and its description for each Action Card based on its mainID 
	 * @param mainID mainID of an Action Card
	 */
	public void assignDescriptions(int mainID) {
		if (mainID == 0) { // Collect from Bank
			switch (subID) {
				case 0 : {
					typeOfCard = "TAX REFUND";
					description = "Get your money back from the taxpayers!";
					break;
				}
				case 1 : {
					typeOfCard = "SELL AN ITEM";
					description = "Get rid of your item and get your money back!";
					break;
				}
				case 2 : { 
					typeOfCard = "BONUS PAYDAY";
					description = "Horray for bonus pay!";
					break;
				}
				case 3 : {
					typeOfCard = "SETUP SCHOOL";
					description = "Give good, get good money!";
					break;
				}
				case 4 : {
					typeOfCard = "WRITE A BOOK";
					description = "Got something to share to the world? Wanna earn good money? Why not BOTH?!";
					break;
				}
			}
			toDoAction = "Collect $30000 From The Bank";
		}
		else if (mainID == 1) { // Pay the Bank
				switch (subID) {
					case 0 : {
						typeOfCard = "BUY AN ITEM!";
						description = "Get ahold of the cool stuff! Got the money for it?";
						break;
					}
					case 1 : {
						typeOfCard = "VISIT A PLACE";
						description = "Go spend some good time in a good place!";
						break;
					}
					case 2 : {
						typeOfCard = "HIKING";
						description = "Climb to the top of the mountains and assert dominance!";
						break;
					}
					case 3 : {
						typeOfCard = "WATCH A SHOW";
						description = "Binge up on the latest shows!";
						break;
					}
					case 4 : {
						typeOfCard = "WIN A COMPETITION";
						description = "Put your skills to the test!";
						break;
					}
					case 5 : {
						typeOfCard = "TRAFFIC VIOLATION";
						description = "Oopsies! No escaping the law!";
						break;
					}
				}
				toDoAction = "Pay $30000 To The Bank";
		}
		else if (mainID == 2) { // Pay the Player
			switch (subID) {
				case 0 : {
					typeOfCard = "LAWSUIT";
					description = "Settle a case!";
					toDoAction = "Choose A Player, then Pay $30000";
					break;
				}
				case 1 : {
					typeOfCard = "CHRISTMAS BONUS";
					description = "Wish all a happy Merry Christmas!";
					toDoAction = "Pay $30000 To Each Players";
					break;
				}
			}
		}
		else { // Collect From Player
			switch (subID) {
				case 0 : {
					typeOfCard = "FILE A LAWSUIT!";
					description = "Bring them to court!";
					toDoAction = "Choose a player, then collect $30000";
					break;
				}
				case 1 : {
					typeOfCard = "IT'S YOUR BIRTHDAY!";
					description = "It's your day! Go celebrate your birthday with others!";
					toDoAction = "Collect $30000 From Each Players";
					break;
				}
			}
		}
	}
	
	/**
	 * This method is responsible for executing an Action Cards instructions based on its mainID.
	 * @param players the players of the game
	 * @param turn current player turn (e.g Player 1's turn, value is 0; Player 2's turn, value is 1)
	 * @param numberOfPlayersInGame total number of players in game
	 */
	public void doAction(Player[] players, int turn, int numberOfPlayersInGame) {
		switch (mainID) {
			case 0 : { // Collect from bank
				// Add money to player
				players[turn].addMoneyBalance(30000.00);
				System.out.println("$30000 added to Player " + (turn + 1));
				break;
			}
			case 1: { // Pay the bank
				// Reduce money from player
				players[turn].reduceMoneyBalance(30000.00);
				System.out.println("$30000 deducted from Player " + (turn + 1));
				break;
			}
			case 2 : { // Pay the player
				if (subID == 0) { // Choose a player to pay to
						// Ask which player to pay to
						System.out.println("Choose player number to pay event.");
				}
				else { // Pay all players
					for (int i = 0; i <= numberOfPlayersInGame - 1; i++)
						if (i != turn) {
							players[turn].reduceMoneyBalance(30000.00); // Reduce money from current player
							players[i].addMoneyBalance(30000.00); // Add money to all other players
							System.out.println("PAID Player " + (i + 1)+ " $30000.00");
						}
				}
				break;
			}
			case 3 : { // Collect from player
				if (subID == 0) { // Choose a player to collect money
					System.out.println("Choose Player number to collect event.");
				}
				else { // Collect money from all players
					for (int i = 0; i <= numberOfPlayersInGame - 1; i++)
						if (i != turn) {
							players[turn].addMoneyBalance(30000.00); // Add money to current player
							players[i].reduceMoneyBalance(30000.00); // Reduce money from all other players
							System.out.println("COLLECTED from Player " + (i + 1)+ " $30000.00");
						}
				}
				break;
			}
		}
	}
}
 


