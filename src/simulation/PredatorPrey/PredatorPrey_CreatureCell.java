package simulation.PredatorPrey;

import java.util.List;
import java.util.Random;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public abstract class PredatorPrey_CreatureCell extends ThreeStateCell {
	private int turnsToReproduce;
	private int turns;
	private boolean reproduce;
	private boolean leaving;
	private int fishNeighbors;
	private int waterNeighbors;

	public PredatorPrey_CreatureCell(Color colorInput, int stateInput, int turnsToReproduce) {
		super(colorInput, stateInput);
		this.turnsToReproduce = turnsToReproduce;
		this.leaving = false;
		resetReproduce();
	}

	public void turnPasses() {
		turns++;
		reproduce = (turns >= turnsToReproduce);
	}

	public boolean getReproduce() {
		return reproduce;
	}

	public void resetReproduce() {
		reproduce = false;
		turns = 0;
	}
	
	public void setTurns(int t){
		this.turns = t;
	}
	
	public int getTurns(){
		return this.turns;
	}

	@Override
	public boolean isNotEmpty() {
		return !this.getLeaving();
	}

	protected void leaving() {
		this.leaving = true;
	}

	private boolean getLeaving() {
		return leaving;
	}
	
	public int getFishNeighbors() {
		return fishNeighbors;
	}

	public void setFishNeighbors(int fishNeighbors) {
		this.fishNeighbors = fishNeighbors;
	}

	public int getWaterNeighbors() {
		return waterNeighbors;
	}

	public void setWaterNeighbors(int waterNeighbors) {
		this.waterNeighbors = waterNeighbors;
	}
	
	protected void countNeighbors(List<Cell> neighborList) {
		for (Cell c : neighborList) {
			if (fishCell(c)) {
				setFishNeighbors(getFishNeighbors() + 1);
			} else if (!c.isNotEmpty()) {
				setWaterNeighbors(getWaterNeighbors() + 1);
			}
		}
	}

	
	private boolean fishCell(Cell c) {
		if (c instanceof PredatorPrey_FishCell){
			return !((PredatorPrey_FishCell) c).checkIfBecomingShark();
		}
		return false;
	}

	public Cell checkMove(List<Cell> neighborList) {
		if (waterNeighbors != 0) {
			Cell cellInOldSpot = tryToMove(neighborList);
			return cellInOldSpot;
		}
		return this;
	}

	private Cell tryToMove(List<Cell> neighborList) {
		Random rand = new Random();
		int pos = waterNeighbors;
		for (Cell neighbor : neighborList) {
			if (neighbor instanceof PredatorPrey_WaterCell) {
				if(((PredatorPrey_WaterCell) neighbor).checkIfBecomingCreature()){
					pos--;
					neighborList.remove(neighbor);
				}
			}
		}
		move((PredatorPrey_WaterCell)neighborList.get(rand.nextInt(pos)));
		return tryToReproduce();

	}
	
	private void move(PredatorPrey_WaterCell water) {
		water.setBecomingCreature(this);
		leaving();
	}

	protected abstract Cell tryToReproduce();
}
