package pt.joja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    public static void main(String[] args) {

        // 求从A出发
        Graph graph2 = new Graph();
        graph2.addEdge(new Edge('A', 'B', 5));
        graph2.addEdge(new Edge('B', 'D', 9));
        graph2.addEdge(new Edge('D', 'F', 4));
        graph2.addEdge(new Edge('F', 'E', 5));
        graph2.addEdge(new Edge('E', 'C', 8));
        graph2.addEdge(new Edge('C', 'A', 7));
        graph2.addEdge(new Edge('A', 'G', 2));
        graph2.addEdge(new Edge('B', 'G', 3));
        graph2.addEdge(new Edge('E', 'G', 4));
        graph2.addEdge(new Edge('G', 'F', 6));

        shortestPath(graph2, 'A');
    }

    public static void shortestPath(Graph graph, char start) {
        Set<Character> visited = new HashSet<>();
        Map<Character, Character> path = new HashMap<>();
        Map<Character, Integer> pathLen = new HashMap<>();

        visited.add(start);
        for (Edge edge : graph.edges) {
            if (edge.contains(start)) {
                char nextVertex = edge.otherside(start);
                path.put(nextVertex, start);
                pathLen.put(nextVertex, edge.weight);
            }
        }

        while (visited.size() < graph.vertexes.size()) {
            // 找出目前联通点中到树根最短的顶点
            char nearestVertex = ' ';
            int len = Integer.MAX_VALUE;
            for (char vertex : pathLen.keySet()) {
                if (visited.contains(vertex)) {
                    continue;
                }
                if (len > pathLen.get(vertex)) {
                    nearestVertex = vertex;
                    len = pathLen.get(vertex);
                }
            }

            // 加入树中
            visited.add(nearestVertex);

            // 输出根到新加入顶点的路径
            LinkedList<Character> stack = new LinkedList<>();
            stack.push(nearestVertex);
            Character fromVertex = nearestVertex;
            while ((fromVertex = path.get(fromVertex)) != null) {
                stack.push(fromVertex);
            }
            System.out.print("加入：");
            while (true) {
                System.out.print(stack.pop());
                if (stack.isEmpty()) {
                    break;
                }
                System.out.print("-");
            }
            System.out.println(" : " + len);

            // 将和新顶点相连的顶点的距树根距离更新
            for (Edge edge : graph.edges) {
                if (edge.contains(nearestVertex)) {
                    char otherside = edge.otherside(nearestVertex);
                    int weight = edge.weight;

                    // 如果已经在树里了，就跳过
                    if (visited.contains(otherside)) {
                        continue;
                    }

                    // 如果已经是备选加入顶点了
                    if (pathLen.containsKey(otherside)) {
                        int currLen = pathLen.get(otherside);
                        // 如果刚才加入的顶点提供了更短的路径，就更新它的信息
                        if (currLen > (weight + len)) {
                            pathLen.put(otherside, weight + len);
                            path.put(otherside, nearestVertex);
                        }
                    } else {
                        pathLen.put(otherside, weight + len);
                        path.put(otherside, nearestVertex);
                    }
                }
            }
        }

    }

    private static class Graph {

        List<Edge> edges = new ArrayList<>();
        Set<Character> vertexes = new HashSet<>();

        void addEdge(Edge edge) {
            edges.add(edge);
            vertexes.add(edge.start);
            vertexes.add(edge.end);
        }

    }

    private static class Edge implements Comparable<Edge> {
        char start;
        char end;
        int weight;

        Edge(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

        public boolean contains(char vertex) {
            return start == vertex || end == vertex;
        }

        public char otherside(char vertex) {
            if (start == vertex) {
                return end;
            } else {
                return start;
            }
        }
    }
}
