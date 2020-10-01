package Model;
/**
 *	The player class includes all the important attributes of a player and methods that can directly modify a player's status during the game.
 * 	This includes a unique player number, current money balance, current loan & interest, 
 * 	chosen career and path, and the current box space that the player is currently occupying.
 */
public class Player {
	private int playerNumber; // 0 - 2
	private double moneyBalance;
	private double moneyLoan;
	private double moneyLoanInterest;
	
	private double salary;
	private double taxDue;
	private int lifePath; // 0 - NO chosen path yet; 1 - Career; 2 - College; 3 - Career (AFTER junction); 4 - Family
	private String career; // CHANGE to CareerCard data type
	
	private int currentPayRaise;
	private int maxPayRaise;
	
	private boolean degree;
	private boolean married;
	private HouseCard houseCardOwned;
	private int numberOfOffsprings;
	
	private int[] currentLocation; // [0] - x, [1] - y
	private boolean endReached; // To check if the player has already reached or has made it past the end space.
	
	/**
	 * 	This constructor creates a player with a provided playerNumber. 
	 * 	The playerNumber is automatically generated and can range from 1-3
	 * 
	 * 	@param playerNumber player number (1-3)
	 */
	public Player(int playerNumber) {
		this.playerNumber = playerNumber;
		this.moneyBalance = 200000.00;
		
		currentLocation = new int[2];
		currentLocation[0] = 0;
		currentLocation[1] = 0;
		career = "";
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
		System.out.println("Updated loan and interest for a player total is now " + (this.moneyLoan + this.moneyLoanInterest));
	}
	
	/**
	 * Accesses the player's current money balance and reduces it.
	 * @param amount the amount to reduce
	 */
	public void reduceMoneyBalance(double amount) {
		while (moneyBalance - amount < 0) // keep on borrowing money until there is enough balance to reduce amount to
			addLoan();
		moneyBalance -= amount;
	}
	
	/**
	 * This method allows the player to pay off his loan.
	 * @return if the payment successful or not
	 */
	public boolean payLoan() { // Pay off a player's entire loan
		if (moneyBalance - (moneyLoan + moneyLoanInterest) < 0)  // Not enough money to pay loan
			return false;

		while (moneyBalance - (moneyLoan + moneyLoanInterest) >= 0 && moneyLoan != 0 && moneyLoanInterest != 0) {
			moneyBalance -= 25000.00;
			moneyLoan -= 20000.00;
			moneyLoanInterest -= 5000.00;

		}
		System.out.println("Loan paid.");
		return true;
	}
	
	/**
	 * Assigns the player a new career and salary
	 * @param careerCard Career card
	 * @param salaryCard Salary card
	 */
	public void setNewCareer(CareerCard careerCard, SalaryCard salaryCard) {
		career = careerCard.getTypeOfCard();
		currentPayRaise = 0;
		maxPayRaise = careerCard.getPayRaise();
		salary = salaryCard.getSalary();
		taxDue = salaryCard.getTaxDue();
		
		System.out.println("Player " + playerNumber + " Career: " + career);
	}
	
	/**
	 * Assigns the player a new career
	 * @param careerCard Career card
	 */
	public void setNewCareer(CareerCard careerCard) {
		career = careerCard.getTypeOfCard();
		currentPayRaise = 0;
		maxPayRaise = careerCard.getPayRaise();
		
		System.out.println("Player " + playerNumber + " Career: " + career);
	}

	/**
	 * Assigns the player a new career and salary
	 * @param salaryCard Salary card
	 */
	public void setNewCareer(SalaryCard salaryCard) {
		salary = salaryCard.getSalary();
		taxDue = salaryCard.getTaxDue();
		
		System.out.println("Player " + playerNumber + " Salary: " + salary);
	}
	
	/**
	 * Sets a given player's career.
	 * @param career chosen career
	 */
	public void setCareer(String career) {
		this.career = career;
	}
	
	/**
	 * Sets the chosen path for a player.
	 * @param path path chosen: 0 - NO chosen path yet; 1 - Career; 2 - College; 3 - Career (AFTER junction); 4 - Family
	 */
	public void setNewPath(int path) {
		lifePath = path;
	}
	
	/**
	 * Executes when a player chooses College path as a starting life path. 
	 */
	public void setLoan() { 
		this.moneyLoan = 40000.00;
		this.moneyLoanInterest = 10000.00;
	}
	
	/**
	 * The amount to be added to the player's current balance.
	 * @param amount salary amount
	 */
	public void setSalary(double amount) {
		this.salary = amount;
	}
	
	/**
	 * Set the tax due for player
	 * @param amount tax due amount
	 */
	public void setTaxDue(double amount) {
		this.taxDue = amount;
	}
	
	/**
	 * Set player to be marrieed
	 * @param status true if the player is to be married, false otherwise
	 */
	public void setMarried(boolean status) {
		married = status;
	}
	
	/**
	 * Set player to be graduated
	 * @param status true if the player has attained a degree, false otherwise
	 */
	public void setGraduate(boolean status) {
		degree = status;
	}
	
	/**
	 * Assign a house card to player (player buys that house)
	 * @param houseCard House card to assign to player
	 */
	public void setHouse(HouseCard houseCard) {
		houseCardOwned = houseCard;
	}
	
	/**
	 * Sets the number of babies for a player (1 or 2)
	 * @param amount number of babies (1 or 2)
	 */
	public void setBabyAmount(int amount) {
		this.numberOfOffsprings = amount;
	}
	
	/**
	 * Increment pay raise everytime player lands on a pay raise green space
	 */
	public void incrementCurrentPayRaise() {
		currentPayRaise++;
	}
	
	/**
	 * Sets the maximum pay raise based on the career card's maximum pay raise
	 * @param amount maximum pay raise amount to assign to player
	 */
	public void setMaxPayRaise(int amount) {
		maxPayRaise = amount;
	}
	
	/**
	 * Set the end status if a player has reached the end space.
	 * @param status true if player has reached the end space, false otherwise
	 */
	public void setReachedEnd(boolean status) {
		endReached = status;
	}
	
	/**
	 * Updates the coordinates of the player. 
	 * @param coordinate int[] that contains the coordinates after moving the board [0]- x, [1] - y
	 */
	public void updateCurrentLocation(int[] coordinate) {
		currentLocation = coordinate;
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
	 * Returns the equivalent player number (0 - 2)
	 * @return player number from 0 to 2
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	/**
	 * Get the life path of the player.
	 * 0 - NO chosen path yet; 1 - Career; 2 - College; 3 - Career (AFTER junction); 4 - Family
	 * @return life path integer
	 */
	public int getLifePath() {
		return lifePath;
	}
	
	/**
	 * Gets the coordinate of the current location of the player
	 * @return the int[] coordinate of the current location [0] - x, [1] - y
	 */
	public int[] getPlayerLocation() {
		return currentLocation;
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
	 * Gets the current salary of the player
	 * @return current salary amount
	 */
	public double getSalary ()
	{
		return this.salary;
	}
	
	/**
	 * Gets the current tax due of the player
	 * @return tax due amount
	 */
	public double getTaxDue() {
		return this.taxDue;
	}
	
	/**
	 * Gets the current number of pay raises
	 * @return current number of pay raises
	 */
	public int getCurrentPayRaise() {
		return currentPayRaise;
	}
	
	/**
	 * Gets the number of babies the player has
	 * @return number of babies (0-2)
	 */
	public int getBabyAmount() {
		return this.numberOfOffsprings;
	}
	
	/**
	 * Gets the maximum pay raise a player can have
	 * @return maximum pay raises
	 */
	public int getMaxPayRaise() {
		return maxPayRaise;
	}
	
	/**
	 * Gets the current house card assigned to the plater
	 * @return current house card assigned
	 */
	public HouseCard getHouseCard() {
		return this.houseCardOwned;
	}
	
	/**
	 * Checks if a player has a college degree.
	 * @return true if degree is present, false otherwise
	 */
	public boolean hasDegree() {
		return this.degree;
	}
	
	/**
	 * Checks if a player is married
	 * @return true if married, false otherwise
	 */
	public boolean isMarried() {
		return married;
	}
	
	/**
	 * Check if the player has reached the maximum pay raise
	 * @return true if the player has reached the maximum pay raise, false otherwise
	 */
	public boolean hasReachedMaxPayraise() {
		return currentPayRaise > maxPayRaise;
	}
	
	/**
	 * Checks if the player has reached the end space located at [15,19]
	 * @return true if the player has reached the end space located at [15,19], false otherwise
	 */
	public boolean hasReachedEndSpace() {
		return endReached;
	}

}
