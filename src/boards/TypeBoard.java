package boards;

import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Shape;
import polys.Triangle;

public abstract class TypeBoard {
	private BoardGenerator bg;
	
	public TypeBoard(int rows, int columns, Shape sha){
		bg = new BoardGenerator(rows, columns, sha);
		if(sha instanceof Triangle)
			bg.moveTriangleDown(sha.getApothem());
	}
	
	public Map<Location, Cell> getBoard(){
		return bg.getBoard();
	}
}
