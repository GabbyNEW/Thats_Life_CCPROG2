package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import Model.*;
import View.*;

public class Controller implements ActionListener {
	
	private static MainGame game;
	private static StartGUI startGUI;
	private static BoardGUI boardGUI;
	
	public Controller (StartGUI startGUI) {
		startGUI.setListener(this);
	}
	
	public Controller (BoardGUI GUI) { // Listen to action events in the middle of the game 
		GUI.setListener(this);
	}

	/**
	 * Driver method. <P> 
	 * ARGUMENT LIST:<P> 
	 * -mt (manual turn) Player number turn is user-specified per round.<P>
	 * -ac (limited action cards) User specifies number of Action Cards before start of game. Note that after running out of decks and reshuffling, number of cards is back to 50.<P>
	 * -cm (custom money) user specifies a custom starting money for each players (default $200000) <P>
	 * -cl (custom loan) user specifies a custom starting loan balance to every player (does not affect interest)<P>
	 * @param args arguments included by the user before program run.
	 */
	public static void main(String[] args) {
		game = new MainGame(); // Create game instance.
		
		startGUI = new StartGUI();
		new Controller(startGUI);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object command = e.getSource();
		
		// Starting window
		if (command.equals(startGUI.startButton) && e.getActionCommand().equals("Next")) {
			game.gameIntro(Integer.parseInt(startGUI.getPlayerNumberFieldString())); // Set players
			startGUI.destroy(); // get rid of start window, open up the actual game window
			boardGUI = new BoardGUI();
			new Controller(boardGUI);
			
			boardGUI.setChooseStartPath();
		}
		
		// (before junction) Player has chosen a path. Proceed to assign chosen path.
		if (command.equals(boardGUI.startButton)) {
			MainGame.getCurrentPlayer().setNewPath(boardGUI.getChosenStartingPath()); // assign chosen path
			
			if (MainGame.getCurrentPlayer().getLifePath() == 1) { // Career
				ArrayList<Cards> cards = game.executeStartLifePathCareer();
				
				boardGUI.setCareerStartPath(cards); // update the GUI showing the drawn career and salary cards
			}
			
			else if (MainGame.getCurrentPlayer().getLifePath() == 2) { // College
				game.executeStartLifePathCollegeCareer();
				boardGUI.setCollegeStartPath();
			}
		}
		
		// Hand over to next player turn
		if (command.equals(boardGUI.proceedButton) && e.getActionCommand().equals("Next Turn!")) {
			if (MainGame.isAllPlayersEnded()); // TODO end the game
			
			else {
				MainGame.nextTurn();
				if (MainGame.getCurrentPlayer().getLifePath() == 0)  // Player has not chosen a life path yet
					boardGUI.setChooseStartPath();				
				else  // Player already has a chosen life path
					boardGUI.setReadyToMove(); // allows the player to "throw a dice"
			}
			
		}
		
		// Collect from selected player (action card event)
		if (e.getActionCommand().equals("Collect")) {
			int chosenPlayer = boardGUI.getChosenPlayer();
			MainGame.getPlayers()[MainGame.getTurn()].addMoneyBalance(30000.00);
			MainGame.getPlayers()[chosenPlayer - 1].reduceMoneyBalance(30000.00);
			System.out.println("Collected from player (0-2): " + (chosenPlayer - 1));
			
			boardGUI.setReadyToHandoverTurn();
			boardGUI.updatePlayerStats();
		}
		
		// Pay to selected player (action card event)
		if (e.getActionCommand().equals("Pay")) {
			int chosenPlayer = boardGUI.getChosenPlayer();
			MainGame.getPlayers()[MainGame.getTurn()].reduceMoneyBalance(30000.00); // Reduce money from current player
			MainGame.getPlayers()[chosenPlayer - 1].addMoneyBalance(30000.00); // Add money to selected player
			System.out.println("Paid to player (0-2): " + (chosenPlayer - 1));
			
			boardGUI.setReadyToHandoverTurn();
			boardGUI.updatePlayerStats();
		}
		
		// User generates a number in a Get Married space (Magenta space event)
		if (e.getActionCommand().equals("Generate Number & Get Married!")) {
			int randomNumber = MainGame.executeGetMarried(); // Set player married, get the random number generated.
			boardGUI.setMagentaSpaceLanded_GetMarried(randomNumber); // Update GUI
		}
		
		// Pay loan (ignore event if not enough money)
		if (command.equals(boardGUI.payLoanButton)) {
			if (MainGame.getCurrentPlayer().payLoan())
				boardGUI.setLoanPaid();
			else
				System.out.println("Reject loan pay event.");
		}
		
		/* Throw dice, then move the player and update the player game piece
		 * Check what kind of space player has landed, then execute the space's instructions.
		 */
		if (command.equals(boardGUI.proceedButton) && e.getActionCommand().equals("Get a Turn & Move!")) { // Player "throws the dice"
			game.throwDice();
			game.movePlayer(); // Updates the coordinates of the player
			boardGUI.panelMid.repaint(); // update the board view to reflect player movement
			
			int spaceLanded = Spaces.getSpaceType(MainGame.getCurrentPlayer().getPlayerLocation()); // Determine what space the player landed
			
			// Proceed to execute
			switch (spaceLanded) {
			case 0 : { // player landed on blank space
				boardGUI.setBlankSpaceLanded();
			} break;
			case 1:  { // // player landed on orange space
				ActionCard cardDrawn = game.executeOrangeSpace(); // player landed on orange space
				boardGUI.setOrangeSpaceLanded(cardDrawn); 
			} break;
			case 2 : { // Player landed on magenta space
				int magentaID = MainGame.executeMagentaSpace();
				
				switch (magentaID) {
				case 0 : { // College Career Choice
				} break;
				case 1 : { // Job Search
				} break;
				case 2 : { // Buy House
				} break;
				case 3 : { // Get Married
					boardGUI.setMagentaSpaceLanded_GetMarried();
				} break;
				case 4 : { // Baby
				} break;
				case 5 : { // Graduate
					MainGame.executeGraduate();
					boardGUI.setMagentaSpaceLanded_Graduated();
				} break;
				case 6 : { // Conjunction
				} break;
				default : System.out.println("Magenta coordinate not recognized.");
				}			
			} break;
			}
		}

	}
}
