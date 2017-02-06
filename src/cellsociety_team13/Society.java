package cellsociety_team13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;

public class Society {
	private Map<Location, ArrayList<Location>> vertexNeighbor;
	private Map<Location, ArrayList<Location>> sideNeighbor;
	private Map<Location, Cell> grid;
	
	public Society(Map<Location, Cell> rawGrid){
		grid = rawGrid;
		generateNeighbors();		
		initializeColorStates();
	}

	
	private void initializeColorStates() {
		for(Location loc : grid.keySet()){
			loc.applyColorStateToPolygon(grid.get(loc).getState());
		}	
	}


	private void generateNeighbors() {
		sideNeighbor = new HashMap<Location, ArrayList<Location>>();
		vertexNeighbor = new HashMap<Location, ArrayList<Location>>(); 
		for(Location pointBase : grid.keySet()){
			ArrayList<Location> tempSide = new ArrayList<Location>();
			ArrayList<Location> tempVertex = new ArrayList<Location>();
			for(Location pointTest : grid.keySet())
				if (pointBase != pointTest){
					if (distance(pointBase.getPoint(), pointTest.getPoint()) <= pointBase.getPoly().getApothem() + pointTest.getPoly().getApothem())
						tempSide.add(pointTest);
					if (distance(pointBase.getPoint(), pointTest.getPoint()) <= pointBase.getPoly().getRadius() + pointTest.getPoly().getRadius())
						tempVertex.add(pointTest);
				}
			vertexNeighbor.put(pointBase, tempVertex);
			sideNeighbor.put(pointBase, tempSide);
		}
	}

	public double distance(Point2D l1, Point2D l2){
		return l1.distance(l2);
	}
	
	
	public Map<Location, Cell> getGrid(){
		return grid;
	}
	
	public List<Cell> getSideNeighbors(Location loc){
		List<Location> neighborLocList = sideNeighbor.get(loc);
		List<Cell> neighborCellList = new ArrayList<Cell>();
		for(Location locNeighbor : neighborLocList){
			neighborCellList.add(grid.get(locNeighbor));
		}
		return neighborCellList;
	}
	
	public List<Cell> getVertexNeighbors(Location loc){
		List<Location> neighborLocList = vertexNeighbor.get(loc);
		List<Cell> neighborCellList = new ArrayList<Cell>();
		for(Location locNeighbor : neighborLocList){
			neighborCellList.add(grid.get(locNeighbor));
		}
		return neighborCellList;
	}


	public void updateGrid(Map<Location, Cell> newGrid) {
		grid = newGrid;
	}
	
	public Point2D getFurthestPoint() {;
		Point2D max = new Point2D(0, 0);
		for(Location loc: grid.keySet()){
			if(loc.getX() > max.getX())
				max = new Point2D(loc.getX(), max.getY());
			if(loc.getY() > max.getY())
				max = new Point2D(max.getX(), loc.getY());
		}
		return max;
	}
}