import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    static boolean isSystemRunning = true;

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        LibraryUser libraryUser = new LibraryUser();
        Scanner scanner = new Scanner(System.in);

        books.add(new Book("Lord of the Rings", "John Tolkien", 1925, 2));
        books.add(new Book("Harry Potter", "J.K Rowling", 1980, 0));

        while (isSystemRunning) {
            MainMenu(libraryUser, books, scanner);

            if (!isSystemRunning) {
                scanner.close();
                break;
            }
        }

    }

    static void MainMenu(LibraryUser libraryUser, ArrayList<Book> books, Scanner scanner) {

        System.out.println(
                "0: Borrow Book\n1: Return Book\n2: List All Books\n3: Search Books\n4: Suggest a Book\n5: Exit System");
        String input = scanner.nextLine();

        switch (input) {
            case "0":
                BorrowBook(libraryUser, books, scanner);
                break;
            case "1":
                break;
            case "2":
                ShowAllBooks(books);
                break;
            case "5":
                isSystemRunning = false;
                break;
            default:
                System.out.println("Invalid input");
                break;
        }

    }

    static void BorrowBook(LibraryUser libraryUser, ArrayList<Book> books, Scanner scanner) {

        // Ask user for the index of the book
        System.out.println("Would you like to borrow any of the books below?");
        ShowAllBooks(books);
        int index = scanner.nextInt();

        // If book has an invalid index
        if (index >= books.size()) {
            System.out.println("Invalid book index, try again");
            return;
        } else {
            Book book = books.get(index);

            // If book still has library copies, borrow the book
            if (book.libraryCopies > 0) {
                book.libraryCopies -= 1;
                libraryUser.books.add(book);
                System.out.println("You have successfully borrowed " + book.title);
            } else {
                System.out.println("There are no more copies left of " + book.title);
            }
        }
    }

    static void ShowAllBooks(ArrayList<Book> books) {
        for (Book book : books) {
            int bookIndex = books.indexOf(book);
            System.out.println(bookIndex + ": " + book.title);
        }

    }
}

class Book {
    String title;
    String author;
    int year;
    int libraryCopies;

    public Book(String title, String author, int year, int libraryCopies) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.libraryCopies = libraryCopies;
    }
}

class LibraryUser {
    String name;
    ArrayList<Book> books = new ArrayList<>();
}