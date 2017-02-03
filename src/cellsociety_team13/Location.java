package cellsociety_team13;
	import javafx.geometry.Point2D;
	import javafx.scene.paint.Color;
	import javafx.scene.shape.Polygon;
	
	public class Location extends Point2D{
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