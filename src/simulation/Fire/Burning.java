package simulation.Fire;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Burning extends FireCell{
	public static final Color color = Color.RED;
	public static final int state = getState_Burning();
	
	public Burning() {
		super(color, state);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public Cell updateCell(List<Cell> neighbors, List<Integer> neighborCount) {
		return new Dead();
	}




}
