package simulation.PredatorPrey;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import referees.NoLocator;

public class PredatorPrey extends NoLocator {

	/*
	 * Rules:
	 * 
	 * only side Neighbors
	 * 
	 * Fish: - will move to a random adjacent water cell - if enough energy,
	 * places new fish where it was (reset energy)
	 * 
	 * Shark: - will eat one random neighbor fish - gains energy of fish - else:
	 * will move to a random adjacent water cell - at zero energy, it dies -
	 * 
	 * Reproduction: - fish or shark will reproduce if they have survived enough
	 * turns - new fish or shark will be placed in random neighbor water cell,
	 * if one is available
	 * 
	 * 
	 */

	private List<Cell> CELLS = Arrays.asList(
			new Cell[] { getPredatorPrey_WaterCell(), getPredatorPrey_FishCell(), getPredatorPrey_SharkCell() });
	public static final boolean torodialWorld = true;
	public static final boolean vertexNeighbors = false;
	private static final Integer REPRODUCE_TURN_NUMBER_SHARK = 8;
	private static final Integer REPRODUCE_TURN_NUMBER_FISH = 4;
	private static final int INITIAL_SHARK_ENERGY = 5;
	private static final Integer FISH_NUTRITIONAL_ENERGY = 2;
	private Map<Location, Cell> grid;
	private Set<Location> becomingShark;
	private Set<Location> becomingFish;
	private Set<Location> becomingWater;
	private Map<Location, Integer> turnCounts;
	private Map<Location, Integer> sharkEnergyCounts;

	public PredatorPrey() {
		super(vertexNeighbors, torodialWorld);
		turnCounts = new HashMap<Location, Integer>();
		sharkEnergyCounts = new HashMap<Location, Integer>();
	}

	private Cell getPredatorPrey_SharkCell() {
		return new PredatorPrey_SharkCell();
	}

	private Cell getPredatorPrey_FishCell() {
		return new PredatorPrey_FishCell();
	}

	private Cell getPredatorPrey_WaterCell() {
		return new PredatorPrey_WaterCell();
	}

	@Override
	public void giveSociety(Society soc) {
		Map<Location, Cell> newGrid = new HashMap<>();
		grid = soc.getGrid();
		becomingShark = new HashSet<Location>();
		becomingWater = new HashSet<Location>();
		becomingFish = new HashSet<Location>();

		
		// shark move first
		for (Location loc : grid.keySet()) {
			if (grid.get(loc) instanceof PredatorPrey_SharkCell) {
				Cell currentCell = grid.get(loc);
				List<Cell> neighborList = pickNeighbors(soc, loc);
				List<Location> neighborLocList = soc.getRawSideNeighbors(loc);
				Cell updatedCell = judge((PredatorPrey_CreatureCell) currentCell, loc, neighborList, neighborLocList);

				newGrid.put(loc, updatedCell);
			}
		}
		
		// then fish
		for (Location loc : grid.keySet()) {
			if (grid.get(loc) instanceof PredatorPrey_FishCell) {
				Cell currentCell = grid.get(loc);

				if (becomingShark.contains(loc)) {
					newGrid.put(loc, new PredatorPrey_SharkCell());
				} else {
					List<Cell> neighborList = pickNeighbors(soc, loc);
					List<Location> neighborLocList = soc.getRawSideNeighbors(loc);
					Cell updatedCell = judge((PredatorPrey_CreatureCell) currentCell, loc, neighborList,
							neighborLocList);
					newGrid.put(loc, updatedCell);
				}

			}
		}
		
		
		// then update water
		for (Location loc : grid.keySet()) {
			if (grid.get(loc) instanceof PredatorPrey_WaterCell) {
				Cell currentCell = grid.get(loc);

				if (becomingShark.contains(loc)) {
					newGrid.put(loc, new PredatorPrey_SharkCell());
				} else if (becomingFish.contains(loc)) {
					newGrid.put(loc, new PredatorPrey_FishCell());
				} else {
					newGrid.put(loc, currentCell);
				}

			}
		}
		grid = newGrid;
	}

	public Cell judge(PredatorPrey_CreatureCell currentCell, Location loc, List<Cell> neighborList,
			List<Location> neighborLocList) {
		int turns = 0;
		if (turnCounts.containsKey(loc)) {
			turns = turnCounts.get(loc);
		}
		turnCounts.put(loc, turns + 1);

		int[] neighborCount = countNeighbors(neighborLocList);

		if (currentCell instanceof PredatorPrey_SharkCell) {
			int energyCount = INITIAL_SHARK_ENERGY;
			if (sharkEnergyCounts.containsKey(loc)) {
				energyCount = sharkEnergyCounts.get(loc);
			}
			sharkEnergyCounts.put(loc, energyCount - 1);

			if (neighborCount[1] != 0) {
				return hunt(((PredatorPrey_SharkCell) currentCell), loc, neighborList, neighborLocList,
						neighborCount[1]);
			} else if (energyCount <= 0) {
				becomingWater.add(loc);
				sharkEnergyCounts.remove(loc);
				return new PredatorPrey_WaterCell();
			}
		}

		if (neighborCount[0] != 0) {
			return move(currentCell, loc, neighborList, neighborLocList, neighborCount[0]);
		} else if (currentCell instanceof PredatorPrey_SharkCell) {
			return checkIfDead(currentCell, loc);
		}

		return currentCell;
	}

	private Cell checkIfDead(PredatorPrey_CreatureCell currentCell, Location loc) {
		if (sharkEnergyCounts.get(loc) == 0) {
			becomingWater.add(loc);
			return new PredatorPrey_WaterCell();
		}
		return currentCell;
	}

	public Cell hunt(PredatorPrey_SharkCell shark, Location loc, List<Cell> neighborList,
			List<Location> neighborLocList, int fishCount) {
		Random rand = new Random();
		int pick = rand.nextInt(fishCount);
		for (Location neighborLoc : neighborLocList) {
			if (grid.get(neighborLoc) instanceof PredatorPrey_FishCell) {
				if (pick == 0) {
					becomingShark.add(neighborLoc);
					updateTurnCounts(loc, neighborLoc);
					sharkEnergyCounts.put(neighborLoc, sharkEnergyCounts.get(loc) + FISH_NUTRITIONAL_ENERGY);
					sharkEnergyCounts.remove(loc);
					return tryToReproduce(shark, loc, neighborLoc);
				}
				pick--;
			}
		}
		return null;

	}

	private Cell tryToReproduce(PredatorPrey_CreatureCell creature, Location loc, Location neighborLoc) {

		if ((creature instanceof PredatorPrey_FishCell && turnCounts.get(neighborLoc) < REPRODUCE_TURN_NUMBER_FISH)
				|| (creature instanceof PredatorPrey_SharkCell && turnCounts.get(neighborLoc) < REPRODUCE_TURN_NUMBER_SHARK)) {
				becomingWater.add(neighborLoc);
				return new PredatorPrey_WaterCell();
		} else {
			if (creature instanceof PredatorPrey_SharkCell) {
				sharkEnergyCounts.put(loc, sharkEnergyCounts.get(neighborLoc) / 2);
				sharkEnergyCounts.put(neighborLoc, sharkEnergyCounts.get(neighborLoc) / 2 );
			}
			turnCounts.put(neighborLoc, 0);
			return creature;
		}

	}

	private Cell move(PredatorPrey_CreatureCell currentCell, Location loc, List<Cell> neighborList,
			List<Location> neighborLocList, int waterCount) {
		Random rand = new Random();
		int pick = rand.nextInt(waterCount);
		for (Location neighborLoc : neighborLocList) {
			if (openWater(neighborLoc)) {
				if (pick == 0) {
					if (currentCell instanceof PredatorPrey_SharkCell) {
						becomingShark.add(neighborLoc);
						sharkEnergyCounts.put(neighborLoc, sharkEnergyCounts.get(loc) - 1);
						sharkEnergyCounts.remove(loc);
					} else if (currentCell instanceof PredatorPrey_FishCell) {
						becomingFish.add(neighborLoc);
					}
					updateTurnCounts(loc, neighborLoc);
					return tryToReproduce(currentCell, loc, neighborLoc);
				}
				pick--;
			}
		}
		return currentCell;

	}

	private void updateTurnCounts(Location loc, Location neighborLoc) {
		turnCounts.put(neighborLoc, turnCounts.get(loc));
		turnCounts.put(loc, 0);
	}

	

	private int[] countNeighbors(List<Location> neighborLocList) {
		int[] counts = new int[2];
		for (Location cLoc : neighborLocList) {
			if (edibleFish(cLoc)) {
				counts[1]++;
			} else if (openWater(cLoc)) {
				counts[0]++;
			}
		}
		return counts;
	}

	private boolean openWater(Location loc) {
		return grid.get(loc) instanceof PredatorPrey_WaterCell && !becomingShark.contains(loc)
				&& !becomingFish.contains(loc);
	}
	
	private boolean edibleFish(Location loc) {
		return grid.get(loc) instanceof PredatorPrey_FishCell && !becomingShark.contains(loc)
				&& !becomingWater.contains(loc);
	}

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	@Override
	public Cell judge(Cell currentCell, List<Cell> neighborList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Location, Cell> getGrid() {
		return grid;
	}

}
