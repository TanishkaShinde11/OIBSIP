import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int id;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Available: " + available;
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}

class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

class Transaction {
    private User user;
    private Book book;
    private String date;

    public Transaction(User user, Book book, String date) {
        this.user = user;
        this.book = book;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public String getDate() {
        return date;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Add some sample books to the library
        library.addBook(new Book(1, "The Catcher in the Rye", "J.D. Salinger"));
        library.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book(3, "1984", "George Orwell"));

        while (true) {
            System.out.println("\n1. Display Books");
            System.out.println("2. Checkout Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter the book ID to checkout: ");
                    int checkoutId = scanner.nextInt();
                    Book checkoutBook = library.findBook(checkoutId);

                    if (checkoutBook != null && checkoutBook.isAvailable()) {
                        checkoutBook.setAvailable(false);
                        System.out.println("Book checked out successfully!");
                    } else {
                        System.out.println("Book not available or does not exist.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the book ID to return: ");
                    int returnId = scanner.nextInt();
                    Book returnBook = library.findBook(returnId);

                    if (returnBook != null && !returnBook.isAvailable()) {
                        returnBook.setAvailable(true);
                        System.out.println("Book returned successfully!");
                    } else {
                        System.out.println("Invalid book ID or the book is already available.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
