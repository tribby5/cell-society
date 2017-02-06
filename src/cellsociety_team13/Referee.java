package cellsociety_team13;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import simulation.Fire.Fire;
import simulation.GameOfLife.GameOfLife;
import simulation.PredatorPrey.PredatorPrey;
import simulation.Segregation.Segregation;


public abstract class Referee {
	public static final List<Referee> REFEREES = Arrays.asList(new Referee[] {
			new GameOfLife(),
			new Fire(),
			new PredatorPrey(),
			new Segregation()
	});
	
	public abstract List<Cell> getCellTypes();
	
	public abstract Cell judge(Cell currentCell, List<Cell> neighborList);

	public abstract void giveSociety(Society soc);

	public abstract Map<Location, Cell> getGrid();
	
}
