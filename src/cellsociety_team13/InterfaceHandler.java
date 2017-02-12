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

	public InterfaceHandler(Stage primaryStage){
		resources = ResourceBundle.getBundle("resources/" + RESOURCE_PACKAGE);
		Interface first = new Interface(primaryStage, this);
		first.setWelcome();
		interfaces.add(first);
	}
	
	public void addInterface(File xmlFile){
		Interface next = new Interface(new Stage(), this);
		next.setXMLFile(xmlFile);
		next.setupSimulation();
		
		interfaces.add(next);
		/*
		for(int i = 0; i < interfaces.size(); i++){
			System.out.println(interfaces.get(i).getXMLFile());
		}
		System.out.println(interfaces.get(0).getManager().getSociety() == interfaces.get(1).getManager().getSociety());
		*/
		if(interfaces.size() == 2){
			createControlPanel();
		}
	}
	
	public void createControlPanel(){
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
