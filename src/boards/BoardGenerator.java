package boards;

import java.util.HashMap;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Shape;
import polys.Triangle_Down;

/**
 * Generates a partial board generator. Creates all location indicating null in their position.
 * @author Andres Lebbos (afl13)
 */
public class BoardGenerator {
	
	/**
	 * A board to store a partial generation of the board.
	 */
	private Map<Location, Cell> board;
	
	/**
	 * Creates a grid with tricky inputs
	 * @param row numbers of elements in a column
	 * @param col number of elements in a row
	 * @param shape the figure that is going to fill the grid
	 * @param right if each second row is shifted right or not
	 */
	public BoardGenerator(int row, int col, Shape shape, boolean right) {
		boolean shifter = false;
		board = new HashMap<Location, Cell>();
		double xAdv = shape.getXAdvance();
		double xMax = col*xAdv;
		double yAdv = shape.getYAdvance();
		double yMax = row*yAdv;
		for(double x=0.0; Double.compare(x, xMax)<0; x+=xAdv){
			for(double y=0.0; Double.compare(y, yMax)<0; y+=yAdv){
				double yProv = y;
				if(right && shifter){
					yProv = y + yAdv/2;
				}
				board.put(new Location(x, yProv, shape), null);
				shape = shape.changeNext();
			}
			if(row%2==0)
				shape = shape.changeNext();
			if(right)
				shifter = !shifter;
		}
	}

	/**
	 * Return the partial board
	 * @return the board
	 */
	public Map<Location, Cell> getBoard(){
		return board;
	}
	
	/**
	 * Moves all the Down triangles to their rightful position.
	 */
	public void moveTriangleDown(){
		Map<Location, Cell> newBoard = new HashMap<Location, Cell>();
		double yDown = new Triangle_Down().getApothem();
		for(Location loc: board.keySet()){
			if(loc.getPoly() instanceof Triangle_Down){
				Location loc2 = new Location(loc.getX()-yDown, loc.getY(), loc.getPoly());
				newBoard.put(loc2, null);
			} else
				newBoard.put(loc, null);
		}
		board = new HashMap<Location, Cell>(newBoard);
	}
}