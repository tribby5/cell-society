package cellsociety_team13;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launches the Application
 * Uses: Used to start the application
 * Dependencies: Calls InterfaceHandler to actuall display the program
 * @author Matthew Tribby
 */
public class Main extends Application{
	public static void main (String[] args){
		launch(args);
	}

	@Override
	/**
	 * Mandatory implemented method for Application. 
	 * @param primaryStage Stage for which to display the application
	 */
	public void start(Stage primaryStage) throws Exception {
		new InterfaceHandler(primaryStage);
	}
}
