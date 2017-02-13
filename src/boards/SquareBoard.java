package boards;

import polys.Square;

/**
 * A class that creates a Square Board.
 * @author Andres Lebbos (afl13)
 */
public class SquareBoard extends TypeBoard{	
	/**
	 * Creates a Square Board
	 * @param rows Number of columns on board.
	 * @param columns Number of rows on board.
	 */
	public SquareBoard(int rows, int columns){
		super(rows, columns, new Square());
	}
}
