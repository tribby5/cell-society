package simulation.GameOfLife;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class On extends GameOfLifeCell{
	
	public static final Color color = Color.BLACK;	
	public static final int state = getState_On();

	public On() {
		super(color, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell updateCell(Map<Location, Cell> grid, List<Location> neighbors, List<Integer> neighborCount) {
		if (neighborCount.get(getState_On()) < 2 || neighborCount.get(getState_On()) > 3){
			return new Off();
		}
		return new On();
	}

	@Override
	public Cell copy() {
		return new On();
	}	
}
