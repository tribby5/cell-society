package simulation.Fire;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;


public class Fire extends Manager{
	public static final String PROBCATCH_PARAMETER_LABEL = "par1";
	public static final String PROBCATCH_PARAMETER_LABEL_GUI = "Probability of Catching Fire";
	private double probCatch;
	private List<Double> probCatchBounds = Arrays.asList( new Double [] {
			0.0,
			probCatch,
			100.0
	});;
	


	public double getProbCatch() {
		return probCatch;
	}

	private static final List<String> PARAMETERS = Arrays.asList(new String[] {
			PROBCATCH_PARAMETER_LABEL
	});;
	
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Dead(),
			new Burning(),
			new Alive()
	});

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
		List<Location> neighborsLoc, List<Integer> neighborCounts) {
		
		FireCell currentCell = (FireCell) currentLocCell.getValue();
		
		// set parameters
		currentCell.setProbCatch(probCatch);
		
		FireCell updatedCell = currentCell.updateCell(neighborCounts);
		newSociety.put(currentLocCell.getKey(), updatedCell);
		
		return true;
	}

	@Override
	public Manager copy() {
		return new Fire();
	}
	
	public List<String> getParametersLabel(){
		return PARAMETERS;
	}

	@Override
	public void setParameters(Map<String, Double> data) {
		probCatch = data.get(PROBCATCH_PARAMETER_LABEL);
		probCatchBounds.set(1, probCatch);
		createParametersBounds();
	}

	@Override
	public void updateParameter(String parameterLabel, double newValue) {
		if(parameterLabel.equals(PROBCATCH_PARAMETER_LABEL_GUI)){
			probCatch = newValue;
		}
		
	}

	@Override
	public void createParametersBounds() {
		Map<String, List<Double>> parametersBounds = new HashMap<String, List<Double>>();
		parametersBounds.put(PROBCATCH_PARAMETER_LABEL_GUI, probCatchBounds);
		setParametersBounds(parametersBounds);
	}

	@Override
	public void setDefaultParameters() {
		probCatch = (probCatchBounds.get(0) + probCatchBounds.get(2))/2;
	}
}
