package cellsociety_team13;

import java.util.Arrays;
import java.util.List;

import simulation.Fire.Fire;
import simulation.GameOfLife.GameOfLife;
import simulation.PredatorPrey.PredatorPrey;
import simulation.Segregation.Segregation;

public abstract class Referee {
	public abstract List<Cell> getCellTypes();
	
	public abstract Cell judge(Cell currentCell, List<Cell> neighborList);
	
}
