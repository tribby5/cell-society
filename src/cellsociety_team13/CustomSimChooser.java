package cellsociety_team13;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

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
	public static final String RESOURCE_PACKAGE = "English";
	private String[] inputs = new String[3];
	private List<String> types = Arrays.asList(new String[] {
			"Game of Life",
			"Fire",
			"Segregation",
			"Predator and Prey",
			"Slime Molds"
	});
	private List<String> shapeTypes = Arrays.asList(new String[]{
			"Rectangle",
			"Triangle",
			"Hexagon"
	});
	
	public CustomSimChooser(){
		resources = ResourceBundle.getBundle("resources/" + RESOURCE_PACKAGE);
		Stage choiceStage = new Stage();
		VBox root = createContent();
		choiceStage.setScene(new Scene(root, 200, 200));
		choiceStage.show();
	}
	
	private VBox createContent(){
		ComboBox<String> type = new ComboBox<String>(listToObsList(types));
		type.setPromptText(resources.getString("simType"));
		type.valueProperty().addListener(createChangeListener(0));
		
		ComboBox<String> shapeType = new ComboBox<String>(listToObsList(shapeTypes));
		shapeType.setPromptText(resources.getString("shapeType"));
		shapeType.valueProperty().addListener(createChangeListener(1));

		Text lengthText = new Text(resources.getString("length"));
		Slider length = new Slider();
		length.setMin(2);
		length.setMax(100);
		length.setShowTickMarks(true);
		length.setShowTickLabels(true);
		length.valueProperty().addListener(new ChangeListener<Number>(){
			@Override 
			public void changed(ObservableValue ov, Number oldParam, Number newParam) {
				inputs[2] = Integer.toString((int) Math.round((double)newParam));
			}
		});
		
		Button go = new Button(resources.getString("go"));
		go.setOnAction(e -> {System.out.println(inputs[0] + " " + inputs[1] + " " + inputs[2]);});
		
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
}

