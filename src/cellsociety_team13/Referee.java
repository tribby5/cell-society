package cellsociety_team13;

import java.util.List;
import java.util.Map;


public abstract class Referee {
	
	public abstract List<Cell> getCellTypes();

	public abstract Cell judge(Cell currentCell, List<Cell> neighborList);

	public abstract void giveSociety(Society soc);

	public abstract Map<Location, Cell> getGrid();
	
	public abstract Cell manageLocation(Society soc, Location loc);

}
