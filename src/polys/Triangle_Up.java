package polys;

import cellsociety_team13.Shape;

/**
 * Class which creates a Triangle Shape that is pointing upwards, extension of Shape
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13)
 */
public class Triangle_Up extends Triangle {
	public static final double startingAngle = 90;

	/**
	 * Constructor which makes a normal, unscaled triangle that is pointed upwards
	 */
	public Triangle_Up() {
		super(startingAngle);
	}

	/**
	 * Returns a triangle that is facing downwards. This is to be used when creating a grid of
	 * alternating upwards and downwards pointing triangles.
	 * @return downward triangle
	 */
	@Override
	public Shape changeNext() {
		return new Triangle_Down();
	}

	@Override
	public int getType() {
		return 3;
	}

	@Override
	public Shape copy() {
		return new Triangle_Up();
	}
}
