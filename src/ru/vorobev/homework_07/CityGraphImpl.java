package ru.vorobev.homework_07;

import java.util.*;

public class CityGraphImpl implements CityGraph {

    private final List<Vertex> vertexList;
    private final boolean[][] adjMatrix;
    private final Integer[][] pathMatrix;

    public CityGraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new boolean[maxVertexCount][maxVertexCount];
        this.pathMatrix = new Integer[maxVertexCount][maxVertexCount];
    }

    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(secondLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        adjMatrix[startIndex][endIndex] = true; ////////////!!!!
//        adjMatrix[endIndex][startIndex] = true; ////////////!!!!

        return true;
    }

    private int indexOf(String label) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getLabel().equals(label)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel, String... others) {
        boolean result = addEdge(startLabel, secondLabel);

        for (String other : others) {
           result &= addEdge(startLabel, other);
        }

        return result;
    }
    
    
    @Override
    public boolean addEdge(String startLabel, String secondLabel, Integer pathLength) {
        boolean result = addEdge(startLabel, secondLabel);
    
        if (result) {
            int startIndex = indexOf(startLabel);
            int endIndex = indexOf(secondLabel);
            pathMatrix[startIndex][endIndex] = pathLength;
        }
        
        return result;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getSize(); i++) {
            sb.append(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMatrix[i][j]) {
                    sb.append(" -> ").append(vertexList.get(j));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);

        if (startIndex == -1) {
            throw new IllegalArgumentException("неверная вершина " + startLabel);
        }

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(stack, vertex);
            } else {
                stack.pop();
            }
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);

        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }

        return null;
    }
    
    private Vertex getNearVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i]) {
                return vertexList.get(i);
            }
        }
        
        return null;
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        stack.push(vertex);
        vertex.setIsVisited(true);
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        queue.add(vertex);
        vertex.setIsVisited(true);
    }

    @Override
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);

        if (startIndex == -1) {
            throw new IllegalArgumentException("неверная вершина " + startLabel);
        }

        Queue<Vertex> stack = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(stack, vertex);
            } else {
                stack.remove();
            }
        }
    }
    
    @Override
    public String getShortestPath(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Wrong index");
        }
        
        final Vertex startVertex = vertexList.get(startIndex);
        Vertex previous;
        Vertex next;
        Route route;
        List<Route> routes = new ArrayList<>();
        
        // Проверяем все направления из начального узла
        while ((next = getNearUnvisitedVertex(startVertex)) != null) {
            previous = startVertex;
            route = new Route();
            route.addVertex(previous);
            route.addVertex(next);
            next.setIsVisited(true);
            int length = pathMatrix[indexOf(previous.getLabel())][indexOf(next.getLabel())];
            
            previous = next;
            // идём по узлам пока не достигнем узла назначения или null
            while ((next = getNearVertex(next)) != null) {
                route.addVertex(next);
                length += pathMatrix[indexOf(previous.getLabel())][indexOf(next.getLabel())];
    
                if (endLabel.equals(next.getLabel())) {
                    break;
                }
                
                previous = next;
            }
            
            route.setLength(length);
            System.out.println(route);
            routes.add(route);
        }
    
        System.out.println("\n============================================\n");
        
        Route minRoute = routes.stream().min(Comparator.comparingInt(Route::getLength)).get();
        
        return minRoute.toString();
    }
    
}
