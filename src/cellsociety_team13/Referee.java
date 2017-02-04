package cellsociety_team13;

import java.util.Arrays;
import java.util.List;

import fire.Fire;
import gameOfLifeSimulation.GameOfLife;
import referees.Predator_Prey;
import referees.Segregation;

public abstract class Referee {
	public abstract List<Cell> getCellTypes();
	
	public abstract Cell judge(Cell currentCell, List<Cell> neighborList);
	
}
