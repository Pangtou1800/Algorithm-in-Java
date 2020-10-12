package pt.joja;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanZipFile {

    public static void main(String[] args) {
        // try {
        // zipFile("C:/Users/Pangtou1404/Desktop/1.jpg",
        // "C:/Users/Pangtou1404/Desktop/1.zip");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        try {
            zipFile("C:/Users/Pangtou1404/Desktop/1.png", "C:/Users/Pangtou1404/Desktop/1.pngzip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile) throws IOException {
        // 创建输入流
        FileInputStream is = new FileInputStream(srcFile);
        // 创建一个和源文件大小一致的byte数组
        byte[] srcBytes = new byte[is.available()];
        is.read(srcBytes);
        is.close();

        loadTree(srcBytes);

        // 创建输出流
        FileOutputStream os = new FileOutputStream(dstFile);
        String somethingBig = encode(srcBytes);

        byte[] srcFromString = decodeString(somethingBig);
        FileOutputStream os2 = new FileOutputStream("C:/Users/Pangtou1404/Desktop/2.png");
        os2.write(srcFromString);
        os2.close();

        byte[] targetBytes = new byte[(somethingBig.length() + 7) / 8];
        System.out.println("[" + somethingBig.length() + "]" + somethingBig.substring(0, 100));
        System.out.println("targetBytes.length: " + targetBytes.length);
        int j = 0;
        int i = 0;
        for (; i < somethingBig.length() - 8; i += 8) {
            String binString = somethingBig.substring(i, i + 8);
            targetBytes[j++] = (byte) Integer.parseInt(binString, 2);
        }
        String binString = somethingBig.substring(i);
        targetBytes[j++] = (byte) Integer.parseInt(binString, 2);

        os.write(targetBytes);
        os.close();

        // 读入
        is = new FileInputStream(dstFile);
        // 创建一个和源文件大小一致的byte数组
        srcBytes = new byte[is.available()];
        is.read(srcBytes);
        is.close();

        byte[] decoded = decode(srcBytes);
        FileOutputStream os3 = new FileOutputStream("C:/Users/Pangtou1404/Desktop/3.png");
        os3.write(decoded);
        os3.close();

    }

    private static HuffmanTree huffmanTree;

    public static void loadTree(byte[] target) {
        Map<Byte, Integer> byts = new HashMap<>();
        for (byte byt : target) {

            Integer weight = byts.get(byt);
            if (weight == null) {
                byts.put(byt, 1);
                continue;
            }
            byts.put(byt, weight + 1);
        }
        // Debug
        System.out.println(byts);
        huffmanTree = new HuffmanTree(byts);
    }

    public static String encode(byte[] target) {
        if (huffmanTree == null) {
            throw new RuntimeException("HuffmanTree not loaded.");
        }
        StringBuffer result = new StringBuffer();
        for (byte byt : target) {
            result.append(huffmanTree.codeMap.get(byt));
        }
        return result.toString();
    }

    public static byte[] decode(byte[] encoded) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HuffmanTree.TreeNode currNode = huffmanTree.root;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < encoded.length - 1; i++) {
            String code = Integer.toBinaryString(encoded[i] | 256);
            code = code.substring(code.length() - 8);
            sb.append(code);
        }
        sb.append(Integer.toBinaryString(encoded[encoded.length - 1]));
        String somethingBig = sb.toString();
        System.out.println("[" + somethingBig.length() + "]" + somethingBig.substring(0, 100));

        for (char chr : somethingBig.toCharArray()) {
            if (chr == '0') {
                currNode = currNode.left;
            } else if (chr == '1') {
                currNode = currNode.right;
            }
            if (currNode.left == null && currNode.right == null) {
                outputStream.write(currNode.byt);
                currNode = huffmanTree.root;
            }
        }
        return outputStream.toByteArray();
    }

    public static byte[] decodeString(String encoded) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HuffmanTree.TreeNode currNode = huffmanTree.root;
        for (char chr : encoded.toCharArray()) {
            if (chr == '0') {
                currNode = currNode.left;
            } else if (chr == '1') {
                currNode = currNode.right;
            }
            if (currNode.left == null && currNode.right == null) {
                outputStream.write(currNode.byt);
                currNode = huffmanTree.root;
            }
        }
        return outputStream.toByteArray();
    }

    private static class HuffmanTree {

        TreeNode root;

        Map<Byte, String> codeMap = new HashMap<>();

        public HuffmanTree(Map<Byte, Integer> byts) {
            List<TreeNode> nodes = new ArrayList<>();
            for (Map.Entry<Byte, Integer> entry : byts.entrySet()) {
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
                codeMap.put(node.byt, code);
                return;
            }
            loadCodeMap(node.left, code + "0");
            loadCodeMap(node.right, code + "1");
        }

        private static class TreeNode implements Comparable<TreeNode> {
            int weight;
            byte byt;
            TreeNode left;
            TreeNode right;

            public TreeNode(int weight, byte byt) {
                this.weight = weight;
                this.byt = byt;
            }

            public TreeNode(int weight, TreeNode left, TreeNode right) {
                this(weight, Byte.MIN_VALUE, left, right);
            }

            public TreeNode(int weight, byte byt, TreeNode left, TreeNode right) {
                this.weight = weight;
                this.byt = byt;
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
