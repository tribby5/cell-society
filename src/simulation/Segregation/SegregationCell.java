package simulation.Segregation;

import java.util.List;

import cellTypes.ThreeStateCell;
import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public abstract class SegregationCell extends ThreeStateCell{
	public static final int stateX = 2;
	public static final int stateO = 1;
	public static final int stateEmpty = 0;
	private double satisficationPercentage;

	public SegregationCell(Color inputColor, int state) {
		super(inputColor, state);
		// TODO Auto-generated constructor stub
	}

	public static int getState_X(){
		return stateX;
	}
	
	public static int getState_O(){
		return stateO;
	}
	public static int getState_Empty(){
		return stateEmpty;
	}
	
	public void setSatisficationPercentage(double input){
		this.satisficationPercentage = input;
	}
	
	@Override
	public Cell updateCell(List<Cell> neighbors, List<Integer> neighborCount) {
		if(!this.isSatisfied(neighborCount.get(this.getState()), neighbors.size())){
			// relocate to empty cell
		}
		return this;
	}

	private boolean isSatisfied(Integer likeCount, Integer totalCount) {
		if (likeCount > totalCount * satisficationPercentage){
			return true;
		}
		return false;
		
	}
}
