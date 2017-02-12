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
}
