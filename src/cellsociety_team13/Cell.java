package cellsociety_team13;
import java.util.List;

import javafx.scene.paint.Color;

public abstract class Cell {
	private Color state;

	public Cell(Color colorInput){
		state = colorInput;
	}

	public Color getState(){
		return state;
	}
	
	public abstract Cell change(int n);
	
	public abstract boolean isNotEmpty();

	public abstract Cell surroundChange(Cell currentCell, List<Cell> neighborList);
}