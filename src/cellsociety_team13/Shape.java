package cellsociety_team13;

public abstract class Shape {
	public final static double INITIAL_SIDE_LENGTH = 1;
	private double sideLength = INITIAL_SIDE_LENGTH;
	
	private double apothem;
	private double radius;
	private int numSides;
	private Double[] vertices;
	private double angle;

	public Shape(int pNumSides, double startingAngle){
		numSides = pNumSides;
		angle = startingAngle;
		updateShapeParameters();
	}
	
	private void updateShapeParameters() {
		calculateRadius();
		calculateApothem();
		calculateVertices();
	}

	private void calculateVertices() {
		double[] center = {sideLength / 2.0, sideLength / 2.0};
		vertices = new Double[numSides * 2];
		//System.out.print(numSides);
		double angleBetweenVertices = 360 / numSides;
		
		for(int i = 0 ; i < numSides; i++){
			double angleOfVertex = angle + i * angleBetweenVertices;															
			vertices[i * 2] = center[0] + this.getRadius() * Math.cos(Math.toRadians(angleOfVertex));
			vertices[i * 2 + 1] = center[1] - this.getRadius() * Math.sin(Math.toRadians(angleOfVertex));
		}
	}
	
	private void calculateRadius() {
		this.radius = sideLength / (2 * Math.sin(Math.toRadians(180.0 / numSides)));		
	}

	private void calculateApothem() {
		this.apothem = sideLength / (2 * Math.tan(Math.toRadians(180.0 / numSides)));		
	}
	
	public double getApothem(){
		return this.apothem;
	}
	
	public double getRadius(){
		return this.radius;
	}
	
	public Double[] getVertices(){
		return this.vertices;
	}
	
	public int getSides(){
		return this.numSides;
	}
	
	public void setSideLength(double length){
		sideLength = length;
		updateShapeParameters();
	}
	
	public double getSideLength(){
		return sideLength;
	}
	
}
