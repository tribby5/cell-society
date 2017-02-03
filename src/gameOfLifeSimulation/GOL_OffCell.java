package cells;

import javafx.scene.paint.Color;

public class OffCell extends TwoStateCell {
	
	public static final Color offColor = Color.BLACK;

	OffCell(){
		super(offColor, false);
	}
	
	public OffCell(OnCell oldCell){
		this();
		// copy data from oldCell
	}
}
