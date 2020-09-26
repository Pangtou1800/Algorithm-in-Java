package pt.joja;

public class JojaHashMap<K, V> {

    private static final int SCALE = 1009;

    @SuppressWarnings("unchecked")
    private Node<K, V>[] values = (Node<K, V>[]) new Node[SCALE];

    public static void main(String[] args) {
        JojaHashMap<Integer, Employee> map = new JojaHashMap<>();
        map.put(1, new Employee(1, "Mary"));
        map.put(2, new Employee(2, "Lily"));
        map.put(1010, new Employee(1010, "Lisa"));

        System.out.println(map);

        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(1010));
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
    }

    public void put(K key, V value) {
        int idx = jojaHashCode(key);

        Node<K, V> node = values[idx];
        while (node != null) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            } else {
                node = node.next;
            }
        }
        Node<K, V> currNode = new Node<>();
        currNode.key = key;
        currNode.value = value;
        currNode.next = values[idx];
        values[idx] = currNode;
    }

    public V get(K key) {
        Node<K, V> node = values[jojaHashCode(key)];
        while (node != null) {
            if (key.equals(node.key)) {
                return node.value;
            } else {
                node = node.next;
            }
        }
        return null;
    }

    private int jojaHashCode(K key) {
        return key.hashCode() % SCALE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("JojaHashMap { ");
        for (Node<K, V> node : values) {
            while (node != null) {
                sb.append("{ ");
                sb.append(node.key);
                sb.append(": ");
                sb.append(node.value);
                sb.append(" }, ");
                node = node.next;
            }
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        sb = sb.deleteCharAt(sb.length() - 1);
        sb.append(" }");
        return sb.toString();
    }

}

class Employee {
    Integer id;
    String name;

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee : { id: " + id + ", name: " + name + " }";
    }
}
