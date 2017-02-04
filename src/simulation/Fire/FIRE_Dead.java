package simulation.Fire;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Dead extends ThreeStateCell{
	
	public static final Color deadColor = Color.BROWN;

	protected FIRE_Dead() {
		super(deadColor, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell change(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}