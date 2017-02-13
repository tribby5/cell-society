package cellsociety_team13;

/**
 * Class which defines the shape of a cell. Designed to create a set of
 * vertices which can later be formed into a visible polygon on a JavaFX scene. Built
 * to maximize flexibility in creating any possible shape. 
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13)
 *
 */
public abstract class Shape {
	public final static double INITIAL_SIDE_LENGTH = 1;
	private double sideLength = INITIAL_SIDE_LENGTH;
	
	private double apothem;
	private double radius;
	private int numSides;
	private Double[] vertices;
	private double angle;
	
	/**
	 * Constructor which creates a shape with a desired number of sides and a starting angle
	 * to start to draw the vertices from. Then method is called to calculate radius, apothem,
	 * and vertices of the shape
	 * @param pNumSides Number of sides
	 * @param startingAngle Angle in degrees
	 */
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
	
	/**
	 * Returns the apothem of the shape (length to side)
	 * @return apothem
	 */
	public double getApothem(){
		return this.apothem;
	}
	
	/**
	 * Returns the radius of the shape (length to vertex)
	 * @return radius
	 */
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Array of vertices of the shape
	 * @return vertices
	 */
	public Double[] getVertices(){
		return this.vertices;
	}
	
	/**
	 * Get the number of sides of the shapes
	 * @return number of sides
	 */
	public int getSides(){
		return this.numSides;
	}
	
	/**
	 * Sets the side length of the shape, useful for resizing shape
	 * @param length New length of the sides
	 */
	public void setSideLength(double length){
		sideLength = length;
		updateShapeParameters();
	}
	
	/**
	 * Returns the current side length of the shape
	 * @return side length
	 */
	public double getSideLength(){
		return sideLength;
	}

	/**
	 * Returns how much each Shape should be moved ahead of the next one on the x-axis
	 * @return necessary x-offset
	 */
	public abstract double getXAdvance();
	
	/**
	 * Returns how much each Shape should be moved ahead of the next one on the x-axis
	 * @return necessary y-offset
	 */
	public abstract double getYAdvance();

	/**
	 * If the board has two shapes it will alternate between them. If the board has only
	 * one shape, this implemented function will do nothing.
	 * @return Shape of next
	 */
	public abstract Shape changeNext();
	
}
