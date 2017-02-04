package fire;

import cells.ThreeStateCell;
import javafx.scene.paint.Color;

public class FIRE_Dead extends ThreeStateCell{
	
	public static final Color offColor = Color.BROWN;

	public FIRE_Dead() {
		super(offColor, 0);
	}

}
