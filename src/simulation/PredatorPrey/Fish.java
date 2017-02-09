package simulation.PredatorPrey;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class Fish extends SeaAnimal{
	public static final Color color = Color.GREEN;
	public static final int priority = 1;
	public static final int state = getState_Fish();

	
	public Fish() {
		super(color, state, priority);
	}

	@Override
	public Cell updateCell(Map<Location, Cell> grid, List<Location> neighbors, List<Integer> neighborCount) {
		return this;
	}


	@Override
	public Cell copy() {
		return new Fish();
	}

}
