package simulation.PredatorPrey;

import java.util.List;
import java.util.Random;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public abstract class PredatorPrey_CreatureCell extends ThreeStateCell {
	private int turnsToReproduce;


	public PredatorPrey_CreatureCell(Color colorInput, int stateInput, int turnsToReproduce) {
		super(colorInput, stateInput);
		this.turnsToReproduce = turnsToReproduce;

	}
	
	@Override
	public boolean isNotEmpty(){
		return true;
	}


}
