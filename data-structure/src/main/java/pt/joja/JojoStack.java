package pt.joja;

import java.util.EmptyStackException;
import java.util.Scanner;

public class JojoStack<T> {

    public static void main(String[] args) {
        JojoStack<Integer> jojaStack = new JojoStack<>();
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
                    } catch (EmptyStackException e) {
                        System.out.println("栈空，出栈失败");
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

    private class Node {
        T val;
        Node next;

        public Node(T val) {
            this.val = val;
        }
    }

    private Node head;

    public void push(T val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T val = head.val;
        head = head.next;
        return val;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return head.val;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        Node temp = head;
        StringBuilder sb = new StringBuilder("[ ");
        while(true) {
            sb.append(temp.val);
            if (temp.next == null) {
                return sb.append(" ]").toString();
            }
            sb.append(", ");
            temp = temp.next;
        }
    }

}
