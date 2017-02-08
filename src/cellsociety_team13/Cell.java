package cellsociety_team13;

import java.util.List;

import javafx.scene.paint.Color;


public abstract class Cell {
	private Color color;
	private int totalStates;
	private int state;
	
	public Cell(Color inputColor, int cellState ,int inputTotalStates){
		setColor(inputColor);
		this.totalStates = inputTotalStates;
		this.state = cellState;
	}

	public Color getColor(){
		return color;
	}
	
	public void setColor(Color inputColor){
		this.color = inputColor;
	}

	public abstract Cell updateCell(List<Cell> neighbors, List<Integer> neighborCount);
	
	public int getState(){
		return state;
	}
	
	public int getMaxState(){
		return totalStates;
	}
	
		
}