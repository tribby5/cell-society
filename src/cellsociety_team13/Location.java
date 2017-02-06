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

public class Location{
	public static final List<String> FIELDS = Arrays.asList(new String[] {
			"x",
			"y",
			"poly"
	});


	public List<Shape> POLYS = Arrays.asList(new Shape[] {
			getSquare(),
			new Hexagon(),
			new Octagon(),
			new Triangle_Up(),
			new Triangle_Down()
	});

	private Point2D place;
	private Shape shape;
	private Polygon polygon;

	public Location(Map<String, String> data) {
		place = new Point2D(Double.parseDouble(data.get(FIELDS.get(0))), Double.parseDouble(data.get(FIELDS.get(1))));
		shape = POLYS.get(Integer.parseInt(data.get(FIELDS.get(2))));
		generatePolygon();
	}

	private Shape getSquare() {
		return new Square();
	}

	public void generatePolygon(){
		Double[] vertices = shape.getVertices();
		polygon = new Polygon();
		for(int i = 0 ; i < shape.getSides() ; i++){
			vertices[i * 2] += getX();
			vertices[i * 2 + 1] += getY();
		}
		polygon.getPoints().addAll(vertices);
	}
	
	public void regeneratePolygon(){
		polygon.getPoints().clear();
		polygon.getPoints().addAll(shape.getVertices());
		//System.out.println(polygon);
	}	
	
	public double getX(){
		return place.getX();
	}
	
	public double getY(){
		return place.getY();
	}

	public void applyColorStateToPolygon(Color colorState){
		polygon.setFill(colorState);
	}

	public Polygon getPolygon(){
		return polygon;
	}

	public Shape getPoly(){
		return shape;
	}


	public void moveCenter(Point2D point) {
		place = new Point2D(getX() * Interface.GRID_WIDTH/point.getX(), getY() * Interface.GRID_HEIGHT/point.getY());
	}

	public Point2D getPoint() {
		return place;
	}

	public void resetShape(Point2D point) {
		shape = new Square();
		shape.setSideLength(Interface.GRID_WIDTH/point.getX());
		generatePolygon();

	}
}