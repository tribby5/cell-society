package gameOfLifeSimulation;

import cells.TwoStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class GOL_OnCell extends TwoStateCell{
	
	public static final Color onColor = Color.BLACK;

	GOL_OnCell(){
		super(onColor, true);
	}
	
	public GOL_OnCell(GOL_OffCell oldCell){
		this();
		// copy data from oldCell
	}

	@Override
	public Cell change(int n) {
		if (n < 2 || n > 3){
			return new GOL_OffCell(); 
		}
		return this;
	}
	
}
