package Model;

import java.util.ArrayList;
import java.util.Random;

public class GreenSpaces extends Spaces {
	
	private Random rand; 
	
	/**
	 * 
	 * @param x x coordinate of the space at the board
	 * @param y y coordinate of the space at the board
	 * @param spaceID the spaceID for the space to be assigned to<P>
	 * 0 - Pay Day<P>
	 * 1 - Pay Raise
	 */
	public GreenSpaces() {
		generateGreenCoordinates();
		rand = new Random();
	}
	
	public void generateGreenCoordinates() {
		int i;
		for (i = 0; i < GreenSpacesCoordinates.length; i++)
			GreenSpacesCoordinates[i] = new int[2];
			// X									// Y	
		GreenSpacesCoordinates[0][0] = 12; GreenSpacesCoordinates[0][1] = 14; // Pay Day
		GreenSpacesCoordinates[1][0] = 5; GreenSpacesCoordinates[1][1] = 16; // Pay Raise
		GreenSpacesCoordinates[2][0] = 9; GreenSpacesCoordinates[2][1] = 16; // Pay Raise
		GreenSpacesCoordinates[3][0] = 7; GreenSpacesCoordinates[3][1] = 19; // Pay Day
	}
	
	/**
	 * 	Returns a spaceID<P>
	 * 	0 - Pay Day<P>
	 * 	1 - Pay Raise
	 * @param e the coordinate of the space
	 * @return spaceID value
	 */
	public static int getSpaceID(int[] e) {
		 if ((e[0] == 12 && e[1] == 14) || (e[0] == 7 && e[1] == 19)) // Pay Day
			 return 0;
		 else if ((e[0] == 5 && e[1] == 16) || (e[0] == 9 && e[1] == 16)) // Pay Day
			 return 1;
		 else
			 return -1;
	}
	
	public void doAction(Player player, int spaceID) {
		
		switch(spaceID) {
			case 0 : { // Pay Day
				if (!(player.hasReachedMaxPayraise())) {
					player.addMoneyBalance(player.getSalary());
					player.incrementCurrentPayRaise();
				}
				
			} break;
			case 1 : { // Pay Raise, salary raise of up to $50000.00
				if (!(player.hasReachedMaxPayraise())) {
					int num = rand.nextInt(6);
					double amount = 10000.00 * num;
					
					player.setSalary(player.getSalary() + amount);
					player.addMoneyBalance(player.getSalary());
					player.setTaxDue(player.getTaxDue() + 2000.00);
				}
			} break;
		}
		
	}
	
	
}
