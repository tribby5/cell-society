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

	/**
	 * Class which creates a population graph for cells. Given a Map with a color and a
	 * count of the color, it graphs the change over time of the data.
	 * Example of how to use: Initialize the graph to your starting values and then whenever
	 * you step through your animation, update the data and redraw the graph and it will
	 * appear as if it is moving in live time. 
	 * Dependencies: None. Data needs to be inputted though.
	 * @author Matthew Tribby (mrt28)
	 */
public class PopGraph {
	private LineChart<Number, Number> popChart;
	private HashMap<Color, XYChart.Series<Number, Number>> population = new HashMap<Color, XYChart.Series<Number, Number>>();
	private int iteration = 0;
	public final static int MAX_SIZE = 10;

	/**
	 * Constructor for PopGraph class. Creates a LineChart from the data passed in by pop.
	 * @param pop Map which has the initial values for the graph, color and count of color
	 * @param maxWidth Maximum width desired for the graph
	 * @param maxHeight Maximum height desired for the graph
	 */
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
	
	/**
	 * Updates the data in the graph, does not actually draw the graph
	 * @param values Map which has the new data to update
	 */
	public void update(Map<Color, Integer> values){
		iteration++;
		for(Color key:values.keySet()){
			if(population.containsKey(key)){
				population.get(key).getData().add(new Data<Number, Number>((Number) iteration, (Number) values.get(key)));
			}
			else{
				population.put(key, createSeries(key, values.get(key)));
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
	
	/**
	 * Draws the graph based on the available data. Given a root, it draws the graph onto
	 * the root and then returns the updated root.
	 * @param root Group to be changed
	 * @return updated root with graph on it
	 */
	public Group draw(Group root){
		popChart.getData().clear();
		for(Color key : population.keySet()){
			popChart.getData().add(population.get(key));
		}
		root.getChildren().add(popChart);
		return root;
	}
	
	/**
	 * Sets the x offset for the graph
	 * @param x value for which to offset the graph on the x-axis
	 */
	public void setX(double x){
		popChart.setLayoutX(x);
	}
	
	/**
	 * Sets the y offset for the graph
	 * @param y value for which to offset the graph on the y-axis
	 */
	public void setY(double y){
		popChart.setLayoutY(y);
	}
}
