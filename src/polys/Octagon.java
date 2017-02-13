package polys;

import cellsociety_team13.Shape;

public class Octagon extends Shape{
	public static final int numSides = 8;
	public static final double startingAngle = 117.5;
	
	public Octagon() {
		super(numSides, 0);
	}

	@Override
	public double getXAdvance() {
		return 0;
	}

	@Override
	public double getYAdvance() {
		return 0;
	}

	@Override
	public Shape changeNext() {
		return new Octagon();
	}

	@Override
	public int getType() {
		return 2;
	}

	@Override
	public Shape copy() {
		return new Octagon();
	}

	@Override
	public double[] getCenter() {
		double[] center = {getSideLength() / 2.0, getSideLength() / 2.0};
		return center;
	}
}
