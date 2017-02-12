package simulation.PredatorPrey;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

public class PredatorPrey extends Manager{
	public static final String FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL = "par1";
	public static final String SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL = "par2";
	public static final String SHARK_INITIAL_ENERGY_PARAMETER_LABEL = "par3";
	
	private int fishTurnsToReproduce;
	private int sharkTurnsToReproduce;
	private int sharkInitialEnergy;
	
	public int getFishTurnsToReproduce() {
		return fishTurnsToReproduce;
	}

	public int getSharkTurnsToReproduce() {
		return sharkTurnsToReproduce;
	}

	public int getSharkInitialEnergy() {
		return sharkInitialEnergy;
	}

	private static final List<String> PARAMETERS = Arrays.asList(new String[] {
			FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL,
			SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL,
			SHARK_INITIAL_ENERGY_PARAMETER_LABEL
	});;
	
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Water(),
			new Fish(),
			new Shark()
	});

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		PredatorPreyCell currentCell = (PredatorPreyCell) currentLocCell.getValue();
		
		//set parameters
		currentCell.setFishTurnsToReproduce(fishTurnsToReproduce);
		currentCell.setSharkInitialEnergy(sharkInitialEnergy);
		currentCell.setSharkTurnsToReproduce(sharkTurnsToReproduce);
		
		
		currentCell.act(currentSociety, newSociety, currentLocCell.getKey(), neighborsLoc, neighborCounts);
		return true;
		
	}

	@Override
	public Manager copy() {
		return new PredatorPrey();
	}
	
	public List<String> getParametersLabel(){
		return PARAMETERS;
	}

	@Override
	public void setParameters(Map<String, Double> data) {
		fishTurnsToReproduce = data.get(FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL).intValue();
		sharkTurnsToReproduce = data.get(SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL).intValue();
		sharkInitialEnergy = data.get(SHARK_INITIAL_ENERGY_PARAMETER_LABEL).intValue();
	}
}
