package polys;

import cellsociety_team13.Shape;

public class Square extends Shape{
	public static final int numSides = 4;
	public static final double startingAngle = 135;
	
	public Square() {
		super(numSides, startingAngle);
	}

	@Override
	public double getXAdvance() {
		return getSideLength();
	}

	@Override
	public double getYAdvance() {
		return getSideLength();
	}

	@Override
	public Shape changeNext() {
		return new Square();
	}

	@Override
	public int getType() {
		return 0;
	}

	@Override
	public Shape copy() {
		return new Square();
	}

	@Override
	public double[] getCenter() {
		double[] center = {getSideLength()/2.0, getSideLength()/2.0};
		return center;
	}
}