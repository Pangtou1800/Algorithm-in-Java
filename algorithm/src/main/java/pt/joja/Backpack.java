package pt.joja;

import java.util.Arrays;

public class Backpack {

    public static void main1(String[] args) {
        int[][] things = { { 1, 10 }, { 2, 5 }, { 3, 15 }, { 4, 25 }, { 5, 40 }, { 6, 50 } };
        int[][] capValueTable = new int[7][11];
        int capacity = 10;

        for (int i = 0; i < things.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                int weight = things[i][0];
                int value = things[i][1];
                if (weight > j) {
                    capValueTable[i + 1][j] = capValueTable[i][j];
                } else {
                    int valueInBag = value + capValueTable[i][j - weight];
                    capValueTable[i + 1][j] = Math.max(capValueTable[i][j], valueInBag);
                }
            }
        }

        for (int[] row : capValueTable) {
            System.out.println(Arrays.toString(row));
        }

    }

    public static void main(String[] args) {
        backpack2(10, 6);

        for (int[] row : capValueTable) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static int[][] things2 = { { 1, 10 }, { 2, 5 }, { 3, 15 }, { 4, 25 }, { 5, 40 }, { 6, 50 } };
    private static int[][] capValueTable = new int[7][11];

    public static void backpack2(int capacity, int idx) {

        if (capacity == 0 || idx == 0) {
            return;
        }

        if (capValueTable[idx][capacity] != 0) {
            return;
        }

        int weight = things2[idx - 1][0];
        int value = things2[idx - 1][1];

        backpack2(capacity, idx - 1);
        int valueNotInBag = capValueTable[idx - 1][capacity];

        if (capacity >= weight) {
            backpack2(capacity - weight, idx - 1);
            int valueInBag = value + capValueTable[idx - 1][capacity - weight];

            capValueTable[idx][capacity] = Math.max(valueInBag, valueNotInBag);
            System.out.println("No." + (idx - 1) + " thing in the bag.");
        } else {
            capValueTable[idx][capacity] = valueNotInBag;
        }

    }
}
