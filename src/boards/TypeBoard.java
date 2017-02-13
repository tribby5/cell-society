package boards;

import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Shape;

/**
 * The abstract class that defines a board generator and contains the types of boards that can be created.
 * @author Andres Lebbos (afl13)
 */
public abstract class TypeBoard {
	
	/**
	 * The board generator that creates the board.
	 */
	private BoardGenerator bg;
	
	/**
	 * The type of board that will create the board generator
	 * @param rows The amount of columms in the board
	 * @param columns the amount of rows in the board
	 * @param sha the type of figures on the board.
	 */
	public TypeBoard(int rows, int columns, Shape sha){
		boolean rowShifter = false;
		if(sha.getSides()==6)
			rowShifter = true;
		bg = new BoardGenerator(rows, columns, sha, rowShifter);
		if(sha.getSides()==3)
			bg.moveTriangleDown();
	}
	
	/**
	 * Just calls the result board from board generator
	 * @return the partial board.
	 */
	public Map<Location, Cell> getBoard(){
		return bg.getBoard();
	}
}