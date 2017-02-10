package cellsociety_team13;

import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;


public abstract class Cell implements Comparable<Cell>{
	private Color color;
	private int totalStates;
	private int state;
	private int priority;
	
	public Cell(Color inputColor, int cellState ,int inputTotalStates, int priority){
		setColor(inputColor);
		this.totalStates = inputTotalStates;
		this.state = cellState;
		this.priority = priority;
	}

	public Color getColor(){
		return color;
	}
	
	public void setColor(Color inputColor){
		this.color = inputColor;
	}

	public int getState(){
		return state;
	}
	
	public int getMaxState(){
		return totalStates;
	}
	
	public int compareTo(Cell c){
		return c.priority - this.priority;
	}

	public abstract Cell copy();
	
		
}