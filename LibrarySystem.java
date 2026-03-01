
package finalproject_2320230176_librarysystem;


public class LibrarySystem {

    public static MyLinkedBag<Book> books;
    public static MyArrayBag<User> users;
    public static MyQueue<BorrowRequest> borrowRequests;

    static {
        books = new MyLinkedBag<>();
        users = new MyArrayBag<>();
        borrowRequests = new MyQueue<>();

        // كتب مبدئية
        books.add(new Book("001", "Calculus", true));
        books.add(new Book("002", "Data Structure", true));
        books.add(new Book("003", "Database", false));


        // مستخدمين مبدئيين 
        users.add(new User("010", "Sama", "123", Constants.STUDENT, true, 80));
        users.add(new User("011", "Shahed", "123", Constants.STUDENT, true, 89));
        users.add(new User("020", "Ahmad", "123", Constants.EMPLOYEE, true, 0));
        users.add(new User("030", "Shatha", "123", Constants.LIBRARIAN, true, 0));
    }

   
    public static User findUserById(String id) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u != null && u.getId().equals(id)) return u;
        }
        return null;
    }

    public static Book findBookByIsbn(String isbn) {
        final Book[] result = new Book[1];
        books.forEach(b -> {
            if (b.getIsbn().equals(isbn)) result[0] = b;
        });
        return result[0];
    }
}
