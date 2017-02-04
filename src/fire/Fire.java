package fire;

import java.util.List;

import cellsociety_team13.Cell;

public class Fire {
	public Cell judge(Cell currentCell, List<Cell> neighborList){
		boolean burningNeighbor = isAnyNeighborBurning(neighborList);
		
		currentCell.change(burningNeighbor);

		
		return currentCell;
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
