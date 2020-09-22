package Model;
import java.util.*;
/**
 * 	This is the absolute main class of the program that stores the card decks, the players, and other information crucial data for program run.
 * 	The class also contains methods that facilitate the execution of the game's actions (e.g draw & generate cards, modify player status)
 *  This program takes in terminal arguments for testing.<P>
 *  ARGUMENT LIST:<P> 
 * -mt (manual turn) Player number turn is user-specified per round.<P>
 * -cm (custom money) user specifies a custom starting money for each players (default $200000) <P>
 * -cl (custom loan) user specifies a custom starting loan balance to every player (does not affect interest)<P>
 */
public class MainGame {
	// Game-influenced variables
	private static Player[] players;
	private static int numberOfPlayersInGame;
	
	private static DeckOfActionCards actionCards;
	private static DeckOfBlueCards blueCards;
	private static DeckOfCareerCards careerCards;
	private static DeckOfSalaryCards salaryCards;
	private static ArrayList<HouseCard> houseCards;
	
	private static int turn; // value 0 for player 1; value 1 for player 2 etc.
	private static int randomMoveNumber; // value generated when a player "throws the dice" during the game
	private static int numberOfMoves; // 1 - 10

	private static Scanner input = new Scanner(System.in);
	private static Random rand = new Random();
	
	public MainGame() { // Constructor
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
	
	public void throwDice() {
		while (numberOfMoves == 0) // Prevent 0, make sure all previous moves have been used up before this is called 
			numberOfMoves = rand.nextInt(8); // (TODO reset to 11) Throw a dice (1-10)
		System.out.println("Move: " + numberOfMoves);
	}
	
	/**
	 * Moves the player across the board and updates its player location based on the given random number generated
	 * @param numOfMoves
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
			while ((newLocation[0] == 0 && newLocation[1] >= 0) && (newLocation[0] == 0 && newLocation[1] < 11) && numberOfMoves != 0) { // [0,0] – [11,0]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 0 && newLocation[1] == 11) && (newLocation[0] < 2 && newLocation[1] == 11) && numberOfMoves != 0) { // [0,11] – [2,11]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 2 && newLocation[1] <= 11) && (newLocation[0] == 2 && newLocation[1] > 3) && numberOfMoves != 0) { // [2,11] – [2,3]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 2 && newLocation[1] == 3) && (newLocation[0] < 3 && newLocation[1] == 3) && numberOfMoves != 0) { // [2,3] – [3,3]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 3 && newLocation[1] >= 3) && (newLocation[0] == 3 && newLocation[1] < 10) && numberOfMoves != 0) { // [3,3] – [3,10]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 3 && newLocation[1] == 10) && (newLocation[0] < 4 && newLocation[1] == 10) && numberOfMoves != 0) { // [3,10] – [4,10]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 4 && newLocation[1] <= 10) && (newLocation[0] == 4 && newLocation[1] > 7) && numberOfMoves != 0) { // [4,10] – [4,7]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 4 && newLocation[1] == 7) && (newLocation[0] < 5 && newLocation[1] == 7) && numberOfMoves != 0) { // [4,7] – [5,7]
				newLocation[0]++;
				numberOfMoves--;
			}
		}
		
		else if (players[turn].getLifePath() == 3) { //  Career path AFTER Junction
			while ((newLocation[0] == 5 && newLocation[1] >= 7) && (newLocation[0] == 5 && newLocation[1] < 13) && numberOfMoves != 0) { // [5,7] – [5,13]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] <= 5 && newLocation[1] == 13) && (newLocation[0] > 1 && newLocation[1] == 13) && numberOfMoves != 0) { // [5,13] – [1,13]
				newLocation[0]--;
				numberOfMoves--;
			}
			while ((newLocation[0] == 1 && newLocation[1] >= 13) && (newLocation[0] == 1 && newLocation[1] < 16) && numberOfMoves != 0) { // [1,13] – [1,16]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 1 && newLocation[1] == 16) && (newLocation[0] < 6 && newLocation[1] == 16) && numberOfMoves != 0) { // [1,16] – [6,16]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 6 && newLocation[1] >= 16) && (newLocation[0] == 6 && newLocation[1] < 19) && numberOfMoves != 0) { // [6,16] – [6,19]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 6 && newLocation[1] == 19) && (newLocation[0] < 9 && newLocation[1] == 19) && numberOfMoves != 0) { // [6,19] – [9,19]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 9 && newLocation[1] <= 19) && (newLocation[0] == 9 && newLocation[1] > 15) && numberOfMoves != 0) { // [9,19] – [9,15]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 9 && newLocation[1] == 15) && (newLocation[0] < 10 && newLocation[1] == 15) && numberOfMoves != 0) { // [9,15] – [10,15]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 10 && newLocation[1] <= 15) && (newLocation[0] == 10 && newLocation[1] > 14) && numberOfMoves != 0) { // [10,15] – [10,14]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 10 && newLocation[1] == 14) && (newLocation[0] < 12 && newLocation[1] == 14) && numberOfMoves != 0) { // [10,14] – [12,14]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 12 && newLocation[1] >= 14) && (newLocation[0] == 12 && newLocation[1] < 19) && numberOfMoves != 0) { // [12,14] – [12,19]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 12 && newLocation[1] == 19) && (newLocation[0] >= 12 && newLocation[1] == 19) && numberOfMoves != 0) { // [12,19] – [15+,19] END SPACE
				newLocation[0]++; // may move past the endspace
				numberOfMoves--;
			}
		}
		
		else if (players[turn].getLifePath() == 4) { //  Family path AFTER Junction
			while ((newLocation[0] == 5 && newLocation[1] >= 7) && (newLocation[0] == 5 && newLocation[1] < 9) && numberOfMoves != 0) { // [5,7] – [5,9]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 5 && newLocation[1] == 9) && (newLocation[0] < 7 && newLocation[1] == 9) && numberOfMoves != 0) { // [5,9] – [7,9]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 7 && newLocation[1] >= 9) && (newLocation[0] == 7 && newLocation[1] < 13) && numberOfMoves != 0) { // [7,9] – [7,13]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 7 && newLocation[1] == 13) && (newLocation[0] < 9 && newLocation[1] == 13) && numberOfMoves != 0) { // [7,13] – [9,13]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 9 && newLocation[1] <= 13) && (newLocation[0] == 9 && newLocation[1] > 12) && numberOfMoves != 0) { // [9,13] – [9,12]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 9 && newLocation[1] == 12) && (newLocation[0] < 10 && newLocation[1] == 12) && numberOfMoves != 0) { // [9,12] – [10,12]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 10 && newLocation[1] <= 12) && (newLocation[0] == 10 && newLocation[1] > 9) && numberOfMoves != 0) { // [10,12] – [10,9]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 10 && newLocation[1] == 9) && (newLocation[0] < 12 && newLocation[1] == 9) && numberOfMoves != 0) { // [10,9] – [12,9]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 12 && newLocation[1] <= 9) && (newLocation[0] == 12 && newLocation[1] > 1) && numberOfMoves != 0) { // [12,9] – [12,1]
				newLocation[1]--;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 12 && newLocation[1] == 1) && (newLocation[0] < 14 && newLocation[1] == 1) && numberOfMoves != 0) { // [12,1] – [14,1]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 14 && newLocation[1] >= 1) && (newLocation[0] == 14 && newLocation[1] < 15) && numberOfMoves != 0) { // [14,1] – [14,15]
				newLocation[1]++;
				numberOfMoves--;
			}
			while ((newLocation[0] >= 14 && newLocation[1] == 15) && (newLocation[0] < 15 && newLocation[1] == 15) && numberOfMoves != 0) { // [14,15] – [15,15]
				newLocation[0]++;
				numberOfMoves--;
			}
			while ((newLocation[0] == 15 && newLocation[1] >= 15) && (newLocation[0] == 15 && newLocation[1] >= 15) && numberOfMoves != 0) { // [15,15] – [15,19+] END
				newLocation[1]++; // may move past the endspace
				numberOfMoves--;
			}
		}
		numberOfMoves = 0;
		// No more moves left, or if numberOfMoves != 0 assume player landed at Magenta space. Proceed to assign new location to the player.
		players[turn].updateCurrentLocation(newLocation);
		
		// TODO add statement to set player end status to true if has moved on or past the end space located at [15,19]
	}

	/**
	 * Get the player out of the magenta space to prevent getting stuck.
	 * @param newLocation current coordinate of the player
	 * @return shifted coordinates after moving past the magenta space (if applicable), else no effect
	 */
	private int[] movePastMagentaSpaceLanded(int[] newLocation) {
		if (Spaces.getSpaceType(newLocation) == 2) { 
			if (newLocation[0] == 3 && newLocation[1] == 7) // College Choice
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
		}
		
		numberOfMoves--;
		return newLocation;
	}
	
	/**
	 * Player takes one Career Card and Salary Card. 
	 * @return ArrayList of cards: one career card[0] and one salary card[1]
	 */
	public ArrayList<Cards> executeStartLifePathCareer() {
		ArrayList<Cards> cardsDrawn = new ArrayList<Cards>();
		Cards temp;
		
		// Keep drawing salary cards until ... 
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

	public void executeStartLifePathCollegeCareer() {
		getCurrentPlayer().setLoan();
	}
	
	public ActionCard executeOrangeSpace() {
		ActionCard cardDrawn = DeckOfActionCards.pop();
		
		cardDrawn.doAction(players, turn, numberOfPlayersInGame); // Manipulate player stats based on the card
		
		return cardDrawn;
	}
	
	public static int executeMagentaSpace() {
		int magentaID = MagentaSpaces.getMagentaID(getCurrentPlayer().getPlayerLocation()); // Check what kind of magenta space it is
		
		switch (magentaID) {
		case 0 : { // College Career Choice
		} break;
		case 1 : { // Job Search
		} break;
		case 2 : { // Buy House
		} break;
		case 3 : { // Get Married
			
		} break;
		case 4 : { // Baby
		} break;
		case 5 : { // Graduate
		} break;
		case 6 : { // Conjunction
		} break;
		default : System.out.println("Magenta coordinate not recognized.");
		}
		
		return magentaID;
	}
	
	/**
	 * Set current player to be married (Get Married event)
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
	
	public static int getNumberofplayersingame() {
		return numberOfPlayersInGame;
	}
	
	public static Player[] getPlayers() {
		return players;
	}
	
	public static Player getCurrentPlayer() {
		return players[turn];
	}
	
	public static void nextTurn() { // TODO: skip players who are done playing
		if (numberOfPlayersInGame == 2) // Cycle from 0 to 1 (Two players)
			turn = (turn == 0) ? turn+1 : 0;
		else // Cycle from 0 to 2 (Three players)
			turn = (turn < 2) ? turn+1 : 0;
	}
	
	/**
	 * Returns the index value of who the current player's turn is.
	 * @return turn value (0 for player 1, 1 for player 2 etc.)
	 */
	public static int getTurn() {
		return turn;
	}
	
	public static int getNumberOfMoves() {
		return numberOfMoves;
	}
	
	public static boolean isAllPlayersEnded() {
		if (numberOfPlayersInGame == 2)
			return (players[0].hasReachedEndSpace() && players[1].hasReachedEndSpace());
		else
			return (players[0].hasReachedEndSpace() && players[1].hasReachedEndSpace() && players[2].hasReachedEndSpace());
	}
	

}