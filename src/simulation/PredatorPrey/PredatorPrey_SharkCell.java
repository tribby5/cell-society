package simulation.PredatorPrey;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_SharkCell extends ThreeStateCell{
	public static final Color sharkColor = Color.YELLOW;
	public static final int turnsToReproduce = 10;
	private int turns;
	private int energy;

	public PredatorPrey_SharkCell() {
		super(sharkColor, 2);
		energy = 5;
	}

	@Override
	public Cell change(int n) {
		return null;
	}

	@Override
	public boolean isNotEmpty() {
		return true;
	}

	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) { 
		return this;
	}
}

