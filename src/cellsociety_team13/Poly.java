package cellsociety_team13;

public abstract class Poly {
	private double apothem;
	private double radius;
	private int numSides;
	private Double[] vertices;

	
	protected Poly(int sideLength, int numSides, double startingAngle){
		calculateApothem(sideLength);
		calculateRadius(sideLength);
		calculateVertices(sideLength, startingAngle);
		
	}
	

	private void calculateRadius(int sideLength) {
		this.radius = sideLength / (2 * Math.sin(Math.toRadians(180.0 / numSides)));		
	}


	private void calculateApothem(int sideLength) {
		this.apothem = sideLength / (2 * Math.tan(Math.toRadians(180.0 / numSides)));		
	}
	
	

	private void calculateVertices(int sideLength, double startingAngle) {
		double[] center = {sideLength / 2.0, sideLength / 2.0};
		vertices = new Double[numSides * 2];
		double angleBetweenVertices = 360 / numSides;
		
		for(int i = 0 ; i < numSides; i++){
			double angleOfVertex = startingAngle + i * angleBetweenVertices;
			
			vertices[i * 2] = center[0] + this.getApothem() * Math.cos(Math.toRadians(angleOfVertex));
			vertices[i * 2 + 1] = center[1] - this.getApothem() * Math.sin(Math.toRadians(angleOfVertex));
		}
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
}
