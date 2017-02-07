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
	private boolean torodialWorld;
	
	public Society(Map<Location, Cell> rawGrid){
		torodialWorld = false;
		grid = rawGrid;
		generateNeighbors();		
		calcCornerPoints();
		
	}

	private void generateNeighbors() {
        sideNeighbor = new HashMap<Location, ArrayList<Location>>();
        vertexNeighbor = new HashMap<Location, ArrayList<Location>>(); 
        for(Location pointBase : grid.keySet()){
            ArrayList<Location> tempSide = new ArrayList<Location>();
            ArrayList<Location> tempVertex = new ArrayList<Location>();
            for(Location pointTest : grid.keySet())
                if (pointBase != pointTest){
                	double distanceBetween = distance(pointBase.getPoint(), pointTest.getPoint());
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



	public double distance(Point2D l1, Point2D l2){
		return l1.distance(l2);
	}
	
	public Map<Location, Cell> getGrid(){
		return grid;
	}
	
	public List<Location> getRawSideNeighbors(Location loc){
		return sideNeighbor.get(loc);
	}
	
	public List<Cell> getSideNeighbors(Location loc){
		List<Location> neighborLocList = sideNeighbor.get(loc);
		return getNeighbors(neighborLocList);		
	}
	
	public List<Cell> getVertexNeighbors(Location loc){
		List<Location> neighborLocList = vertexNeighbor.get(loc);
		return getNeighbors(neighborLocList);
	}

	private List<Cell> getNeighbors(List<Location> neighborLocList) {
		List<Cell> neighborCellList = new ArrayList<Cell>();
		for(Location locNeighbor : neighborLocList){
			neighborCellList.add(grid.get(locNeighbor));
		}
		return neighborCellList;
	}

	public void updateGrid(Map<Location, Cell> newGrid) {
		
		grid = newGrid;
	}
	
	private void calcCornerPoints() {
		Point2D max = new Point2D(0, 0);
		
		for(Location loc: grid.keySet()){
			if(loc.getX() > max.getX())
				max = new Point2D(loc.getX(), max.getY());
			if(loc.getY() > max.getY())
				max = new Point2D(max.getX(), loc.getY());
		}
		this.bottomRightPoint = max;
		
		Point2D min = new Point2D(max.getX(), max.getY());
		for(Location loc: grid.keySet()){
			if(loc.getX() < min.getX())
				min = new Point2D(loc.getX(), min.getY());
			if(loc.getY() < min.getY())
				min = new Point2D(min.getX(), loc.getY());
		}
		
		this.topLeftPoint = min;
	}
	
	public Point2D getBottomRightPoint(){
		return bottomRightPoint;
	}
	
	public Point2D getTopLeftPoint(){
		return topLeftPoint;
	}

	public void addTorodialNeighbors() {
		this.torodialWorld = true;
        for(Location pointBase : grid.keySet()){
            ArrayList<Location> tempSide = sideNeighbor.get(pointBase);
            ArrayList<Location> tempVertex = vertexNeighbor.get(pointBase);
            
            
            
            if(!checkIfOnEdge(tempSide, tempVertex)){
            	continue;
            }
            
            System.out.println("before: " + tempSide.size() + " " + tempVertex.size());

            for(Location pointTest : grid.keySet())
                if (pointBase != pointTest){
                	boolean onOppositeWalls = Math.max(pointBase.getX(), pointTest.getX()) == bottomRightPoint.getX()
                								&& Math.min(pointBase.getX(), pointTest.getX()) == topLeftPoint.getX();
                	
                	boolean onFloorCeiling = Math.max(pointBase.getY(), pointTest.getY()) == bottomRightPoint.getY()
                        						&& Math.min(pointBase.getY(), pointTest.getY()) == topLeftPoint.getY();
                	
                	
                	if (onOppositeWalls && onFloorCeiling){
                		tempVertex.add(pointTest);
                		continue;
                	}
                	
                	
                	if (onOppositeWalls){
                		for (int i = -1 ; i < 2 ; i++){
                			if (Math.abs(pointBase.getY() - pointTest.getY()) <= i * pointBase.getPoly().getRadius() + pointTest.getPoly().getRadius()){
                				tempVertex.add(pointTest);
                				if (i == 0){
                					tempSide.add(pointTest);
                				}
                			}		
                		}
                	}
                	if (onFloorCeiling){
                		for (int i = -1 ; i < 2 ; i++){
                			if (Math.abs(pointBase.getX() - pointTest.getX()) <= i * pointBase.getPoly().getRadius() + pointTest.getPoly().getRadius()){
                				tempVertex.add(pointTest);
                				if (i == 0){
                					tempSide.add(pointTest);
                				}
                			}		
                		}
                	}
                }
            System.out.println("after: " + tempSide.size() + " " + tempVertex.size());
            vertexNeighbor.put(pointBase, tempVertex);
            sideNeighbor.put(pointBase, tempSide);
        }
		
	}
	
	private boolean checkIfOnEdge(ArrayList<Location> tempSide, ArrayList<Location> tempVertex) {
		return(tempSide.size() != 4 || tempVertex.size() != 8);
	}

	
}