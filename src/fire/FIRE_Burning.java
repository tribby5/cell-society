package fire;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Burning extends ThreeStateCell{

	public static final Color offColor = Color.RED;
	
	public FIRE_Burning() {
		super(offColor, 1);
	}

	@Override
	public Cell change(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
