package mapKey_Location;


import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Location extends Point2D{
	private double xPos;
	private double yPos;
	private Poly shape;

	public Location(double xIn, double yIn, Poly polyIn) {
		super(xIn, yIn);
		this.xPos = xIn;
		this.yPos = yIn;
		this.shape = polyIn;
		// TODO Auto-generated constructor stub
	}
	

	void draw(Group root, Color colorInput){
		Double[] vertices = shape.getVertices();
		Polygon polygon = new Polygon();
		
		for(int i = 0 ; i < shape.getSides() ; i++){
			vertices[i * 2] = vertices[i*2] + this.xPos;
			vertices[i * 2 + 1] = vertices[i*2 + 1] + this.yPos;
		}
		
		polygon.getPoints().addAll(vertices);
		root.getChildren().add(polygon);
	}
}
