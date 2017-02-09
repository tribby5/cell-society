package simulation.PredatorPrey;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class Water extends PredatorPreyCell{
	public static final Color color = Color.BLUE;
	public static final int priority = 2;
	public static final int state = getState_Water();
	
	public Water() {
		super(color, state, priority);
	}

	@Override
	public Cell updateCell(Map<Location, Cell> grid, List<Location> neighbors, List<Integer> neighborCount) {
		return new Water();
	}
	

	@Override
	public Cell copy() {
		return new Water();
	}

}
