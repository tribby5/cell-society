package fire;

import cells.ThreeStateCell;
import javafx.scene.paint.Color;

public class FIRE_Alive extends ThreeStateCell{
	
	public static final Color aliveColor = Color.GREEN;

	FIRE_Alive() {
		super(aliveColor, 2);
		// TODO Auto-generated constructor stub
	}

}
