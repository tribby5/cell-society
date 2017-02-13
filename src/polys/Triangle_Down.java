package polys;

import cellsociety_team13.Shape;

/**
 * Class which creates a Triangle Shape that is pointing downwards, extension of Shape
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13)
 */
public class Triangle_Down extends Triangle{
	public static final double startingAngle = 30;

	/**
	 * Constructor which makes a normal, unscaled triangle that is pointed downwards
	 */
	public Triangle_Down() {
		super(startingAngle);
	}

	/**
	 * Returns a triangle that is facing upwards. This is to be used when creating a grid of
	 * alternating upwards and downwards pointing triangles.
	 * @return upwards triangle
	 */
	@Override
	public Shape changeNext() {
		return new Triangle_Up();
	}

	@Override
	public int getType() {
		return 4;
	}

	@Override
	public Shape copy() {
		return new Triangle_Down();
	}
}
