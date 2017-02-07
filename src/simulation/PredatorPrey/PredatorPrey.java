package simulation.PredatorPrey;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import referees.NoLocator;

public class PredatorPrey extends NoLocator{
	
	/* Rules:
	 * 
	 * only side Neighbors
	 * 
	 * Fish:
	 *  - will move to a random adjacent water cell
	 *     - if enough energy, places new fish where it was (reset energy) 
	 * 
	 * Shark:
	 * - will eat one random neighbor fish
	 * 		- gains energy of fish
	 * - else: will move to a random adjacent water cell
	 * - at zero energy, it dies
	 * - 
	 * 
	 * Reproduction:
	 * - fish or shark will reproduce if they have survived enough turns
	 * - new fish or shark will be placed in random neighbor water cell, if one is available
	 * 
	 * 
	 */

	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			getPredatorPrey_WaterCell(),
			getPredatorPrey_FishCell(),
			getPredatorPrey_SharkCell()
	});
	public static final boolean torodialWorld = true;
	public static final boolean vertexNeighbors = false;
	
	public PredatorPrey(){
		super(vertexNeighbors, torodialWorld);
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
	
	private Map<Location, Cell> grid;

	@Override
	public Cell judge(Cell currentCell, List<Cell> neighborList) {
		return currentCell.surroundChange(currentCell, neighborList);
	}



	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}
}
