package simulation.PredatorPrey;

import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public abstract class SeaAnimal extends PredatorPreyCell{
	private int turnsToReproduction;
	private int turns;
	private boolean dead;

	public SeaAnimal(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
		this.dead = false;
	}
	
	protected void tryToReproduce(Society newSociety, Location location, int motherCellState){
		if(this.getState() == getState_Shark()){
			this.turnsToReproduction = this.getSharkTurnsToReproduce();
		} else if (this.getState() == getState_Fish()){
			this.turnsToReproduction = this.getFishTurnsToReproduce();
		}
		if (getTurns() >= turnsToReproduction){
			resetTurns();
			newSociety.put(location, createBabyCell(motherCellState));
		}
	}
	
	private SeaAnimal createBabyCell(int motherState){
		if (motherState == stateShark){
			return new Shark();
		} else {
			return new Fish();
		}
	}
	
	protected int getTurns(){
		return turns;
	};
	
	protected void setTurns(int inputTurns){
		this.turns = inputTurns;
	}
	
	protected void resetTurns(){
		this.turns = 0;
	}
	
	protected void dies(){
		this.dead = true;
	}
	
	protected boolean isAlive(){
		return !this.dead;
	}
	
}
