
package finalproject_2320230176_librarysystem;


public class User {
   
    private String id;
    private String name;
    private String password;
    private int type;
    private boolean isActive;
    private int grade;             // للطالب فقط
    private MyStack<Book> borrowingHistory;

    public User(String id, String name, String password, int type, boolean isActive, int grade) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.isActive = isActive;
        this.grade = grade;
        this.borrowingHistory = new MyStack<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getType() { return type; }
    public boolean isActive() { return isActive; }
    public int getGrade() { return grade; }

    public void toggleActive() { isActive = !isActive; }

    public boolean checkPassword(String p) {
        return password.equals(p);
    }

    public MyStack<Book> getBorrowingHistory() {
        return borrowingHistory;
    }

    public boolean needsApproval() {
        if (type == Constants.EMPLOYEE) return false;
        if (type == Constants.STUDENT) return grade < 85;
        return false;
    }

    @Override
    public String toString() {
        String t = (type == Constants.STUDENT) ? "Student" :
                   (type == Constants.EMPLOYEE) ? "Employee" : "Librarian";
        return id + ", " + name + ", " + t + ", " + (isActive ? "active" : "inactive");
    }

   
}

