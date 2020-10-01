package Model;
import java.util.*;
/**
 * 	This is the absolute main class of the program that stores the card decks, the players, and other information crucial data for program run.
 * 	The class also contains methods that facilitate the execution of the game's actions (e.g draw & generate cards, modify player status)
 *  This program takes in terminal arguments for testing.<P>
 * -mt (manual turn) Player number turn is user-specified per round.<P>
 */
public class MainGame {
	// Game-influenced variables
	private static Player[] players;
	private static int numberOfPlayersInGame;
	
	private static DeckOfActionCards actionCards;
	private static DeckOfBlueCards blueCards;
	private static DeckOfCareerCards careerCards;
	private static DeckOfSalaryCards salaryCards;
	public static ArrayList<HouseCard> houseCards;
	
	private static int turn; // value 0 for player 1; value 1 for player 2 etc.
	private static int randomMoveNumber; // value generated when a player "throws the dice" during the game
	private static int numberOfMoves; // 1 - 10

	private static int trackWinner;
	private static boolean ARGS_manualTurn;
	
	private static Scanner input = new Scanner(System.in);
	private static Random rand = new Random();
	
	/**
	 * Proceed to instantiate cards and check if -mt terminal argument is included. 
	 * @param args terminal arguments
	 */
	public MainGame(String[] args) { // Constructor
		System.out.println("Program start.");
		// Begin instantiation
		actionCards = new DeckOfActionCards(); // instantiate actionCards
		blueCards = new DeckOfBlueCards(); // instantiate blueCards
		careerCards = new DeckOfCareerCards();	//instantiate careerCards
		salaryCards = new DeckOfSalaryCards(); // instantiate salaryCards
		houseCards = new ArrayList<HouseCard>();
		
		for (int i = 0; i < 9; i++)
			houseCards.add(new HouseCard(i));
		
		turn = 0; // Before game starts, set player turn to 0 (e.g Player 1's turn, value is 0; Player 2's turn, value is 1 etc.)
		
		ARGS_manualTurn = (Arrays.asList(args).contains("-mt")) ? true : false;
	}
	
	/**
	 * This method immediately executes after the player clicks "Next" at the start window.
	 * This proceeds to instantiate players based on the selected number of players.
	 * @param numberOfPlayersInGame
	 */
	public void gameIntro(int numberOfPlayersInGame) {
		// Instantiate players
		System.out.println("System: Instantiate " + numberOfPlayersInGame + " players.");
		MainGame.numberOfPlayersInGame = numberOfPlayersInGame;
		players = new Player[numberOfPlayersInGame];
		for (int i = 0; i < numberOfPlayersInGame; i++)
			players[i] = new Player(i);
	}
	
	/**
	 * This method is called whenever a user generates a number of moves. 
	 * Range of numbers to generate is between 1 - 10
	 * Sometimes 0 is generated. In that case, repeat again until non-zero result.
	 */
	public void throwDice() {
		if (ARGS_manualTurn) { // -mt enabled, manually specify number of moves for each round
			System.out.print("Enter # of moves for Player " + (turn + 1) + ": ");
			numberOfMoves = Integer.parseInt(input.nextLine());
		}
		else {
			while (numberOfMoves == 0) // Prevent 0, make sure all previous moves have been used up before this is called 
				numberOfMoves = rand.nextInt(11); 
			System.out.println("Move: " + numberOfMoves);
		}
	}
	
	/**
	 * Moves the player across the board and updates its player location based on the given random number generated
	 */
	public void movePlayer() {
		int[] newLocation = new int[2]; // This is where to store the new player location (then assign it to player as its new location)
			newLocation = players[turn].getPlayerLocation();
			
		newLocation = movePastMagentaSpaceLanded(newLocation);
			
		// Move the player throughout the board, decrement numOfMoves each time location is updated, repeat until no more moves left.
		// Player stops upon reaching a magenta space
		if (players[turn].getLifePath() == 1) { // Career path BEFORE Junction
			while ((newLocation[0] >= 0 && newLocation[1] == 0) && (newLocation[0] < 10 && newLocation[1] == 0) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [0,0] – [10,0]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 10 && newLocation[1] >= 0) && (newLocation[0] == 10 && newLocation[1] < 3) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [10,0] – [10,3]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] <= 10 && newLocation[1] == 3) && (newLocation[0] > 5 && newLocation[1] == 3) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [10,3] – [5,3]
				newLocation[0]--;
				numberOfMoves--;
			}
			while ((newLocation[0] == 5 && newLocation[1] >= 3) && (newLocation[0] == 5 && newLocation[1] < 5) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [5,3] – [5,5]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 5 && newLocation[1] == 5) && (newLocation[0] < 10 && newLocation[1] == 5) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [5,5] – [10,5]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 10 && newLocation[1] >= 5) && (newLocation[0] == 10 && newLocation[1] < 7) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [10,5] – [10,7]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] <= 10 && newLocation[1] == 7) && (newLocation[0] > 5 && newLocation[1] == 7) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [10,7] – [5,7] CONJUNCTION
				newLocation[0]--;
				numberOfMoves--;
			}
		}
		
		else if (players[turn].getLifePath() == 2) { //  College path BEFORE Junction
			while ((newLocation[0] == 0 && newLocation[1] >= 0) && (newLocation[0] == 0 && newLocation[1] < 11) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [0,0] – [11,0]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 0 && newLocation[1] == 11) && (newLocation[0] < 2 && newLocation[1] == 11) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [0,11] – [2,11]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 2 && newLocation[1] <= 11) && (newLocation[0] == 2 && newLocation[1] > 3) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [2,11] – [2,3]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 2 && newLocation[1] == 3) && (newLocation[0] < 3 && newLocation[1] == 3) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [2,3] – [3,3]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 3 && newLocation[1] >= 3) && (newLocation[0] == 3 && newLocation[1] < 10) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [3,3] – [3,10]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 3 && newLocation[1] == 10) && (newLocation[0] < 4 && newLocation[1] == 10) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [3,10] – [4,10]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 4 && newLocation[1] <= 10) && (newLocation[0] == 4 && newLocation[1] > 7) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [4,10] – [4,7]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 4 && newLocation[1] == 7) && (newLocation[0] < 5 && newLocation[1] == 7) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [4,7] – [5,7]
				newLocation[0]++;
				numberOfMoves--;
			}
		}
		
		else if (players[turn].getLifePath() == 3) { //  Career path AFTER Junction
			while ((newLocation[0] == 5 && newLocation[1] >= 7) && (newLocation[0] == 5 && newLocation[1] < 13) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [5,7] – [5,13]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] <= 5 && newLocation[1] == 13) && (newLocation[0] > 1 && newLocation[1] == 13) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [5,13] – [1,13]
				newLocation[0]--;
				numberOfMoves--;
			}
			while ((newLocation[0] == 1 && newLocation[1] >= 13) && (newLocation[0] == 1 && newLocation[1] < 16) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [1,13] – [1,16]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 1 && newLocation[1] == 16) && (newLocation[0] < 6 && newLocation[1] == 16) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [1,16] – [6,16]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 6 && newLocation[1] >= 16) && (newLocation[0] == 6 && newLocation[1] < 19) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [6,16] – [6,19]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 6 && newLocation[1] == 19) && (newLocation[0] < 9 && newLocation[1] == 19)
					& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [6,19] – [9,19]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 9 && newLocation[1] <= 19) && (newLocation[0] == 9 && newLocation[1] > 15) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [9,19] – [9,15]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 9 && newLocation[1] == 15) && (newLocation[0] < 10 && newLocation[1] == 15) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [9,15] – [10,15]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 10 && newLocation[1] <= 15) && (newLocation[0] == 10 && newLocation[1] > 14) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [10,15] – [10,14]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 10 && newLocation[1] == 14) && (newLocation[0] < 12 && newLocation[1] == 14) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [10,14] – [12,14]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 12 && newLocation[1] >= 14) && (newLocation[0] == 12 && newLocation[1] < 19) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [12,14] – [12,19]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 12 && newLocation[1] == 19) && (newLocation[0] >= 12 && newLocation[1] == 19) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [12,19] – [15+,19] END SPACE
				newLocation[0]++; // may move past the endspace
				numberOfMoves--;
			}
		}
		
		else if (players[turn].getLifePath() == 4) { //  Family path AFTER Junction
			while ((newLocation[0] == 5 && newLocation[1] >= 7) && (newLocation[0] == 5 && newLocation[1] < 9) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [5,7] – [5,9]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 5 && newLocation[1] == 9) && (newLocation[0] < 7 && newLocation[1] == 9) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [5,9] – [7,9]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 7 && newLocation[1] >= 9) && (newLocation[0] == 7 && newLocation[1] < 13) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [7,9] – [7,13]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 7 && newLocation[1] == 13) && (newLocation[0] < 9 && newLocation[1] == 13) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [7,13] – [9,13]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 9 && newLocation[1] <= 13) && (newLocation[0] == 9 && newLocation[1] > 12) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [9,13] – [9,12]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 9 && newLocation[1] == 12) && (newLocation[0] < 10 && newLocation[1] == 12) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [9,12] – [10,12]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 10 && newLocation[1] <= 12) && (newLocation[0] == 10 && newLocation[1] > 9) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [10,12] – [10,9]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 10 && newLocation[1] == 9) && (newLocation[0] < 12 && newLocation[1] == 9) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [10,9] – [12,9]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 12 && newLocation[1] <= 9) && (newLocation[0] == 12 && newLocation[1] > 1) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [12,9] – [12,1]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 12 && newLocation[1] == 1) && (newLocation[0] < 14 && newLocation[1] == 1) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [12,1] – [14,1]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 14 && newLocation[1] >= 1) && (newLocation[0] == 14 && newLocation[1] < 15) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [14,1] – [14,15]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 14 && newLocation[1] == 15) && (newLocation[0] < 15 && newLocation[1] == 15) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [14,15] – [15,15]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 15 && newLocation[1] >= 15) && (newLocation[0] == 15 && newLocation[1] >= 15) 
					&& numberOfMoves != 0 && Spaces.getSpaceType(newLocation) != 2) { // [15,15] – [15,19+] END
				newLocation[1]++; // may move past the endspace
				numberOfMoves--;
			}
		}
		numberOfMoves = 0;
		
		if ((newLocation[0] >= 15 && newLocation[1] == 19) || (newLocation[0] == 15 && newLocation[1] >= 19)) {
			players[turn].setReachedEnd(true);
			newLocation[0] = 15;
			newLocation[1] = 19;
		}
		// No more moves left, or if numberOfMoves != 0 assume player landed at Magenta space. Proceed to assign new location to the player.
		players[turn].updateCurrentLocation(newLocation);
		
	}

	/**
	 * Get the player out of the magenta space to prevent getting stuck.
	 * @param newLocation current coordinate of the player
	 * @return shifted coordinates after moving past the magenta space (if applicable), else no effect
	 */
	private int[] movePastMagentaSpaceLanded(int[] newLocation) {
		System.out.println("Current location " + newLocation[0] + "," + newLocation[1]);
		if (Spaces.getSpaceType(newLocation) == 2) { 
			System.out.println("Move out of magenta space " + newLocation[0] + "," + newLocation[1]);
			if (newLocation[0] == 3 && newLocation[1] == 7) // College Career Choice
				newLocation[1]++;
			else if (newLocation[0] == 5 && newLocation[1] == 7) // Conjunction
				newLocation[1]++;
			else if (newLocation[0] == 2 && newLocation[1] == 11) // Graduate
				newLocation[1]--;
			else if (newLocation[0] == 14 && newLocation[1] == 5) // Baby
				newLocation[1]++;
			else if (newLocation[0] == 12 && newLocation[1] == 6) // Buy House
				newLocation[1]--;
			else if (newLocation[0] == 9 && newLocation[1] == 12) // Get Married
				newLocation[0]++;
			else if (newLocation[0] == 1 && newLocation[1] == 16) // Search Job
				newLocation[0]++;
			else if (newLocation[0] == 8 && newLocation[1] == 3) // Get Married (2)
				newLocation[0]--;
			numberOfMoves--;
		}
		System.out.println("Returned new location " + newLocation[0] + "," + newLocation[1]);
		return newLocation;
	}
	
	/**
	 * Player takes one Career Card and Salary Card when the player chooses Career path at start.
	 * @return ArrayList of cards: one career card[0] and one salary card[1]
	 */
	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<Cards> executeStartLifePathCareer() {
		ArrayList<Cards> cardsDrawn = new ArrayList<Cards>();
		Cards temp;
		
		// Keep drawing career cards until ... 
		while (DeckOfCareerCards.top().isDegreeRequired() // Degree must not be required
				|| DeckOfCareerCards.top().equals(getCurrentPlayer().getCareer()) // Career card must not be equal to current player's career  
				|| DeckOfCareerCards.top().hasOwner()) // No one has taken this card yet
			DeckOfCareerCards.pop();
		
		temp = DeckOfCareerCards.top();
		DeckOfCareerCards.top().setHasOwner(true);
		DeckOfCareerCards.shuffle();
		cardsDrawn.add(temp);
		
		// Draw salary card
		temp = DeckOfSalaryCards.pop();
		DeckOfSalaryCards.shuffle();
		
		cardsDrawn.add(temp);
		
		getCurrentPlayer().setNewCareer((CareerCard) cardsDrawn.get(0), (SalaryCard) cardsDrawn.get(1));
		
		return cardsDrawn;
		
	}

	/**
	 * Player starts with loan upon choosing College path at start.
	 */
	public void executeStartLifePathCollegeCareer() {
		getCurrentPlayer().setLoan();
	}
	
	/**
	 * Orange space event. Draw an action card, then perform that action card's instructions
	 * @return drawn action card
	 */
	public ActionCard executeOrangeSpace() {
		ActionCard cardDrawn = DeckOfActionCards.pop();
		
		cardDrawn.doAction(players, turn, numberOfPlayersInGame); // Manipulate player stats based on the card
		
		return cardDrawn;
	}
	
	/**
	 * Call a magenta space event: Get Married event
	 * @return generated number (-1 if already married)
	 */
	public static int executeGetMarried() {
		int randomNumber = MagentaSpaces.getMarried(players, turn);
		
		return randomNumber;
	}

	/**
	 * Graduates the player (Graduate event)
	 */
	public static void executeGraduate() {
		getCurrentPlayer().setGraduate(true);
	}
	
	/**
	 * Draw top two career cards. Career cards drawn must not currently be in use by another player. (College Career Choice magenta event)
	 * @return Cards drawn [0] & [1] - Career cards
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static ArrayList<Cards> executeCollegeCareerChoice_DrawCareer() {
		ArrayList<Cards> cardsDrawn = new ArrayList<Cards>();
		Cards temp;
		
		// Keep drawing career cards until ... 
		while (DeckOfCareerCards.top().equals(getCurrentPlayer().getCareer()) // Career card must not be equal to current player's career  
				|| DeckOfCareerCards.top().hasOwner()) // No one has taken this card yet
			DeckOfCareerCards.pop();
		
		temp = DeckOfCareerCards.top();
		
		cardsDrawn.add(temp);
		
		// Keep drawing career cards until ... 
		DeckOfCareerCards.pop(); // keep searching
		while (DeckOfCareerCards.top().equals(getCurrentPlayer().getCareer()) // Career card must not be equal to current player's career  
				|| DeckOfCareerCards.top().hasOwner()) // No one has taken this card yet
			DeckOfCareerCards.pop();
		
		temp = DeckOfCareerCards.top();
		DeckOfCareerCards.top().setHasOwner(true);
		
		cardsDrawn.add(temp);
		
		DeckOfCareerCards.shuffle();
		
		return cardsDrawn;
	}
	
	/**
	 * Draw top two salary cards. (College Career Choice magenta event)
	 * @return Cards drawn [0] & [1] - Salary cards
	 */
	public static ArrayList<Cards> executeCollegeCareerChoice_DrawSalary() {
		ArrayList<Cards> cardsDrawn = new ArrayList<Cards>();
		Cards temp;
		
		// Draw salary card
		temp = DeckOfSalaryCards.pop();		
		cardsDrawn.add(temp);
		
		// Draw salary card
		temp = DeckOfSalaryCards.pop();
		cardsDrawn.add(temp);
		
		DeckOfSalaryCards.shuffle();
		
		return cardsDrawn;
	}
	
	/**
	 * Executes when a player has chosen a career card (College Career Choice magenta space event).
	 * Assign chosen career card to current player. Set career card ownership status as true.
	 * Also executed on Job Search event
	 * @param typeOfCardChosen
	 */
	public static void executeCollegeCareerChoice_CareerChosen(String typeOfCardChosen) {
		CareerCard tempChosen = null;
		
		// Find the card chosen
		while (tempChosen == null) {
			if (DeckOfCareerCards.top().getTypeOfCard().equals(typeOfCardChosen))
				tempChosen = DeckOfCareerCards.top();
			else
				DeckOfCareerCards.pop();
		}
		DeckOfCareerCards.top().setHasOwner(true);
		
		DeckOfCareerCards.shuffle();
		getCurrentPlayer().setNewCareer(tempChosen);
	}
	
	/**
	 * Executes when a player has chosen a salary card (College Career Choice magenta space event).
	 * Assign chosen salary card to current player.
	 * Also executed on Job Search event
	 * @param typeOfCardChosen
	 */
	public static void executeCollegeCareerChoice_SalaryChosen(String salary) {
		SalaryCard tempDrawn = DeckOfSalaryCards.getCard(salary);
		
		getCurrentPlayer().setNewCareer(tempDrawn);
	}

	/**
	 * Executes when a player lands on a Job Search magenta space.
	 * Draw a career card and salary card. Career card must not be in use by other players.
	 * @return drawn career card [0] and salary card [1]
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static ArrayList<Cards> executeJobSearch() {
		ArrayList<Cards> cardsDrawn = new ArrayList<Cards>();
		Cards temp;
		
		// Keep drawing salary cards until ... 
		while (DeckOfCareerCards.top().equals(getCurrentPlayer().getCareer()) // Career card must not be equal to current player's career  
				|| DeckOfCareerCards.top().hasOwner()) // No one has taken this card yet
			DeckOfCareerCards.pop();
		
		temp = DeckOfCareerCards.top();
		
		cardsDrawn.add(temp);
		
		// Draw salary card
		temp = DeckOfSalaryCards.pop();		
		cardsDrawn.add(temp);
		
		DeckOfCareerCards.shuffle();
		DeckOfSalaryCards.shuffle();
		
		return cardsDrawn;
	}
	
	/**
	 * Executes when a player has chosen a house type to buy.
	 * Find the house card within the deck, buy that house card, then set the owner to the current player.
	 * @param chosenTypeOfHouse chosen house type
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static void executeBuyHouse(String chosenTypeOfHouse) {
		HouseCard tempChosen = null;
		
		for (HouseCard e : houseCards)
			if (e.equals(chosenTypeOfHouse))
				tempChosen = e;
		
		getCurrentPlayer().reduceMoneyBalance(tempChosen.getPrice());
		getCurrentPlayer().setHouse(tempChosen);
		tempChosen.setOwner(getCurrentPlayer());
	}

	/**
	 * Executes when a player lands on a blue space.
	 * Generate a number, then draw a blue card, then execute drawn blue card's instructions.
	 * @return message detail to show to the user
	 */
	public static String executeBlueCard() {
		int nRand = rand.nextInt(6);
		while (nRand == 0)
			nRand = rand.nextInt(6);
		
		return DeckOfBlueCards.pop().doAction(players, turn, nRand);
	}
	
	/**
	 * Execute appropriate greenspace actions
	 * @return spaceID of the green space (0 - Pay Day, 1 - Pay Raise)
	 */
	public static int executeGreenSpace() {
		int spaceID = GreenSpaces.getSpaceID(getCurrentPlayer().getPlayerLocation());
		
		GreenSpaces.doAction(players[turn], spaceID);
		
		return spaceID;
	}
	
	/**
	 * Gets the number of players in the game (2-3)
	 * @return number of players in game
	 */
	public static int getNumberofplayersingame() {
		return numberOfPlayersInGame;
	}
	
	/**
	 * Player lands on Baby magenta space. Generate a number. If odd, a baby. If even, twins.
	 * Collect $5000 gift for each child from each player.
	 * @return generated value
	 */
	public static int executeBaby() {
		int nVal = rand.nextInt(11);
		
		// Set baby
		if (nVal % 2 == 0) // Even, have twins
			getCurrentPlayer().setBabyAmount(2);
		else
			getCurrentPlayer().setBabyAmount(1);

		// Collect money from each player ($5000 per child)
		for (Player e : players)
			if (e.getPlayerNumber() != turn) {
				e.reduceMoneyBalance(5000.00 * getCurrentPlayer().getBabyAmount());
				getCurrentPlayer().addMoneyBalance(5000.00 * getCurrentPlayer().getBabyAmount());
			}
		
		return nVal;
	}
	
	/**
	 * Executes when a player has reached the end space.
	 * 1. Collect payment from bank ($100000 1st, $50000 2nd, $20000 3rd)
	 * 2. Collect $10000 for each child he has from the bank.
	 * 3. Sell your house to the Bank for the amount listed on the card
	 * 4. Repay to the Bank, all outstanding loans with interest.
	 */
	public static void retire() {
		getCurrentPlayer().setReachedEnd(true);
		
		/*
		 * trackWinner starts at 0. Every time a player has made it to the end, increment it. 
		 * It is used to keep in track the winning position of the players.  
		 */
		switch (trackWinner) {
		case 0 : getCurrentPlayer().addMoneyBalance(100000.00); break; // First winner
		case 1 : getCurrentPlayer().addMoneyBalance(50000.00); break; // Second winner
		case 2 : getCurrentPlayer().addMoneyBalance(20000.00); // 3rd winner
		}
		trackWinner++; 
		
		getCurrentPlayer().addMoneyBalance(getCurrentPlayer().getBabyAmount() * 10000.00); // Collect $10000 for each child
		
		if (getCurrentPlayer().getHouseCard() != null) // Sell your house to the Bank
			getCurrentPlayer().addMoneyBalance(getCurrentPlayer().getHouseCard().getPrice());
		
		getCurrentPlayer().payLoan(); // Repay all loans
	}
	
	/**
	 * Determines the winner
	 * @return player turn winer
	 */
	public static int determineWinner() {
		int max = 0;
		if (numberOfPlayersInGame == 2) { // 2 players
			for (int i = 0; i < 2 && max != -1; i++)
				if (players[i].getMoneyBalance() > players[max].getMoneyBalance())
					max = i;
				else if (i != max && players[i].getMoneyBalance() == players[max].getMoneyBalance())
					max = -1;
		}
		
		else { // 3 players
			for (int i = 0; i < 3 && max != -1; i++)
				if (players[i].getMoneyBalance() > players[max].getMoneyBalance())
					max = i;
				else if (i != max && players[i].getMoneyBalance() == players[max].getMoneyBalance())
					max = -1;
		}
		
		return max;
	}
	
	/**
	 * Gets the player object
	 * @return players object
	 */
	public static Player[] getPlayers() {
		return players;
	}
	
	/**
	 * Gets the current player
	 * @return current player object
	 */
	public static Player getCurrentPlayer() {
		return players[turn];
	}
	
	/**
	 * Shifts the turn (modify the turn variable)
	 * The turn variable range is [0-2].
	 * turn = 0 for player 1; turn = 1 for player 2; turn = 2 for player 3
	 */
	public static void nextTurn() {
		if (numberOfPlayersInGame == 2)  // Cycle from 0 to 1 (Two players)
			do
			turn = (turn == 0 ) ? turn+1 : 0;
			while (players[turn].hasReachedEndSpace());
		
		else // Cycle from 0 to 2 (Three players)
			do
			turn = (turn < 2) ? turn+1 : 0;
			while (players[turn].hasReachedEndSpace());
	}
	
	/**
	 * Returns the index value of who the current player's turn is.
	 * @return turn value (0 for player 1, 1 for player 2 etc.)
	 */
	public static int getTurn() {
		return turn;
	}
	
	/**
	 * Get the number of moves for the player
	 * @return number of moves
	 */
	public static int getNumberOfMoves() {
		return numberOfMoves;
	}
	
	/**
	 * Checks if all players have landed on the end space
	 * @return if all players have landed on the end space (true)
	 */
	public static boolean isAllPlayersEnded() {
		if (numberOfPlayersInGame == 2)
			return (players[0].hasReachedEndSpace() && players[1].hasReachedEndSpace());
		else
			return (players[0].hasReachedEndSpace() && players[1].hasReachedEndSpace() && players[2].hasReachedEndSpace());
	}
	

}