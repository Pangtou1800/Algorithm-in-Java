package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {

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
        Shell.sort(eles);
        System.out.println(Arrays.toString(eles));

        StringBuilder result = new StringBuilder();
        for (int target : targets) {
            result.append("[" + target + ":" + search(eles, target) + "],");
        }
        result = result.deleteCharAt(result.length() - 1);
        System.out.println(result.toString());
        System.out.println("[Binary.search] compared: " + compCtr);
    }

    static int compCtr = 0;

    public static boolean search(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            compCtr++;
            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

}
