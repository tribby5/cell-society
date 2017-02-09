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
	private Point2D bottomRightPoint;
	private Point2D topLeftPoint;
	
	public Society(Map<Location, Cell> rawGrid){
		grid = rawGrid;
		generateNeighbors();		
		calcCornerPoints();
	}
	
	public Map<Location, Cell> getGridCopy(){
		return new HashMap<Location, Cell>(grid);
	}
	
	public void pushNewGrid(Map<Location, Cell> newGrid) {
		grid = newGrid;
	}

	private void generateNeighbors() {
        sideNeighbor = new HashMap<Location, ArrayList<Location>>();
        vertexNeighbor = new HashMap<Location, ArrayList<Location>>(); 
        for(Location pointBase : grid.keySet()){
            ArrayList<Location> tempSide = new ArrayList<Location>();
            ArrayList<Location> tempVertex = new ArrayList<Location>();
            for(Location pointTest : grid.keySet())
                if (pointBase != pointTest){
                	double distanceBetween = pointBase.getPoint().distance(pointTest.getPoint());
                    if (distanceBetween 
                    		<= pointBase.getPoly().getApothem() + pointTest.getPoly().getApothem())
                        tempSide.add(pointTest);
                    if (distanceBetween 
                    		<= pointBase.getPoly().getRadius() + pointTest.getPoly().getRadius())
                        tempVertex.add(pointTest);
                }
            vertexNeighbor.put(pointBase, tempVertex);
            sideNeighbor.put(pointBase, tempSide);
        }
    }
	
	public Map<Location, Cell> swap(Location loc1, Location loc2){
		Cell temp = grid.get(loc1);
		grid.put(loc1, grid.get(loc2));
		grid.put(loc2, temp);
		return getGridCopy();
	}
	

	private void calcCornerPoints() {
		Point2D max = new Point2D(0, 0);
		
		for(Location loc: grid.keySet()){
			if(loc.getX() > max.getX())
				max = new Point2D(loc.getX(), max.getY());
			if(loc.getY() > max.getY())
				max = new Point2D(max.getX(), loc.getY());
		}
		this.bottomRightPoint = new Point2D(max.getX() + findSideLength(), max.getY() + findSideLength());
		
		Point2D min = new Point2D(max.getX(), max.getY());
		for(Location loc: grid.keySet()){
			if(loc.getX() < min.getX())
				min = new Point2D(loc.getX(), min.getY());
			if(loc.getY() < min.getY())
				min = new Point2D(min.getX(), loc.getY());
		}	
		this.topLeftPoint = new Point2D(min.getX() - findSideLength(), min.getY() - findSideLength());
	}
	
	
	public List<Location> getSideNeighbors(Location loc){
		return sideNeighbor.get(loc);	
	}
	
	public List<Location> getVertexNeighbors(Location loc){
		return vertexNeighbor.get(loc);
	}



	
	private double findSideLength() {
		for(Location loc: grid.keySet())
			return loc.getPoly().getSideLength();
		return 0;
	}

	public Point2D getBottomRightPoint(){
		return bottomRightPoint;
	}
	
	public Point2D getTopLeftPoint(){
		return topLeftPoint;
	}
	
	

	
}