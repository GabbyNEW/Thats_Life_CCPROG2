package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;

import Model.MainGame;
import javafx.scene.layout.Border;

public class BoardGUI {
	public static boolean testArg = false;
	private JFrame frame;
	private JPanel panel, panelNorth, panelSouth, panelSouthWest, panelSouthEast;
	public BoardArtPanel panelMid;
	
	private JLabel player1Title, player2Title, player3Title;
	private JLabel[] balance, loan, interest, tax, salary, payRaise;
	
	// South-West
	private JLabel careerCard, salaryCard, blueCard, actionCard;
	private ImageIcon careerCardFront, careerCardBack, salaryCardFront, salaryCardBack,
						blueCardFront, blueCardBack, actionCardFront, actionCardBack;
	private JComboBox<String> chooseHouseBox, chooseStartPathBox;
	private JButton buyHouseButton, startButton, returCardButton, drawCardButton;
	
	// South-East
	private JButton collectButton, payButton, confirmCardButton, changeCareerButton, changePathButton, payLoanButton, proceedButton;
	private JLabel chooseCollectFromLabel, choosePayToLabel, messageLabel;
	private JComboBox<String> chooseCollectFromBox, choosePayToBox, chooseCardBox, choosePathBox;

	public BoardGUI() {
		frame = new JFrame();
		frame.setSize(1336, 900);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the state of the frame maximized
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setName("That's Life!");
		
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
		
		panelSouthWest = new JPanel(null);
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
		balance[0].setText("Balance: $" + "200000.00");
		panelNorth.add(balance[0], gridConstraints);
		gridConstraints.gridy = 2;
		loan[0].setText("Loan: $" + "20000.00");
		panelNorth.add(loan[0], gridConstraints);
		gridConstraints.gridy = 3;
		interest[0].setText("Interest: $" + "5000.00");
		panelNorth.add(interest[0], gridConstraints);
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 1;
		tax[0].setText("Tax: $" + "3000.00");
		panelNorth.add(tax[0], gridConstraints);
		gridConstraints.gridy = 2;
		salary[0].setText("Salary: $" + "30000.00");
		panelNorth.add(salary[0], gridConstraints);
		gridConstraints.gridy = 3;
		payRaise[0].setText("Pay Raises: " + "2" + " / " + "5");
		panelNorth.add(payRaise[0], gridConstraints);
		
		gridConstraints.gridx = 3;
		gridConstraints.gridy = 1;
		balance[1].setText("Balance: $" + "200000.00");
		panelNorth.add(balance[1], gridConstraints);
		gridConstraints.gridy = 2;
		loan[1].setText("Loan: $" + "20000.00");
		panelNorth.add(loan[1], gridConstraints);
		gridConstraints.gridy = 3;
		interest[1].setText("Interest: $" + "5000.00");
		panelNorth.add(interest[1], gridConstraints);
		gridConstraints.gridx = 5;
		gridConstraints.gridy = 1;
		tax[1].setText("Tax: $" + "3000.00");
		panelNorth.add(tax[1], gridConstraints);
		gridConstraints.gridy = 2;
		salary[1].setText("Salary: $" + "30000.00");
		panelNorth.add(salary[1], gridConstraints);
		gridConstraints.gridy = 3;
		payRaise[1].setText("Pay Raises: " + "2" + " / " + "5");
		panelNorth.add(payRaise[1], gridConstraints);
		
		gridConstraints.gridx = 6;
		gridConstraints.gridy = 1;
		balance[2].setText("Balance: $" + "200000.00");
		panelNorth.add(balance[2], gridConstraints);
		gridConstraints.gridy = 2;
		loan[2].setText("Loan: $" + "20000.00");
		panelNorth.add(loan[2], gridConstraints);
		gridConstraints.gridy = 3;
		interest[2].setText("Interest: $" + "5000.00");
		panelNorth.add(interest[2], gridConstraints);
		gridConstraints.gridx = 8;
		gridConstraints.gridy = 1;
		tax[2].setText("Tax: $" + "3000.00");
		panelNorth.add(tax[2], gridConstraints);
		gridConstraints.gridy = 2;
		salary[2].setText("Salary: $" + "30000.00");
		panelNorth.add(salary[2], gridConstraints);
		gridConstraints.gridy = 3;
		payRaise[2].setText("Pay Raises: " + "2" + " / " + "5");
		panelNorth.add(payRaise[2], gridConstraints);
		
	}
	
	private void setupPanelSouthWest() {
		careerCardBack = scaleImage(new ImageIcon("purpleBack.png"), 150, 150);
		careerCardFront = scaleImage(new ImageIcon("purpleFront.jpg"), 150, 150);
		careerCard = new JLabel();
		careerCard.setBounds(25, 10, 150, 150);
		careerCard.setIcon(careerCardFront); 
		panelSouthWest.add(careerCard);
		
		salaryCardBack = scaleImage(new ImageIcon("purpleBack.png"), 150, 150);
		salaryCardFront = scaleImage(new ImageIcon("purpleFront.jpg"), 150, 150);
		salaryCard = new JLabel();
		salaryCard.setBounds(150, 10, 150, 150); // x spacing is 125
		salaryCard.setIcon(salaryCardBack);
		panelSouthWest.add(salaryCard);

		blueCardBack = scaleImage(new ImageIcon("blueBack.png"), 150, 150);
		blueCardFront = scaleImage(new ImageIcon("blueFront.jpg"), 150, 150);
		blueCard = new JLabel();
		blueCard.setBounds(275, 10, 150, 150);
		blueCard.setIcon(blueCardBack);
		panelSouthWest.add(blueCard);
		
		actionCardBack = scaleImage(new ImageIcon("orangeBack.png"), 150, 150);
		actionCardFront = scaleImage(new ImageIcon("orangeFront.jpeg"), 150, 150);
		actionCard = new JLabel();
		actionCard.setBounds(400, 10, 150, 150);
		actionCard.setIcon(actionCardBack);
		panelSouthWest.add(actionCard);
		
		chooseHouseBox = new JComboBox<String>(); 
		chooseHouseBox.addItem("SHACK");
		chooseHouseBox.addItem("SINGLE-STOREY HOME");
		chooseHouseBox.addItem("CONTRY COTTAGE");
		chooseHouseBox.addItem("DUTCH COLONIAL");
		chooseHouseBox.addItem("BEACH HOUSE");
		chooseHouseBox.addItem("FARMHOUSE");
		chooseHouseBox.addItem("TURDOR");
		chooseHouseBox.addItem("VICTORIAN");
		chooseHouseBox.setBounds(525, 50, 155, 20);
		panelSouthWest.add(chooseHouseBox);
		
		buyHouseButton = new JButton("BUY HOUSE");
		buyHouseButton.setBounds(525, 100, 155, 20);
		panelSouthWest.add(buyHouseButton);
		
		chooseStartPathBox = new JComboBox<String>(); // ok so let each players choose their path before start of game ok?
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
		chooseCardBox.addItem("MERRRY CHRISTMAS"); // placeholder; dynamically add selections based on drawn cards a player needs to choose
		chooseCardBox.setBounds(300, 20, 175, 20);
		panelSouthEast.add(chooseCardBox);
		
		confirmCardButton = new JButton("Confirm Card");
		confirmCardButton.setBounds(300, 60, 175, 20);
		panelSouthEast.add(confirmCardButton);
		
		changeCareerButton = new JButton("Change Career");
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
		
		messageLabel = new JLabel("<html>MESSAGE DISPLAY<br/>Uses HTML for newline</html>");
		messageLabel.setVerticalAlignment(SwingConstants.TOP);
		messageLabel.setHorizontalAlignment(SwingConstants.LEADING);
		messageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageLabel.setBounds(700, 20, 250, 100);
		panelSouthEast.add(messageLabel);
		
		proceedButton = new JButton("Random Number! & Move!"); // dynamically change label based on context
		proceedButton.setBounds(700, 125, 250, 45);
		panelSouthEast.add(proceedButton);
	}
	
	private void instantiateControlPanelNorth() {
		player1Title = new JLabel("Player 1");
		player1Title.setForeground(Color.RED);
		player2Title = new JLabel("Player 2");
		player2Title.setForeground(Color.CYAN);
		player3Title = new JLabel("Player 3");
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
	
	public void setListener(ActionListener listener) {
		startButton.addActionListener(listener);
	}
	
	// thanks stackoverflow https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
	   public ImageIcon scaleImage(ImageIcon icon, int w, int h) {
	        int nw = icon.getIconWidth();
	        int nh = icon.getIconHeight();

	        if(icon.getIconWidth() > w)
	        {
	          nw = w;
	          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
	        }

	        if(nh > h)
	        {
	          nh = h;
	          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
	        }

	        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
	    }
}
