package simulation.PredatorPrey;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class PredatorPrey_WaterCell extends ThreeStateCell{
	public static final Color waterColor = Color.BLUE;
	private boolean becomingNonEmpty;

	public PredatorPrey_WaterCell() {
		super(waterColor, 0);
		becomingNonEmpty = false;
	}

	@Override
	public Cell change(int n) {
		return null;
	}

	@Override
	public boolean isNotEmpty() {
		return false;
	}

	public void setBecomingNonEmpty(){
		becomingNonEmpty = true;
	}

	public boolean getBecomingNonEmpty(){
		return becomingNonEmpty;
	}

	@Override
	public Cell surroundChange(Cell currentCell, List<Cell> neighborList) { 
		for(Cell c: neighborList)
			if(c instanceof PredatorPrey_FishCell && ((PredatorPrey_FishCell) c).getReproduce()){
				((PredatorPrey_FishCell) c).resetReproduce();
				currentCell = new PredatorPrey_FishCell();
			}
		return currentCell;
	}


}
