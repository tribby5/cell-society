package simulation.Segregation;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class Empty extends SegregationCell {
	public static final Color color = Color.GREY;
	public static final int priority = 1;
	public static final int state = getState_Empty();

	public Empty() {
		super(color, state, priority);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell updateCell(Map<Location, Cell> grid, List<Location> neighbors, List<Integer> neighborCount) {
		return new Empty();
	}

	@Override
	public Cell copy() {
		return new Empty();
	}

}
