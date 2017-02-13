package simulation.PredatorPrey;

import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

/**
 * The shark cell. the updating rules are all here under the act method
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class Shark extends SeaAnimal {
	public static final Color color = Color.GREY;
	public static final int priority = getPriority_Shark();
	public static final int state = getState_Shark();

	private static final int FISH_NUTRITIONAL_VALUE = 2;

	private int energy;

	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Shark() {
		super(color, state, priority);
		energy = this.getSharkInitialEnergy();
	}

	/**
	 * very complicated actions. first, it tries to hunt. if it can't, it moves.
	 * if its energy is too low, it dies. if it hunted or moved, it tries to reproduce
	 */
	public void act(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		if (this.getTurns() == 0) {
			energy = this.getSharkInitialEnergy();
		}

		if (isAlive()) {
			energy--;
			this.setTurns(this.getTurns() + 1);
			boolean hunted = false;
			boolean moved = false;
			if (neighborCounts.get(getState_Fish()) != 0) {
				hunted = swapWithRandomTarget(currentSociety, newSociety, location, neighborsLoc,
						neighborCounts.get(getState_Fish()), getState_Fish());
			}
			// System.out.println(energy);
			if (hunted || energy <= 0) {
				((SeaAnimal) newSociety.get(location)).dies();
				newSociety.put(location, new Water());
				energy += FISH_NUTRITIONAL_VALUE;
			} else if (neighborCounts.get(getState_Water()) != 0) {
				moved = swapWithRandomTarget(currentSociety, newSociety, location, neighborsLoc,
						neighborCounts.get(getState_Water()), getState_Water());
			}

			if (hunted || moved) {
				tryToReproduce(newSociety, location, getState_Shark());
			}
		}
	}

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Shark();
	}

}
