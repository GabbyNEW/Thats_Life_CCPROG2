package Model;

import java.util.Random;

public abstract class Cards {
	protected int mainID, subID;
	protected String typeOfCard;
	protected String description;
	protected String toDoAction;
	
	/**
	 * This randomly generates a subID for an Action Card based on its mainID (see below)<P>  
	 * If ID = 0 (Collect from bank) -> subID 0 - Tax refund, 1 - Sell an Item, 2 - Bonus Payday, 3 - Setup school <P>
	 * If ID = 1 (Pay the Bank) -> subID 0 - Buy an Item, 1 - Visit a Place, 2 - Hiking, 3 - Watch a Show, 4 - Win a Competiton, 5 - Traffic Violation<P>
	 * If ID = 2 (Pay the Player) -> subID 0 - Lawsuit (choose a Player), 1 - Christmas Bonus (pay all players) <P>
	 * If ID = 3 (Collect from Player)-> subID 0 - File a Lawsuit (choose a player), 1 - It's Your Birthday (collect from all players) <P>
	 */
	public void generateSubID() {
		Random generateRandom = new Random();
		
		switch (mainID) {
		case 0 : subID = generateRandom.nextInt(5); break; // Collect from Bank
		case 1 : subID = generateRandom.nextInt(6); break; // Pay the Bank
		case 2 : subID = generateRandom.nextInt(2); break; // Pay the Player
		case 3 : subID = generateRandom.nextInt(2); break; // Collect From Player
		}
	}
	
	/**
	 * Returns the card's mainID value
	 * @return mainID value of an action card
	 */
	public int getMainID() {
		return this.mainID;
	}
	
	/**
	 * Returns the card's subID value. Some types of cards do not need a subID.
	 * @return subID value of an action card.
	 */
	public int getSubID() {
		return this.subID;
	}
	
	/**
	 * Returns the string representation of an action card's type (name).
	 * @return String representation of the type of the action card
	 */
	public String getTypeOfCard() {
		return this.typeOfCard;
	}

	public String getToDoInstruction() {
		return this.toDoAction;
	}
	
	/**
	 * Provides the string value of the card name its description, and instruction.
	 * @return the string value  of the card name its description, and instruction.
	 */
	@Override
	public String toString() {
		return this.typeOfCard + "\n" + this.description + " | " + this.toDoAction + "\n";
	}
}
