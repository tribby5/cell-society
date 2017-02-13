package polys;

import cellsociety_team13.Shape;

/**
 * Class which creates a Square Shape, extension of Shape
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13)
 */
public class Square extends Shape{
	public static final int numSides = 4;
	public static final double startingAngle = 135;
	
	/**
	 * Constructor which makes a normal, unscaled square
	 */
	public Square() {
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
		return getSideLength();
	}

	/**
	 * Returns a new Square (shapes not alternating in square grid, so function has no 
	 * special purpose for this class)
	 * @return new Square
	 */
	@Override
	public Shape changeNext() {
		return new Square();
	}
}
