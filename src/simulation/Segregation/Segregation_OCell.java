package simulation.Segregation;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Segregation_OCell extends ThreeStateCell{
	public static final Color oColor = Color.RED;

	public Segregation_OCell() {
		super(oColor, 1);
	}

	@Override
	public Cell change(int n) {
		return new Segregation_EmptyCell();
	}

	@Override
	public boolean isNotEmpty() {
		return true;
	}

	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) {
		return null;
	}
}
