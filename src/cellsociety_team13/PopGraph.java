package cellsociety_team13;

import java.util.HashMap;
import java.util.Map;


import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import javafx.scene.Node;


public class PopGraph {
	private LineChart<Number, Number> popChart;
	private HashMap<Color, XYChart.Series<Number, Number>> population = new HashMap<Color, XYChart.Series<Number, Number>>();
	private int iteration = 0;
	public final static int MAX_SIZE = 10;

	public PopGraph(Map<Color, Integer> pop, int maxWidth, int maxHeight){
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		popChart = new LineChart<Number,Number>(xAxis, yAxis);
		for(Color key: pop.keySet()){
			population.put(key, createSeries(key, pop.get(key)));
		}
		popChart.setMaxHeight(maxHeight);
		popChart.setMaxWidth(maxWidth);
		popChart.setCreateSymbols(false);
	}
	
	public void update(Map<Color, Integer> map){
		iteration++;
		for(Color key:map.keySet()){
			if(population.containsKey(key)){
				population.get(key).getData().add(new Data<Number, Number>((Number) iteration, (Number) map.get(key)));
			}
			else{
				population.put(key, createSeries(key, map.get(key)));
			}
		}
	}
	
	private Series<Number, Number> createSeries(Color key, Integer value){		
		XYChart.Series<Number, Number> temp = new XYChart.Series<Number, Number>();
		temp.getData().add(new Data<Number, Number>((Number) iteration, (Number) value));
		
		temp.nodeProperty().addListener((ObservableValue<? extends Node> o, Node old, Node node) ->{
			if(node != null){
				//Basic code structure gotten from stack
				//http://stackoverflow.com/questions/29921149/how-to-change-xycharts-color-in-javafx
				String colorCall = String.format("-fx-stroke: %s;", "#" + key.toString().substring(2));
				node.setStyle(colorCall);
			}
		});
		return temp;
	}
	
	public Group draw(Group root){
		popChart.getData().clear();
		for(Color key : population.keySet()){
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
