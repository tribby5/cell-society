package simulation.Fire;

import java.util.List;
import java.util.Random;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Alive extends ThreeStateCell{
	
	public static final Color aliveColor = Color.GREEN;
	private double probCatch;
	private Random myRandom;

	public FIRE_Alive() {
		super(aliveColor, 2);
		myRandom = new Random();
		probCatch = myRandom.nextDouble();
		// TODO initialize probCatch
	}

	@Override
	public Cell change(int numberOfBurningNeighbors) {
		for (int i = 0; i < numberOfBurningNeighbors; i++){
			if (myRandom.nextDouble() < probCatch){
				return new FIRE_Burning();
			}
		}
		return this;
	}

	@Override
	public boolean isNotEmpty() {
		return true;
	}

	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) {
		return null;
	}
}