package Model;

import java.util.*;

/**
 * This class is for blank spaces.
 */
public class BlankSpaces extends Spaces {
	
	public BlankSpaces() {
		generateBlankCoordinates();
	}
	
	private void generateBlankCoordinates() {
		int i;
		for (i = 0; i < BlankSpacesCoordinates.length; i++)
			BlankSpacesCoordinates[i] = new int[2];
			// X									// Y
		BlankSpacesCoordinates[0][0] = 1; BlankSpacesCoordinates[0][1] = 0; 
		BlankSpacesCoordinates[1][0] = 3; BlankSpacesCoordinates[1][1] = 0;
		BlankSpacesCoordinates[2][0] = 6; BlankSpacesCoordinates[2][1] = 0;
		BlankSpacesCoordinates[3][0] = 7; BlankSpacesCoordinates[3][1] = 0;
		BlankSpacesCoordinates[4][0] = 8; BlankSpacesCoordinates[4][1] = 0;
		BlankSpacesCoordinates[5][0] = 10; BlankSpacesCoordinates[5][1] = 1;
		BlankSpacesCoordinates[6][0] = 0; BlankSpacesCoordinates[6][1] = 2;
		BlankSpacesCoordinates[7][0] = 10; BlankSpacesCoordinates[7][1] = 2;
		BlankSpacesCoordinates[8][0] = 10; BlankSpacesCoordinates[8][1] = 2;
		BlankSpacesCoordinates[9][0] = 0; BlankSpacesCoordinates[9][1] = 3;
		BlankSpacesCoordinates[10][0] = 3; BlankSpacesCoordinates[10][1] = 3;
		BlankSpacesCoordinates[11][0] = 5; BlankSpacesCoordinates[11][1] = 3;
		BlankSpacesCoordinates[12][0] = 7; BlankSpacesCoordinates[12][1] = 3;
		BlankSpacesCoordinates[13][0] = 7; BlankSpacesCoordinates[13][1] = 3;
		BlankSpacesCoordinates[14][0] = 9; BlankSpacesCoordinates[14][1] = 3;
		BlankSpacesCoordinates[15][0] = 2; BlankSpacesCoordinates[15][1] = 4;
		BlankSpacesCoordinates[16][0] = 0; BlankSpacesCoordinates[16][1] = 5;
		BlankSpacesCoordinates[17][0] = 2; BlankSpacesCoordinates[17][1] = 5;
		BlankSpacesCoordinates[18][0] = 3; BlankSpacesCoordinates[18][1] = 5;
		BlankSpacesCoordinates[19][0] = 6; BlankSpacesCoordinates[19][1] = 5;
		BlankSpacesCoordinates[20][0] = 9; BlankSpacesCoordinates[20][1] = 5;
		BlankSpacesCoordinates[21][0] = 10; BlankSpacesCoordinates[21][1] = 5;
		BlankSpacesCoordinates[22][0] = 0; BlankSpacesCoordinates[22][1] = 6;
		BlankSpacesCoordinates[23][0] = 3; BlankSpacesCoordinates[23][1] = 6;
		BlankSpacesCoordinates[24][0] = 3; BlankSpacesCoordinates[24][1] = 6;
		BlankSpacesCoordinates[25][0] = 2; BlankSpacesCoordinates[25][1] = 7;
		BlankSpacesCoordinates[26][0] = 4; BlankSpacesCoordinates[26][1] = 7;
		BlankSpacesCoordinates[27][0] = 7; BlankSpacesCoordinates[27][1] = 7;
		BlankSpacesCoordinates[28][0] = 8; BlankSpacesCoordinates[28][1] = 7;
		BlankSpacesCoordinates[29][0] = 10; BlankSpacesCoordinates[29][1] = 7;
		BlankSpacesCoordinates[30][0] = 0; BlankSpacesCoordinates[30][1] = 8;
		BlankSpacesCoordinates[31][0] = 2; BlankSpacesCoordinates[31][1] = 8;
		BlankSpacesCoordinates[32][0] = 3; BlankSpacesCoordinates[32][1] = 9;
		BlankSpacesCoordinates[33][0] = 4; BlankSpacesCoordinates[33][1] = 9;
		BlankSpacesCoordinates[34][0] = 0; BlankSpacesCoordinates[34][1] = 10;
		BlankSpacesCoordinates[35][0] = 2; BlankSpacesCoordinates[35][1] = 10;
		BlankSpacesCoordinates[36][0] = 4; BlankSpacesCoordinates[36][1] = 10;
		BlankSpacesCoordinates[37][0] = 0; BlankSpacesCoordinates[37][1] = 11;
		
		BlankSpacesCoordinates[38][0] = 13; BlankSpacesCoordinates[38][1] = 1;
		BlankSpacesCoordinates[39][0] = 12; BlankSpacesCoordinates[39][1] = 2;
		BlankSpacesCoordinates[40][0] = 14; BlankSpacesCoordinates[40][1] = 2;
		BlankSpacesCoordinates[41][0] = 12; BlankSpacesCoordinates[41][1] = 5;
		BlankSpacesCoordinates[42][0] = 14; BlankSpacesCoordinates[42][1] = 6;
		BlankSpacesCoordinates[43][0] = 12; BlankSpacesCoordinates[43][1] = 7;
		BlankSpacesCoordinates[44][0] = 14; BlankSpacesCoordinates[44][1] = 8;
		BlankSpacesCoordinates[45][0] = 11; BlankSpacesCoordinates[45][1] = 9;
		BlankSpacesCoordinates[46][0] = 14; BlankSpacesCoordinates[46][1] = 9;
		BlankSpacesCoordinates[47][0] = 5; BlankSpacesCoordinates[47][1] = 11;
		BlankSpacesCoordinates[48][0] = 14; BlankSpacesCoordinates[48][1] = 11;
		BlankSpacesCoordinates[49][0] = 7; BlankSpacesCoordinates[49][1] = 12;
		BlankSpacesCoordinates[50][0] = 10; BlankSpacesCoordinates[50][1] = 12;
		BlankSpacesCoordinates[51][0] = 2; BlankSpacesCoordinates[51][1] = 13;
		BlankSpacesCoordinates[52][0] = 5; BlankSpacesCoordinates[52][1] = 13;
		BlankSpacesCoordinates[53][0] = 7; BlankSpacesCoordinates[53][1] = 13;
		BlankSpacesCoordinates[54][0] = 1; BlankSpacesCoordinates[54][1] = 14;
		BlankSpacesCoordinates[55][0] = 14; BlankSpacesCoordinates[55][1] = 14;
		BlankSpacesCoordinates[56][0] = 10; BlankSpacesCoordinates[56][1] = 15;
		BlankSpacesCoordinates[57][0] = 12; BlankSpacesCoordinates[57][1] = 15;
		BlankSpacesCoordinates[58][0] = 14; BlankSpacesCoordinates[58][1] = 15;
		BlankSpacesCoordinates[59][0] = 2; BlankSpacesCoordinates[59][1] = 16;
		BlankSpacesCoordinates[60][0] = 4; BlankSpacesCoordinates[60][1] = 16;
		BlankSpacesCoordinates[61][0] = 6; BlankSpacesCoordinates[61][1] = 16;
		BlankSpacesCoordinates[62][0] = 15; BlankSpacesCoordinates[62][1] = 16;
		BlankSpacesCoordinates[63][0] = 9; BlankSpacesCoordinates[63][1] = 17;
		BlankSpacesCoordinates[64][0] = 12; BlankSpacesCoordinates[64][1] = 17;
		BlankSpacesCoordinates[65][0] = 15; BlankSpacesCoordinates[65][1] = 17;
		BlankSpacesCoordinates[66][0] = 6; BlankSpacesCoordinates[66][1] = 18;
		BlankSpacesCoordinates[67][0] = 9; BlankSpacesCoordinates[67][1] = 18;
		BlankSpacesCoordinates[68][0] = 12; BlankSpacesCoordinates[68][1] = 18;
		BlankSpacesCoordinates[69][0] = 8; BlankSpacesCoordinates[69][1] = 19;
		BlankSpacesCoordinates[70][0] = 14; BlankSpacesCoordinates[70][1] = 19;
		
		BlankSpacesCoordinates[71][0] = 5; BlankSpacesCoordinates[71][1] = 8;
		BlankSpacesCoordinates[72][0] = 5; BlankSpacesCoordinates[72][1] = 9;
		BlankSpacesCoordinates[73][0] = 7; BlankSpacesCoordinates[73][1] = 10;
		BlankSpacesCoordinates[74][0] = 10; BlankSpacesCoordinates[74][1] = 10;
	}

}
