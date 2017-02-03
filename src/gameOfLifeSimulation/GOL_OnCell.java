package gameOfLifeSimulation;

import cells.TwoStateCell;
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
	
}
