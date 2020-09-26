package Model;

import java.util.ArrayList;

/**
 * Class of orange spaces 
 */
public class OrangeSpaces extends Spaces {
	
	/**
	 * Constructor for orange spaces
	 */
	public OrangeSpaces() {
		generateOrangeCoordinates();
	}
	
	/**
	 * Generates the orange space coordinates
	 */
	private void generateOrangeCoordinates() {
		int i;
		for (i = 0; i < OrangeSpacesCoordinates.length; i++)
			OrangeSpacesCoordinates[i] = new int[2];
			// X									// Y	
		OrangeSpacesCoordinates[0][0] = 2; OrangeSpacesCoordinates[0][1] = 0; 
		OrangeSpacesCoordinates[1][0] = 4; OrangeSpacesCoordinates[1][1] = 0; 
		OrangeSpacesCoordinates[2][0] = 5; OrangeSpacesCoordinates[2][1] = 0; 
		OrangeSpacesCoordinates[3][0] = 9; OrangeSpacesCoordinates[3][1] = 0;
		OrangeSpacesCoordinates[4][0] = 10; OrangeSpacesCoordinates[4][1] = 0;
		OrangeSpacesCoordinates[5][0] = 0; OrangeSpacesCoordinates[5][1] = 1;
		OrangeSpacesCoordinates[6][0] = 2; OrangeSpacesCoordinates[6][1] = 3;
		OrangeSpacesCoordinates[7][0] = 6; OrangeSpacesCoordinates[7][1] = 3;
		OrangeSpacesCoordinates[8][0] = 10; OrangeSpacesCoordinates[8][1] = 3;
		OrangeSpacesCoordinates[9][0] = 0; OrangeSpacesCoordinates[9][1] = 4;
		OrangeSpacesCoordinates[10][0] = 3; OrangeSpacesCoordinates[10][1] = 4;
		OrangeSpacesCoordinates[11][0] = 5; OrangeSpacesCoordinates[11][1] = 4;
		OrangeSpacesCoordinates[12][0] = 5; OrangeSpacesCoordinates[12][1] = 5;
		OrangeSpacesCoordinates[13][0] = 7; OrangeSpacesCoordinates[13][1] = 5;
		OrangeSpacesCoordinates[14][0] = 8; OrangeSpacesCoordinates[14][1] = 5;
		OrangeSpacesCoordinates[15][0] = 2; OrangeSpacesCoordinates[15][1] = 6;
		OrangeSpacesCoordinates[16][0] = 10; OrangeSpacesCoordinates[16][1] = 6;
		OrangeSpacesCoordinates[17][0] = 0; OrangeSpacesCoordinates[17][1] = 7;
		OrangeSpacesCoordinates[18][0] = 6; OrangeSpacesCoordinates[18][1] = 7;
		OrangeSpacesCoordinates[19][0] = 9; OrangeSpacesCoordinates[19][1] = 7;
		OrangeSpacesCoordinates[20][0] = 3; OrangeSpacesCoordinates[20][1] = 8;
		OrangeSpacesCoordinates[21][0] = 4; OrangeSpacesCoordinates[21][1] = 8;
		OrangeSpacesCoordinates[22][0] = 0; OrangeSpacesCoordinates[22][1] = 9;
		OrangeSpacesCoordinates[23][0] = 2; OrangeSpacesCoordinates[23][1] = 9;
		OrangeSpacesCoordinates[24][0] = 3; OrangeSpacesCoordinates[24][1] = 10;
		OrangeSpacesCoordinates[25][0] = 1; OrangeSpacesCoordinates[25][1] = 11;
		
		OrangeSpacesCoordinates[26][0] = 12; OrangeSpacesCoordinates[26][1] = 1;
		OrangeSpacesCoordinates[27][0] = 14; OrangeSpacesCoordinates[27][1] = 1;
		OrangeSpacesCoordinates[28][0] = 12; OrangeSpacesCoordinates[28][1] = 3;
		OrangeSpacesCoordinates[29][0] = 14; OrangeSpacesCoordinates[29][1] = 3;
		OrangeSpacesCoordinates[30][0] = 12; OrangeSpacesCoordinates[30][1] = 4;
		OrangeSpacesCoordinates[31][0] = 14; OrangeSpacesCoordinates[31][1] = 4;
		OrangeSpacesCoordinates[32][0] = 14; OrangeSpacesCoordinates[32][1] = 7;
		OrangeSpacesCoordinates[33][0] = 12; OrangeSpacesCoordinates[33][1] = 8;
		OrangeSpacesCoordinates[34][0] = 14; OrangeSpacesCoordinates[34][1] = 10;
		OrangeSpacesCoordinates[35][0] = 7; OrangeSpacesCoordinates[35][1] = 11;
		OrangeSpacesCoordinates[36][0] = 10; OrangeSpacesCoordinates[36][1] = 11;
		OrangeSpacesCoordinates[37][0] = 5; OrangeSpacesCoordinates[37][1] = 12;
		OrangeSpacesCoordinates[38][0] = 14; OrangeSpacesCoordinates[38][1] = 12;
		OrangeSpacesCoordinates[39][0] = 1; OrangeSpacesCoordinates[39][1] = 13;
		OrangeSpacesCoordinates[40][0] = 3; OrangeSpacesCoordinates[40][1] = 13;
		OrangeSpacesCoordinates[41][0] = 4; OrangeSpacesCoordinates[41][1] = 13;
		OrangeSpacesCoordinates[42][0] = 8; OrangeSpacesCoordinates[42][1] = 13;
		OrangeSpacesCoordinates[43][0] = 9; OrangeSpacesCoordinates[43][1] = 13;
		OrangeSpacesCoordinates[44][0] = 14; OrangeSpacesCoordinates[44][1] = 13;
		OrangeSpacesCoordinates[45][0] = 11; OrangeSpacesCoordinates[45][1] = 14;
		OrangeSpacesCoordinates[46][0] = 1; OrangeSpacesCoordinates[46][1] = 15;
		OrangeSpacesCoordinates[47][0] = 9; OrangeSpacesCoordinates[47][1] = 15;
		OrangeSpacesCoordinates[48][0] = 15; OrangeSpacesCoordinates[48][1] = 15;
		OrangeSpacesCoordinates[49][0] = 3; OrangeSpacesCoordinates[49][1] = 16;
		OrangeSpacesCoordinates[50][0] = 12; OrangeSpacesCoordinates[50][1] = 16;
		OrangeSpacesCoordinates[51][0] = 6; OrangeSpacesCoordinates[51][1] = 17;
		OrangeSpacesCoordinates[52][0] = 15; OrangeSpacesCoordinates[52][1] = 18;
		OrangeSpacesCoordinates[53][0] = 6; OrangeSpacesCoordinates[53][1] = 19;
		OrangeSpacesCoordinates[54][0] = 9; OrangeSpacesCoordinates[54][1] = 19;
		OrangeSpacesCoordinates[55][0] = 12; OrangeSpacesCoordinates[55][1] = 19;
		OrangeSpacesCoordinates[56][0] = 13; OrangeSpacesCoordinates[56][1] = 19;
		
		OrangeSpacesCoordinates[57][0] = 6; OrangeSpacesCoordinates[57][1] = 9;
		OrangeSpacesCoordinates[58][0] = 7; OrangeSpacesCoordinates[58][1] = 9;
		OrangeSpacesCoordinates[59][0] = 10; OrangeSpacesCoordinates[59][1] = 9;
		OrangeSpacesCoordinates[60][0] = 5; OrangeSpacesCoordinates[60][1] = 10;
	}
	

}
