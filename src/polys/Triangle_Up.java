package polys;

import cellsociety_team13.Shape;

public class Triangle_Up extends Triangle {
	public static final double startingAngle = 90;

	public Triangle_Up() {
		super(startingAngle);
	}

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
