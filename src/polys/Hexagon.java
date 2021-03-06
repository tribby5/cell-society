package polys;

import cellsociety_team13.Shape;

/**
 * Class which creates a Hexagon Shape, extension of Shape
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13)
 */
public class Hexagon extends Shape{
	public static final int numSides = 6;
	public static final double startingAngle = 120;
	
	/**
	 * Constructor which makes a normal hexagon
	 */
	public Hexagon() {
		super(numSides, startingAngle);
	}

	/**
	 * Returns the amount in the x-direction the next shape should be shifted when a custom 
	 * grid is being made.
	 * @return x-shift amount
	 */
	@Override
	public double getXAdvance() {
		return getApothem();
	}

	/**
	 * Returns the amount in the y-direction the next shape should be shifted when a custom
	 * grid is being made.
	 * @return y-shift amount
	 */
	@Override
	public double getYAdvance() {
		return 3*getSideLength();
	}

	/**
	 * Returns a new Hexagon (shapes not alternating so function has no special purpose for
	 * this class)
	 * @return new Hexagon 
	 */
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
