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
import javafx.scene.paint.Color;

/**
 * Class which holds our data structure, grid, which stores the location and
 * cell, itself. Several methods here are used to perform changes to the grid.
 * Cells have access to these methods. There are usually two instances of this
 * class in cells in order to have the previous state along with the new state
 * (currentSociety and newSociety). The class also holds many map functions.
 * This highlights how society should been seen as an extension of grid.
 * 
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13), Matthew Tribby
 *         (mrt28)
 *
 */
public class Society {
	private Map<Location, ArrayList<Location>> vertexNeighbor;
	private Map<Location, ArrayList<Location>> sideNeighbor;
	private Map<Location, Cell> grid;
	private Point2D scalarPoint;
	private Boolean orderMatters;

	/**
	 * Creates the basics of a Society instance. The constructor calls some
	 * methods to initialize features of Society. For example, neighbors are
	 * calculated from the get-go because they are location based and not cell
	 * based. They only need to be calculated once.
	 * 
	 * @param rawGrid
	 *            the first grid introduced to the society
	 */
	public Society(Map<Location, Cell> rawGrid) {
		grid = rawGrid;
		generateNeighbors();
		calcCornerPoints();
		orderMatters = null;
	}

	/**
	 * A constructor that is called in .copy. This creates an instance that has
	 * all the same instance variables as the parent copy the methods in the
	 * normal constructor are unnecessary for those methods have already been
	 * called
	 * 
	 * @param grid2
	 *            the grid from the parent
	 * @param vertexNeighbor2
	 *            the already-calculated vertex neighbors from parent
	 * @param sideNeighbor2
	 *            the already-calculated side neighbors from parent
	 * @param scalarPoint2
	 * 			  the point to determine scale
	 * @param orderMatters2
	 *            the boolean that controls whether order is important when
	 *            processing cells. For example, in predatorprey simulation,
	 *            sharks move first
	 */
	public Society(Map<Location, Cell> grid2, Map<Location, ArrayList<Location>> vertexNeighbor2,
			Map<Location, ArrayList<Location>> sideNeighbor2, Point2D scalarPoint2, Boolean orderMatters2) {
		this.grid = new HashMap<>(grid2);
		this.vertexNeighbor = vertexNeighbor2;
		this.sideNeighbor = sideNeighbor2;
		this.scalarPoint = scalarPoint2;
		this.orderMatters = orderMatters2;
	}

	/**
	 * creates a copy of society with the all the parameters from the old
	 * society
	 * 
	 * @return the new society constructor, specifically for copying a society
	 */
	public Society copy() {
		return new Society(grid, vertexNeighbor, sideNeighbor, scalarPoint, orderMatters);
	}

	/*
	 * extending the .get function from Map
	 */
	public Cell get(Location loc) {
		return grid.get(loc);
	}

	/**
	 * extending the .put function from Map
	 * 
	 * @param currentLoc
	 * @param updatedCell
	 */
	public void put(Location currentLoc, Cell updatedCell) {
		grid.put(currentLoc, updatedCell);
	}

	/*
	 * extending the .keySet function from Map
	 */
	public Set<Location> keySet() {
		return grid.keySet();
	}

	/*
	 * extending the .values function from Map
	 */
	public Collection<Cell> values() {
		return grid.values();
	}

	/**
	 * gets the key from a specific cell this works because we have unique value
	 * elements
	 * 
	 * @param the
	 *            value element
	 * @return the corresponding key element
	 */
	public Location getKeyFromValue(Cell value) {
		for (Location key : grid.keySet()) {
			if (grid.get(key) == value) {
				return key;
			}
		}
		return null;
	}

	/**
	 * creates a copy of the grid within society. used when producing a grid for
	 * shapes from the custom input
	 * 
	 * @return
	 */
	public Map<Location, Cell> getGridCopy() {
		return new HashMap<Location, Cell>(grid);
	}

	/**
	 * swaps the values of two key values
	 * 
	 * @param loc1
	 * @param loc2
	 */
	public void swap(Location loc1, Location loc2) {
		Cell temp = get(loc1);
		put(loc1, grid.get(loc2));
		put(loc2, temp);
	}

	/**
	 * checks to see whether a swap can occur. This is done by making sure the
	 * target for the swap is still of the same state this is necesary, for
	 * example, if a shark wants to move a spot and a shark has already moved
	 * there. This check is done on new Societies, not current.
	 * 
	 * @param loc
	 *            : the location looking to swap its cell value in the grid of
	 *            society
	 * @param potentialLoc
	 *            : the location that needs to be checked
	 * @param targetState
	 *            : the state that the cell at potentialLoc needs to be in order
	 *            for the swap to be valid
	 * @return if the swap took place, return true. This allows the manager to
	 *         try again with a different potentiall target location
	 */
	public boolean tryToSwap(Location loc, Location potentialLoc, Integer targetState) {
		if (get(potentialLoc).getState() == targetState) {
			swap(loc, potentialLoc);
			return true;
		}
		return false;
	}

	/**
	 * returns the a list of locations that are the side neighbors of the input
	 * location
	 * 
	 * @param loc
	 *            : the location in question
	 * @return : a list of neighbors
	 */
	public List<Location> getSideNeighbors(Location loc) {
		return sideNeighbor.get(loc);
	}

	/**
	 * returns the a list of locations that are the vertex neighbors of the
	 * input location
	 * 
	 * @param loc
	 *            : the location in question
	 * @return : a list of neighbors
	 */
	public List<Location> getVertexNeighbors(Location loc) {
		return vertexNeighbor.get(loc);
	}


	/**
	 * creates a list of integers with the counts of different types of
	 * neighbors the is used in many of the simulations so it is calculated at
	 * every step For example, game of life and fire only need the neighbor
	 * count to update. This is only run by Managers
	 * 
	 * @param neighborsLoc
	 * @return
	 */
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
	
	public double getScalarPoint() {
		return Math.max(scalarPoint.getX(), scalarPoint.getY());
	}

	

	/**
	 * creates a queue of locations that is sorted by the cells priority the
	 * priority value is part of all cells the location queue is then created
	 * using the order of the cells
	 * 
	 * @return
	 */
	public Queue<Location> setProcessingOrder() {
		PriorityQueue<Cell> qCells = new PriorityQueue<Cell>();
		Queue<Location> qLocs = new LinkedList<Location>();

		if (orderMatters == null) {
			orderMatters = isOrderImportant();
		}

		if (orderMatters) {
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

	/**
	 * creates a color population for the graph below the visual grid
	 * @return
	 */
	public Map<Color, Integer> getPopulation() {
		HashMap<Color, Integer> population = new HashMap<Color, Integer>();

		for (Cell cell : grid.values()) {
			Color color = cell.getColor();
			if (!population.containsKey(color)) {
				population.put(color, 1);
			} else {
				population.put(color, population.get(color) + 1);
			}
		}
		return population;
	}


	private void calcCornerPoints() {
		Point2D max = new Point2D(0, 0);

		for (Location loc : grid.keySet()) {
			if (loc.getX() > max.getX())
				max = new Point2D(loc.getX(), max.getY());
			if (loc.getY() > max.getY())
				max = new Point2D(max.getX(), loc.getY());
		}
		this.scalarPoint = new Point2D(1.3*max.getX(), 1.3*max.getY());
	}

	private boolean isOrderImportant() {
		int anyPriorityValue = -1;
		for (Cell c : grid.values()) {
			if (c.getPriority() >= 0) {
				if (anyPriorityValue == -1) {
					// assign first value
					anyPriorityValue = c.getPriority();
				} else {
					// first valued already assigned
					if (anyPriorityValue != c.getPriority()) {
						// a different priority value found
						return true;
					}
				}
			}
		}
		return false;
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

}