package assignment1;

import static java.lang.Integer.parseInt;
import java.lang.Boolean;
public class Book {
    private final String title;
    private final String author;
    private final String ISBN;
    private Boolean isAvailable;
    private int bookID;
    private String borrowerID;
    private String borrowerName;

    public Book(String title, String author, String ISBN, int bookID) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
        this.borrowerName = "-";
        this.borrowerID = "-";
        this.bookID = bookID;
    }

    //constructor for use in importing books via library
    public Book (String csvLine){
        String[] attribute = csvLine.split(",");
        this.bookID = parseInt(attribute[0].trim());
        this.title = attribute[1].trim();
        this.author = attribute[2].trim();
        this.ISBN = attribute[3].trim();
        this.isAvailable = attribute[4].trim().equals("-");
        this.borrowerID = attribute[4].trim();
        this.borrowerName = attribute[5].trim();
    }

    //return csv format for books
    public String returnCSVFormat(){return bookID + "," + title + "," + author + "," + ISBN + "," + borrowerID + "," + borrowerName;}

    public void Borrow(String borrowerID, String borrowerName) {
        if (isAvailable) {
            this.borrowerID = borrowerID;
            this.borrowerName = borrowerName;
            isAvailable = false;
            System.out.println("Book borrowed by " + borrowerName + ".");
        }
        else {
            throw new IllegalStateException("The book is already being borrowed.");
        }
    }

    public void Return(String returnerID) {
        if (this.borrowerID.equals(returnerID) && !isAvailable) {
            borrowerName = "-";
            borrowerID = "-";
            isAvailable = true;
            System.out.println("Book returned.");
        }
        else if (!this.borrowerID.equals(returnerID) && !isAvailable) {
            throw new IllegalStateException("Wrong borrower. Book was borrowed by " + borrowerName);
        }
        else {
            throw new IllegalStateException("Error: Book was not borrowed");
        }
    }

    public void displayBookDetails() {
        System.out.println("Book ID: " + bookID);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Status: " + (isAvailable? "Available" : "Not Available" ));
        if(!isAvailable){
            System.out.println("BorrowerID: " + borrowerID);
            System.out.println("Borrower Name: " + borrowerName);
        }
        System.out.println("-------------------------------------------------");
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public int getBookID(){
        return this.bookID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBorrowerName() {
        return borrowerName;
    }
}
