package boards;

import polys.Hexagon;

public class HexagonBoard extends TypeBoard {	
	public HexagonBoard(int rows, int columns){
		super(rows, columns, new Hexagon());
	}
}
