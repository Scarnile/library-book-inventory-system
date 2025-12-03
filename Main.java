public class Main {
    public static void main(String[] args) {
        Book book = new Book();

        System.out.println(book.title);
    }

}

class Book {
    String title;
    String author;
    int year;
    int copies;
}
