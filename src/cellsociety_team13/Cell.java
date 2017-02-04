package cellsociety_team13;
import javafx.scene.paint.Color;

public abstract class Cell {
		private Color state;

		protected Cell(Color colorInput){
			this.state = colorInput;
		}

		public Color getState(){
			return this.state;
		}
		
		public abstract Cell change(int n);
}