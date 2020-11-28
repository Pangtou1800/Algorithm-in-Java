package pt.joja;

import java.util.LinkedList;

public class Floyd {

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge('A', 'B', 2);
        graph.addEdge('A', 'C', 3);
        graph.addEdge('C', 'D', 1);
        graph.addEdge('B', 'D', 8);

        Floyd floyd = new Floyd(graph, true);

        floyd.showPath('B', 'D');
        floyd.showPath('D', 'A');
        floyd.showPath('D', 'B');
    }

    private int[][] pathLenArray;
    private char[][] passVertexArray;

    private boolean debug = false;

    public Floyd(Graph graph) {
        this(graph, false);
    }

    public Floyd(Graph graph, boolean debug) {
        this.debug = debug;

        this.pathLenArray = copyIntArray(graph.lenArray);
        passVertexArray = initCharArray(graph.getVertexNum());

        for (char chr = 'A'; chr < 'A' + graph.getVertexNum(); chr++) {
            for (int i = 0; i < this.pathLenArray.length; i++) {
                for (int j = 0; j < this.pathLenArray[i].length; j++) {
                    int currLen = pathLenArray[i][j];
                    int lenItoA = pathLenArray[i][chr - 'A'];
                    int lenAtoJ = pathLenArray[chr - 'A'][j];

                    if (lenItoA == Integer.MAX_VALUE || lenAtoJ == Integer.MAX_VALUE) {
                        continue;
                    } else {
                        if (currLen > (lenItoA + lenAtoJ)) {
                            pathLenArray[i][j] = lenItoA + lenAtoJ;
                            passVertexArray[i][j] = passVertexArray[chr - 'A'][j];
                        }
                    }
                }
            }

            if (this.debug) {
                showPathLenArray();
                showPassVertexArray();
            }
        }
    }

    public void showPath(char start, char end) {
        LinkedList<Character> stack = new LinkedList<>();
        stack.push(end);

        char pass = start;
        while ((pass = passVertexArray[end - 'A'][pass - 'A']) != end) {
            stack.push(pass);
        }

        stack.push(start);

        System.out.print("Path from " + start + " to " + end + ": ");
        while (true) {
            System.out.print(stack.pop());
            if (stack.isEmpty()) {
                break;
            }
            System.out.print(" -> ");
        }
        System.out.println(", total length: " + pathLenArray[start - 'A'][end - 'A'] + ".");
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void showPathLenArray() {
        for (int[] row : pathLenArray) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public void showPassVertexArray() {
        for (char[] row : passVertexArray) {
            for (char i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static int[][] copyIntArray(int[][] intArray) {
        int[][] result = new int[intArray.length][intArray[0].length];
        for (int i = 0; i < intArray.length; i++) {
            System.arraycopy(intArray[i], 0, result[i], 0, intArray[i].length);
        }
        return result;
    }

    public static char[][] initCharArray(int len) {
        char[][] result = new char[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                result[i][j] = (char) ('A' + i);
            }
        }
        return result;
    }

    private static class Graph {
        int[][] lenArray;

        public Graph(int vertexes) {
            lenArray = new int[vertexes][vertexes];
            for (int i = 0; i < lenArray.length; i++) {
                for (int j = 0; j < lenArray.length; j++) {
                    if (i != j) {
                        lenArray[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        public int getVertexNum() {
            return lenArray.length;
        }

        public void addEdge(char start, char end, int len) {
            lenArray[start - 'A'][end - 'A'] = len;
            lenArray[end - 'A'][start - 'A'] = len;
        }

        public int getLen(char start, char end) {
            return lenArray[start - 'A'][end - 'A'];
        }
    }
}
