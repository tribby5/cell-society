package cellsociety_team13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import javafx.geometry.Point2D;

public class Society {
	private Map<Location, ArrayList<Location>> vertexNeighbor;
	private Map<Location, ArrayList<Location>> sideNeighbor;
	private Map<Location, Cell> grid;
	private Point2D bottomRightPoint;
	private Point2D topLeftPoint;
	private Boolean orderMatters;

	public Society(Map<Location, Cell> rawGrid) {
		grid = rawGrid;
		generateNeighbors();
		calcCornerPoints();
		orderMatters = null;
	}

	public Cell get(Location loc) {
		return grid.get(loc);
	}
	
	public Set<Location> keySet(){
		return grid.keySet();
	}
	
	public Collection<Cell> values(){
		return grid.values();
	}

	public Society(Map<Location, Cell> grid2, Map<Location, ArrayList<Location>> vertexNeighbor2,
			Map<Location, ArrayList<Location>> sideNeighbor2, Point2D bottomRightPoint2, Point2D topLeftPoint2, Boolean orderMatters2) {
		this.grid = new HashMap<>(grid2);
		this.vertexNeighbor = vertexNeighbor2;
		this.sideNeighbor = sideNeighbor2;
		this.bottomRightPoint = bottomRightPoint2;
		this.topLeftPoint = topLeftPoint2;
		this.orderMatters = orderMatters2;
	}

	public Map<Location, Cell> getGridCopy() {
		return new HashMap<Location, Cell>(grid);
	}

	public List<Integer> countNeighbors(List<Location> neighborsLoc) {
		Integer[] neighborCount = Collections.nCopies(grid.get(neighborsLoc.get(0)).getMaxState(), 0)
				.toArray(new Integer[0]);
		// from
		// http://stackoverflow.com/questions/2154251/any-shortcut-to-initialize-all-array-elements-to-zero/2154340
		for (Location neighborLoc : neighborsLoc) {
			neighborCount[grid.get(neighborLoc).getState()]++;
		}
		return Arrays.asList(neighborCount);

	}

	public Queue<Location> setProcessingOrder() {
		PriorityQueue<Cell> qCells = new PriorityQueue<Cell>();
		Queue<Location> qLocs = new LinkedList<Location>();
		
		if(orderMatters == null){
			orderMatters = isOrderImportant();
		}
		
		if(orderMatters){
			for (Cell c : grid.values()) {
				if (c.getPriority() >= 0) {
					qCells.add(c);
				}
			}
	
			while (!qCells.isEmpty()) {
				qLocs.add(getKeyFromValue(qCells.poll()));
			}
		} else {
			qLocs.addAll(grid.keySet());
		}
		return qLocs;
	}

	private boolean isOrderImportant() {
		int anyPriorityValue = -1;
		for(Cell c : grid.values()){
			if(c.getPriority() >= 0){
				if(anyPriorityValue == -1){
					// assign first value
					anyPriorityValue = c.getPriority();
				} else {
					// first valued already assigned
					if (anyPriorityValue != c.getPriority()){
						// a different priority value found
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public Location getKeyFromValue(Cell value) {
		for (Location key : grid.keySet()) {
			if (grid.get(key) == value) {
				return key;
			}
		}
		return null;
	}

	private void generateNeighbors() {
		sideNeighbor = new HashMap<Location, ArrayList<Location>>();
		vertexNeighbor = new HashMap<Location, ArrayList<Location>>();
		for (Location pointBase : grid.keySet()) {
			ArrayList<Location> tempSide = new ArrayList<Location>();
			ArrayList<Location> tempVertex = new ArrayList<Location>();
			for (Location pointTest : grid.keySet())
				if (pointBase != pointTest) {
					double distanceBetween = pointBase.getPoint().distance(pointTest.getPoint());
					if (distanceBetween <= pointBase.getPoly().getApothem() + pointTest.getPoly().getApothem())
						tempSide.add(pointTest);
					if (distanceBetween <= pointBase.getPoly().getRadius() + pointTest.getPoly().getRadius())
						tempVertex.add(pointTest);
				}
			vertexNeighbor.put(pointBase, tempVertex);
			sideNeighbor.put(pointBase, tempSide);
		}
	}

	public void swap(Location loc1, Location loc2) {
		Cell temp = get(loc1);
		put(loc1, grid.get(loc2));
		put(loc2, temp);
	}

	private void calcCornerPoints() {
		Point2D max = new Point2D(0, 0);

		for (Location loc : grid.keySet()) {
			if (loc.getX() > max.getX())
				max = new Point2D(loc.getX(), max.getY());
			if (loc.getY() > max.getY())
				max = new Point2D(max.getX(), loc.getY());
		}
		this.bottomRightPoint = new Point2D(max.getX() + findSideLength(), max.getY() + findSideLength());

		Point2D min = new Point2D(max.getX(), max.getY());
		for (Location loc : grid.keySet()) {
			if (loc.getX() < min.getX())
				min = new Point2D(loc.getX(), min.getY());
			if (loc.getY() < min.getY())
				min = new Point2D(min.getX(), loc.getY());
		}
		this.topLeftPoint = new Point2D(min.getX() - findSideLength(), min.getY() - findSideLength());
	}

	public List<Location> getSideNeighbors(Location loc) {
		return sideNeighbor.get(loc);
	}

	public List<Location> getVertexNeighbors(Location loc) {
		return vertexNeighbor.get(loc);
	}

	private double findSideLength() {
		for (Location loc : grid.keySet())
			return loc.getPoly().getSideLength();
		return 0;
	}

	public Point2D getBottomRightPoint() {
		return bottomRightPoint;
	}

	public Point2D getTopLeftPoint() {
		return topLeftPoint;
	}

	public Society copy() {
		return new Society(grid, vertexNeighbor, sideNeighbor, bottomRightPoint, topLeftPoint, orderMatters);
	}

	public void put(Location currentLoc, Cell updatedCell) {
		grid.put(currentLoc, updatedCell);
	}

	public boolean tryToSwap(Location loc, Location potentialLoc, Integer targetState) {
		if (get(potentialLoc).getState() == targetState){
			swap(loc, potentialLoc);
			return true;
		}
		return false;
	}

}