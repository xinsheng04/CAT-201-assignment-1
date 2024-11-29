package assignment1;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Borrow_Return_Test {
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        String bookListPath = "bookList.csv";
        String memberListPath = "memberList.csv";
        Library library = new Library(bookListPath, memberListPath);
        library.displayBooks();
        System.out.println("1. Borrow a book");
        System.out.println("2. Return a book");
        System.out.println("3. Add a book");
        System.out.println("4. Remove a book");
        int code = sc.nextInt();
        int bookID;
        String memberID;
        String memberName;
        switch (code){
            case 1:
                System.out.print("Enter bookID: ");
                bookID = sc.nextInt();
                System.out.print("Enter borrower ID: ");
                memberID = sc.nextLine(); //clean the buffer
                memberID = sc.nextLine();
                library.borrowBook(bookID, memberID);
                break;
            case 2:
                System.out.print("Enter bookID: ");
                bookID = sc.nextInt();
                System.out.print("Enter returner ID: ");
                memberID = sc.nextLine(); //clean the buffer
                memberID = sc.nextLine();
                library.returnBook(bookID, memberID);
                break;
            case 3:
                System.out.print("Enter Title: ");
                String title = sc.nextLine();
                title = sc.nextLine();
                System.out.print("Enter Author: ");
                String author = sc.nextLine();
                System.out.print("Enter ISBN: ");
                String ISBN = sc.nextLine();
                library.addBook(title, author, ISBN);
                break;
            case 4:
                System.out.print("Removed book: ");
                bookID = sc.nextInt();
                library.removeBook(bookID);
                break;
            default:
                break;
        }
        library.saveBooks();
    }
}
