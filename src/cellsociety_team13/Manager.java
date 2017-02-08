package cellsociety_team13;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager{
	private Referee myReferee;
	private Society mySociety;
	
	

	public Manager(Society society, Referee referee){
		myReferee = referee;
		mySociety = society;
	}


	public Society getSociety(){
		return mySociety;
	}


	public void update() {
		myReferee.updateGrid(mySociety);
		mySociety.updateGrid(myReferee.getGrid());
	}
	
	


	
	

}

	
	
	