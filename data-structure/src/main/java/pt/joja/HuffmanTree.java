package pt.joja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    private TreeNode root;

    public static void main(String[] args) {

        int[] nums = { 13, 7, 8, 3, 29, 6, 1 };

        List<TreeNode> list = new ArrayList<>();
        for (int num : nums) {
            list.add(new TreeNode(num));
        }

        HuffmanTree huffmanTree = new HuffmanTree(list);
        System.out.println(huffmanTree);
    }

    public HuffmanTree(List<TreeNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            TreeNode left = nodes.remove(0);
            TreeNode right = nodes.remove(0);
            TreeNode tempRoot = new TreeNode(left.val + right.val, left, right);
            nodes.add(tempRoot);
        }
        root = nodes.remove(0);
    }

    @Override
    public String toString() {
        return preOrder(root);
    }

    public String preOrder(TreeNode node) {
        if (node == null) {
            return "";
        }
        if (node.left == null && node.right == null) {
            return "[" + node.val + "]";
        }
        return node.val + "," + preOrder(node.left) + "," + preOrder(node.right);
    }

    private static class TreeNode implements Comparable<TreeNode> {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(TreeNode o) {
            return this.val - o.val;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }

}
