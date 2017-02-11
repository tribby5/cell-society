package simulation.SlimeMolds;

import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public class Patch extends SlimeMoldsCell {
	public static final Color color = Color.SANDYBROWN;
	public static final int priority = getPriority_Patch();
	public static final int state = getState_Patch();
	
	

	public Patch() {
		super(color, state, priority);
	}
	
	public Patch(int chemDeposit){
		this();
		setChemical_deposit_count(chemDeposit);
	}
	
	public Patch(Color inputColor, int state, int priority) {
		super(color, state, priority);
	}

	@Override
	public Cell copy() {
		return new Patch();
	}

	@Override
	public void act(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		// diffuse chemical on neighbor patches
		
	}

}
