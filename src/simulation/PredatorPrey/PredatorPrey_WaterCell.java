package simulation.PredatorPrey;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_WaterCell extends ThreeStateCell{
	public static final Color waterColor = Color.BLUE;
	private boolean becomingNonEmpty;

	public PredatorPrey_WaterCell() {
		super(waterColor, 0);
		becomingNonEmpty = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell change(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNotEmpty() {
		return false;
	}
	
	public void setBecomingNonEmpty(){
		becomingNonEmpty = true;
	}
	
	public boolean getBecomingNonEmpty(){
		return becomingNonEmpty;
	}

}
