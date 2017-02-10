package simulation.Segregation;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class O extends SegregationCell{
	public static final Color color = Color.BLUE;
	public static final int priority = 0;
	public static final int state = getState_O();
	
	public O() {
		super(color, state, priority);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Cell copy() {
		return new O();
	}


}
