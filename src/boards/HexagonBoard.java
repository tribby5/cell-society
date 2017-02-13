package boards;

import polys.Hexagon;

public class HexagonBoard extends TypeBoard {	
	public HexagonBoard(int rows, int columns){
		super(rows/2, 3*columns/2, new Hexagon());
	}
}
