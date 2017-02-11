package simulation.SlimeMolds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public class Slime extends Patch {
	public static final Color color = Color.GREEN;
	public static final int priority = getPriority_Slime();
	public static final int state = getState_Slime();

	private int sniff_threshhold;
	private int sniff_angle_max;
	private int wiggle_angle;
	private int wiggle_bias;

	private int orientation;

	public Slime() {
		super(color, state, priority);
	}

	public Slime(int chemDeposit) {
		this();
		setChemical_deposit_count(chemDeposit);
	}

	@Override
	public Cell copy() {
		return new Slime();
	}

	@Override
	public void act(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		if (this.getChemical_deposit_count() >= sniff_threshhold) {
			Queue<Location> targetNeighborsLoc = orderNeighbors(currentSociety, loc, neighborsLoc);
			boolean moved = false;
			boolean shouldTurn = false;
			while(!shouldTurn || !targetNeighborsLoc.isEmpty()){
				Location tempTargetLoc = targetNeighborsLoc.poll();
				if(currentSociety.get(tempTargetLoc).getState() == getState_Patch()){
					moved = newSociety.tryToSwap(loc, tempTargetLoc, getState_Patch());
					if (moved){
						shouldTurn = true;
					}
				} else if (currentSociety.get(tempTargetLoc).getState() == getState_Patch()){
					shouldTurn = true;
				} else {
					moved = newSociety.tryToSwap(loc, tempTargetLoc, getState_Empty());
					if (moved){
						shouldTurn = true;
					}
				}
				if(shouldTurn){
					orientation +=  calculateAngleDifference(loc, tempTargetLoc); 
				}
				
				if(moved){
					swapChemicalDepositAmounts(currentSociety, loc, tempTargetLoc);
				}
			}
			
			
			
		}

		// drop chemical
		// diffuse chemical on neighbor patches

	}

	private void swapChemicalDepositAmounts(Society currentSociety, Location loc, Location tempTargetLoc) {
		int tempAmount = ((SlimeMoldsCell) currentSociety.get(loc)).getChemical_deposit_count();
		((SlimeMoldsCell) currentSociety.get(loc)).setChemical_deposit_count(((SlimeMoldsCell) currentSociety.get(tempTargetLoc)).getChemical_deposit_count());
		((SlimeMoldsCell) currentSociety.get(tempTargetLoc)).setChemical_deposit_count(tempAmount);
	}

	private Queue<Location> orderNeighbors(Society currentSociety, Location loc, List<Location> neighborsLoc) {
		List<SlimeMoldsCell> targetNeighbors = new ArrayList<SlimeMoldsCell>();
		int maxDeposit = 0;
		for (Location neighborLoc : neighborsLoc) {
			SlimeMoldsCell neighbor = (SlimeMoldsCell) currentSociety.get(neighborLoc);
			double angleDifference = calculateAngleDifference(loc, neighborLoc);

			if (Math.abs(angleDifference) <= sniff_angle_max && neighbor.getChemical_deposit_count() >= maxDeposit) {
				maxDeposit = neighbor.getChemical_deposit_count();
				targetNeighbors.add(neighbor);
			}
		}
		
		Collections.sort(targetNeighbors, new SlimeMoldsCellComparator());
		Queue<Location> targetNeighborsLoc = new LinkedList<Location>();
		for (SlimeMoldsCell c : targetNeighbors){
			targetNeighborsLoc.add(currentSociety.getKeyFromValue(c));
		}
		
		return targetNeighborsLoc;
	}

	private double calculateAngleDifference(Location loc, Location neighborLoc) {
		double distance = loc.getPoint().distance(neighborLoc.getPoint());
		double distanceX = neighborLoc.getX() - loc.getX();
		double angle = (Math.toDegrees(Math.acos(distanceX / distance))) - (orientation);

		if (angle > 180) {
			angle -= 360;
			;
		}

		return angle;
	}

	public int getSniff_threshhold() {
		return sniff_threshhold;
	}

	public void setSniff_threshhold(int sniff_threshhold) {
		this.sniff_threshhold = sniff_threshhold;
	}

	public int getSniff_angle_max() {
		return sniff_angle_max;
	}

	public void setSniff_angle_max(int sniff_angle) {
		this.sniff_angle_max = sniff_angle;
	}

	public int getWiggle_angle() {
		return wiggle_angle;
	}

	public void setWiggle_angle(int wiggle_angle) {
		this.wiggle_angle = wiggle_angle;
	}

	public int getWiggle_bias() {
		return wiggle_bias;
	}

	public void setWiggle_bias(int wiggle_bias) {
		this.wiggle_bias = wiggle_bias;
	}

}
