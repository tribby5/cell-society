package cellsociety_team13;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import boards.HexagonBoard;
import boards.SquareBoard;
import boards.TriangleBoard;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CustomSimChooser {
	private ResourceBundle resources;
	private Interface parent;
	public static final String RESOURCE_PACKAGE = "English";
	private String[] inputs = new String[2];
	private int newSideLength = 0;
	String[] simulationNames = new String[]{
			"Game of Life",
			"Fire",
			"Predator and Prey",
			"Segregation",
			"Slime Molds"
	};
	private String[] shapeTypes = new String[]{
			"Rectangle",
			"Triangle",
			"Hexagon"
	};
	
	public CustomSimChooser(Interface inter){
		parent = inter;
		resources = ResourceBundle.getBundle("resources/" + RESOURCE_PACKAGE);
		Stage choiceStage = new Stage();
		VBox root = createContent();
		choiceStage.setScene(new Scene(root, 200, 200));
		choiceStage.show();
	}
	
	private Map<Location, Cell> getRectangleBoard() {
		return new SquareBoard(newSideLength, newSideLength).getBoard();
	}
	
	private Map<Location, Cell> getTriangleBoard() {
		return new TriangleBoard(newSideLength, newSideLength).getBoard();
	}
	
	private Map<Location, Cell> getHexagonBoard() {
		return new HexagonBoard(newSideLength, newSideLength).getBoard();
	}

	private VBox createContent(){
		ComboBox<String> type = new ComboBox<String>(listToObsList(Arrays.asList(simulationNames)));
		type.setPromptText(resources.getString("simType"));
		type.valueProperty().addListener(createChangeListener(0));
		
		ComboBox<String> shapeType = new ComboBox<String>(listToObsList(Arrays.asList(shapeTypes)));
		shapeType.setPromptText(resources.getString("shapeType"));
		shapeType.valueProperty().addListener(createChangeListener(1));

		Text lengthText = new Text(resources.getString("length"));
		Slider length = new Slider();
		length.setMin(5);
		length.setMax(30);
		length.setShowTickMarks(true);
		length.setShowTickLabels(true);
		length.valueProperty().addListener(new ChangeListener<Number>(){
			@Override 
			public void changed(ObservableValue ov, Number oldParam, Number newParam) {
				newSideLength = (int) Math.round((double)newParam);
			}
		});
		
		Button go = new Button(resources.getString("go"));
		go.setOnAction(e -> {modifyInput();});
		
		VBox root = new VBox(10);
		root.getChildren().addAll(type, shapeType, lengthText, length, go);
		
		root.setAlignment(Pos.CENTER);
		return root;
	}
	
	private ChangeListener<String> createChangeListener(int index){
		return new ChangeListener<String>(){
			@Override 
			public void changed(ObservableValue ov, String oldParam, String newParam) {
				inputs[index] = newParam;
			}
		};
	}
	
	private ObservableList<String> listToObsList(List<String> input){
		//from stack overflow: http://stackoverflow.com/questions/22191954/javafx-casting-arraylist-to-observablelist
		ObservableList<String> obsList = FXCollections.observableArrayList(input);
		return obsList;
	}
	
	private void modifyInput(){
		int simType = 0;
		if(inputs[0] != null){
			for(int i=0; i < simulationNames.length; i++){
				if(inputs[0].equals(simulationNames[i])){
					simType = i;
					break;
				}
			}
		}
		int shapeType = 0;
		if(inputs[1] != null){
			for(int i=0; i < shapeTypes.length; i++){
				if(inputs[1].equals(shapeTypes[i])){
					shapeType = i;
					break;
				}
			}
		}
		if(newSideLength == 0){
			newSideLength = 10;
		}
		
		createSimulation(simType, shapeType);
	}
	
	private void createSimulation(int simType, int shape){
		Manager newManager = XMLReader.MANAGERS.get(simType);
		Map<Location, Cell> grid = new HashMap<>();
		if(shape == 0)
			grid = getRectangleBoard();
		else if(shape ==1)	
			grid = getTriangleBoard();
		else{
			grid = getHexagonBoard();
		}
		
		Society society = new CellGenerator(grid, newManager).getSociety();
		newManager.setSociety(society);
		newManager.createParametersBounds();
		newManager.setDefaultParameters();
		parent.addInterface(newManager);
	}
}

