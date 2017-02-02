package cellsociety_team13;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Interface{
	private Stage stage;
	private Font font = new Font("Times New Roman", 40);
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	private File xmlFile = null;
	
	public Interface(Stage primaryStage){
		stage = primaryStage;
	}
	
	public void step(double elapsedTime){
		
	}
	
	public void setWelcome(){
		Text title = new Text("Welcome");
		title.setFont(font);
   	 
   	 	Button cont = new Button("Continue");
   	 	cont.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            	public void handle(ActionEvent event) {
                	setInfo();
            	}
   	 		});
   	 	
   	 	VBox root = new VBox();
   	 	root.getChildren().addAll(title, cont);
   	 	root.setSpacing(10);
   	 	root.setAlignment(Pos.CENTER);
   	 	Scene welcome = new Scene(root, WIDTH, HEIGHT);
		
		stage.setScene(welcome);
		stage.show();
	}
	
	public void setInfo(){
		GridPane root = new GridPane();
		Text generalInfo = new Text("General Info");
		
		Button cont = new Button("Continue");
		cont.setVisible(false);
		cont.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				setGame();
			}
		});
		
		Button fileChoose = new Button("Choose XML File");
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
		xmlChooser.setTitle("Choose XML file");
		File file = xmlChooser.showOpenDialog(stage);
		if(file != null){
			try {
				String fileType = Files.probeContentType(file.toPath());
				System.out.println(fileType);
				return file;
				
			} catch (IOException e) {
				System.out.println("Cannot find file type" + "\n Exception:" + e);
			}
		}
		return null;
	}
	
	public void setGame(){
		//XMLReader xmlRead = new XMLReader();
		//HashMap<Location> grid = xmlRead.read(xmlFile);
		//Society society = new Society(grid);
	
	}
	
}
