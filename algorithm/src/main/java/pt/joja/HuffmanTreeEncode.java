package pt.joja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanTreeEncode {

    public static void main(String[] args) {
        String target = "i like like like java do you like a java";
        loadTree(target);
        System.out.println(huffmanTree.codeMap);

        String encoded = encode(target);
        System.out.println("[" + encoded.length() + "]" + encoded);

        // Use Integer.parseInt(encoded[i,i+8],2) to change 8 chars to one byte
        // And Integer.toBinaryString to get it back

        String decoded = decode(encoded);
        System.out.println("[" + decoded + "]");
    }

    private static HuffmanTree huffmanTree;

    public static void loadTree(String target) {
        Map<Character, Integer> chars = new HashMap<>();
        for (char chr : target.toCharArray()) {

            Integer weight = chars.get(chr);
            if (weight == null) {
                chars.put(chr, 1);
                continue;
            }
            chars.put(chr, weight + 1);
        }
        // Debug
        System.out.println(chars);
        huffmanTree = new HuffmanTree(chars);
    }

    public static String encode(String target) {
        if (huffmanTree == null) {
            throw new RuntimeException("HuffmanTree not loaded.");
        }
        StringBuffer result = new StringBuffer();
        for (char chr : target.toCharArray()) {
            result.append(huffmanTree.codeMap.get(chr));
        }
        return result.toString();
    }

    public static String decode(String encoded) {
        StringBuffer result = new StringBuffer();
        HuffmanTree.TreeNode currNode = huffmanTree.root;
        for (char chr : encoded.toCharArray()) {
            if (chr == '0') {
                currNode = currNode.left;
            } else if (chr == '1') {
                currNode = currNode.right;
            }
            if (currNode.left == null && currNode.right == null) {
                result.append(currNode.chr);
                currNode = huffmanTree.root;
            }
        }
        return result.toString();
    }

    private static class HuffmanTree {

        TreeNode root;

        Map<Character, String> codeMap = new HashMap<>();

        public HuffmanTree(Map<Character, Integer> chars) {
            List<TreeNode> nodes = new ArrayList<>();
            for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
                nodes.add(new TreeNode(entry.getValue(), entry.getKey()));
            }
            while (nodes.size() > 1) {
                Collections.sort(nodes);
                TreeNode left = nodes.remove(0);
                TreeNode right = nodes.remove(0);
                TreeNode tempRoot = new TreeNode(left.weight + right.weight, left, right);
                nodes.add(tempRoot);
            }
            root = nodes.remove(0);
            loadCodeMap(root, "");
        }

        private void loadCodeMap(TreeNode node, String code) {
            if (node.left == null && node.right == null) {
                codeMap.put(node.chr, code);
                return;
            }
            loadCodeMap(node.left, code + "0");
            loadCodeMap(node.right, code + "1");
        }

        private static class TreeNode implements Comparable<TreeNode> {
            int weight;
            char chr;
            TreeNode left;
            TreeNode right;

            public TreeNode(int weight, char chr) {
                this.weight = weight;
                this.chr = chr;
            }

            public TreeNode(int weight, TreeNode left, TreeNode right) {
                this(weight, Character.MIN_VALUE, left, right);
            }

            public TreeNode(int weight, char chr, TreeNode left, TreeNode right) {
                this.weight = weight;
                this.chr = chr;
                this.left = left;
                this.right = right;
            }

            @Override
            public int compareTo(TreeNode o) {
                return this.weight - o.weight;
            }
        }

    }

}
