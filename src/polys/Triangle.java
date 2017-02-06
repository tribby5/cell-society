package polys;

import cellsociety_team13.Shape;

public abstract class Triangle extends Shape{
	public static final int numSides = 3;

	public Triangle(double startingAngle) {
		super(numSides, startingAngle);
	}
}
