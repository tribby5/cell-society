package simulation.Fire;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class Burning extends FireCell{
	public static final Color color = Color.RED;
	public static final int priority = 1;
	public static final int state = getState_Burning();
	
	public Burning() {
		super(color, state, priority);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public Cell updateCell(Map<Location, Cell> grid, List<Location> neighbors, List<Integer> neighborCount) {
		return new Dead();
	}



	@Override
	public Cell copy() {
		return new Burning();
	}

	


}
