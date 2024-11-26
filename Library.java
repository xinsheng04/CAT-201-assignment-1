package assignment1;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
//For I/O processing
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Library {
    private ArrayList<Book> bookShelf;

    public Library() {
        bookShelf = new ArrayList<Book>();
    }

    public void addBook(String title, String author, String ISBN) {
        Book newbook = new Book (title, author, ISBN);
        bookShelf.add(newbook);
    }

//  This function is for importing books from a csv file into the system
    private void importBooks(String csvFilePath){
        bookShelf = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String entry; //Each row in the csv
            entry = br.readLine(); //read the first line
            if(entry == null){
                return; //No books in the csv
            }
            do{
                Book newBook = new Book(entry);
                bookShelf.add(newBook);
            }
            while ((entry = br.readLine()) != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//  This function is for writing books to a csv file before saving
//  Note: Make sure to call this function before ending the program
    private void saveBooks(String csvFilePath){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            for(Book book: bookShelf){
                bw.write(book.returnCSVFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrowBook(String isbn, String borrower) {
        try {
            for (Book book : bookShelf) {
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
            for (Book book : bookShelf) {
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
        for (Book book : bookShelf) {
            book.displayBookDetails();
            System.out.println();
        }
    }

    public Book searchBookByTitle(String title) {
        for (Book book : bookShelf) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        for (Book book : bookShelf) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByISBN(String isbn) {
        for (Book book : bookShelf) {
            if (book.getISBN().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return bookShelf;
    }
}