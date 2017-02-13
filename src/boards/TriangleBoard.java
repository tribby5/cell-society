package boards;

import polys.Triangle_Down;

/**
 * A class that creates a Triangle Board.
 * @author Andres Lebbos (afl13)
 */
public class TriangleBoard extends TypeBoard {	
	/**
	 * Creates a Triangle Board
	 * @param rows Number of columns on board.
	 * @param columns Number of rows on board.
	 */
	public TriangleBoard(int rows, int columns){
		super(2*rows, columns, new Triangle_Down());
	}
}
