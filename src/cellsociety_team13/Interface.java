package cellsociety_team13;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	/**
	 * Class which creates an interface which is a single instance of simulator
	 * @author Matthew Tribby
	 *
	 */
import simulation.SlimeMolds.SlimeMolds;

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
	public static final int GRID_WIDTH = 550;
	public static final int GRID_HEIGHT = 550;
	private File xmlFile = null;
	public static final int FRAMES_PER_SEC = 1;
	public static final double MILLI_DELAY = 1000.0/FRAMES_PER_SEC;
	public static final String RESOURCE_PACKAGE = "English";
	public static final String XML_FILE_DIRECTORY = "./data";
	public static final String FILE_EXTENSION = ".xml";
	private Group root;
	private VBox buttonPanel;
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
	
	/**
	 * Sets the welcome screen for the simulator. A basic screen with a continue
	 * button
	 */
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
	
	/**
	 * Initialize the population graph which shows the count of different types
	 * of cells over time.
	 * @param initialValues Map which pairs colors and counts of different colors
	 */
	public void makeGraph(Map<Color, Integer> initialValues){
		graph = new PopGraph(initialValues, WIDTH, 130);
		graph.setY(HEIGHT - 210);
	}
	
	/**
	 * Sets the information screen where user can choose which type of simulation
	 * to run. This screen also contains basic information about the simulator.
	 */
	public void setInfo(){
		VBox infoRoot = new VBox(50);
		infoRoot = createInformativeText(infoRoot);
		
		Button cont = new Button(resources.getString("continue"));
		cont.setVisible(false);
		cont.setOnAction(event -> extractManager());
		
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
		
		
		infoRoot.getChildren().addAll(fileChoose, createCustomSimButton(), cont, nonXML);
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
	/**
	 * This method setups a simulator. Important: There must be a proper XML
	 * file in the xmlFile instance variable for this function to work properly.
	 * There is error handling for improper XML files.
	 */
	public void extractManager(){
		if(xmlFile == null){
			setInfo();
		}
		else{
		try {
			XMLReader read = new XMLReader(xmlFile);
			myManager = read.extractManager();
			setupSimulation(TITLE.get(read.getTitleId()));
		} catch (XMLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(resources.getString("badXMLTitle"));
			alert.setContentText(resources.getString("badXML"));
			alert.showAndWait().ifPresent(response ->{setInfo();});
		}
		}

	}
	
	public void setupSimulation(String title){
		root = new Group();
		myDrawer = new Drawer();
		createButtonPanel();
		root.getChildren().add(buttonPanel);
		root = myDrawer.draw(root, myManager, true);
		if(!(myManager instanceof SlimeMolds)){
			makeGraph(myManager.getSociety().getPopulation());
			root = graph.draw(root);
		}
		stage.setScene(new Scene(root, WIDTH, HEIGHT, Color.DARKGRAY));
		stage.setTitle(title);
		
		startSimulation();
	}
	
	private void createButtonPanel(){
		buttonPanel = new VBox(0);
		
		Button play = new Button(resources.getString("play"));
		play.setOnAction(event -> simulation.play());
		Button pause = new Button(resources.getString("pause"));
		pause.setOnAction(event -> simulation.pause());
		Button stepThrough = new Button(resources.getString("step"));
		stepThrough.setOnAction(event -> {simulation.pause(); step();});
		Button reset = new Button(resources.getString("reset"));
		reset.setOnAction(e -> {simulation.stop(); extractManager();});
		Button newInterface = new Button(resources.getString("addSim"));
		newInterface.setOnAction(e -> {addInterface();});
		Slider speed = createSlider(0,1,5);
		speed.setMajorTickUnit(1);
		speed.valueProperty().addListener((observable, oldValue, newValue) -> {changeSimulationSpeed((double) newValue);});
		
		HBox rowOne = new HBox(play, pause, stepThrough, speed, createNewXMLButton(), reset);
		HBox rowTwo = new HBox(newInterface, createParameterChanger());
		buttonPanel.getChildren().addAll(rowOne, rowTwo);
		buttonPanel.setLayoutY(HEIGHT - 70);
	}
	
	private Button createCustomSimButton(){
		Button customSim = new Button(resources.getString("custom"));
		customSim.setOnAction(e -> {new CustomSimChooser(this);});
		return customSim;
	}
	
	private HBox createParameterChanger(){
		HBox parameterChanger = new HBox();
		Map<String, List<Double>> params = myManager.getParametersBounds();
	
		if(params != null){
			//from stack overflow: http://stackoverflow.com/questions/2319538/most-concise-way-to-convert-a-setstring-to-a-liststring
			List<String> names = new ArrayList<String>(params.keySet());
			//from stack overflow: http://stackoverflow.com/questions/22191954/javafx-casting-arraylist-to-observablelist
			ObservableList<String> obsNames = FXCollections.observableArrayList(names);
			ComboBox<String> parameterList = new ComboBox<String>(obsNames);
			parameterList.setMaxWidth(200);
			parameterList.setPromptText(resources.getString("parameterChoose"));
			parameterList.valueProperty().addListener(new ChangeListener<String>(){
				@Override 
				public void changed(ObservableValue ov, String oldParam, String newParam) {      
					Slider slider = createSlider(params.get(newParam).get(0), params.get(newParam).get(1), params.get(newParam).get(2));
					slider.valueProperty().addListener((observable, oldValue, newValue) -> {myManager.updateParameter(newParam, (double) newValue);});
					if(parameterChanger.getChildren().size() == 2){
						parameterChanger.getChildren().remove(1);
					}
					parameterChanger.getChildren().add(slider);
				}    
			});
		
			parameterChanger.getChildren().add(parameterList);
		}
			return parameterChanger;
	}
	
	private void addInterface(){
		File newFile = chooseFile();
		if(newFile != null){
			myHandler.addInterface(newFile);
		}
	}
	
	/**
	 * Adds another interface by giving it a manager
	 * @param manager
	 */
	public void addInterface(Manager manager){
		myHandler.addInterface(manager);
	}
	
	/**
	 * Sets the instance variable for the XML file
	 * @param file file we want to be the XML file for this interface
	 */
	public void setXMLFile(File file){
		xmlFile = file;
	}
	
	/**
	 * Sets the instance variable for the Manager
	 * @param manager
	 */
	public void setManager(Manager manager){
		myManager = manager;
	}
	
	private Slider createSlider(double min, double start, double max){
		Slider slider = new Slider();
		slider.setMin(min);
		slider.setMax(max);
		slider.setValue(start);
		slider.setBlockIncrement((max-min)/5);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		return slider;
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
					extractManager();
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
	
	/**
	 * Steps the simulation through. Important: Assumes there is a simulation
	 * with a timeline that is currently running (Created when run setupSimulation()) 
	 */
	public void step(){
		root.getChildren().clear();
		if(!(myManager instanceof SlimeMolds)){
			graph.update(myManager.getSociety().getPopulation());
			root = graph.draw(root);
			graph.update(myManager.getSociety().getPopulation());
		}
		root.getChildren().add(buttonPanel);
		myManager.update();
		root = myDrawer.draw(root, myManager, false);	
	}
	
	/**
	 * Plays simulation. Important: Assumes that there is a simulation timeline
	 * already created (Created when run setupSimulation())
	 */
	public void playSimulation(){
		simulation.play();
	}
	
	/**
	 * Pauses simulation. Important: Assumes that there is a simulation timeline
	 * already created (Created when run setupSimulation())
	 */
	public void pauseSimulation(){
		simulation.pause();
	}
}