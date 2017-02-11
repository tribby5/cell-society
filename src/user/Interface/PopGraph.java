package user.Interface;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;


public class PopGraph {
	private LineChart<Number, Number> popChart;
	private Map<String, XYChart.Series<Number, Number>> population;
	private int iteration = 0;
	public final static int MAX_SIZE = 10;

	public PopGraph(Map<String, Integer> pop){
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Time");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Count");
		popChart = new LineChart<Number,Number>(xAxis, yAxis);
		popChart.setTitle("Type Population");
		for(String key: pop.keySet()){
			XYChart.Series<Number, Number> temp = new XYChart.Series<Number, Number>();
			temp.getData().add(new Data<Number, Number>((Number) iteration, (Number) pop.get(key)));
			population.put(key, temp);
		}
	}
	
	public void update(Map<String, Integer> pop){
		iteration++;
		for(String key:pop.keySet()){
			population.get(key).getData().add(new Data<Number, Number>((Number) iteration, (Number) pop.get(key)));
		}
		if(iteration > MAX_SIZE){
			for(String key: population.keySet()){
				population.get(key).getData().remove(0);
			}
		}
	}
	
	public Group draw(Group root){
		popChart.getData().clear();
		for(String key : population.keySet()){
			popChart.getData().add(population.get(key));
		}
		root.getChildren().add(popChart);
		return root;
	}
}
