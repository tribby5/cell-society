package simulation.SlimeMolds;

import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public class Patch extends SlimeMoldsCell {
	public static final Color beginningColor = Color.BLACK;
	public static final int priority = getPriority_Patch();
	public static final int state = getState_Patch();
	
	private Color color;

	public Patch() {
		super(beginningColor, state, priority);
		color = beginningColor;
		this.setChemical_deposit_count(MIN_CHEM_VALUE);
	}
	
	public Patch(int chemDeposit){
		super(beginningColor, state, priority);
		setChemical_deposit_count(chemDeposit);
	}
	
	public Patch(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
	}

	@Override
	public Cell copy() {
		return new Patch();
	}

	@Override
	public void act(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		if(newSociety.get(loc).getState() == state){
			if (((Patch) newSociety.get(loc)).getChemical_deposit_count() <  MIN_CHEM_VALUE){
				newSociety.put(loc, new Empty());
			} else {
				newSociety.put(loc, this);
			}
			
		}
		
	}

	public void updateColor() {
		double amount = this.getChemical_deposit_count();		
		double whiteness = (amount / 5.0);
		
		if (whiteness > 1){
			whiteness = 1.0;
		}
		
		this.setColor(Color.color(whiteness, whiteness, whiteness));
	}

}
