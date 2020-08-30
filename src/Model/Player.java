package Model;
/**
 *	The player class includes all the important attributes of a player and methods that can directly modify a player's status during the game.
 * 	This includes a unique player number, current money balance, current loan & interest, 
 * 	chosen career and path, and the current box space that the player is currently occupying.
 */
public class Player {
	private int playerNumber; // 1 - 3
	private double moneyBalance;
	private double moneyLoan;
	private double moneyLoanInterest;
	
	private double salary;
	private int lifePath; // 0 - Career path, 1 - College path 
	private String career; // CHANGE to CareerCard data type
	
	private boolean degree;
	private int numberOfOffsprings;
	
	private int currentSpace; // For the player's current position on the board. Not yet used since I still have to learn javafx and MVC architecture.	
	
	/**
	 * 	This constructor creates a player with a provided playerNumber. 
	 * 	The playerNumber is automatically generated and can range from 1-3
	 * 
	 * 	@param playerNumber player number (1-3)
	 */
	public Player(int playerNumber) {
		this.playerNumber = playerNumber;
		this.moneyBalance = 200000.00;
	}
	
	/**
	 * 	This constructor creates a player with a provided playerNumber and starting money.
	 * 	This constructor is only used for -cm (custom money) argument  
	 * 	The playerNumber is automatically generated and can range from 1-3 while the starting money is user-specified (this affects all players)
	 * 
	 * 	@param playerNumber player number (1-3)
	 * 	@param startingMoney user-specified starting money
	 */
	public Player(int playerNumber, double startingMoney) {
		this.playerNumber = playerNumber;
		this.moneyBalance = startingMoney;
	}
	
	/**
	 * 	Accesses the player's current money balance and increases it.
	 * 	@param amount the amount to increase.
	 */
	public void addMoneyBalance(double amount) {
		this.moneyBalance += amount;
	}
	
	/**
	 * 	This method adds player loan by $20,000 and interest by $5,000.
	 * 	It also displays a notice on the terminal that a loan has been added.
	 * 	However it does not explicitly state in the display to whom player has been added a loan to. This may need to be improved during phase 2 development.
	 */
	public void addLoan() {
		moneyLoan += 20000.00;
		moneyBalance += 20000.00;
		moneyLoanInterest += 5000.00;
		// Multiple instances of this may be printed if more loans are needed.
		// This does not specify which player has a loan added. This may have to be improved upon.
		System.out.println("Warning! A loan added to a player. Make sure to pay it back.");
		System.out.println("Updated loan and interest for a player total is now " + (this.moneyLoan + this.moneyLoanInterest));
	}
	
	/**
	 * Accesses the player's current money balance and reduces it.
	 * @param amount the amountt to reduce
	 */
	public void reduceMoneyBalance(double amount) {
		while (moneyBalance - amount < 0) // keep on borrowing money until there is enough balance to reduce amount to
			addLoan();
		moneyBalance -= amount;
	}
	
	/**
	 * This method allows the player to pay off his loan.
	 * Upon success, set moneyLoan and moneyInterest to 0 
	 * and update the current moneyBalance of the player.
	 * @return true or false if player was able to pay off loan
	 */
	public boolean payLoan() { // Pay off a player's entire loan
		if (moneyBalance - (moneyLoan + moneyLoanInterest) < 0) { // Not enough money to pay loan
			return false;
		} 
		else { // Enough money to pay loan
			moneyBalance -= (moneyLoan + moneyLoanInterest);
			moneyLoan = 0;
			moneyLoanInterest = 0;
			return true;
		}
	}
	
	/**
	 * This method may be called before the start of the game. It sets a given player's career.
	 * @param career chosen career
	 */
	public void setCareer(String career) {
		this.career = career;
	}
	
	/**
	 * This method is only for setting loan to each player at the start of the game.
	 * Only available with -cl (custom loan) argument.
	 * @param amount the amount of loan to set to each players at the start of the game
	 */
	public void setLoan(double amount) { // This is ONLY for -cl (custom loan) argument
		this.moneyLoan = amount;
	}
	
	/**
	 * Returns the String representation of a player's career.
	 * Note that this will change during phase 2 development as the career should not be a String.
	 * @return String value of a player' career
	 */
	public String getCareer() {
		return this.career;
	}
	
	/**
	 * Returns the current money balance of a player.
	 * @return current money balance 
	 */
	public double getMoneyBalance() {
		return this.moneyBalance;
	}
	
	/**
	 * Returns the current loan of a player
	 * @return current loan
	 */
	public double getMoneyLoan() {
		return this.moneyLoan;
	}
	
	/**
	 * Returns the loan interest of a player
	 * @return current loan interest
	 */
	public double getMoneyInterest() {
		return this.moneyLoanInterest;
	}
	
	/**
	 * Checks if a player has a college degree.
	 * @return true if degree is present, false otherwise
	 */
	public boolean hasDegree() {
		return this.degree;
	}
}
