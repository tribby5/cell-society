package cellsociety_team13;

import java.util.HashMap;
import java.util.Map;

import com.sun.prism.paint.Color;

import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Background;
import javafx.scene.Node;


public class PopGraph {
	private LineChart<Number, Number> popChart;
	private HashMap<String, XYChart.Series<Number, Number>> population = new HashMap<String, XYChart.Series<Number, Number>>();
	private int iteration = 0;
	public final static int MAX_SIZE = 10;

	public PopGraph(Map<String, Integer> pop, int maxWidth, int maxHeight){
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		popChart = new LineChart<Number,Number>(xAxis, yAxis);
		for(String key: pop.keySet()){
			population.put(key, createSeries(key, pop.get(key)));
		}
		popChart.setMaxHeight(maxHeight);
		popChart.setMaxWidth(maxWidth);
	}
	
	public void update(Map<String, Integer> pop){
		iteration++;
		for(String key:pop.keySet()){
			if(population.containsKey(key)){
				population.get(key).getData().add(new Data<Number, Number>((Number) iteration, (Number) pop.get(key)));
			}
			else{
				population.put(key, createSeries(key, pop.get(key)));
			}
		}
		/*
		if(iteration > MAX_SIZE){
			for(String key: population.keySet()){
				population.get(key).getData().remove(0);
			}
		}
		*/
	}
	
	private Series<Number, Number> createSeries(String color, Integer value){		
		XYChart.Series<Number, Number> temp = new XYChart.Series<Number, Number>();
		temp.getData().add(new Data<Number, Number>((Number) iteration, (Number) value));
		
		temp.nodeProperty().addListener((ObservableValue<? extends Node> o, Node old, Node node) ->{
			if(node != null){
				String colorCall = String.format("-fx-stroke: %s;", "#" + color.substring(2));
				node.setStyle(colorCall);
			}
		});
		return temp;
	}
	
	public Group draw(Group root){
		popChart.getData().clear();
		for(String key : population.keySet()){
			popChart.getData().add(population.get(key));
		}
		root.getChildren().add(popChart);
		return root;
	}
	
	public void setX(double x){
		popChart.setLayoutX(x);
	}
	
	public void setY(double y){
		popChart.setLayoutY(y);
	}
}
