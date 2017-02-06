package simulation.Fire;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Burning extends ThreeStateCell{
	
	public static final Color burningColor = Color.RED;

	public FIRE_Burning() {
		super(burningColor, 1);
	}

	@Override
	public Cell change(int n) {
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