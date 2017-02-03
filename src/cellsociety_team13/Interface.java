package cellsociety_team13;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Interface{
	private Stage stage;
	private Font font = new Font("Times New Roman", 40);
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	private File xmlFile = null;
	public static final int FRAMES_PER_SEC = 60;
	public static final double MILLI_DELAY = 1000.0/FRAMES_PER_SEC;
	public static final double SEC_DELAY = 1.0/FRAMES_PER_SEC;
	public static final String RESOURCE_PACKAGE = "resources/";
	public static final String LANGUAGE = "English";
	private ResourceBundle resources;
	private Group baseRoot;
	private Group root;
	private Drawer myDrawer;
	private Manager myManager;
	
	public Interface(Stage primaryStage){
		stage = primaryStage;
		resources = ResourceBundle.getBundle(RESOURCE_PACKAGE + LANGUAGE);
	}
	
	public void setWelcome(){
		Text title = new Text(resources.getString("welcome"));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(font);
   	 	
   	 	Button cont = new Button(resources.getString("continue"));
   	 	cont.setOnAction(event -> setInfo());
   	 	
   	 	VBox root = new VBox();
   	 	root.getChildren().addAll(title, cont);
   	 	root.setSpacing(50);
   	 	root.setAlignment(Pos.CENTER);
   	 	Scene welcome = new Scene(root, WIDTH, HEIGHT);
		
		stage.setScene(welcome);
		stage.show();
	}
	
	public void setInfo(){
		GridPane root = new GridPane();
		Text generalInfo = new Text(resources.getString("generalInfo"));
		
		Button cont = new Button(resources.getString("continue"));
		cont.setVisible(false);
		cont.setOnAction(event -> setGame());
		
		Button fileChoose = new Button(resources.getString("chooseXML"));
		fileChoose.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				xmlFile = chooseFile();
				if(xmlFile != null){
					cont.setVisible(true);
				}
			}
		});
		
		root.add(generalInfo, 0, 0);
		root.add(fileChoose, 1, 0);
		root.add(cont, 1,1);
		root.setHgap(WIDTH/2);
		root.setVgap(HEIGHT/2);
		root.setAlignment(Pos.CENTER);

		Scene info  = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(info);
	}
	
	private File chooseFile(){
		FileChooser xmlChooser = new FileChooser();
		xmlChooser.setTitle(resources.getString("chooseXML"));
		File file = xmlChooser.showOpenDialog(stage);
		if(file != null){
			try {
				String fileType = Files.probeContentType(file.toPath());
				System.out.println(fileType);
				return file;
				
			} catch (IOException e) {
				System.out.println(resources.getString("noFileError") + e);
			}
		}
		return null;
	}
	
	public void setGame(){
		baseRoot = new Group();
		
		XMLReader xmlOutput = new XMLReader(xmlFile);
		
		root = baseRoot;
		myManager = new Manager(xmlOutput);
		
		myDrawer = new Drawer(root);
		
		stage.setScene(new Scene(root, WIDTH, HEIGHT));
		startSimulation();
	}
	
	private void startSimulation(){
		Timeline simulation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLI_DELAY), e -> step());
		simulation.setCycleCount(Timeline.INDEFINITE);
		simulation.getKeyFrames().add(frame);
		simulation.play();
	}
	
	private void step(){
		//give Society to Manager
		root = baseRoot;
		myManager.update();
		myDrawer.draw(myManager.getSociety());
	}
	
	
}
