package simulation.PredatorPrey;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_FishCell extends ThreeStateCell{
	public static final Color fishColor = Color.GREEN;
	public static final int turnsToReproduce = 3;
	private int turns;
	private boolean reproduce;

	public PredatorPrey_FishCell() {
		super(fishColor, 1);
		turns = 0;
		resetReproduce();
	}

	@Override
	public Cell change(int n) {
		return null;
	}
	
	public void reproduce() {
		turns++;
		reproduce = turns >= turnsToReproduce;
	}
	
	public boolean getReproduce(){
		return reproduce;
	}
	
	public void resetReproduce(){
		reproduce = false;
		turns = 0;
	}
	
	public boolean edible(){
		return true;
	}

	@Override
	public boolean isNotEmpty() {
		return true;
	}

	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) {
		turns++;
		reproduce();
		return currentCell;
	}
}
