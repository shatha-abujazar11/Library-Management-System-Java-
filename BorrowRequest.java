
package finalproject_2320230176_librarysystem;


public class BorrowRequest {
    private User user;
    private Book book;

    public BorrowRequest(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public User getUser() { return user; }
    public Book getBook() { return book; }

    @Override
    public String toString() {
        return user.getName() + " wants to borrow (" + book.getIsbn() + ", " + book.getName() + ")";
    }
}
