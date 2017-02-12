package cellsociety_team13;

import java.io.File;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Interface{
	public static final List<String> TITLE = Arrays.asList(new String[] {
			"Game of Life",
			"Fire",
			"Predators and Pray / Shark and Fish",
			"Segregation",
			"SlimeMolds",
			"ForagingAnts"
	});
	
	private Stage stage;
	private Font font = new Font("Times New Roman", 35);
	private Font smallFont = new Font("Times New Roman", 20);
	public static final int WIDTH = 500;
	public static final int HEIGHT = 680;
	public static final int GRID_WIDTH = 500;
	public static final int GRID_HEIGHT = 500;
	private File xmlFile = null;
	public static final int FRAMES_PER_SEC = 1;
	public static final double MILLI_DELAY = 1000.0/FRAMES_PER_SEC;
	public static final String RESOURCE_PACKAGE = "English";
	public static final String XML_FILE_DIRECTORY = "./data";
	public static final String FILE_EXTENSION = ".xml";
	private Group root;
	private HBox buttonPanel;
	private ResourceBundle resources;
	private Timeline simulation;
	private Drawer myDrawer;
	private Manager myManager;
	private PopGraph graph;
	private InterfaceHandler myHandler;
	
	/**
	 * The constructor for the Interface class. This will create an interface object
	 * that will provide the user interface for the simulator to be played on. 
	 * @param primaryStage Stage on which Scenes will be displayed
	 */
	public Interface(Stage primaryStage, InterfaceHandler handler){
		stage = primaryStage;
		resources = ResourceBundle.getBundle("resources/" + RESOURCE_PACKAGE);
		setWelcome();
		myHandler = handler;
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
	
	public void makeGraph(Map<Color, Integer> test){
		graph = new PopGraph(test, WIDTH, 170);
		graph.setY(HEIGHT - 200);
	}
	
	public void setInfo(){
		VBox infoRoot = new VBox(50);
		infoRoot = createInformativeText(infoRoot);
		
		Button cont = new Button(resources.getString("continue"));
		cont.setVisible(false);
		cont.setOnAction(event -> setupSimulation());
		
		Text nonXML = new Text(resources.getString("nonXML"));
		nonXML.setFont(smallFont);
		nonXML.setVisible(false);
		
		Button fileChoose = new Button(resources.getString("chooseXML"));
		fileChoose.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				xmlFile = chooseFile();
				if(xmlFile != null){
					cont.setVisible(true);
					nonXML.setVisible(false);
				}
				else{
					nonXML.setVisible(true);
					cont.setVisible(false);
				}
			}
		});
		
		infoRoot.getChildren().addAll(fileChoose, cont, nonXML);
		infoRoot.setAlignment(Pos.CENTER);
		

		Scene info  = new Scene(infoRoot, WIDTH, HEIGHT);
		stage.setScene(info);
	}
	
	private VBox createInformativeText(VBox infoRoot){
		Text generalInfoTitle = new Text(resources.getString("generalInfoTitle"));
		generalInfoTitle.setFont(font);
		Text generalInfoText = new Text(resources.getString("generalInfoText"));
		generalInfoText.setWrappingWidth(WIDTH/2);
		generalInfoText.setTextAlignment(TextAlignment.CENTER);
		generalInfoText.setFont(smallFont);	
		infoRoot.getChildren().addAll(generalInfoTitle, generalInfoText);
		return infoRoot;
	}
	
	private File chooseFile(){
		FileChooser xmlChooser = new FileChooser();
		xmlChooser.setTitle(resources.getString("chooseXML"));
		xmlChooser.setInitialDirectory(new File(XML_FILE_DIRECTORY));
		File file = xmlChooser.showOpenDialog(stage);
		if(file != null){
				String name = file.getName();
				String fileType = name.substring(name.lastIndexOf("."), name.length());
				if(!fileType.equals(FILE_EXTENSION)){
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
		
		XMLReader read = new XMLReader(xmlFile);

		myManager = read.extractManager();
		makeGraph(myManager.getSociety().getPopulation());
		myDrawer = new Drawer();

		root = myDrawer.draw(root, myManager.getSociety(), true);
		root = graph.draw(root);
		stage.setScene(new Scene(root, WIDTH, HEIGHT, Color.DARKGRAY));
		stage.setTitle(TITLE.get(read.getTitleId()));
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
		Button reset = new Button(resources.getString("reset"));
		reset.setOnAction(e -> {simulation.stop(); setupSimulation();});
		Button newInterface = new Button("Add Simulation");
		newInterface.setOnAction(e -> {addInterface();});
		
		buttonPanel.getChildren().addAll(play, pause, stepThrough, createSlider(), createNewXMLButton(), reset, newInterface);
		buttonPanel.setLayoutY(HEIGHT - 40);
	}
	
	private void addInterface(){
		File newFile = chooseFile();
		if(newFile != null){
			myHandler.addInterface(newFile);
		}
		
	}
	
	public void setXMLFile(File file){
		xmlFile = file;
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
	
	private Button createNewXMLButton(){
		Button newXML = new Button(resources.getString("newXML"));
		newXML.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				simulation.pause();
				xmlFile = chooseFile();
				if(xmlFile != null){
					simulation.stop();
					setupSimulation();
				}}});
		return newXML;
	}

	private void startSimulation(){
		simulation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLI_DELAY), e -> step());
		simulation.setCycleCount(Timeline.INDEFINITE);
		simulation.getKeyFrames().add(frame);
	}

	private void changeSimulationSpeed(double factor){
		simulation.setRate(factor);
	}
	
	public void step(){
		root.getChildren().clear();
		graph.update(myManager.getSociety().getPopulation());
		root = graph.draw(root);
		root.getChildren().add(buttonPanel);
		myManager.update();
		root = myDrawer.draw(root, myManager.getSociety(), false);	
	}
	
	public String getXMLFile(){
		return xmlFile.toString();
	}
	public Manager getManager(){
		return myManager;
	}
	
	public void playSimulation(){
		simulation.play();
	}
	
	public void pauseSimulation(){
		simulation.pause();
	}
}