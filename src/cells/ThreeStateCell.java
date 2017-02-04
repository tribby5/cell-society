package cells;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public abstract class ThreeStateCell extends Cell{
	private int trit;

	protected ThreeStateCell(Color colorInput, int stateInput){
		super(colorInput);
		trit = stateInput;
	}
	
}
