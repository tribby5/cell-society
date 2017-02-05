package simulation.Fire;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Dead extends ThreeStateCell{
	
	public static final Color deadColor = Color.BROWN;

	protected FIRE_Dead() {
		super(deadColor, 0);
	}

	@Override
	public Cell change(int n) {
		// stays dead
		return this;
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