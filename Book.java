package assignment1;

public class Book {
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
