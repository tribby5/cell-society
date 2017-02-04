package cells;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public abstract class TwoStateCell extends Cell{
	private boolean stateBool;
	
	public TwoStateCell(Color colorInput, boolean stateInput){
		super(colorInput);
		this.stateBool = stateInput;
	}
	
	
	
	
}
