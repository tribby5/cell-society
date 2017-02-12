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
}
