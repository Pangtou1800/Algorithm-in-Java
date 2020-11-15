package pt.joja;

import java.util.Arrays;

public class Prim {

    public static void main(String[] args) {
        MGraph village = new MGraph(7);
        int[][] weight = new int[][] { //
                { 0, 5, 7, 0, 0, 0, 2 }, //
                { 5, 0, 0, 9, 0, 0, 3 }, //
                { 7, 0, 0, 0, 8, 0, 0 }, //
                { 0, 9, 0, 0, 0, 4, 0 }, //
                { 0, 0, 8, 0, 0, 5, 4 }, //
                { 0, 0, 0, 4, 5, 0, 6 }, //
                { 2, 3, 0, 0, 4, 6, 0 }, };
        village.weight = weight;

        MinTree minTree = new MinTree();
        minTree.createGraph(village);
        minTree.showGraph();

        minTree.getMST(0);
    }

}

class MinTree {
    MGraph graph;

    MGraph mTreeGraph;

    public void createGraph(MGraph graph) {
        this.graph = graph;
        mTreeGraph = new MGraph(graph.vertexes);
    }

    public void showGraph() {
        System.out.println("  " + Arrays.toString(graph.data));
        int i = 0;
        for (int[] row : graph.weight) {

            System.out.println(graph.data[i++] + " " + Arrays.toString(row));
        }
    }

    public void getMST(int startVer) {
        boolean[] visited = new boolean[graph.vertexes];
        visited[startVer] = true;
        prime(visited);
    }

    private void prime(boolean[] visited) {
        Edge shortestEdge = Edge.NOT_EXIST_EDGE;

        for (int i = 0; i < visited.length; i++) {
            // 每一个已访问的顶点
            if (visited[i]) {
                for (int j = 0; j < graph.vertexes; j++) {
                    // 每一个未访问的顶点
                    if (!visited[j]) {
                        // 如果联通
                        int weight = graph.weight[i][j];
                        if (weight != 0) {
                            // 考察是否替换最短边
                            shortestEdge = shortestEdge.weight < weight ? shortestEdge : new Edge(i, j, weight);
                        }
                    }
                }
            }
        }

        if (shortestEdge == Edge.NOT_EXIST_EDGE) {
            // 联通部分的生成已经结束
            return;
        }

        System.out.println("加入边 " + graph.data[shortestEdge.ver1] + "-" + graph.data[shortestEdge.ver2] + " : "
                + shortestEdge.weight);
        visited[shortestEdge.ver2] = true;

        prime(visited);
    }

    static class Edge implements Comparable<Edge> {

        static Edge NOT_EXIST_EDGE = new Edge(0, 0, Integer.MAX_VALUE);

        int ver1;
        int ver2;
        int weight;

        Edge(int ver1, int ver2, int weight) {
            this.ver1 = ver1;
            this.ver2 = ver2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}

class MGraph {
    // 节点个数
    int vertexes;
    // 节点名
    char[] data;
    // 存放边，邻接矩阵
    int[][] weight;

    public MGraph(int vertexes) {
        this.vertexes = vertexes;
        data = new char[vertexes];
        weight = new int[vertexes][vertexes];
        for (int i = 0; i < vertexes; i++) {
            data[i] = (char) ('A' + i);
        }
    }
}
