package simulation.Fire;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Dead extends FireCell{
	public static final Color color = Color.BLACK; //TODO: color imports
	public static final int priority = 1;
	public static final int state = getState_Dead();
	
	public Dead() {
		super(color, state, priority);
	}

	

	@Override
	public FireCell act(List<Integer> neighborCount){
		return new Dead();
	}



	@Override
	public Cell copy() {
		return new Dead();
	}

}
