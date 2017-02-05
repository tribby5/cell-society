package simulation.Fire;

import java.util.List;
import java.util.Random;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Alive extends ThreeStateCell{
	
	public static final Color aliveColor = Color.GREEN;
	private double probCatch;

	FIRE_Alive() {
		super(aliveColor, 2);
		// TODO initialize probCatch
	}

	@Override
	public Cell change(int numberOfBurningNeighbors) {
		Random rand = new Random();
		for (int i = 0; i < numberOfBurningNeighbors; i++){
			if (rand.nextDouble() < probCatch){
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