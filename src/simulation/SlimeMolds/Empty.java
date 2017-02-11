package simulation.SlimeMolds;

import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public class Empty extends SlimeMoldsCell{
	public static final Color color = Color.BLACK;
	public static final int priority = getPriority_Empty();
	public static final int state = getState_Empty();

	public Empty() {
		super(color, state, priority);
		this.setChemical_deposit_count(0);
	}

	@Override
	public Cell copy() {
		return new Empty();
	}

	@Override
	public void act(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		// do nothing
		
	}

}
