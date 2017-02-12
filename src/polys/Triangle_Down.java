package polys;

import cellsociety_team13.Shape;

public class Triangle_Down extends Triangle{
	public static final double startingAngle = 135;

	public Triangle_Down() {
		super(startingAngle);
	}

	@Override
	public Shape changeNext() {
		return new Triangle_Up();
	}
}
