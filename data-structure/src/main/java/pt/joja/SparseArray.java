package pt.joja;

public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始二维数组 11*11
        // 0: 没有棋子，1：黑子，2：蓝子
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;

        // 输出原始二维数组：
        System.out.println("原始二维数组：");
        for (int[] row : chessArr) {
            for (int cell : row) {
                System.out.printf("%2d ", cell);
            }
            System.out.println();
        }

        // 将二维数组转换为稀疏数组
        // 1.遍历二维数组，得到非0值
        int cardinality = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    cardinality++;
                }
            }
        }
        System.out.println("数组中非0值个数：" + cardinality);

        // 2.创建对应的稀疏数组
        int[][] sparseArray = new int[cardinality + 1][3];
        // 3.给稀疏数组赋值
        sparseArray[0][0] = chessArr.length;
        sparseArray[0][1] = chessArr[0].length;
        sparseArray[0][2] = cardinality;

        // 遍历二维数组，将非0值存放到稀疏数组中
        int pos = 1;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sparseArray[pos][0] = i;
                    sparseArray[pos][1] = j;
                    sparseArray[pos][2] = chessArr[i][j];
                    pos++;
                }
            }
        }

        // 输出转化后的二维数组
        System.out.println("转化后的稀疏数组：");
        for (int[] row : sparseArray) {
            for (int val : row) {
                System.out.printf("%2d ", val);
            }
            System.out.println();
        }

        // 将稀疏数组恢复成原始的二维数组
        int[][] chessArr2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        int cardinality2 = sparseArray[0][2];
        for (int i = 1; i <= cardinality2; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        // 输出恢复后的二维数组
        System.out.println("恢复后的二维数组：");
        for (int[] row : chessArr2) {
            for (int cell : row) {
                System.out.printf("%2d ", cell);
            }
            System.out.println();
        }
    }
}
