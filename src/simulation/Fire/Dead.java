package simulation.Fire;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Dead extends FireCell{
	public static final Color color = Color.BLACK;
	public static final int state = getState_Dead();
	
	public Dead() {
		super(color, state);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public Cell updateCell(List<Cell> neighbors, List<Integer> neighborCount) {
		return new Dead();
	}

}
