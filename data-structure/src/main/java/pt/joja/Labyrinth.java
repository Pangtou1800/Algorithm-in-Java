package pt.joja;

public class Labyrinth {

    public static int[][] map;

    public static JojoStack<int[]> path = new JojoStack<>();

    static {
        // 初始化迷宫
        map = new int[][] { //
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, //
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, //
                { 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 }, //
                { 1, 0, 0, 1, 0, 0, 1, 1, 0, 1 }, //
                { 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 }, //
                { 1, 0, 1, 1, 0, 0, 1, 1, 0, 1 }, //
                { 1, 0, 0, 0, 1, 0, 0, 1, 0, 1 }, //
                { 1, 1, 1, 0, 1, 1, 0, 1, 1, 1 }, //
                { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, //
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, };
    }

    public static void main(String[] args) {
        // showMap(1,1);
        findPath(1, 1, 0);
        while (!path.isEmpty()) {
            int[] pos = path.pop();
            showMap(pos[0], pos[1]);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // start : 1,1
    // end : 9,9
    // from : 0 - up, 1 - left, 2 - down, 3- right
    public static boolean findPath(int i, int j, int from) {
        boolean result = false;
        if (i == 8 && j == 8) {
            result = true;
        } else if (map[i][j] == 1) {
            result = false;
        } else {
            // showMap(i, j);
            switch (from) {
                case 0:
                    result = findPath(i, j - 1, 3) || findPath(i + 1, j, 0) || findPath(i, j + 1, 1);
                    break;
                case 1:
                    result = findPath(i + 1, j, 0) || findPath(i, j + 1, 1) || findPath(i - 1, j, 2);
                    break;
                case 2:
                    result = findPath(i, j + 1, 1) || findPath(i - 1, j, 2) || findPath(i, j - 1, 3);
                    break;
                default:
                    result = findPath(i - 1, j, 2) || findPath(i, j - 1, 3) || findPath(i + 1, j, 0);
                    break;

            }
        }

        if (result) {
            path.push(new int[] { i, j });
        }
        return result;
    }

    public static void showMap(int x, int y) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == x && j == y) {
                    System.out.print("〇");
                } else {
                    if (map[i][j] == 1) {
                        System.out.print("[]");
                    } else {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
    }

    public static void showMap() {
        for (int[] row : map) {
            for (int pos : row) {
                if (pos == 1) {
                    System.out.print("[]");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

}
