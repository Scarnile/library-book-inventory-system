import java.util.ArrayList;

public class Library {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        LibraryUser libraryUser = new LibraryUser();

        books.add(new Book("Lord of the Rings", "John Tolkien", 1925, 2));
        books.add(new Book("Harry Potter", "J.K Rowling", 1980, 1));

        System.out.println("Would you like to borrow any of the books below?");
        ShowAllBooks(books);
    }

    static void BorrowBook(Book book) {
        // If book still has library copies, borrow the book
        if (book.libraryCopies > 0) {
            book.libraryCopies -= 1;
        } else {
            System.out.println("There are no more copies left of " + book.title);
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