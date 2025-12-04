import java.text.ListFormat.Style;
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
        books.add(new Book("Meditations", "Marcus Aurelius", 1634, 2));
        books.add(new Book("Harry Potter", "J.K Rowling", 1997, 1));
        books.add(new Book("Steel Ball Run", "Hirohiko Araki", 2004, 1));

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
                "0: Borrow Book\n1: Return Book\n2: List All Books\n3: Find a Book\n4: Donate a Book\n5: Exit System");
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
            case "3":
                FindBook(books, scanner);
                break;
            case "4":
                DonateBook(books, scanner);
                break;
            case "5":
                isSystemRunning = false;
                break;
            default:
                System.out.println("Invalid input: " + input + "\n");
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

    static void DonateBook(ArrayList<Book> libraryBooks, Scanner scanner) {
        Book newBook = new Book(null, null, 0, 0);

        System.out.println("\nWhat is the title of the book?");
        newBook.title = (scanner.next());

        System.out.println("\nWho is the author of the book?");
        newBook.author = (scanner.next());

        System.out.println("\nWhat year was the book released?");
        newBook.year = (scanner.nextInt());

        System.out.println("\nHow many books will you donate to the library?");
        newBook.libraryCopies = (scanner.nextInt());

        libraryBooks.add(newBook);
    }

    static void FindBook(ArrayList<Book> libraryBooks, Scanner scanner) {

        boolean foundBook = false;
        System.out.print("\nType the title of a book you want to find: ");
        String titleSearch = scanner.next();

        for (Book book : libraryBooks) {

            if (titleSearch.equals(book.title)) {
                System.out.println("\n" + book.title.toUpperCase());
                System.out.println("Author: " + book.author);
                System.out.println("Year Published: " + book.year);
                System.out.println("Copies in Library: " + book.libraryCopies);
                System.out.println();
                foundBook = true;
            }
        }

        if (!foundBook) {
            System.out.println("There are no books in the library with that exact title\n");
        }
    }

    static void ShowAllBooks(ArrayList<Book> books) {
        System.out.println();
        for (Book book : books) {
            int bookIndex = books.indexOf(book);
            System.out.println(bookIndex + ": " + book.title);
        }
        System.out.println();
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