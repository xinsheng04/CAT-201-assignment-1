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
    private String memberListPath;
    private String bookListPath;
    private int nextBookID;

    public Library() {
        bookShelf = new ArrayList<Book>();
        memberListPath = "";
    }

    public Library(String bookListPath, String memberListPath){
        importBooks(bookListPath);
        this.memberListPath = memberListPath;
        this.bookListPath = bookListPath;
        nextBookID = 10001 + bookShelf.size();
    }

    public void addBook(String title, String author, String ISBN) {
        Book newbook = new Book (title, author, ISBN, nextBookID++);
        bookShelf.add(newbook);
    }

    //  auto bookID generator
    private int generateBookID(){
        nextBookID+=1;
        return nextBookID;
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
    public void saveBooks(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(bookListPath))) {
            for(Book book: bookShelf){
                bw.write(book.returnCSVFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrowBook(int bookID, String borrowerID) {
        try {
            for (Book book : bookShelf) {
                if (book.getBookID()==bookID) {
//                    attempts to borrow the book
                    String borrowerName = getBorrowerName(borrowerID);
                    book.Borrow(borrowerID, borrowerName);
                    return;
                }
            }
            System.out.println("BookID: " + bookID + " not found.");
        }
        catch (IllegalStateException ise){
            System.out.println("Error:" + ise);
        }
    }

    private String getBorrowerName(String borrowerID){
        try (BufferedReader br = new BufferedReader(new FileReader(memberListPath))) {
            String entry; //Each row in the csv
            String [] splitEntry;
            entry = br.readLine(); //read the first line
            if(entry == null){
                throw new IllegalStateException("No members in list."); //No member
            }
            do{
                splitEntry = entry.split(",");
                if(splitEntry[0].equals(borrowerID))
                    return splitEntry[1];
            }
            while ((entry = br.readLine()) != null);
            throw new IllegalStateException("Member not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public void returnBook(int bookID, String returnerID) {
        try {
            for (Book book : bookShelf) {
                if (book.getBookID()==bookID) {
                    book.Return(returnerID);
                    return;
                }
            }
            System.out.println("BookID: " + bookID + " not found.");
        }
        catch (IllegalStateException ise){
            System.out.println("Error:" + ise);
        }
    }

    public void removeBook(int bookID){
        boolean bookRemoved = bookShelf.removeIf(book -> book.getBookID() == bookID);
        if(bookRemoved)
            return;
        else
            System.out.println("BookID: " + bookID + " not found.");
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