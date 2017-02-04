package cellsociety_team13;
import javafx.scene.paint.Color;

public abstract class Cell {
	private Color state;

	public Cell(Color colorInput){
		this.state = colorInput;
	}

	public Color getState(){
		return this.state;
	}		
}