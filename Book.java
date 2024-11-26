package assignment1;

public class Book {
    private static int bookIDCounter = 10000;
    private String title;
    private String author;
    private String ISBN;
    private Boolean isAvailable;
    private int bookID;
    private String borrowerName;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
        this.borrowerName = null;
    }

    //constructor for use in importing books via library
    public Book (String csvLine){
        String[] attribute = csvLine.split(",");
        this.title = attribute[0].trim();
        this.author = attribute[1].trim();
        this.ISBN = attribute[2].trim();
        this.isAvailable = true;
        this.bookID = generateBookID();
        this.borrowerName = null;
    }

//  auto bookID generator
    public static int generateBookID(){
        bookIDCounter+=1;
        return bookIDCounter;
    }
    //return csv format for books
    public String returnCSVFormat(){return title + "," + author + "," + ISBN;}

    public void Borrow(String borrower) {
        if (isAvailable) {
            this.borrowerName = borrower;
            isAvailable = false;
            System.out.println("Book borrowed by " + borrower + ".");
        }
        else {
            throw new IllegalStateException("The book is already being borrowed.");
        }
    }

    public void Return(String returner) {
        if (this.borrowerName.equals(returner) && isAvailable) {
            borrowerName = null;
            isAvailable = true;
            System.out.println("Book returned by " + returner + ".");
        }
        else if (!this.borrowerName.equals(returner) && !isAvailable) {
            throw new IllegalStateException("Book was borrowed by " + borrowerName + ", not " + returner);
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
        System.out.println("Availability: " + (isAvailable ? "Available" : "Borrowed by " + borrowerName));
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBorrowerName() {
        return borrowerName;
    }
}
