package user.Interface;
import java.util.ArrayList;

import javafx.stage.Stage;

public class InterfaceHandler {
	private ArrayList<Interface> interfaces = new ArrayList<Interface>();

	public InterfaceHandler(Stage primaryStage) throws InterruptedException{
		Interface first = new Interface(primaryStage);
		first.setWelcome();
		interfaces.add(first);
		//checkForNewSimulation();
	}
	
	public Interface addInterface(){
		Interface next = new Interface(new Stage());
		interfaces.add(next);
		return next;
	}
	
	public void checkForNewSimulation() throws InterruptedException{
		System.out.println("Whoops");
			for(int i = 0; i < interfaces.size(); i++){
				if(interfaces.get(i).getNewInterface()){
					Interface next = addInterface();
					next.setXMLFile(interfaces.get(i).getNextFile());
					next.setupSimulation();
				}
			}
	}
}
