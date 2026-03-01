
package finalproject_2320230176_librarysystem;


public class MyArrayBag<T> {
    private Object[] arr;
    private int size;

    public MyArrayBag() {
        arr = new Object[10];
        size = 0;
    }

    public int size() { return size; }

    public void add(T item) {
        ensureCapacity();
        arr[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return (T) arr[index];
    }

    public void set(int index, T item) {
        if (index < 0 || index >= size) return;
        arr[index] = item;
    }

    private void ensureCapacity() {
        if (size >= arr.length) {
            Object[] bigger = new Object[arr.length * 2];
            for (int i = 0; i < arr.length; i++) bigger[i] = arr[i];
            arr = bigger;
        }
    }
}
