package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import Model.*;

public class BoardArtPanel extends JPanel {
	private int criticalGridCoordinate = 100;
	
	@Override
	// Each space x and y separated by 50
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // I don't know why, but the board goes haywire if I don't do this.
		
		new BlankSpaces();
		new OrangeSpaces();
		new GreenSpaces();
		new BlueSpaces();
		new MagentaSpaces();
		
		createBlankSpaces(g);
		createOrangeSpaces(g);
		createMagentaSpaces(g);
		createBlueSpaces(g);
		createGreenSpaces(g);
		createLabelSpaces(g);
		
		playerMovementGUI(g);
	}
	
	
	/**
	 * Note: [1,1] on the board is right next to the start space.
	 * @param g
	 */
	public void playerMovementGUI(Graphics g) { // Draw circle based on players' coordinates.
		Player[] players = MainGame.getPlayers();
		
		g.setColor(Color.RED); // Player 1 RED Format: sideways movement [80 * (x+1)] + [20 * x] | up-down movement [20 * (y+1)] + [10 * y]
		g.fillArc((80 * (players[0].getPlayerLocation()[0] + 1)) + (20 * players[0].getPlayerLocation()[0]), 20* (players[0].getPlayerLocation()[1] + 1) + (10 * players[0].getPlayerLocation()[1]), 10, 10, 0, 360);
		
		g.setColor(Color.CYAN); // Player 2 CYAN Format: sideways movement [90 * (x + 1)] + [10 * x] | up-down movement [(20 * (y + 1)] + [10 * y]
		g.fillArc((90 * (players[1].getPlayerLocation()[0] + 1)) + (10 * players[1].getPlayerLocation()[0]), 20* (players[1].getPlayerLocation()[1] + 1) + (10 * players[1].getPlayerLocation()[1]), 10, 10, 0, 360);
		
		if (MainGame.getNumberofplayersingame() == 3) {
			g.setColor(Color.YELLOW); // Player 3 YELLOW Format: sideways movement [90 * (x + 1)] + [10 * x] | up-down movement [(10 * (y + 1)] + [20 * y]
			g.fillArc((90 * (players[2].getPlayerLocation()[0] + 1)) + (10 * players[2].getPlayerLocation()[0]), 10 * (players[2].getPlayerLocation()[1] + 1) + (20 * players[2].getPlayerLocation()[1]), 10, 10, 0, 360);
		}
	}
	
	public void createBlankSpaces(Graphics g) {
		// Start and End 
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 100, 30);
		g.fillRect(15 * criticalGridCoordinate, 19 * 30, 100, 30);
		
		
		// Rest of blank spaces
		for (int[] e : BlankSpaces.BlankSpacesCoordinates) {
			g.setColor(Color.GRAY);
			g.fillRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
			g.setColor(Color.BLACK);
			g.drawRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
		}
	}
	
	public void createOrangeSpaces(Graphics g) {
		for (int[] e : OrangeSpaces.OrangeSpacesCoordinates) {
			g.setColor(new Color(207, 127, 64));
			g.fillRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
			g.setColor(Color.BLACK);
			g.drawRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
		}
			
	}
	
	public void createMagentaSpaces(Graphics g) {
		for (int[] e : MagentaSpaces.MagentaSpacesCoordinates) {
			g.setColor(Color.MAGENTA);
			g.fillRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
			g.setColor(Color.BLACK);
			g.drawRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
		}
	}
		
	public void createBlueSpaces(Graphics g) {
		for (int[] e : BlueSpaces.BlueSpacesCoordinates) {
			g.setColor(Color.BLUE);
			g.fillRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
			g.setColor(Color.BLACK);
			g.drawRect(e[0] * criticalGridCoordinate, e[1] * 30, 100, 30);
		}
	}
			
	public void createGreenSpaces(Graphics g) {
		for (int[] e : GreenSpaces.GreenSpacesCoordinates) {
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