package cellsociety_team13;

import java.io.File;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	public static final int HEIGHT = 550;
	public static final int GRID_WIDTH = 500;
	public static final int GRID_HEIGHT = 500;
	private File xmlFile = null;
	public static final int FRAMES_PER_SEC = 60;
	public static final double INITIAL_MILLI_DELAY = 1000.0/FRAMES_PER_SEC;
	private double milliDelay = INITIAL_MILLI_DELAY;
	public static final String RESOURCE_PACKAGE = "English";
	public static final String FILE_EXTENSION = ".xml";
	private Group root;
	private HBox buttonPanel;
	private ResourceBundle resources;
	private Timeline simulation;
	private Drawer myDrawer;
	private Manager myManager;
	
	public Interface(Stage primaryStage){
		stage = primaryStage;
		resources = ResourceBundle.getBundle("resources/" + RESOURCE_PACKAGE);
	}
	
	public void setWelcome(){
		Text title = new Text(resources.getString("welcome"));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(font);
   	 	
   	 	Button cont = new Button(resources.getString("continue"));
   	 	cont.setOnAction(event -> setInfo());
   	 	
   	 	VBox welcomeRoot = new VBox();
   	 	welcomeRoot.getChildren().addAll(title, cont);
   	 	welcomeRoot.setSpacing(50);
   	 	welcomeRoot.setAlignment(Pos.CENTER);
   	 	Scene welcome = new Scene(welcomeRoot, WIDTH, HEIGHT);
		
		stage.setScene(welcome);
		stage.show();
	}
	
	public void setInfo(){
		GridPane infoRoot = new GridPane();
		Text generalInfo = new Text(resources.getString("generalInfo"));
		generalInfo.setWrappingWidth(100);
		
		Button cont = new Button(resources.getString("continue"));
		cont.setVisible(false);
		cont.setOnAction(event -> setupSimulation());
		
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
		
		infoRoot.add(generalInfo, 0, 0);
		infoRoot.add(fileChoose, 1, 0);
		infoRoot.add(cont, 1,1);
		infoRoot.setHgap(WIDTH/2);
		infoRoot.setVgap(HEIGHT/2);
		infoRoot.setAlignment(Pos.CENTER);

		Scene info  = new Scene(infoRoot, WIDTH, HEIGHT);
		stage.setScene(info);
	}
	
	private File chooseFile(){
		FileChooser xmlChooser = new FileChooser();
		xmlChooser.setTitle(resources.getString("chooseXML"));
		File file = xmlChooser.showOpenDialog(stage);
		if(file != null){
				String name = file.getName();
				String fileType = name.substring(name.lastIndexOf("."), name.length());
				if(!fileType.equals(FILE_EXTENSION)){
					System.out.println(resources.getString("nonXML"));
					return null;
				}
				return file;
		}
		return null;
	}
	
	public void setupSimulation(){
		root = new Group();
		createButtonPanel();
		
		root.getChildren().add(buttonPanel);
		
		myManager = new XMLReader(xmlFile).getManager();
		myDrawer = new Drawer();
		root = myDrawer.draw(root, myManager.getSociety());
		stage.setScene(new Scene(root, WIDTH, HEIGHT));
		startSimulation();
	}
	
	private void createButtonPanel(){
		buttonPanel = new HBox();
		
		Button play = new Button(resources.getString("play"));
		play.setOnAction(event -> simulation.play());
		Button pause = new Button(resources.getString("pause"));
		pause.setOnAction(event -> simulation.pause());
		Button stepThrough = new Button(resources.getString("step"));
		stepThrough.setOnAction(event -> {simulation.pause(); step();});
		
		Slider speed = createSlider();
		
		buttonPanel.getChildren().addAll(play, pause, stepThrough, speed);
		buttonPanel.setLayoutX(WIDTH/2 - 170);
		buttonPanel.setLayoutY(HEIGHT - 40);
	}
	
	private Slider createSlider(){
		Slider speed = new Slider();
		speed.setMin(0);
		speed.setMax(5);
		speed.setValue(1);
		speed.setBlockIncrement(1);
		speed.setMajorTickUnit(1);
		speed.setShowTickLabels(true);
		speed.setShowTickMarks(true);
		speed.valueProperty().addListener((observable, oldValue, newValue) -> {changeSimulationSpeed((double) newValue);});
		return speed;
	}
	
	private void startSimulation(){
		simulation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(milliDelay), e -> step());
		simulation.setCycleCount(Timeline.INDEFINITE);
		simulation.getKeyFrames().add(frame);
		simulation.play();
	}

	private void changeSimulationSpeed(double factor){
		if(factor == 0){
			milliDelay = Integer.MAX_VALUE;
		}else{
			milliDelay = INITIAL_MILLI_DELAY/factor;
		}
	}
	
	private void step(){
		root.getChildren().clear();
		root.getChildren().add(buttonPanel);
		myManager.update();
		root = myDrawer.draw(root, myManager.getSociety());
	}
	
	
}