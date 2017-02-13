package cellsociety_team13;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class that generates a society based on a partial grid.
 * @author Andres Lebbos
 */
public class CellGenerator {

	/**
	 * List of probabilities for every type of cell within the types defined by manager
	 */
	private List<Integer> probs;
	
	/**
	 * Board with all the information needed to create society
	 */
	private Map<Location, Cell> board;
	
	/**
	 * Manager defines types of posible cells that can be used randomly.
	 */
	private Manager manager;

	/**
	 * Constructs a generator that generates the cells in the map
	 * @param pBoard the board where it generates random cells
	 * @param pSimType the manager with the possible types of cells
	 * @param pProbs the probabilities for every type of cell
	 */
	public CellGenerator(Map<Location, Cell> pBoard, Manager pSimType, List<Integer> pProbs){
		this(pBoard, pSimType);
		probs = pProbs;
		
	}
	
	/**
	 * Constructs a generator that generates the cells in the map
	 * @param pBoard the board where it generates random cells
	 * @param pSimType the manager with the possible types of cells
	 */
	public CellGenerator(Map<Location, Cell> pBoard, Manager pSimType){
		board = pBoard;
		manager = pSimType;
		probs = new ArrayList<>();
		for(int i=0; i<pSimType.getCellTypes().size(); i++)
			probs.add(1);
	}
	
	/**
	 * Creates the society based on all the information provided.
	 * @return the society done and ready to go!
	 */
	public Society getSociety(){
		for(int i=1; i<probs.size(); i++)
			probs.set(i, probs.get(i)+probs.get(i-1));
		for(Location loc: board.keySet()){
			int prob = new Random().nextInt(probs.get(probs.size()-1));
			for(int i=0; i<probs.size(); i++)
				if(prob<probs.get(i)){
					board.put(loc, manager.getCellTypes().get(i));
					break;
				}	
		}
		return new Society(board);
	}
}