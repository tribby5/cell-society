package simulation.Fire;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;

public class Fire extends Referee{

	private static final List<Cell> CELLS = Arrays.asList(new Cell[] {
			new FIRE_Dead(),
			new FIRE_Burning(),
			new FIRE_Alive()
	});

	@Override
	public Cell judge(Cell currentCell, List<Cell> neighborList) {
		// TODO Auto-generated method stub
		return null;
	}
	// use three state cells

	@Override
	public List<Cell> getCellTypes() {
		return null;
	}
		
	private boolean isAnyNeighborBurning(List<Cell> neighborList){
		for (Cell c : neighborList){
			if (c instanceof FIRE_Burning){
				return true;
			}
		}
		return false;
	}
}
