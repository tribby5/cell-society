package simulation.Fire;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;
import simulation.GameOfLife.GOL_OnCell;

public class FIRE_Burning extends ThreeStateCell{
	
	public static final Color burningColor = Color.RED;

	FIRE_Burning() {
		super(burningColor, 1);
	}

	public FIRE_Burning(Cell oldCell) {
		this();
		//adopt values from oldCell
	}

	@Override
	public Cell change(int n) {
		// only lives one turn
		return new FIRE_Dead(); 
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