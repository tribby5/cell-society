package cellsociety_team13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;

public class Society {
	private Map<Location, ArrayList<Location>> vertexNeighborMap;
	private Map<Location, ArrayList<Location>> sideNeighborMap;
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
		sideNeighborMap = new HashMap<Location, ArrayList<Location>>();
		vertexNeighborMap = new HashMap<Location, ArrayList<Location>>(); 
		for(Location pointBase : grid.keySet()){
			ArrayList<Location> tempSideNeighborList = new ArrayList<Location>();
			ArrayList<Location> tempVertexNeighborList = new ArrayList<Location>();
			for(Location pointTest : grid.keySet()){
				if (pointBase != pointTest){
					double maxDistanceBetweenSideNeighboringLocations = (pointBase.getPoly().getRadius() + pointTest.getPoly().getRadius());
					double maxDistanceBetweenVertexNeighboringLocations = (pointBase.getPoly().getApothem() + pointTest.getPoly().getApothem());
					double distanceBetweenLocations = calculateDistanceBetweenLocations(pointBase, pointTest);
					if (distanceBetweenLocations <= maxDistanceBetweenSideNeighboringLocations){
						tempSideNeighborList.add(pointTest);
					}
					if (distanceBetweenLocations <= maxDistanceBetweenVertexNeighboringLocations){
						tempVertexNeighborList.add(pointTest);
					}
				}
			}
			vertexNeighborMap.put(pointBase, tempVertexNeighborList);
			sideNeighborMap.put(pointBase, tempSideNeighborList);
		}
	}

	public double calculateDistanceBetweenLocations(Point2D l1, Point2D l2){
		//return l1.distance(l2);
		return Math.sqrt(Math.pow((l1.getX() - l2.getX()),2)+Math.pow((l1.getY() - l2.getY()),2));
	}
	
	
	public Map<Location, Cell> getGrid(){
		return grid;
	}
	
	public List<Cell> getNeighbors(Location loc){
		List<Location> neighborLocList = sideNeighborMap.get(loc);
		List<Cell> neighborCellList = new ArrayList<Cell>();
		for(Location locNeighbor : neighborLocList){
			neighborCellList.add(grid.get(locNeighbor));
		}
		return neighborCellList;
	}


	public void updateGrid(Map<Location, Cell> newGrid) {
		grid = newGrid;
	}
	
	public Point2D getFurthestPoint( ) {;
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
