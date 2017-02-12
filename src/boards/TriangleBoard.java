package boards;

import polys.Triangle_Down;

public class TriangleBoard extends TypeBoard {	
	public TriangleBoard(int rows, int columns){
		super(rows, columns, new Triangle_Down());
	}
}
