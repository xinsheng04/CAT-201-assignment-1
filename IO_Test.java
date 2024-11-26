package assignment1;
import java.io.*;
import java.util.ArrayList;

//This is for testing the I/O file handling code
public class IO_Test {
    public static void main (String [] args){
        String csvFilePath = "bookList.csv";
        ArrayList<Book> bookShelf = new ArrayList<>();
//      Input from csv code
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
//          Import reader successful
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

            //print all books
            for(Book book: bookShelf){
                book.displayBookDetails();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//      Output to csv code
        String csvFilePath2 = "bookList2.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath2))) {
            for(Book book: bookShelf){
                bw.write(book.returnCSVFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
