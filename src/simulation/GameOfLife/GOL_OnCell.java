package simulation.GameOfLife;

import java.util.List;

import cells.TwoStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class GOL_OnCell extends TwoStateCell{
	
	public static final Color onColor = Color.BLACK;

	public GOL_OnCell(){
		super(onColor, true);
	}
	
	@Override
	public Cell change(int n) {
		if (n < 2 || n > 3){
			return new GOL_OffCell(); 
		}
		return this;
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
