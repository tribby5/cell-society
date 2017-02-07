package simulation.PredatorPrey;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_WaterCell extends ThreeStateCell {
	public static final Color waterColor = Color.BLUE;


	public PredatorPrey_WaterCell() {
		super(waterColor, 0);
	}

	@Override
	public Cell change(int n) {
		return null;
	}

	@Override
	public boolean isNotEmpty() {
		return false;
	}




	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) {
		return null;
	}




}
