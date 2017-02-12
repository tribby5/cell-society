package boards;

import polys.Square;

public class SquareBoard extends TypeBoard{	
	public SquareBoard(int rows, int columns){
		super(rows, columns, new Square());
	}
}
