package assignment1;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void BorrowBook(String isbn, String borrower) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                book.Borrow(borrower);
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    public void Return(String isbn, String returner) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                book.Return(returner);
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }

    public void displayBooks() {
        for (Book book : books) {
            book.displayBookDetails();
            System.out.println();
        }
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getISBN().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }
}