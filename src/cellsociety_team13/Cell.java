package cellsociety_team13;

import java.util.Collection;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * Class which defines the basic attributes of a cell. A cell holds the representation of
 * state of the cell as well as the color it will appear on the screen. The cell is the
 * basic building block for creating a dynamic grid.
 * Dependencies: Needs a Location instance to be drawn because that contains the position
 * and shape of the actual cell. 
 * Example of use: Pair with a Location instance and draw on a grid by combining the two. Can
 * also compare different cells to each other based on state. 
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13), Matthew Tribby (mrt28)
 *
 */
public abstract class Cell implements Comparable<Cell>{
	private Color color;
	private int totalStates;
	private int state;
	private int priority;
	
	/**
	 * Creates the basics of a Cell. The constructor sets all the inputs to their
	 * respective instance variables.
	 * @param inputColor Color for which the cell should be colored when displayed
	 * @param cellState current state of the cell
	 * @param inputTotalStates The total states in the simulation
	 * @param priority Priority ranking of this cell relative to other cells
	 */
	public Cell(Color inputColor, int cellState ,int inputTotalStates, int priority){
		setColor(inputColor);
		this.totalStates = inputTotalStates;
		this.state = cellState;
		this.priority = priority;
	}

	/**
	 * Returns the current color
	 * @return Color instance
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Sets the current color to the input color
	 * @param inputColor Color instance
	 */
	public void setColor(Color inputColor){
		this.color = inputColor;
	}

	/**
	 * Returns the current state
	 * @return Integer representation of state
	 */
	public int getState(){
		return state;
	}
	
	/**
	 * Sets the current state
	 * @param newState Integer representation of desired new state
	 */
	public void setState(int newState){
		state = newState;
	}
	
	/**
	 * Returns the highest state 
	 * @return the total number of states
	 */
	public int getMaxState(){
		return totalStates;
	}
	
	/**
	 * Compares a cell with another by means of priority. A positive result means the instance cell has higher
	 * priority, a 0 means that they have the same priority and a negative number means that
	 * the instance cell has lower priority.
	 * @param c Cell to be compared
	 * @return Integer representation of the difference in priority of the Cell instance
	 * and the input Cell instance. 
	 */
	public int compareTo(Cell c){
		return this.priority - c.priority;
	}
	
	/**
	 * Method that is necessary to create unique instance of a Cell. Used to ensure that
	 * when creating a variable number of cells that they are not the same instance.
	 * @return a instance of the Cell
	 */
	public abstract Cell copy();
	
	/**
	 * Return the integer representation of priority of the cell.
	 * @return integer representing priority
	 */
	public int getPriority(){
		return priority;
	}
	
	/**
	 * Randomly decides where a cell should swap with based on its location,
	 * its possible locations. Returns true if swap was successful.
	 * @param currentSociety current version of the Society instance
	 * @param newSociety The next version of the Society instance, used for updating
	 * @param loc Location of the cell in the grid
	 * @param eligibleLocs Collection of possible locations that are reachable
	 * @param targetCount Number of instances of targets of state targetState in eligibleLocs
	 * @param targetState State that you want to swap with
	 * @return
	 */
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
	
	/**
	 * Integer representation of an empty cell
	 * @return int representation of empty cell
	 */
	public abstract int getDefaultEmptyState();
}