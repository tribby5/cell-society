package cellsociety_team13;

import java.util.List;

public abstract class Referee {
	public abstract List<Cell> getCellTypes();
	
	public abstract Cell judge(Cell currentCell, List<Cell> neighborList);
	
}
