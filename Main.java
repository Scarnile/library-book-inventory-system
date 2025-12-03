public class Main {
    public static void main(String[] args) {
        Book book = new Book("Lord of the Rings", "John Tolkien", 1925, 2);
        System.out.println(book.title);
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
