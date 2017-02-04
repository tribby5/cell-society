package simulation.Segregation;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Segregation_EmptyCell extends ThreeStateCell{
	public static final Color emptyColor = Color.WHITE;
	private boolean becomingNonEmpty;

	public Segregation_EmptyCell() {
		super(emptyColor, 0);
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
