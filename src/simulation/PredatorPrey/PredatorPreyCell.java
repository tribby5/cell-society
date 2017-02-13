package simulation.PredatorPrey;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public abstract class PredatorPreyCell extends ThreeStateCell {
	public static final int stateShark = 2;
	public static final int stateFish = 1;
	public static final int stateWater = 0;
	public static final int priorityShark = 0;
	public static final int priorityFish = 1;
	public static final int priorityWater = -1;

	private int fishTurnsToReproduce;
	private int sharkTurnsToReproduce;
	private int sharkInitialEnergy;

	/**
	 * basic constructor, sends parameters from subclass to superclass
	 * 
	 * @param inputColor
	 *            : color
	 * @param state
	 * @param priority
	 */
	public PredatorPreyCell(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
	}

	/**
	 * 
	 * @return states and priorities of cells used to avoid all instanceof and
	 *         ensures no errors in casting
	 */
	public static int getState_Shark() {
		return stateShark;
	}

	public static int getState_Fish() {
		return stateFish;
	}

	public static int getState_Water() {
		return stateWater;
	}

	public static int getPriority_Shark() {
		return priorityShark;
	}

	public static int getPriority_Fish() {
		return priorityFish;
	}

	public static int getPriority_Water() {
		return priorityWater;
	}

	public int getDefaultEmptyState() {
		return getState_Water();
	}

	/**
	 * the action method for the predator prey cells sharks hunt then move fish
	 * just move water does nothing shark and fish can reproduce
	 * 
	 * @param currentSociety
	 * @param newSociety
	 * @param location
	 * @param neighborsLoc
	 * @param neighborCounts
	 */

	public abstract void act(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts);

	/**
	 * getters and setters of parameter values in the simulation
	 */
	public int getFishTurnsToReproduce() {
		return fishTurnsToReproduce;
	}

	public void setFishTurnsToReproduce(int fishTurnsToReproduce) {
		this.fishTurnsToReproduce = fishTurnsToReproduce;
	}

	public int getSharkTurnsToReproduce() {
		return sharkTurnsToReproduce;
	}

	public void setSharkTurnsToReproduce(int sharkTurnsToReproduce) {
		this.sharkTurnsToReproduce = sharkTurnsToReproduce;
	}

	public int getSharkInitialEnergy() {
		return sharkInitialEnergy;
	}

	public void setSharkInitialEnergy(int sharkInitialEnergy) {
		this.sharkInitialEnergy = sharkInitialEnergy;
	}

}
