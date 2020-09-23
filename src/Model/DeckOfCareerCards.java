package Model;

import java.util.*;

public class DeckOfCareerCards {
	private static ArrayList<CareerCard> careerCards;
	private static int head;
	private int i;
	
	public DeckOfCareerCards() {
		careerCards = new ArrayList<CareerCard>();
		head = 6;
		generateDeck();
	}
	
	public void generateDeck() {
		// generate deck of career cards
		for (i = 0; i < 7; i++)
		{
			careerCards.add(new CareerCard (i));
			careerCards.get(i).assignDescription(i);
		}	 
		Collections.shuffle(careerCards);	// Shuffle after generating decks
	}
	
	public static CareerCard top () 
	{
		return careerCards.get(head);
	}
	
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
	
	public static void shuffle () {
		Collections.shuffle(careerCards);
		head = 6;
	}
	
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
	 * @param deck the deck to show all cards
	 */
	public static void displayDeck() {
		for(CareerCard card : careerCards) // probably what you see on the terminal
			System.out.println(card.getMainID() + " - " + card.getTypeOfCard()
			+ " - Pay Raise generated: "+ card.getPayRaise());
	}
	
}
