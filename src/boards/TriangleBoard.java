package boards;

import polys.Triangle_Down;

public class TriangleBoard extends TypeBoard {	
	public TriangleBoard(int rows, int columns){
		super(2*rows, columns, new Triangle_Down());
	}
}
