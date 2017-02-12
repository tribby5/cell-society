package simulation.ForagingAnts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public class Ant {
	public static final double N = 10.0;
	public static final double K = 0.001;
	private boolean hasFoodItem;
	private double orientation_angle;
	private double wiggle_angle_max;

	private ForagingAntsCell home;
	
	public Ant(ForagingAntsCell inputHome){
		home = inputHome;
	}

	public static final Color color = Color.RED; // TODO: color imports

	public void act(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc) {
		if (hasFoodItem) {
			headToGoal(currentSociety, newSociety, loc, neighborsLoc, ForagingAntsCell.HOME_PHEROMONE_LABEL);
		} else {
			headToGoal(currentSociety, newSociety, loc, neighborsLoc, ForagingAntsCell.FOOD_PHEROMONE_LABEL);
		}
	}

	private void headToGoal(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc,
			String label) {
		ifAtStartTurnToMaxPheromone(currentSociety, loc, neighborsLoc, label);

		Location targetLoc = selectTarget(currentSociety, loc, neighborsLoc, label);

		if (targetLoc != null) {
			dropPheromones(currentSociety, neighborsLoc, label);

			orientation_angle += loc.calculateAngleDifference(targetLoc, orientation_angle);

			moveToTarget(newSociety, loc, targetLoc);

			if (label.equals(ForagingAntsCell.HOME_PHEROMONE_LABEL)
					&& home.getState() == ForagingAntsCell.getState_FoodSource()) {
				hasFoodItem = true;
			} else if (label.equals(ForagingAntsCell.FOOD_PHEROMONE_LABEL)
					&& home.getState() == ForagingAntsCell.getState_Nest()) {
				hasFoodItem = false;
			}
		}

	}

	private void ifAtStartTurnToMaxPheromone(Society currentSociety, Location loc, List<Location> neighborsLoc,
			String label) {
		if ((label.equals(ForagingAntsCell.HOME_PHEROMONE_LABEL) && home.getState() == ForagingAntsCell.getState_FoodSource())
				|| (label.equals(ForagingAntsCell.FOOD_PHEROMONE_LABEL)
						&& home.getState() == ForagingAntsCell.getState_Nest())) {
			turnToMaxPheromone(currentSociety, loc, neighborsLoc, label);
		}
	}

	private void turnToMaxPheromone(Society currentSociety, Location loc, List<Location> neighborsLoc,
			String pheromoneLabel) {
		Location targetLoc = pickViaMaxPheromoneLevel(currentSociety, neighborsLoc, pheromoneLabel);
		orientation_angle += loc.calculateAngleDifference(targetLoc, orientation_angle);
	}

	private Location pickViaMaxPheromoneLevel(Society currentSociety, List<Location> eligibleNeighborsLoc,
			String label) {
		double maxCount = -1;
		Location targetLoc = null;
		for (Location neighborLoc : eligibleNeighborsLoc) {
			if (label.equals(ForagingAntsCell.HOME_PHEROMONE_LABEL)) {
				if (((ForagingAntsCell) currentSociety.get(neighborLoc)).getHome_pheromone_amount() > maxCount) {
					maxCount = ((ForagingAntsCell) currentSociety.get(neighborLoc)).getHome_pheromone_amount();
					targetLoc = neighborLoc;
				}
			}
			if (label.equals(ForagingAntsCell.FOOD_PHEROMONE_LABEL)) {
				if (((ForagingAntsCell) currentSociety.get(neighborLoc)).getFood_pheromone_amount() > maxCount) {
					maxCount = ((ForagingAntsCell) currentSociety.get(neighborLoc)).getHome_pheromone_amount();
					targetLoc = neighborLoc;
				}
			}
		}
		return targetLoc;
	}

	private Location selectTarget(Society currentSociety, Location loc, List<Location> neighborsLoc, String label) {
		List<Location> eligibleNeighborsLoc = extractEligibleNeighbors(currentSociety, loc, neighborsLoc,
				wiggle_angle_max);
		if (eligibleNeighborsLoc.size() == 0) {
			eligibleNeighborsLoc = extractEligibleNeighbors(currentSociety, loc, neighborsLoc, 180.0);
		}

		System.out.println(eligibleNeighborsLoc.size());
		
		if (eligibleNeighborsLoc.size() == 0) {
			return null;
		} else if (eligibleNeighborsLoc.size() == 1){
			return eligibleNeighborsLoc.get(0);
		}

		Location target = null;

		if (label.equals(ForagingAntsCell.FOOD_PHEROMONE_LABEL)) {
			target = pickViaProbability(currentSociety, eligibleNeighborsLoc, label);
		} else if (label.equals(ForagingAntsCell.HOME_PHEROMONE_LABEL)) {
			target = pickViaMaxPheromoneLevel(currentSociety, eligibleNeighborsLoc, label);
		}

		return target;
	}

	private List<Location> extractEligibleNeighbors(Society currentSociety, Location loc, List<Location> neighborsLoc,
			double wiggle_angle_max2) {
		List<Location> eligibleNeighborsLoc = new ArrayList<Location>();
		for (Location neighborLoc : neighborsLoc) {
			if (currentSociety.get(neighborLoc).getState() != ForagingAntsCell.getState_Obstacle()
					&& !((ForagingAntsCell) currentSociety.get(neighborLoc)).atAntCapacity()
					&& Math.abs(loc.calculateAngleDifference(neighborLoc, orientation_angle)) <= wiggle_angle_max) {
				eligibleNeighborsLoc.add(neighborLoc);
			}
		}
		return eligibleNeighborsLoc;
	}

	private Location pickViaProbability(Society currentSociety, List<Location> eligibleNeighborsLoc, String label) {
		double[] probabilities = calculateProbabilities(currentSociety, eligibleNeighborsLoc, label);
		Random rand = new Random();
		double pick = rand.nextDouble() * probabilities[-1];
		for (int i = 0; i < probabilities.length; i++) {
			if (pick < probabilities[i]) {
				return eligibleNeighborsLoc.get(i);
			}
		}
		return null;
	}

	private double[] calculateProbabilities(Society currentSociety, List<Location> eligibleNeighborsLoc, String label) {
		double[] probabilities = new double[eligibleNeighborsLoc.size()];
		int i = 0;
		for (Location neighborLoc : eligibleNeighborsLoc) {
			if (label.equals(ForagingAntsCell.HOME_PHEROMONE_LABEL)) {
				probabilities[i] = Math
						.pow((K + ((ForagingAntsCell) currentSociety.get(neighborLoc)).getHome_pheromone_amount()), N);
			}
			if (label.equals(ForagingAntsCell.FOOD_PHEROMONE_LABEL)) {
				probabilities[i] = Math
						.pow((K + ((ForagingAntsCell) currentSociety.get(neighborLoc)).getFood_pheromone_amount()), N);
			}
			i++;
		}
		for (int j = 1; j < probabilities.length; j++) {
			probabilities[j] += probabilities[j - 1];
		}
		return probabilities;
	}

	private void dropPheromones(Society currentSociety, List<Location> neighborsLoc, String label) {
		if (label.equals(ForagingAntsCell.FOOD_PHEROMONE_LABEL)
				&& home.getState() == ForagingAntsCell.getState_Nest()) {
			// top off nest
		} else if (label.equals(ForagingAntsCell.HOME_PHEROMONE_LABEL)
				&& home.getState() == ForagingAntsCell.getState_FoodSource()) {
			// top off food source
		} else {
			double des = getMaxPheromoneLevel(currentSociety, neighborsLoc, label) - 2;
			if (label.equals(ForagingAntsCell.FOOD_PHEROMONE_LABEL)) {
				des -= home.getFood_pheromone_amount();
				if (des > 0) {
					home.setFood_pheromone_amount(home.getFood_pheromone_amount() + des);
				}
			} else if (label.equals(ForagingAntsCell.HOME_PHEROMONE_LABEL)) {
				des -= home.getHome_pheromone_amount();
				if (des > 0) {
					home.setHome_pheromone_amount(home.getHome_pheromone_amount() + des);
				}
			}

		}

	}

	private void moveToTarget(Society newSociety, Location loc, Location targetLoc) {
		((ForagingAntsCell) newSociety.get(loc)).removeAnt(this);
		((ForagingAntsCell) newSociety.get(targetLoc)).addAnt(this);

	}

	private double getMaxPheromoneLevel(Society currentSociety, List<Location> neighborsLoc, String label) {
		double maxCount = 0.0;
		for (Location neighborLoc : neighborsLoc) {
			if (label.equals(ForagingAntsCell.FOOD_PHEROMONE_LABEL)) {
				if (((ForagingAntsCell) currentSociety.get(neighborLoc)).getHome_pheromone_amount() > maxCount) {
					maxCount = ((ForagingAntsCell) currentSociety.get(neighborLoc)).getHome_pheromone_amount();
				}
			}
			if (label.equals(ForagingAntsCell.HOME_PHEROMONE_LABEL)) {
				if (((ForagingAntsCell) currentSociety.get(neighborLoc)).getFood_pheromone_amount() > maxCount) {
					maxCount = ((ForagingAntsCell) currentSociety.get(neighborLoc)).getHome_pheromone_amount();
				}
			}
		}
		return maxCount;
	}

}
