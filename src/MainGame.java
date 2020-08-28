import java.util.*;
/** This program can take in specific arguments for testing. <P>
 * ARGUMENT LIST:<P> 
 * -mt (manual turn) Player number turn is user-specified per round.<P>
 * -ac (limited action cards) User specifies number of Action Cards before start of game. Note that after running out of decks and reshuffling, number of cards is back to 50.
 * -cm (custom money) user specifies a custom starting money for each players (default $200000) 
 * -cl (custom loan) user specifies a custom starting loan balance to every player (does not affect interest)
 * @author gabby
 */
public class MainGame {
	private static boolean ARG_MANUALTURN;
	private static boolean ARG_LIMITEDACTIONCARD;
	private static boolean ARG_CUSTOMSTARTINGMONEY;
	private static boolean ARG_CUSTOMSTARTINGLOAN;
	
	private int numberOfPlayersInGame;
	private ArrayList<ActionCard> actionCards;
	private Player[] players;
	private int turn;

	private static Scanner input = new Scanner(System.in);
	
	/** This is what executes at the start of the program.
	 * First, a deck of Action Cards is generated.
	 * Next, the program asks the user how many players are there (2-3), then uses that to instantiate player array.
	 * Each player's career will be assigned as Athlete (for now). <P>
	 * 
	 * After this, the first round starts at phase1Game() method.
	 */
	public void preliminaryStart() {
		actionCards = new ArrayList<ActionCard>();
		turn = 0; // Before game starts, set player turn to 0 (e.g Player 1's turn, value is 0; Player 2's turn, value is 1 etc.)
		
		generateDeckOfActionCards(actionCards, ARG_LIMITEDACTIONCARD); // Generate deck of action cards and shuffle them
		
		do {
			// Ask user how many players in the game (2-3)
			System.out.print("Enter number of players: ");
			numberOfPlayersInGame = Integer.parseInt(input.nextLine());
			players = new Player[numberOfPlayersInGame];
			
			// Check valid input
			if (numberOfPlayersInGame < 1 || numberOfPlayersInGame > 3)
			 System.out.println("Invalid input!");
			else if(numberOfPlayersInGame == 1)
				System.out.println("The game is NOT designed for single player.");
			
		} while (numberOfPlayersInGame < 2 || numberOfPlayersInGame > 3);
		
		// Instantiate Players based on how much players there are
		if (ARG_CUSTOMSTARTINGMONEY) { // -cm (Custom money) arguemnt
			System.out.print("Enter custom starting money:");
			double startingMoney = Double.parseDouble(input.nextLine());
			for (int i = 0; i < numberOfPlayersInGame; i++)
				players[i] = new Player((i+1), startingMoney);
		}
		else // default
			for (int i = 0; i < numberOfPlayersInGame; i++)
				players[i] = new Player((i+1));
		
		// -cl set custom loan if argument is included
		if (ARG_CUSTOMSTARTINGLOAN) {
			System.out.println("Set custom starting LOAN for each player: ");
			double amount = Double.parseDouble(input.nextLine());
			for (Player player : players)
				player.setLoan(amount);
		}
		
		// Predefine each player's career to Athlete (for now)
		System.out.println("Notice: Career assigned to each player is Athlete for now.");
		for (int i = 0; i < numberOfPlayersInGame; i++)
			players[i].setCareer("Athlete");
	}
	/**	This method is responsible for generating 50 Action Cards.
	 * 
	 * @param actionCards ArrayList of ActionCard to create decks to
	 * @param ARG_LIMITEDACTIONCARD Argument modifier for setting which index to start drawing a card. Resets at next reshuffle.
	 */
	public void generateDeckOfActionCards(ArrayList<ActionCard> actionCards, boolean ARG_LIMITEDACTIONCARD) {
		// Begin instantiation
		int i;
		for (i = 0; i < 20; i++) {	// 20/50
			actionCards.add(new ActionCard(0)); // create cards with ID 0 (collect from bank)
			actionCards.get(i).generateSubID(); 
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		for (i = 20; i < 40; i++) {	//20/50
			actionCards.add(new ActionCard(1)); // create cards with ID 1 (pay the bank)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		for (i = 40; i < 45; i++) {	// 5/50
			actionCards.add(new ActionCard(2)); // ID 2 (pay the player)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		for (i = 45; i < 50; i++) {	//5/50
			actionCards.add(new ActionCard(3)); // ID 3 (collect from player)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		Collections.shuffle(actionCards); // Shuffle after generating decks
		if (ARG_LIMITEDACTIONCARD) { // -ac (limited action cards) see documentation
			displayActionCards(actionCards);
			System.out.println("Enter number of Action Cards to start off: ");
			int num = Integer.parseInt(input.nextLine());
			ActionCard.setHead(num - 1);
			System.out.println("NOTE: 50 Action cards are still generated, but head starts at specified input.");
		}
		
	}
	
	public static void displayActionCards(ArrayList<ActionCard> deck) {
		System.out.println("Action Cards generated (uses stack implementation):");
		for (ActionCard card : deck)
			System.out.print(card.getTypeOfCard() + ", ");
		System.out.println("\n");
	}
	
	/**
	 *	First, show the current player's status.
	 * 	Then draw a card. (Uses stack implementation, head starts at the last card (index 49)
	 * 	Show card details.
	 * 	Execute the action of the card.<P>
	 * 	Note that this method may become deprecated at phase 2 development.
	 * @param turn current player turn e.g Player 1's turn, the value is 0; Player 2's turn, the value is 1
	 */
	private void phase1Game(int turn) {
		do {
			String answer; // This is just for user confirmation if he wants to continue/exit the game. 
			
			System.out.println("PLAYER " + (this.turn+1) 
					+ "'s turn! | MONEY: " + this.players[this.turn].getMoneyBalance() + 
					" | LOAN: " + this.players[this.turn].getMoneyLoan() + "| INTEREST: " + this.players[this.turn].getMoneyInterest());
			
			ActionCard drawn = this.drawDeck(); // Draw a card
			
			System.out.println("Action card drawn: " + drawn.toString()); // Display drawn card
			
			drawn.doAction(this.players, this.turn, numberOfPlayersInGame); // Execute card action
			
			System.out.println("Continue playing? Y/N: "); // Prompt user if to continue program
			answer = MainGame.input.nextLine();
			if (answer.equalsIgnoreCase("N"))
				break;
			
			if (ARG_MANUALTURN) { // Specify next player turn for -mt
				System.out.print("Choose player turn: ");
				this.turn = Integer.parseInt(MainGame.input.nextLine()) - 1;
			}
			
			else // default increment player turn
				this.turn = (this.turn == (this.numberOfPlayersInGame - 1)) ? 0 : this.turn + 1;
			
			System.out.println("--------");
		} while (true);
	}
	/**	This method is called whenever a player draws a card (action card) from a deck. 
	 * 	It calls the pop method of that respective deck to retrieve the top most card 
	 * 	and shifts the head of the deck (uses stack implementation).
	 * @return action card drawn from top of the deck,
	 */
	public ActionCard drawDeck() {
		return ActionCard.pop(this.actionCards);
	}
	
	public static void main(String[] args) {
		// Command-line arguments
		MainGame.ARG_MANUALTURN = Arrays.asList(args).contains("-mt");
		MainGame.ARG_LIMITEDACTIONCARD = Arrays.asList(args).contains("-ac");
		MainGame.ARG_CUSTOMSTARTINGMONEY = Arrays.asList(args).contains("-cm");
		MainGame.ARG_CUSTOMSTARTINGLOAN = Arrays.asList(args).contains("-cl");
		
		System.out.println("Welcome! This is an initial build of the \"That's Life!\" project. "
				+ "THE PROGRAM SUPPORTS TERMINAL-ARGUMENTS for testing. Please refer to the provided Javadoc for details.\n");

		MainGame game = new MainGame(); // Create game instance.
		
		game.preliminaryStart();
		MainGame.displayActionCards(game.actionCards);
		
		game.phase1Game(game.turn);
		
	}

}

/* Changes:
 * Added -cl (custom loan) argument
 * Fixed bug where a player can only do one loan request when multiple requests are needed.
 * Fixed toDoAction String typo for some action cards (at assignDescriptions() method)
 * Added exit behavior when user enters invalid head value for stacks (-ac)
*/