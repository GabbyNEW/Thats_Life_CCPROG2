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
	
	private ArrayList<Cards> cardsDrawn = new ArrayList<Cards>();
	
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
			if (MainGame.isAllPlayersEnded()) {
				int turnWinner = MainGame.determineWinner();
				boardGUI.endGame(turnWinner);
			}
			
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
		
		// User has chosen a career card in College Career Choice space (Magenta space event)
		if (e.getActionCommand().equals("Confirm Career Card")) {
			MainGame.executeCollegeCareerChoice_CareerChosen(boardGUI.getChosenCareerCard()); // Assign chosen career card
			cardsDrawn = MainGame.executeCollegeCareerChoice_DrawSalary(); // Draw salary cards
			boardGUI.setMagentaSpaceLanded_CollegeCareerChoice_Salary(cardsDrawn); // Show the salary cards drawn, allow the user to choose one
		}
		
		// User has chosen a career card in College Career Choice space (Magenta space event)
		if (e.getActionCommand().equals("Confirm Salary Card")) {
			MainGame.executeCollegeCareerChoice_SalaryChosen(boardGUI.getChosenSalaryCard());
			boardGUI.setReadyToHandoverTurn();
		}
		
		// User has chosen a path at Junction
		if (e.getActionCommand().equals("Change Path")) {
			MainGame.getCurrentPlayer().setNewPath(boardGUI.getChosenJunctionPath());
			boardGUI.setReadyToMove();
		}
		
		// Job Search event, user changes to new career and salary cards
		if (e.getActionCommand().equals("New Career & Salary")) {
			DeckOfCareerCards.revokeOwnership(MainGame.getCurrentPlayer().getCareer()); // Lose ownership to current career card
			MainGame.executeCollegeCareerChoice_CareerChosen(cardsDrawn.get(0).getTypeOfCard()); // Set new career
			MainGame.executeCollegeCareerChoice_SalaryChosen(((SalaryCard)cardsDrawn.get(1)).getSalaryString()); // Set new salary
			
			boardGUI.setReadyToMove();
		}
		
		// User ignores Job Search
		if (e.getActionCommand().equals("Reject")) {
			boardGUI.setReadyToMove();
		}
		
		if (e.getActionCommand().equals("BUY HOUSE")) {
			MainGame.executeBuyHouse(boardGUI.getChosenHouseType());
			boardGUI.setReadyToMove();
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
				int magentaID = MagentaSpaces.getMagentaID(MainGame.getCurrentPlayer().getPlayerLocation());
				
				switch (magentaID) {
				case 0 : { // College Career Choice
					cardsDrawn = MainGame.executeCollegeCareerChoice_DrawCareer(); // Draw 2 Career cards
					boardGUI.setMagentaSpaceLanded_CollegeCareerChoice_Career(cardsDrawn); // Show the career cards drawn, allow user to choose one
				} break;
				case 1 : { // Job Search
					cardsDrawn = MainGame.executeJobSearch();
					boardGUI.setMagentaSpaceLanded_JobSearch(cardsDrawn);
				} break;
				case 2 : { // Buy House
					boardGUI.setMagentaSpaceLanded_BuyHouse(MainGame.houseCards);
				} break;
				case 3 : { // Get Married
					boardGUI.setMagentaSpaceLanded_GetMarried();
				} break;
				case 4 : { // Baby
					int nVal = MainGame.executeBaby(); // Generate baby, collect money from other players
					boardGUI.setMagentaSpaceLanded_Baby(nVal); // Alert player baby events
				} break;
				case 5 : { // Graduate
					MainGame.executeGraduate();
					boardGUI.setMagentaSpaceLanded_Graduated();
				} break;
				case 6 : { // Conjunction
					boardGUI.setMagentaSpaceLanded_Junction();
				} break;
				default : System.out.println("Magenta coordinate not recognized.");
				}			
			} break;
			case 3 : { // Player landed on blue space
				String message = MainGame.executeBlueCard();
				boardGUI.setBlueSpaceLanded(message);
			} break;
			case 4 : { // Green Space
				int spaceID = MainGame.executeGreenSpace();
				boardGUI.setGreenSpaceLanded(spaceID);
			} break;
			default : { // End space
				 MainGame.retire();
				 boardGUI.setReadyToHandoverTurn();
			}
			}
		}

	}
}
