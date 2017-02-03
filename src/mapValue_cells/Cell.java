package mapValue_cells;

import javafx.scene.paint.Color;

public abstract class Cell {
	private Color state;
	
	Cell(Color colorInput){
		this.state = colorInput;
	}
	
	public Color getState(){
		return this.state;
	}
	
	
}
