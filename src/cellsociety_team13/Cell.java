package cellsociety_team13;

import java.util.Collection;
import java.util.Random;

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
		return this.priority - c.priority;
	}

	public abstract Cell copy();
	
	public int getPriority(){
		return priority;
	}
	
	public boolean swapWithRandomTarget(Society currentSociety, Society newSociety, Location loc, Collection<Location> eligibleLocs, Integer targetCount, Integer targetState){
		Random rand = new Random();
		int pick = rand.nextInt(targetCount) + 1;
		for(Location neighborLoc : eligibleLocs){
			if(currentSociety.get(neighborLoc).getState() == targetState){
				pick--;
				if (pick == 0){
					if(newSociety.tryToSwap(loc, neighborLoc, targetState)){
						return true;
					} else {
						pick++;
					}
				}
			}
		}
		return false;
	}
	
	public abstract int getDefaultEmptyState();
}