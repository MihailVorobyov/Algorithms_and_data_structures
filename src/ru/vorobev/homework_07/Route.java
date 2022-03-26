package ru.vorobev.homework_07;

import java.util.ArrayList;
import java.util.List;

public class Route {
	
	int length;
	List<Vertex> vertexList;
	
	public Route() {
		this.length = -1;
		this.vertexList = new ArrayList<>();
	}
	
	int getLength() {
		return length;
	}
	
	void setLength(int length) {
		this.length = length;
	}
	
	void addVertex(Vertex v) {
		vertexList.add(v);
	}
	
	List<Vertex> getVertexList() {
		return vertexList;
	}
	
	@Override
	public String toString() {

            StringBuilder sb = new StringBuilder();

            int counter = 0;
            for (Vertex next : vertexList) {
                sb.append(next.getLabel());

                if (++counter < vertexList.size()) {
                    sb.append(" -> ");
                }
			}
            sb.append(", " + length + " км;");
		
		return sb.toString();
	}
}
