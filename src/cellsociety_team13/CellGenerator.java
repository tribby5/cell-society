package cellsociety_team13;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CellGenerator {

	private List<Integer> probs;
	private Map<Location, Cell> board;
	private Manager manager;

	public CellGenerator(Map<Location, Cell> pBoard, Manager pSimType, List<Integer> pProbs){
		this(pBoard, pSimType);
		probs = pProbs;
		
	}
	
	public CellGenerator(Map<Location, Cell> pBoard, Manager pSimType){
		board = pBoard;
		manager = pSimType;
		probs = new ArrayList<>();
		for(int i=0; i<pSimType.getCellTypes().size(); i++)
			probs.add(1);
	}
	
	public Society getSociety(){
		for(int i=1; i<probs.size(); i++)
			probs.set(probs.get(i), probs.get(i)+probs.get(i-1));
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