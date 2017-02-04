package simulation.Fire;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Alive extends ThreeStateCell{
	
	public static final Color aliveColor = Color.GREEN;

	FIRE_Alive() {
		super(aliveColor, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell change(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}