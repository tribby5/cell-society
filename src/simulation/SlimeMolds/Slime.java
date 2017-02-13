package simulation.SlimeMolds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

/**
 * The slime cell. It has very complicated rules for updating it first looks at
 * neighbors to turn to then decides where to move then deposits chemicals
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class Slime extends Patch {
	public static final Color color = Color.GREEN;
	public static final int priority = getPriority_Slime();
	public static final int state = getState_Slime();

	private int chemical_drops = 2;
	private double sniff_threshhold = 1.0;

	private double orientation_angle;

	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Slime() {
		super(color, state, priority);
		Random rand = new Random();
		orientation_angle = rand.nextDouble() * 360;
	}

	/**
	 * constructor that allows for a preset chemDeposit value
	 * 
	 * @param chemDeposit
	 */
	public Slime(int chemDeposit) {
		this();
		setChemical_deposit_count(chemDeposit);
	}

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Slime();
	}

	/**
	 * try to turn and move then drop chemical deposits
	 */
	@Override
	public void act(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		if (this.getChemical_deposit_count() >= sniff_threshhold) {
			turnAndMove(currentSociety, newSociety, loc, neighborsLoc);
		}
		dropChemicalDeposit();
	}

	private void turnAndMove(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc) {
		Queue<Location> targetNeighborsLoc = orderNeighbors(currentSociety, loc, neighborsLoc);

		boolean moved = false;
		boolean shouldTurn = false;
		while (!shouldTurn && !targetNeighborsLoc.isEmpty()) {
			Location tempTargetLoc = targetNeighborsLoc.poll();
			if (currentSociety.get(tempTargetLoc).getState() == getState_Patch()) {
				moved = newSociety.tryToSwap(loc, tempTargetLoc, getState_Patch());
				if (moved) {
					shouldTurn = true;
				}
			} else if (currentSociety.get(tempTargetLoc).getState() == getState_Patch()) {
				shouldTurn = true;
			} else {
				moved = newSociety.tryToSwap(loc, tempTargetLoc, getState_Empty());
				if (moved) {
					shouldTurn = true;
				}
			}
			if (shouldTurn) {
				orientation_angle += loc.calculateAngleDifference(tempTargetLoc, orientation_angle);
			}

			if (moved) {
				swapChemicalDepositAmounts(currentSociety, newSociety, loc, tempTargetLoc);
			}
		}
	}

	private void swapChemicalDepositAmounts(Society currentSociety, Society newSociety, Location loc,
			Location tempTargetLoc) {
		double tempAmount = ((SlimeMoldsCell) currentSociety.get(tempTargetLoc)).getChemical_deposit_count();
		if (tempAmount == 0) {
			newSociety.put(loc,
					new Patch(((SlimeMoldsCell) currentSociety.get(tempTargetLoc)).getChemical_deposit_count()));
		} else {
			((SlimeMoldsCell) newSociety.get(loc)).setChemical_deposit_count(
					((SlimeMoldsCell) currentSociety.get(tempTargetLoc)).getChemical_deposit_count());
		}
		((SlimeMoldsCell) newSociety.get(tempTargetLoc)).setChemical_deposit_count(tempAmount);

	}

	private Queue<Location> orderNeighbors(Society currentSociety, Location loc, List<Location> neighborsLoc) {
		List<SlimeMoldsCell> targetNeighbors = new ArrayList<SlimeMoldsCell>();
		double maxDeposit = 0.0;
		for (Location neighborLoc : neighborsLoc) {
			SlimeMoldsCell neighbor = (SlimeMoldsCell) currentSociety.get(neighborLoc);
			double angleDifference = loc.calculateAngleDifference(neighborLoc, orientation_angle);
			if (Math.abs(angleDifference) <= this.getSniff_angle_max()
					&& neighbor.getChemical_deposit_count() >= maxDeposit) {
				maxDeposit = neighbor.getChemical_deposit_count();
				targetNeighbors.add(neighbor);
			}
		}
		Collections.sort(targetNeighbors, new SlimeMoldsCellComparator());
		Queue<Location> targetNeighborsLoc = new LinkedList<Location>();
		for (SlimeMoldsCell c : targetNeighbors) {
			targetNeighborsLoc.add(currentSociety.getKeyFromValue(c));
		}
		return targetNeighborsLoc;
	}

	/**
	 * drop chemicals on current location
	 */
	public void dropChemicalDeposit() {
		this.setChemical_deposit_count(this.getChemical_deposit_count() + chemical_drops);
	}
}
