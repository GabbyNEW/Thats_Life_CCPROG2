public class Player {
	private int playerNumber; // From 0 - 3
	private double moneyBalance;
	private double moneyLoan;
	private double moneyLoanInterest;
	
	private double salary;
	private int chosenPath;
	private String career;
	
	private boolean degree;
	private int numberOfOffsprings;
	
	private int currentSpace; // For the player's current position on the board. Not yet used since I still have to learn javafx and MVC architecture.	
	
	public Player(int playerNumber) {
		this.playerNumber = playerNumber;
		this.moneyBalance = 200000.00;
	}
	
	public Player(int playerNumber, double startingMoney) {
		this.playerNumber = playerNumber;
		this.moneyBalance = startingMoney;
	}
	
	public void addMoneyBalance(double amount) {
		this.moneyBalance += amount;
	}
	
	public void addLoan() {
		moneyLoan += 20000.00;
		moneyBalance += 20000.00;
		moneyLoanInterest += 5000.00;
		// Multiple instances of this may be printed if more loans are needed.
		// This does not specify which player has a loan added. This may have to be improved upon.
		System.out.println("Warning! A loan added to a player. Make sure to pay it back.");
		System.out.println("Updated loan and interest for a player total is now " + (this.moneyLoan + this.moneyLoanInterest));
	}
	
	public void reduceMoneyBalance(double amount) {
		while (moneyBalance - amount < 0) // keep on borrowing money until there is enough balance to reduce amount to
			addLoan();
		moneyBalance -= amount;
	}
	
	public boolean payLoan() { // Pay off a player's entire loan
		if (moneyBalance - (moneyLoan + moneyLoanInterest) < 0) { // Not enough money to pay loan
			return false;
		} 
		else {
			moneyBalance -= (moneyLoan + moneyLoanInterest);
			moneyLoan = 0;
			moneyLoanInterest = 0;
			return true;
		}
	}
	
	public void setCareer(String career) {
		this.career = career;
	}
	
	public void setLoan(double amount) { // This is ONLY for -cl (custom loan) argument
		this.moneyLoan = amount;
	}
	
	public String getCareer() {
		return this.career;
	}
	
	public double getMoneyBalance() {
		return this.moneyBalance;
	}
	
	public double getMoneyLoan() {
		return this.moneyLoan;
	}
	
	public double getMoneyInterest() {
		return this.moneyLoanInterest;
	}
	
	public boolean hasDegree() {
		return this.degree;
	}
}
