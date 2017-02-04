package fire;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Alive extends ThreeStateCell{

	public static final Color offColor = Color.GREEN;
	
	public FIRE_Alive() {
		super(offColor, 2);
	}

	@Override
	public Cell change(int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
