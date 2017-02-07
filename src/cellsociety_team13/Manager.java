package cellsociety_team13;

import javafx.geometry.Point2D;

public class Manager {
	private Referee myReferee;
	private Society mySociety;
	

	public Manager(Society society, Referee referee){
		myReferee = referee;
		mySociety = society;
		
		if (myReferee.isTorodialWorld()){
			//mySociety.addTorodialNeighbors();
		}
	}


	public Society getSociety(){
		return mySociety;
	}


	public void update() {
		myReferee.giveSociety(mySociety);
		mySociety.updateGrid(myReferee.getGrid());
	}
	
	public Point2D getFurthestPoint(){
		return mySociety.getBottomRightPoint();
	}
}
