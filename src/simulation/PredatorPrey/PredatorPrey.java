package simulation.PredatorPrey;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;
import simulation.GameOfLife.GOL_OffCell;
import simulation.GameOfLife.GOL_OnCell;

public class PredatorPrey extends Referee{

	private static final List<Cell> CELLS = Arrays.asList(new Cell[] {
			new PredatorPrey_WaterCell(),
			new PredatorPrey_FishCell(),
			new PredatorPrey_SharkCell()
	});

	@Override
	public Cell judge(Cell currentCell, List<Cell> neighborList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

}
