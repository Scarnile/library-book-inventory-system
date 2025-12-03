import java.util.ArrayList;

public class Library {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("Lord of the Rings", "John Tolkien", 1925, 2));
        books.add(new Book("Harry Potter", "J.K Rowling", 1980, 1));
        ShowAllBooks(books);
    }

    static void BorrowBook() {

    }

    static void ShowAllBooks(ArrayList<Book> books) {
        for (Book book : books) {
            System.out.println(book.title);
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
