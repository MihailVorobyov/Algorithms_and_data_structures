package ru.vorobev.homework_07;

public class Main {
    public static void main(String[] args) {
        findShortestPath();
    }

    private static void findShortestPath() {

        CityGraph graph = new CityGraphImpl(10);

        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Липецк");
        graph.addVertex("Тамбов");
        graph.addVertex("Орёл");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");
    
        graph.addEdge("Москва", "Тула", 184);
        graph.addEdge("Москва","Рязань", 199);
        graph.addEdge("Москва","Калуга", 194);
    
        graph.addEdge("Тула", "Липецк", 299);
        graph.addEdge("Липецк", "Воронеж", 125);
        
        graph.addEdge("Рязань", "Тамбов", 281);
        graph.addEdge("Тамбов", "Саратов", 390);
        graph.addEdge("Саратов", "Воронеж", 520);
        
        graph.addEdge("Калуга", "Орёл", 209);
        graph.addEdge("Орёл", "Курск", 167);
        graph.addEdge("Курск", "Воронеж", 226);
        
        System.out.println("Кратчайший путь: " + graph.getShortestPath("Москва", "Воронеж"));
    }
    
}
