
package finalproject_2320230176_librarysystem;
import java.util.Scanner;

public class Main {
  

    private static Scanner in = new Scanner(System.in);

    public static void start() {
        while (true) {
            User user = login();
            if (user == null) continue;

            if (user.getType() == Constants.LIBRARIAN) {
                librarianMenu(user);
            } else {
                userMenu(user);
            }
        }
    }

    // ================= LOGIN =================
    private static User login() {
        System.out.println("\nWelcome to Library System");
        System.out.print("Enter your ID: ");
        String id = in.nextLine().trim();

        User u = LibrarySystem.findUserById(id);
        if (u == null) {
            System.out.println("User not found.");
            return null;
        }

        if (!u.isActive()) {
            System.out.println("User is inactive.");
            return null;
        }

        System.out.print("Enter your password: ");
        String pass = in.nextLine().trim();

        if (!u.checkPassword(pass)) {
            System.out.println("Wrong password.");
            return null;
        }

        return u;
    }

    // ================= USER MENU =================
    private static void userMenu(User user) {
        while (true) {
            System.out.println("\nWelcome " + user.getName());
            System.out.println("1- Show available books");
            System.out.println("2- Borrow a book");
            System.out.println("3- Search for books");
            System.out.println("0- Logout");
            System.out.print("> ");

            String c = in.nextLine();

            switch (c) {
                case "1":
                    showBooks();
                    break;
                case "2":
                    borrowBook(user);
                    break;
                case "3":
                    searchBook(user);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ================= LIBRARIAN MENU =================
    private static void librarianMenu(User user) {
        while (true) {
            System.out.println("\nWelcome Librarian " + user.getName());
            System.out.println("1- Approve Borrow Requests");
            System.out.println("2- View All Books");
            System.out.println("3- Book History");
            System.out.println("4- User History");
            System.out.println("5- Manage Users");
            System.out.println("6- Explore Books");
            System.out.println("7- Add Book");
            System.out.println("0- Logout");
            System.out.print("> ");

            String c = in.nextLine();

            switch (c) {
                case "1":
                    approveRequests(in);
                    break;
                case "2":
                    viewAllBooks();
                    break;
                case "3":
                     bookHistory(in);
                     break;
                case "4":
                     userHistory(in);
                     break;
                case "5":
                     manageUsers(in);
                     break;
                case "6":
                     exploreBooks(in);
                     break;
                case "7":
                    addBook(in);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // =================  FUNCTIONS =================
    private static void showBooks() {
        LibrarySystem.books.forEach(b -> {
            System.out.println(b.toString());
            System.out.println("Borrowed by:");
            MyStack<User> h = b.getBorrowingHistory();
            if (h.size() == 0) System.out.println("- none");
            else h.printStackTopToBottom();
            System.out.println();
        });
    }

    private static void borrowBook(User user) {
        System.out.print("Enter ISBN: ");
        String isbn = in.nextLine();

        Book b = LibrarySystem.findBookByIsbn(isbn);
        if (b == null || !b.isAvailable()) {
            System.out.println("Book not available.");
            return;
        }

        if (user.getType() == Constants.STUDENT && user.needsApproval()) {
            LibrarySystem.borrowRequests.enqueue(new BorrowRequest(user, b));
            System.out.println("Request sent to librarian.");
            return;
        }

        b.borrowBy(user);
        user.getBorrowingHistory().push(b);
        System.out.println("Book borrowed successfully.");
    }

    private static void searchBook(User user) {
        System.out.print("Enter name or ISBN: ");
        String key = in.nextLine().toLowerCase();

        LibrarySystem.books.forEach(b -> {
            if (b.getName().toLowerCase().contains(key)
                || b.getIsbn().contains(key)) {
                System.out.println(b);
            }
        });
    }
    private static void approveRequests(Scanner in){
        System.out.println("Borrowing Request: ");
        if(LibrarySystem.borrowRequests.isEmpty()){
            System.out.println("There is no more borrowing requests.\n");
            return;
        }
        BorrowRequest r = LibrarySystem.borrowRequests.dequeue();
        System.out.println("1- " + r.toString());
        System.out.println("Approve it?(1:yes , 0:no) ");
        String ans = in.nextLine().trim();
        if (!ans.equals("1")){
            System.out.println("Request rejected.\n");
            return;
        }
        Book b = r.getBook();
        User u = r.getUser();
        if(!b.isAvailable()){
            System.out.println("Cannot approve: book is not available anymore.\n");
            return;
        }
        b.borrowBy(u);
        u.getBorrowingHistory().push(b);
        System.out.println("The book has been registered as borrowed.\n");
    }
    private static void viewAllBooks(){
        System.out.println();
        LibrarySystem.books.forEach(b -> {
            System.out.println(b.toString());
            System.out.println("Borrowed by:");
            MyStack<User> hist = b.getBorrowingHistory();
            if(hist.size()==0) System.out.println("- (none)");
            else hist.printStackTopToBottom();
            System.out.println();
        });
    }
    private static void bookHistory(Scanner in){
        System.out.println("Books: ");
        LibrarySystem.books.forEach(b -> System.out.println(b.getIsbn() + ", "+b.getName()) );
        System.out.println("Enter book ISBN: ");
        String isbn = in.nextLine().trim();
        
        Book b = LibrarySystem.findBookByIsbn(isbn);
        if(b == null){
            System.out.println("Book not found.\n");
            return;
        }
        System.out.println("This Book has been borrowed by: ");
        if(b.getBorrowingHistory().size() ==0 ) System.out.println(" -(none) \n");
        else {
            b.getBorrowingHistory().printStackTopToBottom();
            System.out.println();
        }
    }
    private static void userHistory(Scanner in){
        for(int i = 0; i<LibrarySystem.users.size(); i++){
            User u = LibrarySystem.users.get(i);
            if(u != null) System.out.println(u.toString());
        }
        System.out.println("Which user's history you want to cheack? Enter ID: ");
        String id = in.nextLine().trim();
        
        User u = LibrarySystem.findUserById(id);
        if(u == null){
            System.out.println("User not Found.\n");
            return;
        }
        System.out.println(u.getName() + " borrowed: ");
        if(u.getBorrowingHistory().size() == 0){
            System.out.println("- (none)\n");
        } else{
            u.getBorrowingHistory().printStackTopToBottom();
            System.out.println();
        }
    }
    private static void manageUsers(Scanner in){
        for (int i = 0; i < LibrarySystem.users.size(); i++) {
            User u = LibrarySystem.users.get(i);
            if( u != null){
                System.out.println(u.toString());
            }
        }
        System.out.println("Which User's status you want to toggle? Enter ID: ");
        String id = in.nextLine().trim();
        User u = LibrarySystem.findUserById(id);
        if (u == null){
            System.out.println("User not found.\n");
            return;
        }
        u.toggleActive();
        System.out.println(u.getName()+ " is " + (u.isActive() ? "active " : "inactive ") + " now.\n");   
    }
    private static void exploreBooks(Scanner in){
        Object[] arr = LibrarySystem.books.toArray();
        if(arr.length == 0){
            System.out.println("No Books.\n");
            return;
        }
        int idx = 0;
        while(true){
            Book b = (Book) arr[idx];
            System.out.println("Current book is:");
            System.out.println(b.getIsbn() + " , " + b.getName());
            System.out.println("Press n for next , p for previous, f for first , l for last , q to quite.");
            String cmd = in.nextLine().trim().toLowerCase();
            
            if(cmd.equals("q")) {
                System.out.println();
                return;
            } else if (cmd.equals("n")){
                if(idx < arr.length-1) idx++;
            } else if (cmd.equals("p")){
                if(idx> 0) idx--;
            } else if (cmd.equals("f")){
                idx =0;
            } else if (cmd.equals("l")){
                idx = arr.length -1;
            } else {
                System.out.println("Invalid.\n");
            }
        }
    }
    private static void addBook(Scanner in){
        System.out.println("Book ISBN is: ");
        String isbn = in.nextLine().trim();
        
        if(LibrarySystem.findBookByIsbn(isbn) != null){
            System.out.println("This ISBN already exists.\n");
            return;
        }
        System.out.println("Book Name: ");
        String name = in.nextLine().trim();
        
        Book b = new Book(isbn , name , true);
        LibrarySystem.books.add(b);
        System.out.println(name + " book is added now.\n");
    }
    public static void main(String[] args) {
        Main.start();
    }
  
}
        
