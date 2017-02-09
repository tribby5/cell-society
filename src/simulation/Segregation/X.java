package simulation.Segregation;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class X extends SegregationCell{
	public static final Color color = Color.RED;
	public static final int priority = 0;
	public static final int state = getState_X();
	
	public X() {
		super(color, state, priority);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell updateCell(Map<Location, Cell> grid, List<Location> neighbors, List<Integer> neighborCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell copy() {
		return new X();
	}



}
