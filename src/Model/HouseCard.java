package Model;

import java.util.*;

public class HouseCard extends Cards{
	private String houseType;
	private double price;
	
	private Player owner;
	private boolean hasOwner;
	
	public HouseCard(int mainID) {
		this.mainID = mainID;
		hasOwner = false;
		assignDescriptions();
	}
	
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
	
	public void setOwner(Player player) {
		owner = player;
		hasOwner = true;
	}
	
	public boolean hasOwner() {
		return hasOwner;
	}
	
	@Override
	public boolean equals(Object obj) {
		String name = (String) obj;
		return name.contentEquals(houseType);
	}
}


