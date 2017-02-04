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

// TODO BIG PROBLEM WITH THIS CLASS AS IT HAS A SUPER THAT IS SUPER PRIVATE
public class Location extends Point2D{
	public static final List<String> FIELDS = Arrays.asList(new String[] {
			"x",
			"y",
			"poly"
	});

	public static final List<Poly> POLYS = Arrays.asList(new Poly[] {
			new Square(),
			new Hexagon(),
			new Octagon(),
			new Triangle_Up(),
			new Triangle_Down()
	});

	private Poly shape;
	private Polygon polygon;

	public Location(Map<String, String> data) {
		super(Double.parseDouble(data.get(FIELDS.get(0))), Double.parseDouble(data.get(FIELDS.get(1))));
		shape = POLYS.get(Integer.parseInt(data.get(FIELDS.get(2))));
		generatePolygon();
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

	public void applyColorStateToPolygon(Color colorState){
		polygon.setFill(colorState);
	}

	public Polygon getPolygon(){
		return polygon;
	}

	public Poly getPoly(){
		return shape;
	}

	public Point2D multiply(double scale) {
		return super.multiply(scale);
	}
}