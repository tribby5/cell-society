package simulation.GameOfLife;

import java.util.List;

import cells.TwoStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class GOL_OffCell extends TwoStateCell {

	public static final Color offColor = Color.WHITE;


	public GOL_OffCell(){
		super(offColor, false);
	}
	
	@Override
	public Cell change(int n) {
		if (n == 3)
			return new GOL_OnCell(); 
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
