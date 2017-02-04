package fire;

import cells.ThreeStateCell;
import javafx.scene.paint.Color;

public class FIRE_Burning extends ThreeStateCell{

	public static final Color offColor = Color.RED;
	
	public FIRE_Burning() {
		super(offColor, 1);
	}

}
