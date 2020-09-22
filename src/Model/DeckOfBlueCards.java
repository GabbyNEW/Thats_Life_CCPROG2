package Model;

import java.util.*;

public class DeckOfBlueCards {
	private static ArrayList<BlueCard> blueCards;	
	private static int head;
	int i;
	
	public DeckOfBlueCards() {
		blueCards = new ArrayList<BlueCard>();
		generateDeck();
		
		head = 6;
	}
	
	private void generateDeck() {
		// generate deck of blue cards
		for (i = 0; i < 7; i++)
		{
			blueCards.add(new BlueCard (i));
			blueCards.get(i).assignDescription(i);
		}
		Collections.shuffle(blueCards);	// Shuffle after generating decks
	}
	
	public static BlueCard top ()
	{
		return blueCards.get(head);
	}
	
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
