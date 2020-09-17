package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;

public class BoardArtPanel extends JPanel {
	private int criticalGridCoordinate = 100;
	// TODO: MOVE THESE VARIABLES TO THEIR RESPECTIVE CLASSES AND MAKE PUBLIC
	public static int[][] BlankSpacesCoordinates = new int[75][];
	public static int[][] OrangeSpacesCoordinates = new int[61][];
	public static int[][] MagentaSpacesCoordinates = new int[8][];
	public static int[][] BlueSpacesCoordinates = new int[2][];
	public static int[][] GreenSpacesCoordinates = new int[4][];

	// Methods to generate coordinates of each spaces TODO: MOVE TO THEIR RESPECTIVE CLASSES AND MAKE PRIVATE
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
		BlankSpacesCoordinates[13][0] = 8; BlankSpacesCoordinates[13][1] = 3;
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
	
	private void generateMagentaCoordinates() {
		int i;
		for (i = 0; i < MagentaSpacesCoordinates.length; i++)
			MagentaSpacesCoordinates[i] = new int[2];
				// X									// Y
		MagentaSpacesCoordinates[0][0] = 3;	MagentaSpacesCoordinates[0][1] = 7;
		MagentaSpacesCoordinates[1][0] = 5;	MagentaSpacesCoordinates[1][1] = 7;
		MagentaSpacesCoordinates[2][0] = 2;	MagentaSpacesCoordinates[2][1] = 11;
		
		MagentaSpacesCoordinates[3][0] = 14; MagentaSpacesCoordinates[3][1] = 5;
		MagentaSpacesCoordinates[4][0] = 12; MagentaSpacesCoordinates[4][1] = 6;
		MagentaSpacesCoordinates[5][0] = 9; MagentaSpacesCoordinates[5][1] = 12;
		MagentaSpacesCoordinates[6][0] = 1; MagentaSpacesCoordinates[6][1] = 16;
		MagentaSpacesCoordinates[7][0] = 8; MagentaSpacesCoordinates[7][1] = 3;
	}
	
	public void generateBlueCoordinates() {
		int i;
		for (i = 0; i < BlueSpacesCoordinates.length; i++)
			BlueSpacesCoordinates[i] = new int[2];
			// X									// Y	
		BlueSpacesCoordinates[0][0] = 12; BlueSpacesCoordinates[0][1] = 9;
		BlueSpacesCoordinates[1][0] = 10; BlueSpacesCoordinates[1][1] = 14;
	}
	
	public void generateGreenCoordinates() {
		int i;
		for (i = 0; i < GreenSpacesCoordinates.length; i++)
			GreenSpacesCoordinates[i] = new int[2];
			// X									// Y	
		GreenSpacesCoordinates[0][0] = 12; GreenSpacesCoordinates[0][1] = 14;
		GreenSpacesCoordinates[1][0] = 5; GreenSpacesCoordinates[1][1] = 16;
		GreenSpacesCoordinates[2][0] = 9; GreenSpacesCoordinates[2][1] = 16;
		GreenSpacesCoordinates[3][0] = 7; GreenSpacesCoordinates[3][1] = 19;
	}
	
	// Each space x and y separated by 50
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		generateBlankCoordinates();
		generateOrangeCoordinates();
		generateMagentaCoordinates();
		generateBlueCoordinates();
		generateGreenCoordinates();
		
		createBlankSpaces(g);
		createOrangeSpaces(g);
		createMagentaSpaces(g);
		createBlueSpaces(g);
		createGreenSpaces(g);
		createLabelSpaces(g);
		
		playerMovementGUI(g);
	}
	
	public void playerMovementGUI(Graphics g) {
		if (BoardGUI.testArg) {
			g.setColor(Color.CYAN);
			g.fillArc(70 * 1, 20*1, 20, 20, 0, 360);
		}
	}
	
	public void createBlankSpaces(Graphics g) {
		// Start and End 
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 100, 30);
		g.fillRect(15 * criticalGridCoordinate, 19 * 30, 100, 30);
		
		
		// Rest of blank spaces
		for (int[] e : BlankSpacesCoordinates) {
			g.setColor(Color.GRAY);
			g.fillRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
			g.setColor(Color.BLACK);
			g.drawRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
		}
	}
	
	public void createOrangeSpaces(Graphics g) {
		for (int[] e : OrangeSpacesCoordinates) {
			g.setColor(new Color(207, 127, 64));
			g.fillRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
			g.setColor(Color.BLACK);
			g.drawRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
		}
			
	}
	
	public void createMagentaSpaces(Graphics g) {
		for (int[] e : MagentaSpacesCoordinates) {
			g.setColor(Color.MAGENTA);
			g.fillRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
			g.setColor(Color.BLACK);
			g.drawRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
		}
	}
		
	public void createBlueSpaces(Graphics g) {
		for (int[] e : BlueSpacesCoordinates) {
			g.setColor(Color.BLUE);
			g.fillRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
			g.setColor(Color.BLACK);
			g.drawRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
		}
	}
			
	public void createGreenSpaces(Graphics g) {
		for (int[] e : GreenSpacesCoordinates) {
			g.setColor(Color.GREEN);
			g.fillRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
			g.setColor(Color.BLACK);
			g.drawRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
		}
	}
	
	public void createLabelSpaces(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("START", 24, 20);
		g.drawString("END", 1540, 590);
		g.drawString("CAREER PATH", 108, 20);
		g.drawString("↑", 2 * 100, 9 * 35);
		g.drawString("↑", 2 * 100, 8 * 35);
		g.drawString("↑", 2 * 100, 7 * 35);
		g.drawString("↑", 2 * 100, 6 * 35);
		g.drawString("↑", 2 * 100, 5 * 35);
		g.drawString("↑", 2 * 100, 4 * 35);
		g.drawString("→", 2 * 100, 3 * 35);
		g.drawString("→", 3 * 100, 9 * 35);
		g.drawString("↓", 3 * 100, 8 * 35);
		g.drawString("↓", 3 * 100, 7 * 35);
		g.drawString("↓", 3 * 100, 6 * 35);
		g.drawString("↓", 3 * 100, 5 * 35);
		g.drawString("↓", 3 * 100, 4 * 35);
		g.drawString("↓", 3 * 100, 3 * 35);
		g.drawString("↑", 4 * 100, 7 * 35);
		g.drawString("↑", 4 * 100, 8 * 35);
		g.drawString("↑", 4 * 100, 9 * 35);
		
		g.setColor(Color.BLACK);
		g.drawString("COLLEGE PATH", 5, 50);
		g.drawString("Career", 528, 320);
		g.drawString("Family", 628, 289);
		g.drawString("Pay Raise", 520, 500);
		g.drawString("Pay Raise", 922, 500);
		g.drawString("Pay Day", 727, 590);
		g.drawString("Pay Day", 1226, 440);
		
		g.setColor(Color.WHITE);
		g.drawString("Graduate", 215, 350);
		g.drawString("College Career", 305, 230);
		g.drawString("CONJUNCTION", 510, 230);
		g.drawString("Get Married", 816, 110);
		g.drawString("Get Married", 915, 380);
		g.drawString("Buy House", 1217, 200);
		g.drawString("Baby", 1430, 170);
		g.drawString("Search Job", 117, 500);
	}
}

// fillArc(int x, int y, int width, int height, int startAngle, int arcAngle)