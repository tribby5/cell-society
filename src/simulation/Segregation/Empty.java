package simulation.Segregation;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Empty extends SegregationCell {
	public static final Color color = Color.GREY;
	public static final int state = getState_Empty();

	public Empty() {
		super(color, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell updateCell(List<Cell> neighbors, List<Integer> neighborCount) {
		return new Empty();
	}

}
