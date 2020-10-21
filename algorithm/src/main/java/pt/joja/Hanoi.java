package pt.joja;

import java.util.LinkedList;

public class Hanoi {

    public static void main(String[] args) {
        // hanoi(5, 'A', 'B', 'C');

        int n = 10;

        for (int i = n; i > 0; i--) {
            A.push(i);
        }

        hanoi(n, A, C, B);
    }

    /**
     * 
     * @param num
     * @param a   from
     * @param b   temp
     * @param c   to
     */
    public static void hanoi(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.format("Plate%02d %s to %s\n", 1, a, c);
            return;
        }

        hanoi(num - 1, a, c, b);
        System.out.format("Plate%02d %s to %s\n", num, a, c);
        hanoi(num - 1, b, a, c);
    }

    private static int ctr = 1;
    private static LinkedList<Integer> A = new LinkedList<>();
    private static LinkedList<Integer> B = new LinkedList<>();
    private static LinkedList<Integer> C = new LinkedList<>();

    public static void hanoi(int num, LinkedList<Integer> from, LinkedList<Integer> to, LinkedList<Integer> temp) {
        if (num == 1) {
            to.push(from.pop());
            showStatus();
        } else {
            hanoi(num - 1, from, temp, to);
            to.push(from.pop());
            showStatus();
            hanoi(num - 1, temp, to, from);
        }
    }

    public static void showStatus() {
        int height = Math.max(A.size(), B.size());
        height = Math.max(height, C.size());
        System.out.println("-------- " + ctr++ + " --------");
        for (int i = 0; i < height; i++) {
            int idx = i - (height - A.size());
            if (idx >= 0) {
                System.out.format("  %02d  ", A.get(idx));
            } else {
                System.out.print("      ");
            }
            idx = i - (height - B.size());
            if (idx >= 0) {
                System.out.format("  %02d  ", B.get(idx));
            } else {
                System.out.print("      ");
            }
            idx = i - (height - C.size());
            if (idx >= 0) {
                System.out.format("  %02d  ", C.get(idx));
            } else {
                System.out.print("      ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
