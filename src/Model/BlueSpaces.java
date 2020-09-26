package Model;

import java.util.Random;

/**
 * Class of blue spaces.
 */
public class BlueSpaces extends Spaces {

	/**
	 * Constructor for blue space.
	 */
	public BlueSpaces() {
		generateBlueCoordinates();
	}
	
	/**
	 * 	Assigns space ID to each blue spaces.<P>
	 * 	0 - Player collects money from Bank
	 * 	1 - Pay a Player
	 * 	2 - Pay bank
	 */
	public static int getSpaceID() {
		Random rand = new Random();
		
		return rand.nextInt(3);
	}
	
	/**
	 * Generates the coordinates of blue spaces.
	 */
	public void generateBlueCoordinates() {
		int i;
		for (i = 0; i < BlueSpacesCoordinates.length; i++)
			BlueSpacesCoordinates[i] = new int[2];
			// X									// Y	
		BlueSpacesCoordinates[0][0] = 12; BlueSpacesCoordinates[0][1] = 9;
		BlueSpacesCoordinates[1][0] = 10; BlueSpacesCoordinates[1][1] = 14;
	}
}
