package simulation.ForagingAnts;

import java.util.ArrayList;
import java.util.List;

import cells.FourStateCell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public abstract class ForagingAntsCell extends FourStateCell {
	public static final int stateObstacle = 3;
	public static final int stateNest = 1;
	public static final int stateFoodSource = 2;
	public static final int statePatch = 0;

	public static final int priorityObstacle = -1;
	public static final int priorityNest = 0;
	public static final int priorityFoodSource = 2;
	public static final int priorityPatch = 1;

	public static final String FOOD_PHEROMONE_LABEL = "FOOD";
	public static final String HOME_PHEROMONE_LABEL = "HOME";
	public static final int ANT_CAPACITY = 10;

	private List<Ant> myAnts;

	private double home_pheromone_amount;
	private double food_pheromone_amount;
	private double home_pheromone_evaporation;
	private double food_pheromone_evaporation;
	private double home_pheromone_diffusion;
	private double food_pheromone_diffusion;

	public double getHome_pheromone_evaporation() {
		return home_pheromone_evaporation;
	}

	public void setHome_pheromone_evaporation(double home_pheromone_evaporation) {
		this.home_pheromone_evaporation = home_pheromone_evaporation;
	}

	public double getFood_pheromone_evaporation() {
		return food_pheromone_evaporation;
	}

	public void setFood_pheromone_evaporation(double food_pheromone_evaporation) {
		this.food_pheromone_evaporation = food_pheromone_evaporation;
	}

	public double getHome_pheromone_diffusion() {
		return home_pheromone_diffusion;
	}

	public void setHome_pheromone_diffusion(double home_pheromone_diffusion) {
		this.home_pheromone_diffusion = home_pheromone_diffusion;
	}

	public double getFood_pheromone_diffusion() {
		return food_pheromone_diffusion;
	}

	public void setFood_pheromone_diffusion(double food_pheromone_diffusion) {
		this.food_pheromone_diffusion = food_pheromone_diffusion;
	}

	public double getHome_pheromone_amount() {
		return home_pheromone_amount;
	}

	public void setHome_pheromone_amount(double home_pheromone_amount) {
		this.home_pheromone_amount = home_pheromone_amount;
	}

	public double getFood_pheromone_amount() {
		return food_pheromone_amount;
	}

	public void setFood_pheromone_amount(double food_pheromone_amount) {
		this.food_pheromone_amount = food_pheromone_amount;
	}

	public ForagingAntsCell(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
		myAnts = new ArrayList<Ant>();
	}

	public static int getState_Obstacle() {
		return stateObstacle;
	}

	public static int getState_Nest() {
		return stateNest;
	}

	public static int getState_FoodSource() {
		return stateFoodSource;
	}

	public static int getState_Patch() {
		return statePatch;
	}

	public static int getPriority_Obstacle() {
		return priorityObstacle;
	}

	public static int getPriority_Nest() {
		return priorityNest;
	}

	public static int getPriority_FoodSource() {
		return priorityFoodSource;
	}

	public static int getPriority_Patch() {
		return priorityPatch;
	}

	public int getDefaultEmptyState() {
		return statePatch;
	}

	public int getAntCount() {
		return myAnts.size();
	}

	public boolean atAntCapacity() {
		return (getAntCount() == ANT_CAPACITY);
	}

	public void removeAnt(Ant ant) {
		myAnts.remove(ant);

	}

	public void addAnt(Ant ant) {
		myAnts.add(ant);

	}

	protected void updatePheromoneLevels(Society currentSociety, Society newSociety, List<Location> neighborsLoc,
			String label) {
		double amount = 0.0;
		double evaporation = 0.0;
		double diffusion = 0.0;

		if (label.equals(FOOD_PHEROMONE_LABEL)) {
			amount = getFood_pheromone_amount();
			evaporation = getFood_pheromone_evaporation();
			diffusion = getFood_pheromone_diffusion();
		} else if (label.equals(HOME_PHEROMONE_LABEL)) {
			amount = getHome_pheromone_amount();
			evaporation = getHome_pheromone_evaporation();
			diffusion = getHome_pheromone_diffusion();
		}

		if (amount != 0) {
			// evaporate
			amount *= (1 - evaporation);

			// diffuse to neighbors
			for (Location neighborLoc : neighborsLoc) {
				if (currentSociety.get(neighborLoc).getState() == getState_Patch()) {
					if (label.equals(FOOD_PHEROMONE_LABEL)) {
						((Patch) newSociety.get(neighborLoc)).setFood_pheromone_amount(amount * diffusion);
					} else if (label.equals(HOME_PHEROMONE_LABEL)) {
						((Patch) newSociety.get(neighborLoc)).setHome_pheromone_amount(amount * diffusion);
					}
				}
			}

			// reduce diffused amount
			amount *= (1 - neighborsLoc.size() * diffusion);
		}

		if (label.equals(FOOD_PHEROMONE_LABEL)) {
			setFood_pheromone_amount(amount);
			setFood_pheromone_evaporation(evaporation);
			setFood_pheromone_diffusion(diffusion);
		} else if (label.equals(HOME_PHEROMONE_LABEL)) {
			setHome_pheromone_amount(amount);
			setHome_pheromone_evaporation(evaporation);
			setHome_pheromone_diffusion(diffusion);
		}

	}

	public void update(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc) {
		updatePheromoneLevels(currentSociety, newSociety, neighborsLoc, HOME_PHEROMONE_LABEL);
		updatePheromoneLevels(currentSociety, newSociety, neighborsLoc, FOOD_PHEROMONE_LABEL);
		if (!myAnts.isEmpty()) {
			for (Ant ant : myAnts) {
				ant.act(currentSociety, newSociety, loc, neighborsLoc);
			}
			
			if(this.getState() == getState_Patch()){
				((Patch) this).updateColor();
			}
			
		}

		if (this.getState() == getState_Nest() && this.myAnts.size() != ANT_CAPACITY) {
			this.myAnts.add(new Ant(this));
			System.out.println("new ant at Nest");
		}
		
		newSociety.put(loc, this);

	}

}
