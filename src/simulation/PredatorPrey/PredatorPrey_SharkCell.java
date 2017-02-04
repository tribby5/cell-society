package simulation.PredatorPrey;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_SharkCell extends ThreeStateCell{
	public static final Color sharkColor = Color.YELLOW;
	public static final int turnsToReproduce = 10;
	private int turns;
	private int energy;

	public PredatorPrey_SharkCell() {
		super(sharkColor, 2);
		energy = 5;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell change(int n) {
		if(energy == 0){
			return new PredatorPrey_WaterCell();
		}
		// if fish around, eat it, gain energy
		// else if water around, move and lose energy
		//      if enough turns, reproduce
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNotEmpty() {
		return true;
	}

}

