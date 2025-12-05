import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    static boolean isSystemRunning = true;

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    public static void main(String[] args) {
        ArrayList<Book> libraryBooks = new ArrayList<>();
        LibraryUser libraryUser = new LibraryUser();
        Scanner scanner = new Scanner(System.in);

        scanner.useDelimiter(System.lineSeparator());

        // Initialize Books
        libraryBooks.add(new Book("Meditations", "Marcus Aurelius", 1634, 2));
        libraryBooks.add(new Book("Harry Potter", "J.K Rowling", 1997, 1));
        libraryBooks.add(new Book("Steel Ball Run", "Hirohiko Araki", 2004, 1));

        // System Loop
        while (isSystemRunning) {
            MainMenu(libraryUser, libraryBooks, scanner);

            if (!isSystemRunning) {
                scanner.close();
                break;
            }
        }
    }

    static void MainMenu(LibraryUser libraryUser, ArrayList<Book> books, Scanner scanner) {

        printColored("MAIN MENU", YELLOW);
        System.out.println(
                "0: Borrow Book\n1: Return Book\n2: List All Books\n3: Find a Book\n4: Donate a Book\n5: Exit System\n");
        printColored("Select a number: ", GREEN);
        String input = scanner.next();

        switch (input) {
            case "0":
                BorrowBook(libraryUser, books, scanner);
                break;
            case "1":
                ReturnBook(libraryUser, books, scanner);
                break;
            case "2":
                printColored("\nAll Books in the Library", YELLOW);
                ShowAllBooks(books, true);
                printColored("------------------------" + "\n", YELLOW);
                break;
            case "3":
                FindBook(books, scanner);
                break;
            case "4":
                DonateBook(books, scanner);
                break;
            case "5":
                printColored(
                        "\nMy Github Commits: https://github.com/Scarnile/library-book-inventory-system/commits/main",
                        BLUE);
                isSystemRunning = false;
                break;
            default:
                printColored("\n" + "Invalid input: " + input + "\n", RED);
                break;
        }

    }

    static void BorrowBook(LibraryUser libraryUser, ArrayList<Book> libraryBooks, Scanner scanner) {

        // Ask user for the index of the book
        printColored("\nWould you like to borrow any of the books below?", GREEN);
        ShowAllBooks(libraryBooks, true);
        System.out.print("\nInsert Book Index: ");
        int inputIndex = scanner.nextInt();

        // If book has an invalid index
        if (inputIndex >= libraryBooks.size() || inputIndex < 0) {
            printColored("Invalid book index, try again\n", RED);
            return;
        } else {
            Book selectedBook = libraryBooks.get(inputIndex);

            // If selectedBook still has library copies, borrow the selectedBook
            if (selectedBook.libraryCopies > 0) {
                selectedBook.libraryCopies -= 1;
                libraryUser.books.add(selectedBook);
                printColored("You have successfully borrowed " + selectedBook.title, BLUE);
            } else {
                printColored("There are no more copies left of " + selectedBook.title, RED);
            }
            System.out.println(""); // Print empty line
        }
    }

    static void ReturnBook(LibraryUser libraryUser, ArrayList<Book> libraryBooks, Scanner scanner) {
        // Display all the library user's books
        if (!libraryUser.books.isEmpty()) {
            printColored("\nWhich book do you want to return?: ", GREEN);
            ShowAllBooks(libraryUser.books, false);

            int inputIndex = scanner.nextInt();

            // If book has an invalid index
            if (inputIndex >= libraryUser.books.size()) {
                printColored("Invalid book index, try again\n", RED);
                return;
            } else {
                // Find LibraryUser's book in Library and add a copy
                Book selectedBook = libraryUser.books.get(inputIndex);

                for (Book libraryBook : libraryBooks) {
                    // Book to return matches the library data
                    if (selectedBook.title == libraryBook.title) {
                        libraryBook.libraryCopies++;
                        libraryUser.books.remove(selectedBook);
                        printColored("Successfully returned " + selectedBook.title + "\n", BLUE);
                    }
                }
            }
        } else {
            printColored("You currently don't have any borrowed books from the library\n", RED);
        }

    }

    static void DonateBook(ArrayList<Book> libraryBooks, Scanner scanner) {
        Book newBook = new Book(null, null, 0, 0);
        printColored("\nDo you want to donate a book to the library right now? y/n", GREEN);
        String confirmation = scanner.next();

        if (confirmation.equals("n")) {
            System.out.println();
            return;
        } else if (confirmation.equals("y")) {

            // Ask for user to input details of the book they're about to donate
            printColored("\nWhat is the title of the book?", GREEN);
            newBook.title = (scanner.next());

            printColored("\nWho is the author of the book?", GREEN);
            newBook.author = (scanner.next());

            printColored("\nWhat year was the book released?", GREEN);
            newBook.year = (scanner.nextInt());

            printColored("\nHow many copies of " + newBook.title + " will you donate to the library?", GREEN);
            newBook.libraryCopies = (scanner.nextInt());

            libraryBooks.add(newBook);

            printColored("\nYou have successfully added " + newBook.title + " to the Library!\n", BLUE);
        } else {
            printColored("Invalid input\n", RED);
        }
    }

    static void FindBook(ArrayList<Book> libraryBooks, Scanner scanner) {

        boolean foundBook = false;
        printColored("\nType the title of a book you want to find: ", GREEN);
        String titleSearch = scanner.next();

        // Find if the user input matches the book title
        for (Book book : libraryBooks) {
            if (titleSearch.equals(book.title)) {
                printColored("\n" + book.title.toUpperCase(), BLUE);
                System.out.println("Author: " + book.author);
                System.out.println("Year Published: " + book.year);
                System.out.println("Copies in Library: " + book.libraryCopies);
                System.out.println();
                foundBook = true;
            }
        }

        if (!foundBook) {
            printColored("There are no books in the library with that exact title\n", RED);
        }
    }

    static void ShowAllBooks(ArrayList<Book> books, boolean showUnavailable) {
        for (Book book : books) {
            int bookIndex = books.indexOf(book);

            // Make the books red if no copies are available
            if (showUnavailable && book.libraryCopies <= 0) {
                System.out.println(RED + bookIndex + ": " + RESET + book.title);
            } else {
                System.out.println(bookIndex + ": " + book.title);
            }
        }
    }

    public static void printColored(String message, String colorCode) {
        System.out.println(colorCode + message + RESET);
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