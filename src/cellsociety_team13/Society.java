package cellsociety_team13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Point2D;

public class Society {
	private Map<Location, ArrayList<Location>> vertexNeighborMap;
	private Map<Location, ArrayList<Location>> sideNeighborMap;
	private Map<Location, Cell> Grid;
	
	Society(Map<Location, Cell> rawGrid){
		this.Grid = rawGrid;
		getNeighbors();		
	}

	
	private void getNeighbors() {
		sideNeighborMap = new HashMap<Location, ArrayList<Location>>();
		vertexNeighborMap = new HashMap<Location, ArrayList<Location>>(); 
		for(Location pointBase : this.Grid.keySet()){
			ArrayList<Location> tempSideNeighborList = new ArrayList<Location>();
			ArrayList<Location> tempVertexNeighborList = new ArrayList<Location>();
			for(Location pointTest : this.Grid.keySet()){
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

	static double calculateDistanceBetweenLocations(Point2D l1, Point2D l2){
		return Math.sqrt(Math.pow((l1.getX() - l2.getX()),2)+Math.pow((l1.getY() - l2.getY()),2));
	}
	
}
