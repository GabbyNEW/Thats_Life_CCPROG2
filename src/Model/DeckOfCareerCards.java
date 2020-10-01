package Model;

import java.util.*;

/**
 * Class for deck of career cards. There are a total of 7 career cards. 
 */
public class DeckOfCareerCards {
	private static ArrayList<CareerCard> careerCards;
	private static int head;
	private int i;
	
	public DeckOfCareerCards() {
		careerCards = new ArrayList<CareerCard>();
		head = 6;
		generateDeck();
	}
	
	/**
	 * Generates career cards.
	 */
	public void generateDeck() {
		// generate deck of career cards
		for (i = 0; i < 7; i++)
		{
			careerCards.add(new CareerCard (i));
			careerCards.get(i).assignDescription(i);
		}	 
		Collections.shuffle(careerCards);	// Shuffle after generating decks
	}
	
	/** 
	 * Access the top most card.
	 * @return top most career card.
	 */
	public static CareerCard top () 
	{
		return careerCards.get(head);
	}
	
	/**
	 * Get the top most card.
	 * @return top most career card
	 */
	public static CareerCard pop () 
	{
		CareerCard temp = top();
		if (head > 0)
			head--;
		else
		{
			Collections.shuffle(careerCards);
			head = 6;
		}
		
		return temp;
	}
	
	/**
	 * Shuffle the deck.
	 */
	public static void shuffle () {
		Collections.shuffle(careerCards);
		head = 6;
	}
	
	/**
	 * Unassign the career card from the player.
	 * @param typeOfCard
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static void revokeOwnership(String typeOfCard) {
		for (CareerCard e : careerCards)
			if (e.equals(typeOfCard)) {
				e.setHasOwner(false);
				System.out.println(e.getTypeOfCard() + ": Revoked ownership");
			}
		
		shuffle();
	}
	
	/**
	 * Display on the terminal all cards generated on a given deck.
	 */
	public static void displayDeck() {
		for(CareerCard card : careerCards) // probably what you see on the terminal
			System.out.println(card.getMainID() + " - " + card.getTypeOfCard()
			+ " - Pay Raise generated: "+ card.getPayRaise());
	}
	
}
