package simulation.PredatorPrey;

import java.util.List;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_FishCell extends PredatorPrey_CreatureCell {
	public static final Color fishColor = Color.GREEN;
	public static final int turnsToReproduce = 4;
	private boolean becomingShark;
	private int newSharkTurns;
	private int newSharkEnergy;

	public PredatorPrey_FishCell() {
		super(fishColor, 1, turnsToReproduce);
		becomingShark = false;
	}

	public PredatorPrey_FishCell(int initialTurns) {
		this();
		this.setTurns(initialTurns);
	}

	@Override
	public Cell change(int n) {
		return null;
	}

	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) {
		if (becomingShark){
			return new PredatorPrey_SharkCell(newSharkTurns, newSharkEnergy);
		}
		countNeighbors(neighborList);
		return checkMove(neighborList);
	}

	protected Cell tryToReproduce() {
		if (getReproduce()) {
			return new PredatorPrey_FishCell();
		}
		return new PredatorPrey_WaterCell();

	}
	
	public void becomingShark(PredatorPrey_SharkCell shark){
		this.newSharkTurns = shark.getTurns();
		this.becomingShark = true;
		this.newSharkEnergy = shark.getEnergy() + 2;
	}

	public boolean checkIfBecomingShark() {
		return becomingShark;
	}
}
