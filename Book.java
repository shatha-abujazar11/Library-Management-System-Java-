
package finalproject_2320230176_librarysystem;


public class Book {
    private String isbn;
    private String name;
    private boolean isAvailable;
    private MyStack<User> borrowingHistory;

    public Book(String isbn, String name, boolean isAvailable) {
        this.isbn = isbn;
        this.name = name;
        this.isAvailable = isAvailable;
        this.borrowingHistory = new MyStack<>();
    }

    public String getIsbn() { return isbn; }
    public String getName() { return name; }
    public boolean isAvailable() { return isAvailable; }

    public void borrowBy(User u) {
        isAvailable = false;
        borrowingHistory.push(u);
    }

    public void returnBook() {
        isAvailable = true;
    }

    public MyStack<User> getBorrowingHistory() {
        return borrowingHistory;
    }

    @Override
    public String toString() {
        return isbn + ", " + name + ", " + (isAvailable ? "available" : "not available");
    }
}
