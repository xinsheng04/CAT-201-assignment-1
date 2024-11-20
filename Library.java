package assignment1;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<Book>();
    }

    public void addBook(String title, String author, String ISBN) {
        Book newbook = new Book (title, author, ISBN);
        books.add(newbook);
    }

//  This function is for importing books from a csv file into the system
    private void importBooks(String csvFilePath){
        File inFile = new File (csvFilePath);
//      to be added
    }

    public void borrowBook(String isbn, String borrower) {
        try {
            for (Book book : books) {
                if (book.getISBN().equals(isbn)) {
//                    attempts to borrow the book
                    book.Borrow(borrower);
                    return;
                }
            }
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
        catch (IllegalStateException ise){
            System.out.println("Error:" + ise);
        }
    }

    public void returnBook(String isbn, String returner) {
        try {
            for (Book book : books) {
                if (book.getISBN().equals(isbn)) {
                    book.Return(returner);
                    return;
                }
            }
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
        catch (IllegalStateException ise){
            System.out.println("Error:" + ise);
        }
    }

    public void displayBooks() {
//        note: no header
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