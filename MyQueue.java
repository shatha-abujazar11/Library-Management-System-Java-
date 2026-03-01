
package finalproject_2320230176_librarysystem;


public class MyQueue<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> front, rear;
    private int size;

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) {
        Node<T> n = new Node<>(item);
        if (isEmpty()) {
            front = rear = n;
        } else {
            rear.next = n;
            rear = n;
        }
        size++;
    }

    public T peek() {
        if (isEmpty()) return null;
        return front.data;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        T val = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return val;
    }
}
    
