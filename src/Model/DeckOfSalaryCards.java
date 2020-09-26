package Model;

import java.util.*;

/**
 * Class for deck of salary cards. There are a total of 10 salary cards.
 */
public class DeckOfSalaryCards {
	private static ArrayList<SalaryCard> salaryCards; // 10 salary cards
	private static int head;
	int i;
		
	/**
	 * Constructor for deck of salary cards.
	 */
	public DeckOfSalaryCards() {
		head = 9;
		salaryCards = new ArrayList<SalaryCard>();
		generateDeck();
	}
	
	/**
	 * Generates the deck of salary cards.
	 */
	public void generateDeck() {
		for (i = 1; i <= 10; i++)
			salaryCards.add(new SalaryCard(i));
		Collections.shuffle(salaryCards);
	}
	
	
	/**
	 * Returns the current top most card pointed by the head.
	 * @return
	 */
	public static SalaryCard top() {
		return salaryCards.get(head);
	}
	
	/**
	 * Returns the topmost card and shifts the head (decrement). 
	 * If the head is 0, return the last card, then automatically shuffle the deck and reset the head value to 9.
	 * @return the topmost card
	 */
	public static SalaryCard pop() { // return the top most card, then shift head to next card.
		SalaryCard temp = top();
		if(head > 0) { // Deck is NOT about to become empty
			head--;
		}
		else {	// Deck is about to become empty. Shuffle deck and reset head value.
			System.out.println("DECK RAN OUT! Cards to be automatically reshuffled at NEXT turn.");
			Collections.shuffle(salaryCards);
			head = 9;	
		}
		
		return temp;
	
	// TODO: generate deck of salary cards
	}
	
	/**
	 * Get a salary card with given salary
	 * @param salary salary of the card to find
	 * @return salary card found
	 */
	public static SalaryCard getCard(String salary) {
		for (SalaryCard e : salaryCards)
			if (e.getSalary() == Double.parseDouble(salary))
				return e;
		return null;
	}
	
	public static void shuffle () {
		Collections.shuffle(salaryCards);
		head = 9;
	}
}
