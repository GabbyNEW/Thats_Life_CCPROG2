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
	
	public void setNewPath(int path) {
		lifePath = path;
	}
	
	/**
	 * Executes when a player chooses College path as a starting life path. 
	 * @param amount the amount of loan to set to each players at the start of the game
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
	
	public void setTaxDue(double amount) {
		this.taxDue = amount;
	}
	
	public void setMarried(boolean status) {
		married = status;
	}
	
	public void setGraduate(boolean status) {
		degree = status;
	}
	
	public void setHouse(HouseCard houseCard) {
		houseCardOwned = houseCard;
	}
	
	public void setBabyAmount(int amount) {
		this.numberOfOffsprings = amount;
	}
	
	public void incrementCurrentPayRaise() {
		currentPayRaise++;
	}
	
	public void setMaxPayRaise(int amount) {
		maxPayRaise = amount;
	}
	
	public void setReachedEnd(boolean status) {
		endReached = status;
	}
	
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
	
	public int getLifePath() {
		return lifePath;
	}
	
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
	
	public double getSalary ()
	{
		return this.salary;
	}
	
	public double getTaxDue() {
		return this.taxDue;
	}
	
	public int getCurrentPayRaise() {
		return currentPayRaise;
	}
	
	public int getBabyAmount() {
		return this.numberOfOffsprings;
	}
	
	public int getMaxPayRaise() {
		return maxPayRaise;
	}
	
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
	
	public boolean isMarried() {
		return married;
	}
	
	public boolean hasReachedMaxPayraise() {
		return currentPayRaise > maxPayRaise;
	}
	
	public boolean hasReachedEndSpace() {
		return endReached;
	}

}
