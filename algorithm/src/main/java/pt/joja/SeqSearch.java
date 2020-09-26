package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class SeqSearch {

    public static void main(String[] args) {

        int nums = 100;

        Random rand = new Random(47);
        int[] eles = new int[nums];

        for (int i = 0; i < nums; i++) {
            eles[i] = rand.nextInt(1000);
        }

        int targetNums = 20;
        int[] targets = new int[targetNums];
        for (int i = 0; i < targetNums; i++) {
            targets[i] = rand.nextInt(1000);
        }

        System.out.println(Arrays.toString(eles));
        StringBuilder result = new StringBuilder();
        for (int target : targets) {
            result.append("[" + target + ":" + search(eles, target) + "],");
        }
        result = result.deleteCharAt(result.length() - 1);
        System.out.println(result.toString());
        System.out.println("[SeqSearch.search] compared: " + compCtr);

    }

    static int compCtr = 0;

    public static boolean search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            compCtr++;
            if (arr[i] == target) {
                return true;
            }
        }
        return false;
    }
}