package polys;

import cellsociety_team13.Shape;

public class Hexagon extends Shape{
	public static final int numSides = 6;
	public static final double startingAngle = 120;
	
	public Hexagon() {
		super(numSides, startingAngle);
	}

	@Override
	public double getXAdvance() {
		return (2*getRadius())+getSideLength();
	}

	@Override
	public double getYAdvance() {
		return 2*getApothem();
	}

	@Override
	public Shape changeNext() {
		return new Hexagon();
	}
	
	@Override
	public int getType() {
		return 1;
	}

	@Override
	public Shape copy() {
		return new Hexagon();
	}
	
	@Override
	public double[] getCenter() {
		double[] center = {getSideLength(), getSideLength()};
		return center;
	}
}
