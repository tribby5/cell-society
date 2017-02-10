package simulation.PredatorPrey;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class Shark extends SeaAnimal{
	public static final Color color = Color.GREY;
	public static final int priority = 0;
	public static final int state = getState_Shark();
	
	public Shark() {
		super(color, state, priority);
	}

	
	@Override
	public Cell copy() {
		return new Shark();
	}

}
