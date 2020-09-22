package Model;

import java.util.ArrayList;
import java.util.Random;

public class MagentaSpaces extends Spaces {
	
	private static Random rand;

	public MagentaSpaces() {
		generateMagentaCoordinates();
		
		rand = new Random();
	}
	
	private void generateMagentaCoordinates() {
		int i;
		for (i = 0; i < MagentaSpacesCoordinates.length; i++)
			MagentaSpacesCoordinates[i] = new int[2];
				// X									// Y
		MagentaSpacesCoordinates[0][0] = 3;	MagentaSpacesCoordinates[0][1] = 7; // College Career Choice
		MagentaSpacesCoordinates[1][0] = 5;	MagentaSpacesCoordinates[1][1] = 7; // Conjunction
		MagentaSpacesCoordinates[2][0] = 2;	MagentaSpacesCoordinates[2][1] = 11; // Graduate
		
		MagentaSpacesCoordinates[3][0] = 14; MagentaSpacesCoordinates[3][1] = 5; // Baby
		MagentaSpacesCoordinates[4][0] = 12; MagentaSpacesCoordinates[4][1] = 6; // Buy House
		MagentaSpacesCoordinates[5][0] = 9; MagentaSpacesCoordinates[5][1] = 12; // Get Married
		MagentaSpacesCoordinates[6][0] = 1; MagentaSpacesCoordinates[6][1] = 16; // Search Job
		MagentaSpacesCoordinates[7][0] = 8; MagentaSpacesCoordinates[7][1] = 3; // Get Married (2)
	}
	
	/**
	 * 	Get an ID for each Magenta space.<P>
	 *  0 - College Career Choice<P>
	 *  1 - Job Search
	 *  2 - Buy a House
	 *  3 - Get Married
	 *  4 - Have Baby
	 *  5 - Graduate 
	 *  6 - Conjunction
	 */
	@SuppressWarnings("unused")
	public static int getMagentaID(int[] e) {
		if (e[0] == 3 && e[1] == 7)
			return 0; // College Career Choice
		else if (e[0] == 5 && e[1] == 7)
			return 6; // Conjunction
		else if (e[0] == 2 && e[1] == 11)
			return 5; // Graduate
		else if (e[0] == 14 && e[1] == 5)
			return 4; // Baby
		else if (e[0] == 12 && e[1] == 6)
			return 2; // Buy House
		else if ((e[0] == 9 && e[1] == 12) || (e[0] == 8 && e[1] == 3))
			return 3; // Get Married
		else if (e[0] == 1 && e[1] == 16)
			return 1; // Job Search
		else
			 return -1;
	}
	
	public static CareerCard[] draw2CareerCards() { // necessary for College Career Choice magenta space, don't forget to reshuffle
		CareerCard[] temp = new CareerCard[2];
		temp[0] = DeckOfCareerCards.pop();
		temp[1] = DeckOfCareerCards.pop();
		return temp;
	}
	
	public static SalaryCard[] draw2SalaryCards() { // necessary for College Career Choice magenta spacem, don't forget to reshuffle
		SalaryCard[] temp = new SalaryCard[2];
		temp[0] = DeckOfSalaryCards.pop();
		temp[1] = DeckOfSalaryCards.pop();
		return temp;
	}
	
	/**
	 * Set user to be married
	 * @param players all players
	 * @param turn current turn
	 */
	public static int getMarried(Player[] players, int turn) {
		if (!(players[turn].isMarried())) { // Set player married if single
			int number = rand.nextInt(11);
			double amount = (number % 2 == 0) ? 10000.00 : 5000.00;
			double amountCompensate = 0;
			players[turn].setMarried(true);
			
			for (Player e : players)
				if (e.getPlayerNumber() != turn) {
					e.reduceMoneyBalance(amount);
					amountCompensate += amount;
				}
			
			players[turn].addMoneyBalance(amountCompensate);
			System.out.println("Player" + (turn + 1) + " set married.");
			
			return number;
		} // Player is already married, reject Get Married event
		else {
			return -1;
		}
	}
	
	public static void haveBaby(Player[] players, int turn) {
		int number = rand.nextInt();
		int numberOfOffsprings = (number % 2 == 0) ? 1 : 2; // 1 if even, 2 if odd
		double amount = 5000.00 * numberOfOffsprings;
		double amountCompensate = 0;
		
		for (Player e : players)
			if (e.getPlayerNumber() != turn) {
				e.reduceMoneyBalance(amount);
				amountCompensate += amount;
			}
		
		players[turn].addMoneyBalance(amountCompensate);
	}
}
