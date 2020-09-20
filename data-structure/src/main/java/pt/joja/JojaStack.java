package pt.joja;

import java.util.Scanner;

public class JojaStack {

    public static void main(String[] args) {
        JojaStack jojaStack = new JojaStack(4);
        String key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s - show 显示栈");
            System.out.println("e - exit 退出程序");
            System.out.println("p - push 入栈");
            System.out.println("q - pop 出栈");
            key = scanner.next();
            switch (key) {
                case "s":
                    System.out.println(jojaStack.toString());
                    break;
                case "p":
                    int value = scanner.nextInt();
                    jojaStack.push(value);
                    System.out.println(jojaStack);
                    break;
                case "q":
                    try {
                        System.out.println("出栈：" + jojaStack.pop() + "  " + jojaStack);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    // 底层数组
    private int[] arr;
    // 栈顶
    private int top = -1;

    public JojaStack(int maxSize) {
        arr = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

    public void push(int val) {
        if (isFull()) {
            System.out.println("栈满，入栈失败：" + val);
            return;
        }
        arr[++top] = val;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，出栈失败");
        }
        return arr[top--];
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        int curr = top;
        StringBuilder sb = new StringBuilder("[ ");
        while (true) {
            sb.append(arr[curr--]);
            if (curr == -1) {
                sb.append(" ]");
                return sb.toString();
            }
            sb.append(", ");
        }
    }
}