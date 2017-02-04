package simulation.Fire;

import java.util.Random;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FIRE_Alive extends ThreeStateCell{
	
	public static final Color aliveColor = Color.GREEN;
	private double probCatch;

	FIRE_Alive() {
		super(aliveColor, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell change(int n) {
		Random rand = new Random();
		for (int i = 0; i < n; i++){
			if (rand.nextDouble() < probCatch){
				return new FIRE_Burning();
			}
		}
		return this;
	}

}