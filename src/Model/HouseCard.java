package Model;

import java.util.*;

/**
 * Class of house cards.
 */
public class HouseCard extends Cards{
	private String houseType;
	private double price;
	
	private Player owner;
	private boolean hasOwner;

	/**
	 * Constructor for house card
	 * @param mainID
	 */
	public HouseCard(int mainID) {
		this.mainID = mainID;
		hasOwner = false;
		assignDescriptions();
	}
	
	/**
	 * Assign the description of the card
	 */
	public void assignDescriptions() {
		switch (mainID) {
			case 0 : {
				houseType = "Split-Level";
				price = 40000.00;
			}
			break;
			case 1 : {
				houseType = "Mobile Home";
				price = 60000.00;
			}
			break;
			case 2 : {
				houseType = "Log Cabin";
				price = 80000.00;
			}
			break;
			case 3 : {
				houseType = "Country Cottage";
				price = 100000.00;
			} 
			break;
			case 4 : {
				houseType = "Dutch Colonial";
				price = 120000.00;
			}
			break;
			case 5 : {
				houseType = "Beach House";
				price = 140000.00;
			}
			break;
			case 6 : {
				houseType = "Farmhouse";
				price = 160000.00;
			}
			break;
			case 7 : {
				houseType = "Tudor";
				price = 180000.00;
			}
			break;
			case 8 : {
				houseType = "Victorian";
				price = 200000.00;
			}
			break;
		}
	}
	
	@Override
	/**
	 * Gets the string representation of a house card
	 */
	public String toString() {
		switch (mainID) {
		case 0 : return "Split-Level | $40K";
		case 1 : return "Mobile Home | $60K";
		case 2 : return "Log Cabin | $80K";
		case 3 : return "Country Cottage | $100K";
		case 4 : return "Dutch Colonial | $120K";
		case 5 : return "Beach House | $140K";
		case 6 : return "Farmhouse | $160K";
		case 7 : return "Tudor | $180K";
		case 8 : return "Victorian | $200K";
		default : return "";
		}
	}
	
	/**
	 * Set the house card's owner
	 * @param player player who buys the house
	 */
	public void setOwner(Player player) {
		owner = player;
		hasOwner = true;
	}
	
	/**
	 * Returns if house card has an owner
	 * @return owner status
	 */
	public boolean hasOwner() {
		return hasOwner;
	}
	
	/**
	 * Get the house type of the house card.
	 * @return house type String value
	 */
	public String getHouseType() {
		return houseType;
	}
	
	/**
	 * Gets the price of a house card
	 * @return price of the house card
	 */
	public double getPrice() {
		return price;
	}
	
	@Override
	/**
	 * Check if the given house type is equal to the card's house type.
	 */
	public boolean equals(Object obj) { 
		String name = (String) obj; // Compare the house type String to the card
		return name.contentEquals(houseType);
	}
}


