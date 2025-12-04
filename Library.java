import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        LibraryUser libraryUser = new LibraryUser();

        books.add(new Book("Lord of the Rings", "John Tolkien", 1925, 2));
        books.add(new Book("Harry Potter", "J.K Rowling", 1980, 0));

        MainMenu(libraryUser, books);
        // ShowAllBooks(books);
        // int answer = scanner.nextInt();
        // BorrowBook(libraryUser, books, answer);

        // scanner.close();
    }

    static void MainMenu(LibraryUser libraryUser, ArrayList<Book> books) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("0: Borrow Book\n1: Return Book\n2: List All Books\n3: Search Books\n4: Suggest a Book");
        int input = scanner.nextInt();

        switch (input) {
            case 0:
                BorrowBook(libraryUser, books);
                break;
            case 1:
                break;
            case 2:
                ShowAllBooks(books);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }

        scanner.close();
    }

    static void BorrowBook(LibraryUser libraryUser, ArrayList<Book> books) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for the index of the book
        System.out.println("Would you like to borrow any of the books below?");
        ShowAllBooks(books);
        int index = scanner.nextInt();

        // If book has an invalid index
        if (index >= books.size()) {
            System.out.println("Invalid book index, try again");
            scanner.close();
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
        scanner.close();
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