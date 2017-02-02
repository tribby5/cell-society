package mapValue_cells;

import javafx.scene.paint.Color;

public abstract class Cell {
	private Color state;
	
	Cell(Color colorInput){
		this.state = colorInput;
	}
	
	Color getState(){
		return this.state;
	}
	
	
}
