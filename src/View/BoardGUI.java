package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

import Model.ActionCard;
import Model.Cards;
import Model.HouseCard;
import Model.MainGame;
import Model.Player;
import Model.SalaryCard;

/**
 * This class is responsible for the board game GUI. 
 */
public class BoardGUI {

	private JFrame frame;
	private JPanel panel, panelNorth, panelSouth, panelSouthWest, panelSouthEast;
	public BoardArtPanel panelMid; // Center

	// North
	private JLabel player1Title, player2Title, player3Title;
	private JLabel[] balance, loan, interest, tax, salary, payRaise;

	// South-West
	public JLabel careerCard, salaryCard, blueCard, actionCard;
	public ImageIcon careerCardFront, careerCardBack, salaryCardFront, salaryCardBack, blueCardFront, blueCardBack,
			actionCardFront, actionCardBack;
	public JComboBox<String> chooseHouseBox, chooseStartPathBox;
	public JButton buyHouseButton, startButton, returCardButton, drawCardButton;

	// South-East
	public JButton collectButton, payButton, confirmCardButton, changeCareerButton, changePathButton, payLoanButton,
			proceedButton;
	private JLabel chooseCollectFromLabel, choosePayToLabel, messageLabel;
	public JComboBox<String> chooseCollectFromBox, choosePayToBox, chooseCardBox, choosePathBox;

	public BoardGUI() {
		frame = new JFrame();
		frame.setSize(1336, 900);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the state of the frame maximized
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("That's Life!");

		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);

		panelNorth = new JPanel(new GridBagLayout());
		panelNorth.setBackground(new Color(227, 166, 166));
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.weightx = 0.2;
		gridConstraints.weighty = 0.2;
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.ipady = 20;
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.gridwidth = GridBagConstraints.REMAINDER;
		setupPanelNorth();
		panel.add(panelNorth, gridConstraints);

		panelMid = new BoardArtPanel();
		panelMid.setBackground(new Color(231, 232, 181));
		gridConstraints.gridy = 1;
		gridConstraints.ipady = 500;
		panel.add(panelMid, gridConstraints);

		panelSouth = new JPanel(new GridBagLayout());
		gridConstraints.gridy = 2;
		gridConstraints.ipady = 20;
		panel.add(panelSouth, gridConstraints);

		panelSouthWest = new JPanel();
		panelSouthWest.setLayout(null);
		setupPanelSouthWest();
		gridConstraints.gridwidth = 1;
		panelSouth.add(panelSouthWest, gridConstraints);

		panelSouthEast = new JPanel(null);
		gridConstraints.gridx = 1;
		panelSouth.add(panelSouthEast, gridConstraints);
		setupPanelSouthEast();

		frame.add(panel);
		frame.revalidate();
	}

	private void setupPanelNorth() {
		Player[] tempPlayers = MainGame.getPlayers();

		instantiateControlPanelNorth();
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.fill = GridBagConstraints.BOTH;
		gridConstraints.weightx = 0.5;
		gridConstraints.weighty = 0.5;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		panelNorth.add(player1Title, gridConstraints);
		gridConstraints.gridx = 4;
		panelNorth.add(player2Title, gridConstraints);
		gridConstraints.gridx = 7;
		panelNorth.add(player3Title, gridConstraints);

		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		balance[0].setText("Balance: $" + tempPlayers[0].getMoneyBalance());
		panelNorth.add(balance[0], gridConstraints);
		gridConstraints.gridy = 2;
		loan[0].setText("Loan: $" + tempPlayers[0].getMoneyLoan());
		panelNorth.add(loan[0], gridConstraints);
		gridConstraints.gridy = 3;
		interest[0].setText("Interest: $" + tempPlayers[0].getMoneyInterest());
		panelNorth.add(interest[0], gridConstraints);
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 1;
		tax[0].setText("Tax: $" + tempPlayers[0].getTaxDue());
		panelNorth.add(tax[0], gridConstraints);
		gridConstraints.gridy = 2;
		salary[0].setText("Salary: $" + tempPlayers[0].getSalary());
		panelNorth.add(salary[0], gridConstraints);
		gridConstraints.gridy = 3;
		payRaise[0].setText(
				"Pay Raises: " + tempPlayers[0].getCurrentPayRaise() + " / " + tempPlayers[0].getMaxPayRaise());
		panelNorth.add(payRaise[0], gridConstraints);

		gridConstraints.gridx = 3;
		gridConstraints.gridy = 1;
		balance[1].setText("Balance: $" + tempPlayers[1].getMoneyBalance());
		panelNorth.add(balance[1], gridConstraints);
		gridConstraints.gridy = 2;
		loan[1].setText("Loan: $" + tempPlayers[1].getMoneyLoan());
		panelNorth.add(loan[1], gridConstraints);
		gridConstraints.gridy = 3;
		interest[1].setText("Interest: $" + tempPlayers[1].getMoneyInterest());
		panelNorth.add(interest[1], gridConstraints);
		gridConstraints.gridx = 5;
		gridConstraints.gridy = 1;
		tax[1].setText("Tax: $" + tempPlayers[1].getTaxDue());
		panelNorth.add(tax[1], gridConstraints);
		gridConstraints.gridy = 2;
		salary[1].setText("Salary: $" + tempPlayers[1].getSalary());
		panelNorth.add(salary[1], gridConstraints);
		gridConstraints.gridy = 3;
		payRaise[1].setText(
				"Pay Raises: " + tempPlayers[1].getCurrentPayRaise() + " / " + tempPlayers[1].getMaxPayRaise());
		panelNorth.add(payRaise[1], gridConstraints);

		if (MainGame.getNumberofplayersingame() == 3) {
			gridConstraints.gridx = 6;
			gridConstraints.gridy = 1;
			balance[2].setText("Balance: $" + tempPlayers[2].getMoneyBalance());
			panelNorth.add(balance[2], gridConstraints);
			gridConstraints.gridy = 2;
			loan[2].setText("Loan: $" + tempPlayers[2].getMoneyLoan());
			panelNorth.add(loan[2], gridConstraints);
			gridConstraints.gridy = 3;
			interest[2].setText("Interest: $" + tempPlayers[2].getMoneyInterest());
			panelNorth.add(interest[2], gridConstraints);
			gridConstraints.gridx = 8;
			gridConstraints.gridy = 1;
			tax[2].setText("Tax: $" + tempPlayers[2].getTaxDue());
			panelNorth.add(tax[2], gridConstraints);
			gridConstraints.gridy = 2;
			salary[2].setText("Salary: $" + tempPlayers[2].getSalary());
			panelNorth.add(salary[2], gridConstraints);
			gridConstraints.gridy = 3;
			payRaise[2].setText(
					"Pay Raises: " + tempPlayers[2].getCurrentPayRaise() + " / " + tempPlayers[2].getMaxPayRaise());
			panelNorth.add(payRaise[2], gridConstraints);
		}

	}

	private void setupPanelSouthWest() {
		careerCardBack = scaleImage(new ImageIcon("purpleBack.png"), 150, 150);
		careerCardFront = scaleImage(new ImageIcon("purpleFront.jpg"), 150, 150);
		careerCard = new JLabel();
		careerCard.setBounds(25, 10, 105, 150);
		careerCard.setIcon(careerCardBack);
		panelSouthWest.add(careerCard);

		salaryCardBack = scaleImage(new ImageIcon("purpleBack.png"), 150, 150);
		salaryCardFront = scaleImage(new ImageIcon("purpleFront.jpg"), 150, 150);
		salaryCard = new JLabel();
		salaryCard.setBounds(150, 10, 105, 150); // x spacing is 125
		salaryCard.setIcon(salaryCardBack);
		panelSouthWest.add(salaryCard);

		blueCardBack = scaleImage(new ImageIcon("blueBack.png"), 150, 150);
		blueCardFront = scaleImage(new ImageIcon("blueFront.jpg"), 150, 150);
		blueCard = new JLabel();
		blueCard.setBounds(275, 10, 105, 150);
		blueCard.setIcon(blueCardBack);
		panelSouthWest.add(blueCard);

		actionCardBack = scaleImage(new ImageIcon("orangeBack.png"), 150, 150);
		actionCardFront = scaleImage(new ImageIcon("orangeFront.jpeg"), 150, 150);
		actionCard = new JLabel();
		actionCard.setBounds(400, 10, 105, 150);
		actionCard.setIcon(actionCardBack);
		panelSouthWest.add(actionCard);

		chooseHouseBox = new JComboBox<String>();
		chooseHouseBox.addItem("Split-Level");
		chooseHouseBox.addItem("Mobile Home");
		chooseHouseBox.addItem("Log Cabin");
		chooseHouseBox.addItem("Country Cottage");
		chooseHouseBox.addItem("Dutch Colonial");
		chooseHouseBox.addItem("Beach House");
		chooseHouseBox.addItem("Farmhouse");
		chooseHouseBox.addItem("Tudor");
		chooseHouseBox.addItem("Victorian");
		chooseHouseBox.setBounds(525, 50, 155, 20);
		panelSouthWest.add(chooseHouseBox);

		buyHouseButton = new JButton("BUY HOUSE");
		buyHouseButton.setBounds(525, 100, 155, 20);
		panelSouthWest.add(buyHouseButton);

		chooseStartPathBox = new JComboBox<String>(); // ok so let each players choose their path before start of game
														// ok?
		chooseStartPathBox.addItem("START a CAREER");
		chooseStartPathBox.addItem("START COLLEGE");
		chooseStartPathBox.setBounds(700, 50, 155, 20);
		panelSouthWest.add(chooseStartPathBox);

		startButton = new JButton("START");
		startButton.setBounds(700, 100, 155, 20);
		panelSouthWest.add(startButton);
	}

	private void setupPanelSouthEast() {

		chooseCollectFromLabel = new JLabel("Collect from player");
		chooseCollectFromLabel.setBounds(0, 15, 150, 20);
		panelSouthEast.add(chooseCollectFromLabel);

		chooseCollectFromBox = new JComboBox<String>();
		chooseCollectFromBox.addItem("1"); // placeholder; dynamically add selections based on current turn
		chooseCollectFromBox.setBounds(15, 60, 100, 20);
		panelSouthEast.add(chooseCollectFromBox);

		collectButton = new JButton("Collect");
		collectButton.setBounds(15, 110, 100, 20);
		panelSouthEast.add(collectButton);

		choosePayToLabel = new JLabel("Pay to player");
		choosePayToLabel.setBounds(160, 15, 150, 20); // x separated by
		panelSouthEast.add(choosePayToLabel);

		choosePayToBox = new JComboBox<String>();
		choosePayToBox.addItem("1"); // placeholder; dynamically add selections based on current turn
		choosePayToBox.setBounds(160, 60, 100, 20);
		panelSouthEast.add(choosePayToBox);

		payButton = new JButton("Pay");
		payButton.setBounds(160, 110, 100, 20);
		panelSouthEast.add(payButton);

		chooseCardBox = new JComboBox<String>();
		chooseCardBox.addItem("MERRRY CHRISTMAS"); // placeholder; dynamically add selections based on drawn cards a
													// player needs to choose
		chooseCardBox.setBounds(300, 20, 175, 20);
		panelSouthEast.add(chooseCardBox);

		confirmCardButton = new JButton("Confirm Card");
		confirmCardButton.setBounds(300, 60, 175, 20);
		panelSouthEast.add(confirmCardButton);

		changeCareerButton = new JButton("New Career & Salary");
		changeCareerButton.setBounds(300, 110, 175, 20);
		panelSouthEast.add(changeCareerButton);

		choosePathBox = new JComboBox<String>();
		choosePathBox.addItem("Change Career Path");
		choosePathBox.addItem("Start Family Path");
		choosePathBox.setBounds(510, 20, 175, 20);
		panelSouthEast.add(choosePathBox);

		changePathButton = new JButton("Change Path");
		changePathButton.setBounds(510, 60, 175, 20);
		panelSouthEast.add(changePathButton);

		payLoanButton = new JButton("Pay Loan");
		payLoanButton.setBounds(510, 110, 175, 20);
		panelSouthEast.add(payLoanButton);

		messageLabel = new JLabel("<html></html>"); // <html>MESSAGE DISPLAY<br/>Uses HTML for newline</html>
		messageLabel.setVerticalAlignment(SwingConstants.TOP);
		messageLabel.setHorizontalAlignment(SwingConstants.LEADING);
		messageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageLabel.setBounds(700, 20, 250, 100);
		panelSouthEast.add(messageLabel);

		proceedButton = new JButton("Get a Turn & Move!"); // dynamically change label based on context
		proceedButton.setBounds(700, 125, 250, 45);
		panelSouthEast.add(proceedButton);
	}

	private void instantiateControlPanelNorth() {
		player1Title = new JLabel("Player 1: ");
		player1Title.setForeground(Color.RED);
		player2Title = new JLabel("Player 2: ");
		player2Title.setForeground(Color.CYAN);
		player3Title = new JLabel("Player 3: ");
		player3Title.setForeground(Color.YELLOW);

		balance = new JLabel[3];
		loan = new JLabel[3];
		interest = new JLabel[3];
		tax = new JLabel[3];
		salary = new JLabel[3];
		payRaise = new JLabel[3];
		for (int i = 0; i < 3; i++) {
			balance[i] = new JLabel();
			loan[i] = new JLabel();
			interest[i] = new JLabel();
			tax[i] = new JLabel();
			salary[i] = new JLabel();
			payRaise[i] = new JLabel();
		}
	}

	/**
	 * Refreshes the player stats view.
	 */
	public void updatePlayerStats() {
		Player[] tempPlayers = MainGame.getPlayers();
		player1Title.setText("Player 1: " + tempPlayers[0].getCareer());
		player2Title.setText("Player 2: " + tempPlayers[1].getCareer());
		if (tempPlayers.length == 3) {
			player3Title.setText("Player 3: " + tempPlayers[2].getCareer());
		}

		balance[0].setText("Balance: $" + tempPlayers[0].getMoneyBalance());
		loan[0].setText("Loan: $" + tempPlayers[0].getMoneyLoan());
		interest[0].setText("Interest: $" + tempPlayers[0].getMoneyInterest());
		tax[0].setText("Tax: $" + tempPlayers[0].getTaxDue());
		salary[0].setText("Salary: $" + tempPlayers[0].getSalary());
		payRaise[0].setText(
				"Pay Raises: " + tempPlayers[0].getCurrentPayRaise() + " / " + tempPlayers[0].getMaxPayRaise());

		balance[1].setText("Balance: $" + tempPlayers[1].getMoneyBalance());
		loan[1].setText("Loan: $" + tempPlayers[1].getMoneyLoan());
		interest[1].setText("Interest: $" + tempPlayers[1].getMoneyInterest());
		tax[1].setText("Tax: $" + tempPlayers[1].getTaxDue());
		salary[1].setText("Salary: $" + tempPlayers[1].getSalary());
		payRaise[1].setText(
				"Pay Raises: " + tempPlayers[1].getCurrentPayRaise() + " / " + tempPlayers[1].getMaxPayRaise());

		if (MainGame.getNumberofplayersingame() == 3) {
			balance[2].setText("Balance: $" + tempPlayers[2].getMoneyBalance());
			loan[2].setText("Loan: $" + tempPlayers[2].getMoneyLoan());
			interest[2].setText("Interest: $" + tempPlayers[2].getMoneyInterest());
			tax[2].setText("Tax: $" + tempPlayers[2].getTaxDue());
			salary[2].setText("Salary: $" + tempPlayers[2].getSalary());
			payRaise[2].setText(
					"Pay Raises: " + tempPlayers[2].getCurrentPayRaise() + " / " + tempPlayers[2].getMaxPayRaise());
		}

	}

	public void setLoanPaid() {
		updatePlayerStats();
		payLoanButton.setEnabled(false);
		updatePlayerStats();
	}

	/**
	 * Executes if a player has not yet chosen a life path before start of the
	 * player's first round.
	 */
	public void setChooseStartPath() {
		// SW
		careerCard.setIcon(careerCardBack);
		salaryCard.setIcon(salaryCardBack);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(true);
		startButton.setEnabled(true);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);
		payLoanButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Choose your starting path.</html>");
		proceedButton.setEnabled(false);
	}

	/**
	 * Player chose Career path (before Junction), show Career and Salary cards,
	 * allow player to throw dice.
	 * 
	 * @param cards
	 */
	public void setCareerStartPath(ArrayList<Cards> cards) {
		updatePlayerStats();

		// SW
		careerCard.setIcon(careerCardBack);
		salaryCard.setIcon(salaryCardBack);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);
		if (MainGame.getCurrentPlayer().getMoneyLoan() != 0)
			payLoanButton.setEnabled(true);
		else
			payLoanButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Career card:"
				+ cards.get(0).getTypeOfCard() + "<br/>" + "Salary: " + ((SalaryCard) cards.get(1)).getSalary()
				+ "<br/>Tax Due: " + ((SalaryCard) cards.get(1)).getTaxDue() + "<br/><br/>Throw a \"Dice\"!</html>");

		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(true);
	}

	public void setCollegeStartPath() {
		updatePlayerStats();
		// SW
		careerCard.setIcon(careerCardBack);
		salaryCard.setIcon(salaryCardBack);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);
		if (MainGame.getCurrentPlayer().getMoneyLoan() != 0)
			payLoanButton.setEnabled(true);
		else
			payLoanButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + "<br/>Borrowed $40000 from Bank."
				+ "<br/><br/>Throw a \"Dice\"!</html>");
		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(true);
	}

	/**
	 * Allows the user to "throw a dice".
	 */
	public void setReadyToMove() {
		updatePlayerStats();
		// SW
		careerCard.setIcon(careerCardBack);
		salaryCard.setIcon(salaryCardBack);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		confirmCardButton.setText("Confirm Card");
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);
		if (MainGame.getCurrentPlayer().getMoneyLoan() != 0)
			payLoanButton.setEnabled(true);
		else
			payLoanButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Throw a \"Dice\"!</html>");
		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(true);
	}

	/**
	 * Allows the player to hand over the next turn
	 */
	public void setReadyToHandoverTurn() {
		updatePlayerStats();
		// SW
		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);

		proceedButton.setText("Next Turn!");
		proceedButton.setEnabled(true);
	}

	public void setBlankSpaceLanded() {
		// SW
		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Landed on a Blank Space.</html>");
		proceedButton.setText("Next Turn!");
		proceedButton.setEnabled(true);
	}

	public void setOrangeSpaceLanded(ActionCard cardDrawn) {
		updatePlayerStats();

		// SW
		actionCard.setIcon(actionCardFront);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		if (cardDrawn.getToDoInstruction().equals("Choose A Player, then Pay $30000")) { // Allow player to choose
																							// someone to pay
			int i;
			chooseCollectFromBox.setEnabled(false);
			collectButton.setEnabled(false);
			choosePayToBox.setEnabled(true);
			choosePayToBox.removeAllItems();
			for (i = 1; i <= MainGame.getNumberofplayersingame(); i++)
				if (i - 1 != MainGame.getTurn())
					choosePayToBox.addItem(Integer.toString(i));

			payButton.setEnabled(true);

			proceedButton.setEnabled(false);
		}

		else if (cardDrawn.getToDoInstruction().equals("Choose a player, then collect $30000")) { // Allow player to
																									// choose someone to
																									// pay
			int i;
			chooseCollectFromBox.setEnabled(true);
			chooseCollectFromBox.removeAllItems();
			for (i = 1; i <= MainGame.getNumberofplayersingame(); i++)
				if (i - 1 != MainGame.getTurn())
					chooseCollectFromBox.addItem(Integer.toString(i));

			collectButton.setEnabled(true);
			choosePayToBox.setEnabled(false);
			payButton.setEnabled(false);
			proceedButton.setEnabled(false);
		}

		else {
			chooseCollectFromBox.setEnabled(false);
			collectButton.setEnabled(false);
			choosePayToBox.setEnabled(false);
			payButton.setEnabled(false);
			proceedButton.setEnabled(true);
		}

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Action Card drawn:<br/>"
				+ cardDrawn.getTypeOfCard() + "<br/>" + cardDrawn.getToDoInstruction() + "</html>");
		proceedButton.setText("Next Turn!");

	}

	/**
	 * Inform player is graduated. Allow player to throw dice.
	 */
	public void setMagentaSpaceLanded_Graduated() {
		// SW
		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Graduated!<br/> </html>");
		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(true);
	}

	/**
	 * Allow player to generate number and get married.
	 */
	public void setMagentaSpaceLanded_GetMarried() {
		// SW
		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Landed on Get Married space.<br/>"
				+ "Generate a number (odd or even) and<br/> Get Married!" + "</html>");
		proceedButton.setText("Generate Number & Get Married!");
		proceedButton.setEnabled(true);
	}

	/**
	 * Show generated number and inform if player set married is successful or
	 * rejected. Player must have already generated a number beforehand upon landing
	 * on a Get Married space. Then, allow user to throw dice again.
	 * 
	 * @param generatedNumber number generated
	 */
	public void setMagentaSpaceLanded_GetMarried(int generatedNumber) {
		updatePlayerStats();

		// SW
		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);

		if (generatedNumber != -1 && generatedNumber % 2 != 0) { // Player successfully set to be married (odd)
			messageLabel.setText(
					"<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Married!<br/>" + "Generated number is odd: "
							+ generatedNumber + "<br/>Collected $5000 from other players." + "</html>");
		} else if (generatedNumber != -1 && generatedNumber % 2 == 0) { // Player successfully set to be married (even)
			messageLabel.setText(
					"<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Married!<br/>" + "Generated number is even: "
							+ generatedNumber + "<br/>Collected $10000 from other players." + "</html>");
		} else // Player is already married (get married event rejected)
			messageLabel
					.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Cannot marry again!<br/>" + "</html>");

		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(true);
	}

	/**
	 * Show career cards drawn, allow user to choose career card (College Career
	 * Choice magenta event)
	 * 
	 * @param cardsDrawn [0] & [1] - Career cards
	 */
	public void setMagentaSpaceLanded_CollegeCareerChoice_Career(ArrayList<Cards> cardsDrawn) {
		// SW
		careerCard.setIcon(careerCardFront);
		salaryCard.setIcon(salaryCardBack);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(true);
		chooseCardBox.removeAllItems();
		chooseCardBox.addItem(cardsDrawn.get(0).getTypeOfCard());
		chooseCardBox.addItem(cardsDrawn.get(1).getTypeOfCard());
		confirmCardButton.setEnabled(true);
		confirmCardButton.setText("Confirm Career Card");
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Career cards:<br/>1. "
				+ cardsDrawn.get(0).getTypeOfCard() + "<br/>2. " + cardsDrawn.get(1).getTypeOfCard() + "</html>");
		proceedButton.setText("Next Turn!");
		proceedButton.setEnabled(false);
	}

	/**
	 * Show salary cards drawn, allow user to choose Salary Card (College Career
	 * Choice magenta event)
	 * 
	 * @param cardsDrawn [0] & [1] - Salary cards
	 */
	public void setMagentaSpaceLanded_CollegeCareerChoice_Salary(ArrayList<Cards> cardsDrawn) {
		updatePlayerStats();
		// SW
		careerCard.setIcon(careerCardBack);
		salaryCard.setIcon(salaryCardFront);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(true);
		chooseCardBox.removeAllItems();
		chooseCardBox.addItem(((SalaryCard) cardsDrawn.get(0)).getSalaryString());
		chooseCardBox.addItem(((SalaryCard) cardsDrawn.get(1)).getSalaryString());
		confirmCardButton.setEnabled(true);
		confirmCardButton.setText("Confirm Salary Card");
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Salary cards income:<br/>1. "
				+ ((SalaryCard) cardsDrawn.get(0)).getSalaryString() + "<br/>2. "
				+ ((SalaryCard) cardsDrawn.get(1)).getSalaryString() + "</html>");
		proceedButton.setText("Next Turn!");
		proceedButton.setEnabled(false);
	}

	public void setMagentaSpaceLanded_Junction() {
		updatePlayerStats();
		// SW
		careerCard.setIcon(careerCardBack);
		salaryCard.setIcon(salaryCardBack);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(true);
		changePathButton.setEnabled(true);
		if (MainGame.getCurrentPlayer().getMoneyLoan() != 0)
			payLoanButton.setEnabled(true);
		else
			payLoanButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Choose a New Path!</html>");
		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(false);
	}

	/**
	 * Allow user to confirm or reject change of career and salary (Job Search
	 * event)
	 * 
	 * @param cardsDrawn the cards drawn upon landing on Job Search
	 */
	public void setMagentaSpaceLanded_JobSearch(ArrayList<Cards> cardsDrawn) {
		updatePlayerStats();
		// SW
		careerCard.setIcon(careerCardFront);
		salaryCard.setIcon(salaryCardFront);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(true);
		confirmCardButton.setText("Reject");
		changeCareerButton.setEnabled(true);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);
		if (MainGame.getCurrentPlayer().getMoneyLoan() != 0)
			payLoanButton.setEnabled(true);
		else
			payLoanButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>" + "Career drawn: "
				+ cardsDrawn.get(0).getTypeOfCard() + "<br/>Salary: " + ((SalaryCard) cardsDrawn.get(1)).getSalary()
				+ "<br/>Switch to these cards?" + "</html>");
		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(false);
	}

	public void setMagentaSpaceLanded_BuyHouse(ArrayList<HouseCard> houseCards) {
		updatePlayerStats();
		// SW
		careerCard.setIcon(careerCardBack);
		salaryCard.setIcon(salaryCardBack);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(true); // enable house box, remove selections with ownership
		for (HouseCard e : houseCards)
			if (e.hasOwner())
				chooseHouseBox.removeItem(e.getHouseType());
		buyHouseButton.setEnabled(true);

		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		confirmCardButton.setText("Confirm Card");
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);
		if (MainGame.getCurrentPlayer().getMoneyLoan() != 0)
			payLoanButton.setEnabled(true);
		else
			payLoanButton.setEnabled(false);

		messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1) + ":<br/>Buy a House!</html>");
		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(false);
	}

	public void setMagentaSpaceLanded_Baby(int nVal) {
		updatePlayerStats();
		// SW
		careerCard.setIcon(careerCardBack);
		salaryCard.setIcon(salaryCardBack);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);

		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		confirmCardButton.setText("Confirm Card");
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);
		if (MainGame.getCurrentPlayer().getMoneyLoan() != 0)
			payLoanButton.setEnabled(true);
		else
			payLoanButton.setEnabled(false);

		if (nVal % 2 == 0) // Even, player had twins
			messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1)
					+ ":<br/>Have Twins! Collected $10000.00<br/> from other players!</html>");
		else
			messageLabel.setText("<html>Player " + (MainGame.getTurn() + 1)
					+ ":<br/>Have Baby! Collected $5000.00<br/> from other players!</html>");

		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(true);
	}

	public void setBlueSpaceLanded(String message) {
		updatePlayerStats();
		// SW
		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);

		messageLabel.setText("<html>" + message + "</html>");
		proceedButton.setText("Next Turn!");
		proceedButton.setEnabled(true);
	}

	public void setGreenSpaceLanded(int spaceID) {
		updatePlayerStats();
		// SW
		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);
		String message = (spaceID == 0) ? "Collected salary from bank." : "Pay Raise landed.";
		messageLabel.setText("<html>" + message + "</html>");
		proceedButton.setText("Next Turn!");
		proceedButton.setEnabled(true);
	}

	public void endGame(int playerNumberWinner) {
		updatePlayerStats();
		// SW
		careerCard.setIcon(careerCardBack);
		salaryCard.setIcon(salaryCardBack);
		blueCard.setIcon(blueCardBack);
		actionCard.setIcon(actionCardBack);

		chooseHouseBox.setEnabled(false);
		buyHouseButton.setEnabled(false);
		chooseStartPathBox.setEnabled(false);
		startButton.setEnabled(false);

		// SE
		chooseCollectFromBox.setEnabled(false);
		collectButton.setEnabled(false);
		choosePayToBox.setEnabled(false);
		payButton.setEnabled(false);

		chooseCardBox.setEnabled(false);
		confirmCardButton.setEnabled(false);
		changeCareerButton.setEnabled(false);
		choosePathBox.setEnabled(false);
		changePathButton.setEnabled(false);
		payLoanButton.setEnabled(false);

		if (playerNumberWinner == -1) // Tie
			messageLabel.setText("<html>END GAME! TIE! Too BAD!!</html>");
		else
			messageLabel.setText("<html>END GAME! Winner:</br>" + "Player " + (playerNumberWinner + 1) + "!</html>");
		proceedButton.setText("Get a Turn & Move!");
		proceedButton.setEnabled(false);
	}

	public void setListener(ActionListener listener) {
		chooseHouseBox.addActionListener(listener);
		chooseStartPathBox.addActionListener(listener);
		startButton.addActionListener(listener);
		buyHouseButton.addActionListener(listener);

		chooseCollectFromBox.addActionListener(listener);
		choosePayToBox.addActionListener(listener);
		chooseCardBox.addActionListener(listener);
		choosePathBox.addActionListener(listener);
		collectButton.addActionListener(listener);
		payButton.addActionListener(listener);
		confirmCardButton.addActionListener(listener);
		changeCareerButton.addActionListener(listener);
		changePathButton.addActionListener(listener);
		payLoanButton.addActionListener(listener);
		proceedButton.addActionListener(listener);
	}

	/* 
	 * Mr. Polywhir, October 2015. 
	 * Retrieved from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon 
	 */
	public ImageIcon scaleImage(ImageIcon icon, int w, int h) {
		int nw = icon.getIconWidth();
		int nh = icon.getIconHeight();

		if (icon.getIconWidth() > w) {
			nw = w;
			nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
		}

		if (nh > h) {
			nh = h;
			nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
		}

		return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
	}

	/**
	 * Get chosen starting path that the player has selected in the dropdown box.
	 * 
	 * @return the chosen starting path (0 - Career, 1 - College)
	 */
	public int getChosenStartingPath() {
		if (chooseStartPathBox.getSelectedIndex() == 0) // Career
			return 1;
		else // College
			return 2;
	}

	/**
	 * Get chosen junction path that the player has selected in the dropdown box
	 * 
	 * @return chosen junction path (3 - Career, 4 - Family)
	 */
	public int getChosenJunctionPath() {
		if (choosePathBox.getSelectedIndex() == 0) // Career
			return 3;
		else // Family
			return 4;
	}

	/**
	 * Get chosen player from the selection boxes (XOR only one JComboBox must be
	 * active on the SE side).
	 * 
	 * @return selected player number (n-1)
	 */
	public int getChosenPlayer() {
		if (choosePayToBox.isEnabled())
			return Integer.parseInt((String) choosePayToBox.getSelectedItem());
		else
			return Integer.parseInt((String) chooseCollectFromBox.getSelectedItem());
	}

	/**
	 * Get chosen career card
	 * 
	 * @return the name of the career card (type of card)
	 */
	public String getChosenCareerCard() {
		return (String) chooseCardBox.getSelectedItem();
	}

	/**
	 * Get the chosen salary card
	 * 
	 * @return the String representation of the salary amount of the chosen salary
	 *         card
	 */
	public String getChosenSalaryCard() {
		return (String) chooseCardBox.getSelectedItem();
	}

	public String getChosenHouseType() {
		return (String) chooseHouseBox.getSelectedItem();
	}
}
