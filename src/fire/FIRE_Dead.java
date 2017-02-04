package fire;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Dead extends ThreeStateCell{
	
	public static final Color offColor = Color.BROWN;

	public FIRE_Dead() {
		super(offColor, 0);
	}

	@Override
	public Cell change(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
