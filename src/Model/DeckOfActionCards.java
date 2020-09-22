package Model;
import java.util.*;

public class DeckOfActionCards {
	protected static ArrayList<ActionCard> actionCards;
	private static int head;
	int i;
	
	public DeckOfActionCards() {
		actionCards = new ArrayList<ActionCard>();
		generateDeck();
		head = 49;
	}
	
	/**
	 *	Generates deck of action cards. 
	 */
	private void generateDeck() {
		// generate deck of action cards
		for (i = 0; i < 20; i++) {	// 20/50
			actionCards.add(new ActionCard(0)); // create cards with ID 0 (collect from bank)
			actionCards.get(i).generateSubID(); 
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		for (i = 20; i < 40; i++) {	//20/50
			actionCards.add(new ActionCard(1)); // create cards with ID 1 (pay the bank)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		for (i = 40; i < 45; i++) {	// 5/50
			actionCards.add(new ActionCard(2)); // ID 2 (pay the player)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		for (i = 45; i < 50; i++) {	//5/50
			actionCards.add(new ActionCard(3)); // ID 3 (collect from player)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).getMainID());
		}
		Collections.shuffle(actionCards); // Shuffle after generating decks
	}
	
	/**
	 * Returns the topmost card (pointed by the head).
	 * @return the topmost card
	 */
	public static ActionCard top() {
		return actionCards.get(head);
	}
	
	/**
	 * Returns the topmost card and shifts the head (decrement). 
	 * If the head is 0, return the last card, then automatically shuffle the deck and reset the head value to 49. 
	 * @return the topmost card
	 */
	public static ActionCard pop() { // return the top most card, then shift head to next card.
		ActionCard temp = top();
		if(head > 0) { // Deck is NOT about to become empty
			head--;
		}
		else {	// Deck is about to become empty. Shuffle deck and reset head value.
			System.out.println("DECK RAN OUT! Cards to be automatically reshuffled at NEXT turn.");
			Collections.shuffle(actionCards);
			head = 49;	
		}
		
		return temp;
	}
	
	/**
	 * Display on the terminal all cards generated on a given deck.
	 * @param deck the deck to show all cards
	 */
	public static void displayDeck() {
			System.out.println("System: Showing all generated ACTION CARDS:");
			for (ActionCard card : actionCards)
				System.out.print(card.getTypeOfCard() + ", ");
			System.out.println("\n");
	}
	
}
