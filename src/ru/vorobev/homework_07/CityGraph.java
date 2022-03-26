package ru.vorobev.homework_07;

public interface CityGraph extends Graph {
	
	boolean addEdge(String startLabel, String secondLabel, Integer path);
	
	String getShortestPath(String startLabel, String endLabel);
}
