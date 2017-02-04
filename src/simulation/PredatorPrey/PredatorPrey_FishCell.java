package simulation.PredatorPrey;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_FishCell extends ThreeStateCell{
	public static final Color fishColor = Color.GREEN;
	public static final int turnsToReproduce = 10;
	private int turns;

	public PredatorPrey_FishCell() {
		super(fishColor, 1);
		turns = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell change(int n) {
		turns++;
		// if moved
			if (turns >= turnsToReproduce){
				// reproduce
				turns = 0;
			}
			
		return null;
	}

	@Override
	public boolean isNotEmpty() {
		return true;
	}


}
