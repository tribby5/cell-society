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

	}


	@Override
	public Cell change(int n) {
		return null;
	}

	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) {
		return null;
	}

}
