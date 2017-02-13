package boards;

import polys.Hexagon;

/**
 * A class that creates a Hexagon Board.
 * @author Andres Lebbos (afl13)
 */
public class HexagonBoard extends TypeBoard {	
	/**
	 * Creates a Hexagon Board
	 * @param rows Number of columns on board.
	 * @param columns Number of rows on board.
	 */
	public HexagonBoard(int rows, int columns){
		super(rows/2, 3*columns/2, new Hexagon());
	}
}
