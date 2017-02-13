package simulation.SlimeMolds;

import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;


/**
 * The patch cell. It holds chemicals that need to be dispersed.
 * The cell must be updated to an empty cell if the deposit count is zero
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class Patch extends SlimeMoldsCell {
	public static final Color beginningColor = Color.BLACK;
	public static final int priority = getPriority_Patch();
	public static final int state = getState_Patch();
	
	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Patch() {
		super(beginningColor, state, priority);
		this.setChemical_deposit_count(MIN_CHEM_VALUE);
	}
	
	/**
	 * basic constructor, sends preset values to the superclass
	 * this one takes in an initial value of chemical deposit
	 * this happens when a slime moves and the new patch has the old chemical deposit value
	 */
	public Patch(double d){
		super(beginningColor, state, priority);
		setChemical_deposit_count(d);
	}
	
	/**
	 * constructor for slime. allows for slime to have its own properties
	 */
	public Patch(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
	}

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Patch();
	}

	/**
	 * either returns itself or a new empty cell.
	 * 
	 */
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

	/**
	 * updates the color of the patch cell, shows concentration
	 */
	public void updateColor() {
		double amount = this.getChemical_deposit_count();		
		double whiteness = (amount / 5.0);
		
		if (whiteness > 1){
			whiteness = 1.0;
		}
		
		this.setColor(Color.color(whiteness, whiteness, whiteness));
	}

}
