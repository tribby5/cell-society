package simulation.PredatorPrey;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_WaterCell extends ThreeStateCell {
	public static final Color waterColor = Color.BLUE;
	private boolean becomingShark;
	private boolean becomingFish;
	private int newCreatureTurns;
	private int newSharkEnergy;

	public PredatorPrey_WaterCell() {
		super(waterColor, 0);
		becomingFish = false;
		becomingShark = false;
	}

	@Override
	public Cell change(int n) {
		return null;
	}

	@Override
	public boolean isNotEmpty() {
		return this.checkIfBecomingCreature();
	}

	public void setBecomingCreature(PredatorPrey_CreatureCell motherCell) {
		if (motherCell instanceof PredatorPrey_FishCell) {
			becomingFish = true;
		} else if (motherCell instanceof PredatorPrey_SharkCell) {
			this.newSharkEnergy = ((PredatorPrey_SharkCell)motherCell).getEnergy();
			becomingShark = true;
		}
		this.newCreatureTurns = motherCell.getTurns();
	}

	public boolean checkIfBecomingShark() {
		return becomingShark;
	}

	public boolean checkIfBecomingFish() {
		return becomingFish;
	}

	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) {
		if (checkIfBecomingShark()){
			return new PredatorPrey_SharkCell(newCreatureTurns, newSharkEnergy);
		} else if (checkIfBecomingFish()){
			return new PredatorPrey_FishCell(newCreatureTurns);
		}
		return currentCell;
	}

	public boolean checkIfBecomingCreature() {
		return checkIfBecomingFish() && checkIfBecomingShark();
	}

}
