package pt.joja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HorseTravel {

    public static void main(String[] args) {
        Board board = new Board(6);
        Pos startPos = new Pos(0, 0);
        board.visit(startPos, 1);

        // travel(board, startPos, 2);

        // travelAll(board, startPos, 2);

        travelSols(board, startPos, 2);
        System.out.println("Solutions: " + sols);
    }

    // 解法太多
    public static void travelAll(Board board, Pos pos, int step) {
        if (board.visited == board.maxSize) {
            board.print();
            return;
        }

        List<Pos> possiblePoses = board.possiblePoses(pos);
        if (possiblePoses.size() == 0) {
            return;
        }

        for (Pos nextPos : possiblePoses) {
            board.visit(nextPos, step);
            travelAll(board, nextPos, step + 1);
            board.regret(nextPos);
        }
    }

    static int sols = 0;
    static int ctr = 1;
    static HashMap<String, Integer> solvedSols = new HashMap<>();

    public static void travelSols(Board board, Pos pos, int step) {

        // String key = board.leftNow(pos);
        // if (solvedSols.containsKey(key)) {
        //     sols += solvedSols.get(key);
        //     if (sols > ctr) {
        //         System.out.println(new java.util.Date() + ": Over " + ctr + " sols found...");
        //         ctr += ctr;
        //     }
        //     return;
        // }

        // int before = sols;

        if (board.visited == board.maxSize) {
            sols++;
            if (sols > ctr) {
                System.out.println(new java.util.Date() + ": Over " + ctr + " sols found...");
                ctr += ctr;
            }
        } else {
            List<Pos> possiblePoses = board.possiblePoses(pos);
            if (possiblePoses.size() == 0) {

            } else {

                for (Pos nextPos : possiblePoses) {
                    board.visit(nextPos, step);
                    travelSols(board, nextPos, step + 1);
                    board.regret(nextPos);
                }
            }
        }

        // if (sols > before) {
        //     solvedSols.put(key, sols - before);
        // }
    }

    // 解法太多,第一个解时停止遍历
    public static boolean travel(Board board, Pos pos, int step) {
        if (board.visited == board.maxSize) {
            board.print();
            return true;
        }

        List<Pos> possiblePoses = board.possiblePoses(pos);
        if (possiblePoses.size() == 0) {
            return false;
        }

        boolean result = false;
        for (Pos nextPos : possiblePoses) {
            board.visit(nextPos, step);
            result = travel(board, nextPos, step + 1);
            if (result) {
                break;
            }
            board.regret(nextPos);

        }
        return result;
    }

    private static class Board {
        int[][] board;

        int maxSize = 0;
        int visited = 0;

        public Board(int size) {
            this.board = new int[size][size];
            this.maxSize = size * size;
        }

        private boolean visited(Pos pos) {
            return board[pos.row][pos.col] != 0;
        }

        public void visit(Pos pos, int step) {
            visited++;
            board[pos.row][pos.col] = step;
        }

        public void regret(Pos pos) {
            visited--;
            board[pos.row][pos.col] = 0;
        }

        private boolean validPos(Pos pos) {
            if (!(pos.row > -1 && pos.row < board.length && pos.col > -1 && pos.col < board.length)) {
                return false;
            }

            if (visited(pos)) {
                return false;
            }
            return true;
        }

        public List<Pos> possiblePoses(Pos currPos) {
            List<Pos> result = new ArrayList<>();
            // - 7 - 0 -
            // 6 - - - 1
            // - - X - -
            // 5 - - - 2
            // - 4 - 3 -
            Pos pos0 = new Pos(currPos.row - 2, currPos.col + 1);
            if (validPos(pos0)) {
                result.add(pos0);
            }
            Pos pos1 = new Pos(currPos.row - 1, currPos.col + 2);
            if (validPos(pos1)) {
                result.add(pos1);
            }
            Pos pos2 = new Pos(currPos.row + 1, currPos.col + 2);
            if (validPos(pos2)) {
                result.add(pos2);
            }
            Pos pos3 = new Pos(currPos.row + 2, currPos.col + 1);
            if (validPos(pos3)) {
                result.add(pos3);
            }
            Pos pos4 = new Pos(currPos.row + 2, currPos.col - 1);
            if (validPos(pos4)) {
                result.add(pos4);
            }
            Pos pos5 = new Pos(currPos.row + 1, currPos.col - 2);
            if (validPos(pos5)) {
                result.add(pos5);
            }
            Pos pos6 = new Pos(currPos.row - 1, currPos.col - 2);
            if (validPos(pos6)) {
                result.add(pos6);
            }
            Pos pos7 = new Pos(currPos.row - 2, currPos.col - 1);
            if (validPos(pos7)) {
                result.add(pos7);
            }

            return result;
        }

        int printCtr = 1;

        public void print() {
            System.out.println("-------- Sol " + printCtr++ + "--------");
            for (int[] row : board) {
                int i = 0;
                while (true) {
                    int step = row[i++];
                    System.out.print(step == 0 ? "[  ]" : String.format("[%2d]", step));
                    if (i == row.length) {
                        break;
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
        }

        public String leftNow(Pos pos) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 0) {
                        sb.append("0");
                    } else if (pos.row == i && pos.col == j) {
                        sb.append("2");
                    } else {
                        sb.append("1");
                    }
                }
            }
            return sb.toString();
        }
    }

    private static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }
}
