package simulation.PredatorPrey;

import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public class Water extends PredatorPreyCell{
	public static final Color color = Color.BLUE;
	public static final int priority = -1;
	public static final int state = getState_Water();
	
	public Water() {
		super(color, state, priority);
	}

	@Override
	public Cell copy() {
		return new Water();
	}


	@Override
	public void act(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		// does nothing
		
	}

}
