package pt.joja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Kruskal {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(new Edge('A', 'B', 12));
        graph.addEdge(new Edge('B', 'C', 10));
        graph.addEdge(new Edge('C', 'D', 3));
        graph.addEdge(new Edge('D', 'E', 4));
        graph.addEdge(new Edge('C', 'E', 5));
        graph.addEdge(new Edge('E', 'F', 2));
        graph.addEdge(new Edge('B', 'F', 7));
        graph.addEdge(new Edge('A', 'F', 16));
        graph.addEdge(new Edge('E', 'G', 8));
        graph.addEdge(new Edge('F', 'G', 9));
        graph.addEdge(new Edge('A', 'G', 14));

        mst(graph);

        System.out.println();

        // 和普利姆算法对同一个联通图求最小生成树
        Graph graph2 = new Graph();
        graph2.addEdge(new Edge('A', 'B', 5));
        graph2.addEdge(new Edge('B', 'D', 9));
        graph2.addEdge(new Edge('D', 'F', 4));
        graph2.addEdge(new Edge('F', 'E', 5));
        graph2.addEdge(new Edge('E', 'C', 8));
        graph2.addEdge(new Edge('C', 'A', 5));
        graph2.addEdge(new Edge('A', 'G', 2));
        graph2.addEdge(new Edge('B', 'G', 3));
        graph2.addEdge(new Edge('E', 'G', 4));
        graph2.addEdge(new Edge('G', 'F', 6));

        mst(graph2);
    }

    public static void mst(Graph graph) {
        Map<Character, Character> endVertexMap = new HashMap<>();
        for (Character vertex : graph.vertexes) {
            endVertexMap.put(vertex, vertex);
        }
        Collections.sort(graph.edges);
        Set<Character> inTree = new HashSet<>();
        for (Edge edge : graph.edges) {
            // 查询边的起点的所在树的根
            char endVertexStart = edge.start;
            // 根指向它自己
            while (endVertexStart != endVertexMap.get(endVertexStart)) {
                endVertexStart = endVertexMap.get(endVertexStart);
            }

            // 查询边的终点所在树的根
            char endVertexEnd = edge.end;
            // 根指向它自己
            while (endVertexEnd != endVertexMap.get(endVertexEnd)) {
                endVertexEnd = endVertexMap.get(endVertexEnd);
            }

            // 树根相同代表边的起点和终点已经在同一颗树上了
            if (endVertexStart == endVertexEnd) {
                continue;
            }

            // 树根不同时加入边
            System.out.println("加入边 " + edge.start + "-" + edge.end + " : " + edge.weight);
            inTree.add(edge.start);
            inTree.add(edge.end);

            // 将其中一颗树的树根指向另一颗树
            // 突然意识到其实不用判断大小，始终使用起点的树根作为新树根好了
            // if (endVertexStart > endVertexEnd) {
            // endVertexMap.put(endVertexEnd, endVertexStart);
            // } else {
            // endVertexMap.put(endVertexStart, endVertexEnd);
            // }
            endVertexMap.put(endVertexEnd, endVertexStart);

            // 因为每个点的最终点计算复杂，省略联通时终止的优化
            // Iterator<Character> endVertexs = endVertexMap.values().iterator();
            // char endVertexTemp = endVertexs.next();
            // boolean allInTree = true;
            // while (endVertexs.hasNext()) {
            // char endVertex = endVertexs.next();
            // if (endVertexTemp != endVertex) {
            // allInTree = false;
            // break;
            // }
            // }

            // if (allInTree) {
            // break;
            // }
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
    }
}
