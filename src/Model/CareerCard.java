package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CareerCard extends Cards {
	
	private boolean DegreeRequired;
	private boolean hasOwner;
	
	private int payRaise;
	
	public CareerCard (int randomNumber)
	{
		mainID = randomNumber;
	}
	
	public void assignDescription (int mainID)
	{
		Random rand = new Random ();
		switch (mainID)
		{
			case 0 : 
			{
				typeOfCard = "LAWYER";
		//		description = "";
				DegreeRequired = true;
				payRaise = rand.nextInt(4) + 5;	//[5, 8]
				break;
			}
			case 1 :
			{
				typeOfCard = "ACCOUNTANT";
		//		description = "";
				DegreeRequired = true;
				payRaise = rand.nextInt(4) + 4;	//[4, 7]
				break;
			}
			case 2 :
			{
				typeOfCard = "COMPUTER CONSULTANT";
		//		description = "";
				DegreeRequired = true;
				payRaise = rand.nextInt(5) + 3;	//[3, 7]
				break;
			}
			case 3 :
			{
				typeOfCard = "DOCTOR";
		//		description = "";
				DegreeRequired = true;
				payRaise = rand.nextInt(4) + 5;	//[5, 8]
				break;
			}
			case 4 :
			{
				typeOfCard = "SERVER";
		//		description = "";
				DegreeRequired = false;
				payRaise = rand.nextInt(4) + 1;	// [1, 4]
				break;
			}
			case 5 :
			{
				typeOfCard = "RACECAR DRIVER";
		//		description = "";
				DegreeRequired = false;
				payRaise = rand.nextInt(7) + 2;	//[2, 8]
				break;
			}
			case 6 :
			{
				typeOfCard = "ATHLETE";
		//		description = "";
				DegreeRequired = false;
				payRaise = rand.nextInt(6) + 1;	//[1, 6]
				break;
			}

		}
	}
	
	
	public int getPayRaise ()
	{
		return this.payRaise;
	}
	
	public boolean isDegreeRequired ()
	{
		return this.DegreeRequired;
	}
	
	public void setHasOwner(boolean status) {
		hasOwner = status;
	}
	
	public boolean hasOwner() {
		return hasOwner;
	}
	
	@Override
	/**
	 * Checks if a given player's career (String) matches that of the card.
	 */
	public boolean equals (Object obj) {
		String careerToCompare = (String) obj;
		
		return careerToCompare.equals(typeOfCard);
	}
	
}
