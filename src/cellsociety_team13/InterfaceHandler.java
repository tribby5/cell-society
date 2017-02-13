package cellsociety_team13;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InterfaceHandler {
	private static final String RESOURCE_PACKAGE = "English";
	private ArrayList<Interface> interfaces = new ArrayList<Interface>();
	private ResourceBundle resources;
	public static final double CONTROL_PANEL_WIDTH = 250;
	public static final double CONTROL_PANEL_HEIGHT = 30;
	
	/**
	 * Constructor which takes a stage and creates the first interface that goes in handler
	 * @param primaryStage Stage to display the interface on
	 */
	public InterfaceHandler(Stage primaryStage){
		resources = ResourceBundle.getBundle("resources/" + RESOURCE_PACKAGE);
		Interface first = new Interface(primaryStage, this);
		first.setWelcome();
		interfaces.add(first);
	}
	
	/**
	 * Creates a new instance of interface based on the xmlFile passed and starts it at
	 * the simulation screen.
	 * @param xmlFile xmlFile from which the simulation is to be created
	 */
	public void addInterface(File xmlFile){
		Interface next = new Interface(new Stage(), this);
		next.setXMLFile(xmlFile);
		next.extractManager();
		interfaces.add(next);
		if(interfaces.size() == 2){
			createControlPanel();
		}
	}
	
	public void addInterface(Manager manager) {
		Interface next = new Interface(new Stage(), this);
		next.setManager(manager);
		next.setupSimulation(resources.getString("custom"));
		interfaces.add(next);
	}	

	private void createControlPanel(){
		Button playAll = new Button(resources.getString("playAll"));
		playAll.setOnAction(e -> {playAll();});
		Button pauseAll = new Button(resources.getString("pauseAll"));
		pauseAll.setOnAction(e -> {pauseAll();});
		Button stepThrough = new Button(resources.getString("step"));
		stepThrough.setOnAction(e -> {stepAll();});
		
		HBox controlPanel = new HBox(playAll, pauseAll, stepThrough);
		Stage stage = new Stage();
		stage.setScene(new Scene(controlPanel, CONTROL_PANEL_WIDTH, CONTROL_PANEL_HEIGHT));
		stage.show();
	}

	private void playAll(){
		for(int i = 0; i < interfaces.size(); i++){
			interfaces.get(i).playSimulation();
		}
	}
	
	private void pauseAll(){
		for(int i = 0; i < interfaces.size(); i++){
			interfaces.get(i).pauseSimulation();
		}
	}
	
	private void stepAll(){
		for(int i = 0; i < interfaces.size(); i++){
			interfaces.get(i).pauseSimulation();
			interfaces.get(i).step();
		}
	}
	
}
