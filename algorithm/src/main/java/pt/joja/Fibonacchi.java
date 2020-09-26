package pt.joja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Fibonacchi {

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

        eles = toFibonacchi(eles);
        System.out.println(Arrays.toString(eles));

        int currFib = fibNums.size() - 1;
        StringBuilder result = new StringBuilder();
        for (int target : targets) {
            result.append("[" + target + ":" + search(eles, target, currFib) + "],");
        }
        result = result.deleteCharAt(result.length() - 1);
        System.out.println(result.toString());
        System.out.println("[Fibnoacchi.search] compared: " + compCtr);
    }

    static int compCtr = 0;

    public static List<Integer> fibNums = new ArrayList<>();

    public static int[] toFibonacchi(int[] nums) {
        fibNums.add(1);
        fibNums.add(1);
        int currFib = 1;
        int currFibLen = fibNums.get(currFib);
        while (currFibLen < nums.length) {
            currFib++;
            if (currFib >= fibNums.size()) {
                fibNums.add(fibNums.get(currFib - 1) + fibNums.get(currFib - 2));
            }
            currFibLen = fibNums.get(currFib);
        }

        int[] result = new int[currFibLen];
        System.arraycopy(nums, 0, result, 0, nums.length);
        for (int i = nums.length; i < currFibLen; i++) {
            result[i] = result[i - 1];
        }
        return result;
    }

    public static boolean search(int[] nums, int target, int currFib) {
        int low = 0;
        int high = nums.length - 1;
        int tempFib = currFib;
        while (low <= high && tempFib > 0) {
            compCtr++;
            int mid = low + fibNums.get(tempFib - 1) - 1;
            System.out.printf("low: %d, mid: %d, high: %d\n", low, mid, high);
            if (nums[mid] < target) {
                low = mid + 1;
                tempFib -= 2;
            } else if (nums[mid] > target) {
                high = mid - 1;
                tempFib -= 1;
            } else {
                return true;
            }
        }
        return false;
    }

}
