package boards;

import java.util.HashMap;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Shape;
import polys.Triangle_Down;

public class BoardGenerator {
	private Map<Location, Cell> board;
	
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

	public Map<Location, Cell> getBoard(){
		return board;
	}
	
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