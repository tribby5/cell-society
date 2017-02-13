package boards;

import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Shape;

public abstract class TypeBoard {
	private BoardGenerator bg;
	
	public TypeBoard(int rows, int columns, Shape sha){
		boolean rowShifter = false;
		if(sha.getSides()==6)
			rowShifter = true;
		bg = new BoardGenerator(rows, columns, sha, rowShifter);
		if(sha.getSides()==3)
			bg.moveTriangleDown();
	}
	
	public Map<Location, Cell> getBoard(){
		return bg.getBoard();
	}
}