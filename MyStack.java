
package finalproject_2320230176_librarysystem;

public class MyStack <T>{

   class Node<T> {

    T data;
    Node<T> next;
    
    public Node (T data){
        this.data = data;
        this.next = null;
    }
}

    private Node<T> top;
    private int size;
    public MyStack(){
       this.top=null; 
       this.size=0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void push(T item) {
        Node<T> n = new Node<>(item);
        n.next = top;
        top = n;
        size++;
    }

    public T peek() {
        if (isEmpty()) return null;
        return top.data;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is Empty!!");
            return null;
        }
        T val = top.data;
        top = top.next;
        size--;
        return val;
    }
    
    
    // مفيد للعرض بدون تخريب الستاك 
    public void printStackTopToBottom() {
        MyStack<T> temp = new MyStack<>();
        while (!isEmpty()) {                     //بخرب الستاك مؤقتا لطباعة العناصر
            T x = pop();
            System.out.println(x);
            temp.push(x);
        }
        while (!temp.isEmpty()) push(temp.pop());            // برجع كل اشي زي ما كان
    }
}

