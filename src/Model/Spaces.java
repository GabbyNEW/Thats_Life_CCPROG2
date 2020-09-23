package Model;

import java.util.*;

public abstract class Spaces {
	public static int[][] BlankSpacesCoordinates = new int[75][];
	public static int[][] OrangeSpacesCoordinates = new int[61][];
	public static int[][] MagentaSpacesCoordinates = new int[8][];
	public static int[][] BlueSpacesCoordinates = new int[2][];
	public static int[][] GreenSpacesCoordinates = new int[4][];

	
	protected static int[] endSpaceCoordinate; // TODO This is located at [15, 19] on the board.
	
	public Spaces() {}
	
	/**
	 * Determines what kind of space it is given coordinates.<P>
	 * 0 - Blank Space<P>
	 * 1 - Orange Space<P>
	 * 2 - Magenta Space<P>
	 * 3 - Blue Space<P>
	 * 4 - Green Space
	 * -1 - unknown (may signify end space has been reached)
	 * @return integer representation of a space
	 */
	public static int getSpaceType(int coordinate[]) {
		for (int[] e : BlankSpacesCoordinates)
			if (e[0] == coordinate[0] && e[1] == coordinate[1])
				return 0;
		for (int[] e : OrangeSpacesCoordinates)
			if (e[0] == coordinate[0] && e[1] == coordinate[1])
				return 1;
		for (int[] e : MagentaSpacesCoordinates)
			if (e[0] == coordinate[0] && e[1] == coordinate[1])
				return 2;
		for (int[] e : BlueSpacesCoordinates)
			if (e[0] == coordinate[0] && e[1] == coordinate[1])
				return 3;
		for (int[] e : GreenSpacesCoordinates)
			if (e[0] == coordinate[0] && e[1] == coordinate[1])
				return 4;
		
		return -1; // end space
	}
	
}
