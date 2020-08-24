import java.util.*;
/** This program can take in specific arguments for testing. <P>
 * ARGUMENT LIST:<P> -mt (manual turn) Player number turn is user-specified per round.<P>
 * -ac (limited action cards) User specifies number of Action Cards before start of game. 
 *	Note that after running out of decks and reshuffling, number of cards is back to 50. 
 * @author gabby
 *
 */
public class MainGame {
	private static boolean ARG_MANUALTURN;
	private static boolean ARG_LIMITEDACTIONCARD;
	
	private int numberOfPlayersInGame;
	private ArrayList<ActionCard> actionCards;
	private Player[] players;
	private int turn;

	private Scanner input = new Scanner(System.in);
	
	/* This is what executes at the start of the program.
	 * First, a deck of Action Cards is generated.
	 * Next, the program asks the user how many players are there (2-3), then uses that to instantiate player array.
	 * Each player's career will be assigned as Athlete (for now)
	 * 
	 * After this, the first round starts at phase1Game() method.
	 */
	public void preliminaryStart() {
		actionCards = new ArrayList<ActionCard>();
		turn = 0;
		
		generateDeckOfActionCards(actionCards, ARG_LIMITEDACTIONCARD);
		
		do {
			System.out.print("Enter number of players: ");
			numberOfPlayersInGame = Integer.parseInt(input.nextLine());
			players = new Player[numberOfPlayersInGame];
			
			if (numberOfPlayersInGame < 1 || numberOfPlayersInGame > 3)
			 System.out.println("Error!");
			else if(numberOfPlayersInGame == 1)
				System.out.println("The game is NOT designed for single player.");
			
		} while (numberOfPlayersInGame < 2 || numberOfPlayersInGame > 3);
		
		for (int i = 0; i < numberOfPlayersInGame; i++)
			players[i] = new Player((i+1));

		
		System.out.println("Notice: Career assigned to each player is Athlete for now.");
		for (int i = 0; i < numberOfPlayersInGame; i++)
			players[i].setCareer("Athlete");
	}
	
	public void generateDeckOfActionCards(ArrayList<ActionCard> actionCards, boolean ARG_LIMITEDACTIONCARD) {
		int i;
		for (i = 0; i < 20; i++) {
			actionCards.add(new ActionCard(0)); // create cards with ID 0 (collect from bank)
			actionCards.get(i).generateSubID(); // See ActionCard class
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		for (i = 20; i < 40; i++) {
			actionCards.add(new ActionCard(1)); // create cards with ID 1 (pay the bank)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		for (i = 40; i < 45; i++) {
			actionCards.add(new ActionCard(2)); // ID 2 (pay the player)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		for (i = 45; i < 50; i++) {
			actionCards.add(new ActionCard(3)); // ID 3 (collect from player)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		Collections.shuffle(actionCards);
		if (ARG_LIMITEDACTIONCARD) {
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
	
	/*	First, show the current player's status.
	 * 	Then draw a card. (Uses stack implementation, head starts at the last card (index 49)
	 * 	Show card details.
	 * 	Execute the action of the card.
	 */
	private void phase1Game(int turn) {
		System.out.println("PLAYER " + (turn+1) 
				+ "'s turn! | MONEY: " + this.players[turn].getMoneyBalance() + " | LOAN: " + this.players[turn].getMoneyLoan());
		
		ActionCard drawn = this.drawDeck();
		
		System.out.println("Action card drawn: " + drawn.toString());
		
		drawn.doAction(this.players, turn, numberOfPlayersInGame);
	}
	
	public ActionCard drawDeck() {
		return ActionCard.pop(this.actionCards);
	}
	
	public static void main(String[] args) {
		// Command-line argument
		MainGame.ARG_MANUALTURN = Arrays.asList(args).contains("-mt");
		MainGame.ARG_LIMITEDACTIONCARD = Arrays.asList(args).contains("-ac");
		
		System.out.println("Welcome! This is an initial build of the \"That's Life!\" project. "
				+ "The program accepts a number of arguments for testing. Please refer to the provided Javadoc for details.\n");

		MainGame game = new MainGame(); // Create game instance.
		
		game.preliminaryStart();
		MainGame.displayActionCards(game.actionCards);
		
		do {
			String answer; // This is just for user confirmation if he wants to continue/exit the game. 
			
			game.phase1Game(game.turn); // The turn variable is for tracking who's player turn it is (Current player number - 1) e.g Player 1's turn -> turn = 0
			
			System.out.println("Continue playing? Y/N: ");
			answer = game.input.nextLine();
			
			if (answer.equalsIgnoreCase("N"))
				break;
			
			if (ARG_MANUALTURN) {
				System.out.print("Choose player turn: ");
				game.turn = Integer.parseInt(game.input.nextLine()) - 1;
			}	
			else
				game.turn = (game.turn == (game.numberOfPlayersInGame - 1)) ? 0 : game.turn + 1;
			
			System.out.println("--------");
		} while (true);
		
	}

}
