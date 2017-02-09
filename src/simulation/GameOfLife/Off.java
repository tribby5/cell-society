package simulation.GameOfLife;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class Off extends GameOfLifeCell{
	public static final Color color = Color.WHITE;
	public static final int state = getState_Off();

	public Off() {
		super(color, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell updateCell(Map<Location, Cell> grid, List<Location> neighbors, List<Integer> neighborCount) {
		if (neighborCount.get(getState_On()) == 3){
			return new On();
		}
		return this;
	}

	@Override
	public Cell copy() {
		return new Off();
	}
	
	
}
