package simulation.Fire;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Dead extends FireCell{
	public static final Color color = Color.BLACK; //TODO: color imports
	public static final int priority = getPriority_Dead();
	public static final int state = getState_Dead();
	
	public Dead() {
		super(color, state, priority);
	}

	

	@Override
	public FireCell act(List<Integer> neighborCount){
		return this;
	}



	@Override
	public Cell copy() {
		return new Dead();
	}

}
