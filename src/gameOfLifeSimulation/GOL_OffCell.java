package gameOfLifeSimulation;

import cells.TwoStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class GOL_OffCell extends TwoStateCell {
	
	public static final Color offColor = Color.BLACK;

	GOL_OffCell(){
		super(offColor, false);
	}
	
	public GOL_OffCell(GOL_OnCell oldCell){
		this();
		// copy data from oldCell
	}

	@Override
	public Cell change(int n) {
		if (n == 3){
			return new GOL_OnCell(); 
		}
		return this;
	}
}
