package Model;

import java.util.*;

public class SalaryCard extends Cards {

	private double salary;
	private double taxDue;
	
	/**
	 * Creates a salary card based on a randomly generated number (1-10)
	 * @param num random number
	 */
	public SalaryCard(int num) {
		salary = num * 10000.00;
		taxDue = num * 1000.00;
	}

	/**
	 * Simply assign the card's values to the player.
	 * @param player the player to assign salary and tax due to.
	 */
	public void toDoAction(Player player) {
		player.setSalary(salary);
		player.setTaxDue(taxDue);
	}
	
	/**
	 * 	Provides the string representation of the card's salary and tax values. 
	 */
	@Override
	public String toString() {
		return "Salary: $" + Double.toString(salary) + "\nTax Due: $" + Double.toString(taxDue);
	}
	
	public double getSalary() {
		return salary;
	}
	
	public double getTaxDue() {
		return taxDue;
	}

}
