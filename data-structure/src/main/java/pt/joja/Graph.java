package pt.joja;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    // Sample Graph:
    // A-B-C-D
    // ..|\|
    // E-F-G-H
    // ---------
    // 0-1-2-3
    // ..|\|
    // 4-5-6-7

    // DFS: A-B-C-D-G-F-E-H
    // BFS: A-B-C-F-G-D-E-H

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        for (char chr : "ABCDEFGH".toCharArray()) {
            graph.insertVertex("" + chr);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 5, 1);
        graph.insertEdge(1, 6, 1);
        graph.insertEdge(2, 3, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(4, 5, 1);
        graph.insertEdge(5, 6, 1);
        graph.insertEdge(6, 7, 1);

        graph.dfs(0);
        graph.bfs(0);
    }

    private ArrayList<String> vertexList;
    private int[][] edges;
    private int edgeCnt;

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        edgeCnt = 0;
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeCnt++;
    }

    public int edgeCnt() {
        return edgeCnt;
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[vertexList.size()];
        System.out.print("DFS: ");
        dfs(start, visited);
        System.out.println();
    }

    private void dfs(int start, boolean[] visited) {
        if (visited[start]) {
            return;
        }
        System.out.print(vertexList.get(start) + " ");
        visited[start] = true;
        for (int i = 0; i < edges.length; i++) {
            if (edges[start][i] == 1) {
                dfs(i, visited);
            }
        }
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[vertexList.size()];
        LinkedList<Integer> vertQueue = new LinkedList<>();
        vertQueue.add(start);
        System.out.print("BFS: ");
        while (vertQueue.size() > 0) {
            int curr = vertQueue.removeFirst();
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            System.out.print(vertexList.get(curr) + " ");
            for (int i = 0; i < edges.length; i++) {
                if (edges[curr][i] == 1) {
                    if (visited[i]) {
                        continue;
                    }
                    vertQueue.add(i);
                }
            }
        }
        System.out.println();
    }
}
