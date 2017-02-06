package simulation.PredatorPrey;

import java.util.List;
import java.util.Random;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_SharkCell extends PredatorPrey_CreatureCell {
	public static final Color sharkColor = Color.YELLOW;
	public static final int turnsToReproduce = 7;
	private int energy;

	public PredatorPrey_SharkCell() {
		super(sharkColor, 2, turnsToReproduce);
		energy = 5;
	}
	
	public PredatorPrey_SharkCell(int initialTurns, int initialEnergy) {
		super(sharkColor, 2, turnsToReproduce);
		energy = initialEnergy;
		this.setTurns(initialTurns);
	}
	
	public int getEnergy(){
		return this.energy;
	}

	@Override
	public Cell change(int n) {
		return null;
	}

	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) {
		energy--;
		turnPasses();

		countNeighbors(neighborList);

		if (this.getFishNeighbors() != 0) {
			currentCell = hunt(neighborList, this.getFishNeighbors());
		} else {
			if (energy == 0) {
				((PredatorPrey_CreatureCell) currentCell).leaving();
				return new PredatorPrey_WaterCell();
			} else {
				currentCell = checkMove(neighborList);
			}
		}
		return this;
	}

	private Cell hunt(List<Cell> neighborList, int fishCount) {
		Random rand = new Random();
		int pick = rand.nextInt(fishCount);
		for (Cell neighbor : neighborList) {
			if (neighbor instanceof PredatorPrey_FishCell) {
				if (pick == 0) {
					eatFish(((PredatorPrey_FishCell) neighbor));
					break;
				}
				pick--;
			}
		}
		return tryToReproduce();

	}
	
	private void eatFish(PredatorPrey_FishCell fish) {
		fish.becomingShark(this);
	}

	protected Cell tryToReproduce() {
		if (getReproduce()) {
			return new PredatorPrey_SharkCell();
		}
		return new PredatorPrey_WaterCell();

	}

}
