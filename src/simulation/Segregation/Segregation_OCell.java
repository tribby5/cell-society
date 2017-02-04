package simulation.Segregation;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Segregation_OCell extends ThreeStateCell{
	public static final Color oColor = Color.RED;

	public Segregation_OCell() {
		super(oColor, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell change(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNotEmpty() {
		return true;
	}

}
