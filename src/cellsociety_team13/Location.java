package cellsociety_team13;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polys.Hexagon;
import polys.Octagon;
import polys.Square;
import polys.Triangle_Down;
import polys.Triangle_Up;

/**
 * Class which holds the x and y locations and the Shape to be used in displaying the grid
 * and that are stored in Society. 
 * Uses: When paired with Cell instances which contain color and state, it creates a drawable
 * entity for the simulation. 
 * Note: Need Cell to create the fill and given state to this Locations. Also need
 * the Drawer class to physically draw these.
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13), Matthew Tribby (mrt28)
 */
public class Location{
	public static final List<String> FIELDS = Arrays.asList(new String[] {
			"x",
			"y",
			"poly"
	});
	
	public static final List<Shape> POLYS = Arrays.asList(new Shape[] {
			getSquare(),
			getHexagon(),
			getOctagon(),
			getTriangle_Up(),
			getTriangle_Down()
	});

	private Point2D place;
	private Shape shape;
	private Polygon polygon;

	/**
	 * Constructor for the Location class. Given data in the form of a Map, the place, which
	 * is a 2D point representing the position of Location and the Shape instance of Location
	 * are created.
	 * Expected use: Designed to integrate well with an XML reader which would parse as Strings.
	 * @param data Map with String keys and values. The data is parsed and converted into the
	 * appropriate types. 
	 */
	public Location(Map<String, String> data) {
		place = new Point2D(Double.parseDouble(data.get(FIELDS.get(0))), Double.parseDouble(data.get(FIELDS.get(1))));
		shape = POLYS.get(Integer.parseInt(data.get(FIELDS.get(2))));
	}
	
	/**
	 * A Constructor for the Location class. Creates the place, the 2D point which represents
	 * the position of the Location and the Shape instance. 
	 * Expected use: Non-XML work unlike the first constructor
	 * @param x
	 * @param y
	 * @param pShape Shape instance
	 */
	public Location(double x, double y, Shape pShape) {
		place = new Point2D(x, y);
		shape = pShape;
	}
	
	private static Shape getTriangle_Down() {
		return new Triangle_Down();
	}

	private static Shape getTriangle_Up() {
		return new Triangle_Up();
	}

	private static Shape getOctagon() {
		return new Octagon();
	}

	private static Shape getHexagon() {
		return new Hexagon();
	}

	private static Shape getSquare() {
		return new Square();
	}

	/**
	 * Generate a polygon based on the Shape instance which is created upon instantiation
	 * of the Location. The polygon is the actual drawable object in JavaFX so it is extracted
	 * from the non-drawable Shape class. 
	 */
	public void generatePolygon(){
		Double[] vertices = shape.getVertices();
		polygon = new Polygon();
		for(int i = 0 ; i < shape.getSides() ; i++){
			vertices[i * 2] += getY();
			vertices[i * 2 + 1] += getX();
		}
		polygon.getPoints().addAll(vertices);
	}
	
	/**
	 * Regenerates the polygon based on current vertices.
	 * Expected Use: Should be used after vertices are changed without changing
	 * the polygon instance.
	 */
	public void regeneratePolygon(){
		polygon.getPoints().clear();
		polygon.getPoints().addAll(shape.getVertices());
	}	
	
	/**
	 * Returns x coordinate of Location
	 * @return x position
	 */
	public double getX(){
		return place.getX();
	}
	
	/**
	 * Returns y coordinate of Location
	 * @return y position
	 */
	public double getY(){
		return place.getY();
	}

	/**
	 * Colors in the Polygon instance of the class given a certain color
	 * @param colorState color to fill in
	 */
	public void applyColorStateToPolygon(Color colorState){
		polygon.setFill(colorState);
	}

	/**
	 * Returns the Polygon instance variable
	 * @return Polygon, visual representation of Location
	 */
	public Polygon getPolygon(){
		return polygon;
	}

	/**
	 * Returns the Shape instance variable
	 * @return Shape, the definition of the Location's shape
	 */
	public Shape getPoly(){
		return shape;
	}

	/**
	 * Returns a 2D point for the Location (including both x and y)
	 * @return 2D position
	 */
	public Point2D getPoint() {
		return place;
	}

	/**
	 * Moves and scales a Location based on the furthest point in the grid
	 * @param furthestPoint 2D point representing the farthest point on the grid (lower right)
	 */
	public void moveAndScaleShape(Point2D furthestPoint) {
		this.moveCenter(furthestPoint);
		this.resetShape(furthestPoint);
	}
	
	/**
	 * Moves the center of a Location by scaling based on the grid's width
	 * @param point 2D point to be scaled
	 */
	public void moveCenter(Point2D point) {
		place = new Point2D(getX() * Interface.GRID_WIDTH/point.getX(), 
							getY() * Interface.GRID_HEIGHT/point.getY());
	}
	
	/**
	 * Resets the Shape instance variable and sets side length for the Shape by scaling
	 * based on the grid's width. The corresponding polygon is also generated.
	 * @param Furthest 2D point on the grid
	 */
	public void resetShape(Point2D point) {
		shape = new Square();
		shape.setSideLength(Interface.GRID_WIDTH/point.getX());
		generatePolygon();
	}
	
	/**
	 * Returns the difference in angle between the Location instance and another Location
	 * instance given an orientation which describes how the Location is facing in degrees
	 * @param neighborLoc Location instance
	 * @param orientation Angle that the point is facing
	 * @return double representing the difference in angles
	 */
	public double calculateAngleDifference(Location neighborLoc, double orientation) {
		//double distance = this.getPoint().distance(neighborLoc.getPoint());
		double distanceX = neighborLoc.getX() - this.getX();
		double distanceY = -1 * (neighborLoc.getY() - this.getY());
		
		double angle = -1 * Math.toDegrees(Math.atan2(distanceY , distanceX));
		double angleDif = (angle) - (orientation);

		if (angleDif > 180) {
			angleDif -= 360;
		} else if (angleDif < -180){
			angleDif += 360;
		}
		return angleDif;
	}
}