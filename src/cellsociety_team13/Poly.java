package cellsociety_team13;

import java.util.Arrays;
import java.util.List;

import polys.Hexagon;
import polys.Octagon;
import polys.Square;
import polys.Triangle_Down;
import polys.Triangle_Up;

public abstract class Poly {

	public static final List<Poly> POLYS = Arrays.asList(new Poly[] {
			new Square(),
			new Hexagon(),
			new Octagon(),
			new Triangle_Up(),
			new Triangle_Down()
	});
	
	private static final int DEFAULT_SIDE_LENGTH = 1;
	
	private double apothem;
	private double radius;
	private int numSides;
	private Double[] vertices;
	
	public Poly(int pNumSides, double startingAngle){
		numSides = pNumSides;
		calculateApothem();
		calculateRadius();
		calculateVertices(startingAngle);	
	}
	

	private void calculateRadius() {
		this.radius = DEFAULT_SIDE_LENGTH / (2 * Math.sin(Math.toRadians(180.0 / numSides)));		
	}


	private void calculateApothem() {
		this.apothem = DEFAULT_SIDE_LENGTH / (2 * Math.tan(Math.toRadians(180.0 / numSides)));		
	}
	
	

	private void calculateVertices(double startingAngle) {
		double[] center = {DEFAULT_SIDE_LENGTH / 2.0, DEFAULT_SIDE_LENGTH / 2.0};
		vertices = new Double[numSides * 2];
		System.out.print(numSides);
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
