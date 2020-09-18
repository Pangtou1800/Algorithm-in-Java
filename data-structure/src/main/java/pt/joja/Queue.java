package pt.joja;

import java.util.Scanner;

public class Queue {
    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(3);

        char key = ' ';
        Scanner scanner = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
            System.out.println("--------------- Queue Test ---------------");
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add) ：添加数据到队列");
            System.out.println("g(get) : 从队列取出数据");
            System.out.println("h(head): 查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    System.out.println(arrayQueue.toString());
                    break;
                case 'a':
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int val = arrayQueue.getQueue();
                        System.out.printf("取出的数据是：%2d\n", val);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int val = arrayQueue.headQueue();
                        System.out.printf("头数据是：%2d\n", val);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }
}

class ArrayQueue {

    /**
     * 数组的最大容量
     */
    private int maxSize;
    /**
     * 队头指针
     */
    private int front;
    /**
     * 队尾指针
     */
    private int rear;
    /**
     * 内部数组
     */
    private int[] arr;

    /**
     * 创建队列
     * 
     * @param maxSize
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部，不包含
        rear = -1; // 指向队列尾部，包含
    }

    /**
     * 判断队列是否满
     * 
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     * 
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 入队
     * 
     * @param n
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据：" + n);
            return;
        }
        rear++;
        arr[rear] = n;
    }

    /**
     * 出队
     * 
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("队列为空");
        }
        front++;
        return arr[front];
    }

    /**
     * 查看头数据
     * 
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("队列为空");
        }
        return arr[front + 1];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        int i = front;
        for (; i < rear; i++) {
            result.append(arr[i + 1]);
            result.append(", ");
        }
        if (i > front) {
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
        }
        result.append("]");
        return result.toString();
    }
}

class CircleArrayQueue {

    /**
     * 数组的最大容量
     */
    private int maxSize;
    /**
     * 队头指针
     */
    private int front;
    /**
     * 队尾指针
     */
    private int rear;
    /**
     * 内部数组
     */
    private int[] arr;

    /**
     * 创建队列
     * 
     * @param maxSize
     */
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部，不包含
        rear = -1; // 指向队列尾部，包含
    }

    /**
     * 判断队列是否满
     * 
     * @return
     */
    public boolean isFull() {
        return rear == front + maxSize;
    }

    /**
     * 判断队列是否为空
     * 
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 入队
     * 
     * @param n
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据：" + n);
            return;
        }
        rear++;
        arr[rear % maxSize] = n;
    }

    /**
     * 出队
     * 
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("队列为空");
        }
        front++;
        return arr[front % maxSize];
    }

    /**
     * 查看头数据
     * 
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("队列为空");
        }
        return arr[(front + 1) % maxSize];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        int i = front;
        for (; i < rear; i++) {
            result.append(arr[(i + 1) % maxSize]);
            result.append(", ");
        }
        if (i > front) {
            result.deleteCharAt(result.length() - 1);
            result.deleteCharAt(result.length() - 1);
        }
        result.append("]");
        return result.toString();
    }
}
