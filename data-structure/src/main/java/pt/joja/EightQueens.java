package pt.joja;

public class EightQueens {

    int[][] chessBoard = new int[8][8];
    int ctr = 0;

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.placeQueen(0);
    }

    public void placeQueen(int n) {
        for (int i = 0; i < 8; i++) {
            chessBoard[n][i] = 1;
            if (valid(n, i)) {
                // showChessBoard();
                if (n == 7) {
                    System.out.format("---- Sol %2d ----%n", ++ctr);
                    showChessBoard();
                } else {
                    placeQueen(n + 1);
                }
            }
            chessBoard[n][i] = 0;
        }
    }

    void showChessBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard[i][j] == 0) {
                    System.out.print("＋");
                } else {
                    System.out.print("〇");
                }
            }
            System.out.println();
        }
    }

    boolean valid(int row, int col) {
        // check column
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 1) {
                return false;
            }
        }
        // check \
        int i = row - 1, j = col - 1;
        while (i >= 0 && j >= 0) {
            if (chessBoard[i--][j--] == 1) {
                return false;
            }
        }
        // check /
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j <= 7) {
            if (chessBoard[i--][j++] == 1) {
                return false;
            }
        }
        return true;
    }

}
