package simulation.Fire;

import java.util.List;
import java.util.Random;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Alive extends FireCell{
	public static final Color color = Color.GREEN; //TODO: color imports
	public static final int priority = getPriority_Alive();
	public static final int state = getState_Alive();
	private double probCatch;
	
	public Alive() {
		super(color, state, priority);
		Random rand = new Random();
		this.probCatch = rand.nextDouble();
	}

	@Override
	public FireCell act(List<Integer> neighborCount){
		if (neighborCount.get(getState_Burning()) != 0){
			Random rand = new Random();
			if(probCatch < rand.nextDouble()){
				return new Burning();
			}
		}
		return this;
	}
	
	public void setProbCatch(double inputProbCatch){
		this.probCatch = inputProbCatch;
	}

	@Override
	public Cell copy() {
		return new Alive();
	}

}
