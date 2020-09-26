package pt.joja;

import java.util.Arrays;
import java.util.Random;

public class DivisionSearch {

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
        System.out.println("[Division.search] compared: " + compCtr);
    }

    static int compCtr = 0;

    public static boolean search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            compCtr++;
            if (target < nums[low]) {
                break;
            }
            compCtr++;
            if (target > nums[high]) {
                break;
            }
            compCtr++;
            int mid = (high - low) * (target - nums[low]) / (nums[high] - nums[low]) + low;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
