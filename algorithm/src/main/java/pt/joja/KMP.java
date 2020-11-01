package pt.joja;

import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(Arrays.toString(partMatchTable(str2)));
        System.out.println(match(str1, str2));

    }

    public static int match(String text, String matcher) {
        int[] partMatchTable = partMatchTable(matcher);
        int i = 0, j = 0;
        while (i < text.length()) {
            // show matching part:
            System.out.println(text);
            for (int k = 0; k < i; k++) {
                System.out.print(' ');
            }
            System.out.println(matcher);

            while (matcher.charAt(j) == text.charAt(i + j)) {
                j++;
                if (j == matcher.length()) {
                    return i;
                }
            }

            if (j == 0) {
                i++;
            } else {
                i += j - partMatchTable[j - 1];
                j = partMatchTable[j - 1];
            }
        }
        return -1;
    }

    public static int[] partMatchTable(String matcher) {
        int[] result = new int[matcher.length()];
        for (int i = 1; i <= matcher.length(); i++) {
            result[i - 1] = biggestCommonPart(matcher.substring(0, i));
        }
        return result;
    }

    public static int biggestCommonPart(String word) {
        for (int i = word.length() - 1; i > 0; i--) {
            String preStr = word.substring(0, i);
            String lstStr = word.substring(word.length() - i);
            if (preStr.equals(lstStr)) {
                return i;
            }
        }
        return 0;
    }

}
