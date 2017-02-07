package simulation.PredatorPrey;

import java.util.List;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_FishCell extends PredatorPrey_CreatureCell {
	public static final Color fishColor = Color.GREEN;
	public static final int turnsToReproduce = 4;

	public PredatorPrey_FishCell() {
		super(fishColor, 1, turnsToReproduce);
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
