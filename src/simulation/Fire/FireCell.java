package simulation.Fire;

import java.util.List;

import cells.ThreeStateCell;
import javafx.scene.paint.Color;

public abstract class FireCell extends ThreeStateCell {
	public static final int stateAlive = 2;
	public static final int stateBurning = 1;
	public static final int stateDead = 0;
	public static final int priorityAlive = 0;
	public static final int priorityBurning = 1;
	public static final int priorityDead = -1;

	private double probCatch;
	
	/**
	 * basic constructor, sends parameters from subclass to superclass
	 * @param inputColor : color
	 * @param state
	 * @param priority
	 */

	public FireCell(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
	}
	
	/**
	 * 
	 * @return states and priorities of cells
	 * used to avoid all instanceof and ensures no errors in casting
	 */

	public static int getState_Alive() {
		return stateAlive;
	}

	public static int getState_Burning() {
		return stateBurning;
	}

	public static int getState_Dead() {
		return stateDead;
	}

	public static int getPriority_Alive() {
		return priorityAlive;
	}

	public static int getPriority_Burning() {
		return priorityBurning;
	}

	public static int getPriority_Dead() {
		return priorityDead;
	}

	public int getDefaultEmptyState() {
		return getState_Dead();
	}
	
	/**
	 * getters and setters for variables
	 * @param probCatch
	 */
	
	public void setProbCatch(double probCatch) {
		this.probCatch = probCatch;
	}

	protected double getProbCatch() {
		return this.probCatch;
	}
	
	
	/**
	 * update cell method, sends updates via the act method in cells
	 * @param neighborCount
	 * @return
	 */
	public FireCell updateCell(List<Integer> neighborCount) {
		return this.act(neighborCount);
	}

	protected abstract FireCell act(List<Integer> neighborCount);



}
