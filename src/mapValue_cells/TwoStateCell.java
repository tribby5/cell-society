package mapValue_cells;

import javafx.scene.paint.Color;

public abstract class TwoStateCell extends Cell{
	private boolean stateBool;
	
	TwoStateCell(Color colorInput, boolean stateInput){
		super(colorInput);
		this.stateBool = stateInput;
	}
	
	
	
	
}
