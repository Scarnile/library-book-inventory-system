import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    static boolean isSystemRunning = true;

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        LibraryUser libraryUser = new LibraryUser();
        Scanner scanner = new Scanner(System.in);

        scanner.useDelimiter(System.lineSeparator());

        // Initialize Books
        books.add(new Book("Lord of the Rings", "John Tolkien", 1925, 1));
        books.add(new Book("Harry Potter", "J.K Rowling", 1980, 1));

        // System Loop
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
        String input = scanner.next();

        switch (input) {
            case "0":
                BorrowBook(libraryUser, books, scanner);
                break;
            case "1":
                ReturnBook(libraryUser, books, scanner);
                break;
            case "2":
                ShowAllBooks(books);
                break;
            case "5":
                isSystemRunning = false;
                break;
            default:
                System.out.println("Invalid input: " + input);
                break;
        }

    }

    static void BorrowBook(LibraryUser libraryUser, ArrayList<Book> libraryBooks, Scanner scanner) {

        // Ask user for the index of the book
        System.out.println("\nWould you like to borrow any of the books below?");
        ShowAllBooks(libraryBooks);
        System.out.print("\nInsert Book Index: ");
        int inputIndex = scanner.nextInt();

        // If book has an invalid index
        if (inputIndex >= libraryBooks.size()) {
            System.out.println("Invalid book index, try again");
            return;
        } else {
            Book selectedBook = libraryBooks.get(inputIndex);

            // If selectedBook still has library copies, borrow the selectedBook
            if (selectedBook.libraryCopies > 0) {
                selectedBook.libraryCopies -= 1;
                libraryUser.books.add(selectedBook);
                System.out.println("You have successfully borrowed " + selectedBook.title);
            } else {
                System.out.println("There are no more copies left of " + selectedBook.title);
            }
            System.out.println(""); // Print empty line
        }
    }

    static void ReturnBook(LibraryUser libraryUser, ArrayList<Book> libraryBooks, Scanner scanner) {
        // Display all the library user's books
        if (!libraryUser.books.isEmpty()) {
            System.out.println("\nWhich book do you want to return?: ");
            ShowAllBooks(libraryUser.books);

            int inputIndex = scanner.nextInt();

            // If book has an invalid index
            if (inputIndex >= libraryUser.books.size()) {
                System.out.println("Invalid book index, try again");
                return;
            } else {
                // Find LibraryUser's book in Library and add a copy
                Book selectedBook = libraryUser.books.get(inputIndex);

                for (Book libraryBook : libraryBooks) {
                    // Book to return matches the library data
                    if (selectedBook.title == libraryBook.title) {
                        libraryBook.libraryCopies++;
                        libraryUser.books.remove(selectedBook);
                        System.out.println("Successfully returned " + selectedBook.title + "\n");
                    }
                }
            }
        } else {
            System.out.println("Sorry, you haven't borrowed any books from the library yet\n");
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