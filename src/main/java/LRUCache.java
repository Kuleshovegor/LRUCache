import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private Node<K, V> head;
    private Node<K, V> tail;
    private final Map<K, Node<K, V>> cacheMap = new HashMap<>();

    public LRUCache(int capacity) {
        assert (capacity > 0);
        this.capacity = capacity;
    }

    public V get(K key) {
        assert (key != null);
        Node<K, V> resultNode = cacheMap.get(key);

        if (resultNode == null) {
            return null;
        }

        moveToHead(resultNode);
        assert (head.key == key);

        return resultNode.value;
    }

    public void put(K key, V value) {
        assert (key != null);
        Node<K, V> oldNode = cacheMap.get(key);

        if (oldNode != null) {
            oldNode.value = value;
            moveToHead(oldNode);

            checkPutPost(key, value);

            return;
        }

        if (cacheMap.size() == capacity) {
            deleteLast();
        }

        Node<K, V> newNode = new Node<>(key, value);
        cacheMap.put(key, newNode);
        addHead(newNode);

        checkPutPost(key, value);
    }

    public int size() {
        return cacheMap.size();
    }

    private void checkPutPost(K key, V value) {
        assert (size() <= capacity);
        assert (head.key == key && head.value == value);
    }

    private void deleteLast() {
        if (tail == null) {
            return;
        }

        if (tail == head) {
            tail = null;
            head = null;

            return;
        }

        cacheMap.remove(tail.key);
        tail.prev.next = null;
        tail = tail.prev;
    }

    private void moveToHead(Node<K, V> node) {
        if (node == head) {
            return;
        }

        if (node == tail) {
            tail = tail.prev;
        } else {
            node.next.prev = node.prev;
        }

        node.prev.next = node.next;
        node.next = head;
        node.prev = null;
        head.prev = node;
        head = node;
    }

    private void addHead(Node<K, V> node) {
        if (head == null) {
            head = node;
            tail = node;

            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}