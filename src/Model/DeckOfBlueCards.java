package Model;

import java.util.*;

/**
 * Class for the deck of blue cards
 */
public class DeckOfBlueCards {
	private static ArrayList<BlueCard> blueCards;	
	private static int head;
	int i;
	
	/**
	 * Generate deck of blue cards when this constructor is called.
	 */
	public DeckOfBlueCards() {
		blueCards = new ArrayList<BlueCard>();
		generateDeck();
		
		head = 6;
	}
	
	/**
	 * This creates seven blue cards.
	 */
	private void generateDeck() {
		// generate deck of blue cards
		for (i = 0; i < 7; i++)
		{
			blueCards.add(new BlueCard (i));
			blueCards.get(i).assignDescription(i);
		}
		Collections.shuffle(blueCards);	// Shuffle after generating decks
	}
	
	/**
	 * Allow access to the topmost blue card
	 * @return
	 */
	public static BlueCard top ()
	{
		return blueCards.get(head);
	}
	
	/**
	 * Get the top most card
	 * @return
	 */
	public static BlueCard pop ()
	{
		BlueCard temp = top();
		
		if(head > 0)
			head--;
		else
		{
			Collections.shuffle(blueCards);
			head = 6;
		}
		
		return temp;
	}
	
	/**
	 * Display on the terminal all cards generated on a given deck.
	 * @param deck the deck to show all cards
	 */
	public static void displayDeck() {
			System.out.println("System: Showing all generated BLUE CARDS:");
			for (BlueCard card : blueCards)
				System.out.print(card.getTypeOfCard() + ", ");
			System.out.println("\n");
	}
	
}
