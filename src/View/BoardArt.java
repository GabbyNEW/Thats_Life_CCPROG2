package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;

public class BoardArt extends JPanel {
	private int criticalGridCoordinate = 100;
	// TODO: MOVE THESE VARIABLES TO THEIR RESPECTIVE CLASSES AND MAKE PUBLIC
	public static int[][] BlankSpacesCoordinates = new int[38][];
	public static int[][] OrangeSpacesCoordinates = new int[26][];
	public static int[][] MagentaSpacesCoordinates = new int[3][];
	
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
	}
	
	private void generateMagentaCoordinates() {
		int i;
		for (i = 0; i < MagentaSpacesCoordinates.length; i++)
			MagentaSpacesCoordinates[i] = new int[2];
				// X										// Y
		MagentaSpacesCoordinates[0][0] = 3;		MagentaSpacesCoordinates[0][1] = 7;
		MagentaSpacesCoordinates[1][0] = 5;		MagentaSpacesCoordinates[1][1] = 7;
		MagentaSpacesCoordinates[2][0] = 2;		MagentaSpacesCoordinates[2][1] = 11;
	}
	
	// Each space x and y separated by 50
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		generateBlankCoordinates();
		generateOrangeCoordinates();
		generateMagentaCoordinates();
		
		createBlankSpaces(g);
		createOrangeSpaces(g);
		createMagentaSpaces(g);
		createLabelSpaces(g);
		
		playerMovementGUI(g);
	}
	
	public void playerMovementGUI(Graphics g) {
		
	}
	
	public void createBlankSpaces(Graphics g) {
		// Start and End 
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 100, 30);
		
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
			g.setColor(Color.ORANGE);
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
	
	public void createLabelSpaces(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("START", 24, 20);
		g.drawString("CAREER PATH", 108, 20);
		g.setColor(Color.BLACK);
		g.drawString("COLLEGE PATH", 5, 50);
		g.setColor(Color.WHITE);
		g.drawString("Graduate", 215, 350);
		g.drawString("College Career", 305, 230);
		g.drawString("CONJUNCTION", 510, 230);
	}
}

// fillArc(int x, int y, int width, int height, int startAngle, int arcAngle)