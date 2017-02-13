package polys;

import cellsociety_team13.Shape;

/**
 * Class which creates the basic Triangle Shape, extension of Shape
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13)
 */
public abstract class Triangle extends Shape{
	public static final int numSides = 3;

	/**
	 * Creates a triangle instance given a starting angle. Abstract because going to be
	 * implemented as upwards and downwards facing
	 * @param startingAngle
	 */
	public Triangle(double startingAngle) {
		super(numSides, startingAngle);
	}
	
	/**
	 * Returns the amount in the x-direction the next shape should be shifted when a custom 
	 * grid is being made.
	 * @return x-shift amount
	 */
	@Override
	public double getXAdvance() {
		return getSideLength();
	}

	/**
	 * Returns the amount in the y-direction the next shape should be shifted when a custom 
	 * grid is being made.
	 * @return y-shift amount
	 */
	@Override
	public double getYAdvance() {
		return 3*getApothem();
	}
}
