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

	public static final double MIN_CHEM_VALUE = 0.1;

	private double chemical_deposit_count;
	private double evaporation_rate;
	private double diffusion;
	private double sniff_angle_max;

	/**
	 * basic constructor, sends parameters from subclass to superclass
	 * @param inputColor : color
	 * @param state
	 * @param priority
	 */
	public SlimeMoldsCell(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
	}

	/**
	 * 
	 * @return states and priorities of cells used to avoid all instanceof and
	 *         ensures no errors in casting
	 */
	public static int getState_Slime() {
		return stateSlime;
	}

	public static int getState_Patch() {
		return statePatch;
	}

	public static int getState_Empty() {
		return stateEmpty;
	}

	public static int getPriority_Slime() {
		return prioritySlime;
	}

	public static int getPriority_Patch() {
		return priorityPatch;
	}

	public static int getPriority_Empty() {
		return priorityEmpty;
	}

	public int getDefaultEmptyState() {
		return getState_Empty();
	}

	/**
	 * getters and setters for variables of cells
	 * 
	 * @return
	 */
	public double getEvaporation_rate() {
		return evaporation_rate;
	}

	public void setEvaporation_rate(double evaporation_rate) {
		this.evaporation_rate = evaporation_rate;
	}

	public double getDiffusion() {
		return diffusion;
	}

	public void setDiffusion(double diffusion) {
		this.diffusion = diffusion;
	}

	public double getSniff_angle_max() {
		return sniff_angle_max;
	}

	public void setSniff_angle_max(double sniff_angle_max) {
		this.sniff_angle_max = sniff_angle_max;
	}

	public double getChemical_deposit_count() {
		return chemical_deposit_count;
	}

	/**
	 * requires a color update to show concentration
	 * 
	 * @param chemical_deposit_count
	 */

	public void setChemical_deposit_count(double chemical_deposit_count) {
		this.chemical_deposit_count = chemical_deposit_count;
		if (this.chemical_deposit_count < MIN_CHEM_VALUE) {
			this.chemical_deposit_count = 0;
		} else if (this.getState() == getState_Patch()) {
			((Patch) this).updateColor();
		}
	}

	public void incrementChemical_deposit_count(double chemical_deposit_count_addition) {
		setChemical_deposit_count(this.chemical_deposit_count + chemical_deposit_count_addition);
	}

	/**
	 * update first updates the chemical values of the cell and its neighbors
	 * 
	 * @param currentSociety
	 * @param newSociety
	 * @param location
	 * @param neighborsLoc
	 * @param neighborCounts
	 */
	public void update(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		updateChemicalDeposits(newSociety, location, neighborsLoc);
		this.act(currentSociety, newSociety, location, neighborsLoc, neighborCounts);

	}

	/**
	 * each cell acts, the slime cells moved
	 * 
	 * @param currentSociety
	 * @param newSociety
	 * @param loc
	 * @param neighborsLoc
	 * @param neighborCounts
	 */
	public abstract void act(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc,
			List<Integer> neighborCounts);

	/**
	 * the sort that determines the order of slime cells. first patches, then
	 * slimes, then empties
	 * 
	 * @author Miguel Anderson
	 *
	 */
	protected class SlimeMoldsCellComparator implements Comparator<SlimeMoldsCell> {
		@Override
		public int compare(SlimeMoldsCell c1, SlimeMoldsCell c2) {
			if (c1.getState() != c2.getState()) {
				if (c1.getState() == getState_Patch()) {
					return 1;
				} else if (c2.getState() == getState_Patch()) {
					return -1;
				} else {
					return c1.getState() - c2.getState();
				}
			} else {
				return (int) (c1.getChemical_deposit_count() - c2.getChemical_deposit_count());
			}
		}
	}

	private void updateChemicalDeposits(Society newSociety, Location loc, List<Location> neighborsLoc) {
		if (this.getChemical_deposit_count() >= MIN_CHEM_VALUE) {
			// evaporate
			((SlimeMoldsCell) newSociety.get(loc))
					.setChemical_deposit_count(this.getChemical_deposit_count() * (1 - evaporation_rate));
			// diffuse to neighbors
			for (Location neighborLoc : neighborsLoc) {
				if (newSociety.get(neighborLoc).getState() == stateEmpty) {
					newSociety.put(neighborLoc, new Patch());
				}
				((SlimeMoldsCell) newSociety.get(neighborLoc))
						.incrementChemical_deposit_count(this.getChemical_deposit_count() * diffusion);
			}

			// reduce diffused amount
			((SlimeMoldsCell) newSociety.get(loc)).setChemical_deposit_count(
					this.getChemical_deposit_count() * (1 - neighborsLoc.size() * diffusion));
		}
	}
}
