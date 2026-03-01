
package finalproject_2320230176_librarysystem;



public class MyLinkedBag<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head;
    private int size;

    public int size() { return size; }

    public void add(T item) {
        Node<T> n = new Node<>(item);
        n.next = head;
        head = n;
        size++;
    }

    public interface Visitor<T> {
        void visit(T item);
    }

    public void forEach(Visitor<T> v) {
        Node<T> cur = head;
        while (cur != null) {
            v.visit(cur.data);
            cur = cur.next;
        }
    }

    public Object[] toArray() {
        Object[] out = new Object[size];
        Node<T> cur = head;
        int i = 0;
        while (cur != null) {
            out[i++] = cur.data;
            cur = cur.next;
        }
        return out;
    }
}

