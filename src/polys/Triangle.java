package polys;

import cellsociety_team13.Shape;

public abstract class Triangle extends Shape{
	public static final int numSides = 3;

	public Triangle(double startingAngle) {
		super(numSides, startingAngle);
	}
	
	@Override
	public double getYAdvance() {
		return INITIAL_SIDE_LENGTH/2;
	}

	@Override
	public double getXAdvance() {
		return getRadius()+getApothem();
	}
	
	@Override
	public double[] getCenter() {
		double[] center = {getSideLength() / 2.0, getRadius()};
		return center;
	}
}
