package fire;

import cells.ThreeStateCell;
import javafx.scene.paint.Color;

public class FIRE_Alive extends ThreeStateCell{

	public static final Color offColor = Color.GREEN;
	
	public FIRE_Alive() {
		super(offColor, 2);
	}

}
