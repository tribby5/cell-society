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
	
	public class Location extends Point2D{
		public static final List<String> FIELDS = Arrays.asList(new String[] {
				"xPos",
				"yPos",
				"polyType"
		});

		private static final int DEFAULT_SIDE_LENGTH = 1;
		
		public static final List<Poly> POLYS = Arrays.asList(new Poly[] {
				new Square(DEFAULT_SIDE_LENGTH),
				new Hexagon(DEFAULT_SIDE_LENGTH),
				new Octagon(DEFAULT_SIDE_LENGTH),
				new Triangle_Up(DEFAULT_SIDE_LENGTH),
				new Triangle_Down(DEFAULT_SIDE_LENGTH)
		});
		
		private double xPos;
		private double yPos;
		private Poly shape;
		private Polygon polygon;
	
		public Location(double xIn, double yIn, Poly polyIn) {
			super(xIn, yIn);
			this.xPos = xIn;
			this.yPos = yIn;
			this.shape = polyIn;
			generatePolygon();
			
		}
		
		public Location(Map<String, String> data) {
			super(Double.parseDouble(data.get(FIELDS.get(0))), Double.parseDouble(data.get(FIELDS.get(1))));
			this.xPos = Double.parseDouble(data.get(FIELDS.get(0)));
			this.yPos = Double.parseDouble(data.get(FIELDS.get(1)));
			this.shape = POLYS.get(Integer.parseInt(data.get(FIELDS.get(2))));
			generatePolygon();
		}

		public void generatePolygon(){
			Double[] vertices = shape.getVertices();
			polygon = new Polygon();
			
			for(int i = 0 ; i < shape.getSides() ; i++){
				vertices[i * 2] += this.xPos;
				vertices[i * 2 + 1] += this.yPos;
			}
			
			polygon.getPoints().addAll(vertices);
		}
		
		public void applyColorStateToPolygon(Color colorState){
			polygon.setFill(colorState);
		}
		
		public Polygon getPolygon(){
			return this.polygon;
		}
		
		public Poly getPoly(){
			return this.shape;
		}
	}