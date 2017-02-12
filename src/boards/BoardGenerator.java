package boards;

import java.util.HashMap;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Shape;
import polys.Triangle_Up;

public class BoardGenerator {
	private Map<Location, Cell> board;
	
	public BoardGenerator(int row, int col, Shape sha) {
		board = new HashMap<Location, Cell>();
		double xAdv = sha.getXAdvance();
		double xMax = col*xAdv;
		double yAdv = sha.getYAdvance();
		double yMax = col*yAdv;
		for(double x=0.0; Double.compare(x, xMax)<0; x+=xAdv)
			for(double y=0.0; Double.compare(y, yMax)<0; y+=yAdv){
				Location loc = new Location(x, y, sha);
				board.put(loc, null);
				sha = sha.changeNext();
			}
	}

	public Map<Location, Cell> getBoard(){
		return board;
	}
	
	public void moveTriangleDown(double yDown){
		Map<Location, Cell> newBoard = new HashMap<Location, Cell>();
		for(Location loc: board.keySet()){
			if(loc.getPoly() instanceof Triangle_Up){
				Location loc2 = new Location(loc.getX(), loc.getY()+yDown, loc.getPoly());
				newBoard.put(loc2, null);
			}
		}
		board = newBoard;
	}
}
