package polys;

import cellsociety_team13.Poly;

public abstract class Triangle extends Poly{
	public static final int numSides = 3;

	public Triangle(double startingAngle) {
		super(numSides, startingAngle);
	}
	
}
