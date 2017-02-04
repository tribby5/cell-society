package gameOfLifeSimulation;

import cells.TwoStateCell;
import javafx.scene.paint.Color;

public class GOL_OffCell extends TwoStateCell {
	
	public static final Color offColor = Color.BLACK;

	public GOL_OffCell(){
		super(offColor, false);
	}
	
	public GOL_OffCell(GOL_OnCell oldCell){
		this();
		// copy data from oldCell
	}
}
