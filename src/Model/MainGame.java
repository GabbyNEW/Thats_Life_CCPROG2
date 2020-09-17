package Model;
import java.util.*;
/**
 * 	This is the absolute main class of the program that stores the card decks, the players, and other information crucial data for program run.
 * 	The class also contains methods that facilitate the execution of the game's actions (e.g draw & generate cards, modify player status)
 *  This program takes in terminal arguments for testing.<P>
 *  ARGUMENT LIST:<P> 
 * -mt (manual turn) Player number turn is user-specified per round.<P>
 * -ac (limited action cards) User specifies number of Action Cards before start of game. Note that after running out of decks and reshuffling, number of cards is back to 50.<P>
 * -cm (custom money) user specifies a custom starting money for each players (default $200000) <P>
 * -cl (custom loan) user specifies a custom starting loan balance to every player (does not affect interest)<P>
 */
public class MainGame {
	// Argument variables
	private static boolean ARG_MANUALTURN;
	private static boolean ARG_LIMITEDACTIONCARD;
	private static boolean ARG_CUSTOMSTARTINGMONEY;
	private static boolean ARG_CUSTOMSTARTINGLOAN;
	
	// Game-influenced variables
	private static int numberOfPlayersInGame;
	private static ArrayList<ActionCard> actionCards;
	private static Player[] players;
	private static int turn;

	private static Scanner input = new Scanner(System.in);
	
	public MainGame() { // Constructor
		System.out.println("PROGRAM START! KEEP THE TERMINAL OPEN FOR REAL-TIME PROGRAM RUN LOG AND TERMINAL ARGUMENTS INPUT.");
		// Begin instantiation
		actionCards = new ArrayList<ActionCard>(); // instantiate actionCards
		turn = 0; // Before game starts, set player turn to 0 (e.g Player 1's turn, value is 0; Player 2's turn, value is 1 etc.)
		
		// Generate decks
		generateDeckOfCards();
	}
	
	// Create and instantiate objects based on given input on the intro GUI
	public void gameIntro(int numberOfPlayersInGame) {
		// Instantiate players
		System.out.println("System: Instantiate " + numberOfPlayersInGame + " players.");
		MainGame.numberOfPlayersInGame = numberOfPlayersInGame;
		players = new Player[numberOfPlayersInGame];
		for (int i = 0; i < numberOfPlayersInGame; i++)
			players[i] = new Player((i+1));
		
		
	}
	
	/**	This method is responsible for generating a deck of Action Cards.
	 * 	The deck uses stack implementation in handling the drawing of cards. 
	 * 	This means there is a head value pointing to the card to be drawn.
	 *   
	 * 	The cards are created in sequential order: all cards of ID 0 are first created, then ID 1, until ID 3.
	 * 	After creation, shuffle the cards and set head 49 value (pointing to the last index). <P>
	 * 	User can set -ac before program run to specify a custom head value.
	 * 	
	 * @param actionCards ArrayList of ActionCard to create decks to
	 * @param ARG_LIMITEDACTIONCARD Argument modifier for setting which index to start drawing a card. Resets at next reshuffle.
	 */
	public void generateDeckOfCards() {
		// Begin instantiation of action cards
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
		if (ARG_LIMITEDACTIONCARD) { // -ac (limited action cards)
			// display generated action cards, ask user at which card sequence to start drawing (end program if invalid input)
			displayActionCards();
			System.out.println("Enter number of Action Cards to start off: ");
			int num = Integer.parseInt(input.nextLine());
			ActionCard.setHead(num - 1);
			System.out.println("NOTE: 50 Action cards are still generated, but head starts at specified input.");
		}
	}
	/**
	 * Display on the terminal all cards generated on a given deck.
	 * @param deck the deck to show all cards
	 */
	public void displayDeck(Object deck) {
		if (deck instanceof ActionCard) {
			System.out.println("System: Showing all generated ACTION CARDS:");
			for (ActionCard card : actionCards)
				System.out.print(card.getTypeOfCard() + ", ");
			System.out.println("\n");
		}
	}
	
	/**	This method is called whenever a player draws a card (action card) from a deck. 
	 * 	It calls the pop method of that respective deck to retrieve the top most card 
	 * 	and shifts the head of the deck (uses stack implementation).
	 * @return action card drawn from top of the deck,
	 */
	public ActionCard drawDeck(ArrayList<ActionCard> deck) {
		return ActionCard.pop(actionCards);
	}
	
	/**
	 * This method is called by the controller to determine the 
	 * @param argument
	 * @return
	 */
	public static void setArguments(String[] args) {
		// Command-line arguments
		ARG_MANUALTURN = Arrays.asList(args).contains("-mt");
		ARG_LIMITEDACTIONCARD = Arrays.asList(args).contains("-ac");
		ARG_CUSTOMSTARTINGMONEY = Arrays.asList(args).contains("-cm");
		ARG_CUSTOMSTARTINGLOAN = Arrays.asList(args).contains("-cl");
	}
	
	public static int getNumberOfPlayers() {
		return numberOfPlayersInGame;
	}
	
	// DEPRECATED METHODS. No longer being worked on. (DO NOT DELETE)
	
	/** This is what executes at the start of the program before the first round of the game.
	 * First, a deck of Action Cards is generated.
	 * Next, the program asks the user how many players are there (2-3), then uses that to instantiate players.<P>
	 * If -cm enabled, set custom starting money, otherwise default $200000<P>
	 * If -cl enabled, set custom starting loan, otherwise default no starting loan<P>
	 * Each player's career will be assigned as Athlete (for now). <P>
	 * 
	 * After this, the game starts at phase1Game() method.
	 * @deprecated METHOD NO LONGER BEING WORKED ON (PHASE 1)
	 */
	public void preliminaryStart() {
		actionCards = new ArrayList<ActionCard>(); // instantiate actionCards
		turn = 0; // Before game starts, set player turn to 0 (e.g Player 1's turn, value is 0; Player 2's turn, value is 1 etc.)
		
		generateDeckOfActionCards(actionCards); // Generate deck of action cards and shuffle them
		
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
		
		// Instantiate Players based on how much players there are AND set starting money if -cm enabled
		if (ARG_CUSTOMSTARTINGMONEY) { // -cm (Custom money) arguemnt
			// Ask for custom starting money for each players, then proceed to create players
			System.out.print("Enter custom starting money:");
			double startingMoney = Double.parseDouble(input.nextLine());
			for (int i = 0; i < numberOfPlayersInGame; i++)
				players[i] = new Player((i+1), startingMoney);
		}
		else // default starting money $200000, proceed to create players
			for (int i = 0; i < numberOfPlayersInGame; i++)
				players[i] = new Player((i+1));
		
		// -cl set custom loan
		if (ARG_CUSTOMSTARTINGLOAN) {
			System.out.println("Set custom starting LOAN for each player: ");
			double amount = Double.parseDouble(input.nextLine());
			for (Player player : players) // For each player in players (array), set their loan
				player.setLoan(amount);
		}
		
		// Predefine each player's career to Athlete (for now)
		System.out.println("Notice: Career assigned to each player is Athlete for now.");
		for (int i = 0; i < numberOfPlayersInGame; i++)
			players[i].setCareer("Athlete");
	}
	
	/**
	 * 	This method facilitates the flow of each round of the game. 
	 * 	It handles the drawing of cards, the execution of the card's instructions, and modifies a player's status.<P>
	 *	First, show the current player's status (e.g money balance, loan amount)
	 * 	Then draw a card. (Uses stack implementation, head starts at the last card (index 49).
	 * 	Show the card details (type of card, description and instructions).
	 * 	Execute the action of the card.<P>
	 * 
	 * 	If -mt (manual turn) is enabled, the method asks at the end of each round who the next player turn should be. <P>
	 * 	Note that this method may become deprecated at phase 2 development.
	 *  @param turn current player turn (e.g Player 1's turn, the value is 0; Player 2's turn, the value is 1)
	 *  @deprecated METHOD NO LONGER BEING WORKED ON (PHASE 1)
	 */
	public void phase1Game() {
		do {
			String answer; // user confirmation if he wants to continue/exit the game. 
			
			// show player status
			System.out.println("PLAYER " + (turn+1) 
					+ "'s turn! | MONEY: " + players[turn].getMoneyBalance() + 
					" | LOAN: " + players[turn].getMoneyLoan() + "| INTEREST: " + players[turn].getMoneyInterest());
			
			ActionCard drawn = this.drawDeck(actionCards); // Draw a card
			
			System.out.println("Action card drawn: " + drawn.toString()); // Display drawn card
			
			drawn.doAction(players, turn, numberOfPlayersInGame); // Execute card action
			
			System.out.println("Continue playing? Y/N: "); // Prompt user if to continue program
			answer = MainGame.input.nextLine();
			if (answer.equalsIgnoreCase("N"))
				break;
			
			if (ARG_MANUALTURN) { // -mt (manual turn) if enabled, Specify next player turn 
				System.out.print("Choose player turn: ");
				turn = Integer.parseInt(MainGame.input.nextLine()) - 1;
			}
			
			else // default increment player turn
				turn = (turn == (numberOfPlayersInGame - 1)) ? 0 : turn + 1;
			
			System.out.println("--------");
		} while (true);
	}
	
	/**	This method is responsible for generating a deck of Action Cards.
	 * 	The deck uses stack implementation in handling the drawing of cards. 
	 * 	This means there is a head value pointing to the card to be drawn.
	 *   
	 * 	The cards are created in sequential order: all cards of ID 0 are first created, then ID 1, until ID 3.
	 * 	After creation, shuffle the cards and set head 49 value (pointing to the last index). <P>
	 * 	User can set -ac before program run to specify a custom head value.
	 * 	
	 * @param actionCards ArrayList of ActionCard to create decks to
	 * @param ARG_LIMITEDACTIONCARD Argument modifier for setting which index to start drawing a card. Resets at next reshuffle.
	 * @deprecated METHOD NO LONGER BEING WORKED ON (PHASE 1)
	 */
	public void generateDeckOfActionCards(ArrayList<ActionCard> actionCards) {
		// Begin instantiation of action cards
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
		if (ARG_LIMITEDACTIONCARD) { // -ac (limited action cards)
			// display generated action cards, ask user at which card sequence to start drawing (end program if invalid input)
			displayActionCards();
			System.out.println("Enter number of Action Cards to start off: ");
			int num = Integer.parseInt(input.nextLine());
			ActionCard.setHead(num - 1);
			System.out.println("NOTE: 50 Action cards are still generated, but head starts at specified input.");
		}

	}
	
	/**
	 * This method shows all the ActionCards generated
	 * @param deck Action Card deck to show cards
	 * @deprecated METHOD NO LONGER BEING WORKED ON (PHASE 1)
	 */
	public static void displayActionCards() { 
		System.out.println("Action Cards generated (uses stack implementation):");
		for (ActionCard card : actionCards)
			System.out.print(card.getTypeOfCard() + ", ");
		System.out.println("\n");
	}
}