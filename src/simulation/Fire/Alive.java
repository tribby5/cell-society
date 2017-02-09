package simulation.Fire;

import java.util.List;
import java.util.Map;
import java.util.Random;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import javafx.scene.paint.Color;

public class Alive extends FireCell{
	public static final Color color = Color.GREEN;
	public static final int priority = 0;
	public static final int state = getState_Alive();
	private double probCatch;
	
	public Alive() {
		super(color, state, priority);
		Random rand = new Random();
		this.probCatch = rand.nextDouble();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cell updateCell(Map<Location, Cell> grid, List<Location> neighbors, List<Integer> neighborCount) {
		if (neighborCount.get(getState_Burning()) != 0){
			Random rand = new Random();
			if(probCatch < rand.nextDouble()){
				return new Burning();
			}
		}
		return new Alive();
	}
	
	public void setProbCatch(double inputProbCatch){
		this.probCatch = inputProbCatch;
	}

	@Override
	public Cell copy() {
		return new Alive();
	}

}
