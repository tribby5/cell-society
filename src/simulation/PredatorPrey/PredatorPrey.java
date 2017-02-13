package simulation.PredatorPrey;

import java.util.Arrays;
import java.util.HashMap;
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
	public static final String FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI = "Number of turns for fish to reproduce";
	public static final String SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI = "Number of turns for shark to reproduce";
	public static final String SHARK_INITIAL_ENERGY_PARAMETER_LABEL_GUI = "Initial energy for the shark";
	
	private int fishTurnsToReproduce;
	private int sharkTurnsToReproduce;
	private int sharkInitialEnergy;
	
	private List<Double> fishTurnsToReproduceBounds = Arrays.asList(new Double[] {
			1.0,
			(double) fishTurnsToReproduce,
			10.0
	});;
	
	private List<Double> sharkTurnsToReproduceBounds = Arrays.asList(new Double[] {
			1.0,
			(double) sharkTurnsToReproduce,
			20.0
	});;
	
	private List<Double> sharkInitialEnergyBounds = Arrays.asList(new Double[] {
			1.0,
			(double) sharkInitialEnergy,
			20.0
	});;
	
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
		fishTurnsToReproduceBounds.set(1, (double) fishTurnsToReproduce);
		sharkTurnsToReproduceBounds.set(1, (double) sharkTurnsToReproduce);
		sharkInitialEnergyBounds.set(1, (double) sharkInitialEnergy);
		createParametersBounds();
	}

	@Override
	public void createParametersBounds() {
		Map<String, List<Double>> parametersBounds = new HashMap<>();
		parametersBounds.put(FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI, fishTurnsToReproduceBounds);
		parametersBounds.put(SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI, sharkTurnsToReproduceBounds);
		parametersBounds.put(SHARK_INITIAL_ENERGY_PARAMETER_LABEL_GUI, sharkInitialEnergyBounds);
		setParametersBounds(parametersBounds);
		
	}

	@Override
	public void updateParameter(String parameterLabel, double newValue) {
		if(parameterLabel.equals(FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI)){
			fishTurnsToReproduce = (int) newValue;
		} else if(parameterLabel.equals(SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI)){
			sharkTurnsToReproduce = (int) newValue;
		} else if(parameterLabel.equals(SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI)){
			sharkInitialEnergy = (int) newValue;
		}
	}

	@Override
	public void setDefaultParameters() {
		fishTurnsToReproduce = 7;
		sharkTurnsToReproduce = 10;
		sharkInitialEnergy = 10;
	}

	@Override
	public int getType() {
		return 2;
	}

	@Override
	public String getParameterValue(String par) {
		// TODO Auto-generated method stub
		return null;
	}
}
