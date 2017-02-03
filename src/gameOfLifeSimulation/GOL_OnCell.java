package cells;

import javafx.scene.paint.Color;

public class OnCell extends TwoStateCell{
	
	public static final Color onColor = Color.BLACK;

	OnCell(){
		super(onColor, true);
	}
	
	public OnCell(OffCell oldCell){
		this();
		// copy data from oldCell
	}
	
}
