package simulation.SlimeMolds;

import java.util.Comparator;
import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public abstract class SlimeMoldsCell extends ThreeStateCell {
	public static final int prioritySlime = 0;
	public static final int priorityPatch = 1;
	public static final int priorityEmpty = -1;
	public static final int stateEmpty = 0;
	public static final int stateSlime = 2;
	public static final int statePatch = 1;
	
	private int chemical_deposit_count;

	public SlimeMoldsCell(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
	}
	
	public int getChemical_deposit_count() {
		return chemical_deposit_count;
	}

	public void setChemical_deposit_count(int chemical_deposit_count) {
		this.chemical_deposit_count = chemical_deposit_count;
	}
	
	
	
	
	public static int getState_Slime(){
		return stateSlime;
	}
	
	public static int getState_Patch(){
		return statePatch;
	}
	
	public static int getState_Empty(){
		return stateEmpty;
	}
	
	public static int getPriority_Slime(){
		return prioritySlime;
	}
	
	public static int getPriority_Patch(){
		return priorityPatch;
	}
	
	public static int getPriority_Empty(){
		return priorityEmpty;
	}
	
	public int getDefaultEmptyState(){
		return getState_Empty();
	}

	public abstract void act(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts);
	
	class SlimeMoldsCellComparator implements Comparator<SlimeMoldsCell> {
		@Override
		public int compare(SlimeMoldsCell c1, SlimeMoldsCell c2){
			if(c1.getState() != c2.getState()){
				if(c1.getState() == getState_Patch()){
					return 1;
				} else if (c2.getState() == getState_Patch()){
					return -1;
				} else {
					return c1.getState() - c2.getState();
				}
			} else {
				return c1.getChemical_deposit_count() - c2.getChemical_deposit_count(); 
			}
		}

	}
}
