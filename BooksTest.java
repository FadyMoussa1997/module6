import java.util.ArrayList;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

class BooksTest {



// testing that books can be added
    @org.junit.jupiter.api.Test
    void testAddingBooks() {
        Main.clearBo();


        Main.addingBooks (12345, "testBook", "fady") ;
        ArrayList<Books> bo = Main.bo;

        assertEquals(1, bo.size());
        assertEquals(12345 , bo.get(0).getBarcode()) ;


    }



// testing if a new library file can be made
    @org.junit.jupiter.api.Test
    void testCreatingNewLibrary() {

        Main.createNewFile("testFile");
        File file = new File ("testFile.csv") ;

        assertTrue(file.exists());

        // Deleting the test file that was just created for testing purposes
        file.delete() ;

    }



// testing iff loading books from an existing library is valid
    @org.junit.jupiter.api.Test
    void testLoadingBooksFromLibrary() {
        Main.createNewFile("testFile");
        File file = new File ("testFile.csv") ;

        try (PrintWriter writer = new PrintWriter (new FileWriter  (file)) ) {

            writer.println (1+", "+"book1"+", "+"author1") ;
            writer.println (2+", "+"book2"+", "+"author2") ;
            writer.println (3+", "+"book3"+", "+"author3") ;


        } catch (IOException e) {
            fail("Failed to create test data");
        }

        ArrayList<Books> bo = new ArrayList<>();
        bo = Main.loadBooksFromFile(file);

        assertEquals(3 , bo.size());

        assertEquals(1, bo.get(0).getBarcode());
        assertEquals(2, bo.get(1).getBarcode());
        assertEquals(3, bo.get(2).getBarcode());


        assertEquals(" book1", bo.get(0).getTitle());
        assertEquals(" book2", bo.get(1).getTitle());
        assertEquals(" book3", bo.get(2).getTitle());


        assertEquals(" author1", bo.get(0).getAuthor());
        assertEquals(" author2", bo.get(1).getAuthor());
        assertEquals(" author3", bo.get(2).getAuthor());


        // Deleting the test file that was just created for testing purposes
        file.delete();

    }


// testing deleting books by barcode
    @org.junit.jupiter.api.Test
    void testDeletingBooks() {
        ArrayList<Books> bo = Main.bo;
        Main.clearBo();


        bo.add(new Books(1, "title1" , "author1")) ;
        bo.add(new Books(2, "title2" , "author2")) ;

        Main.deletingBooks(1);
        assertEquals(1, bo.size());

    }



    // testing for checking out books
    // if a book is checked out then the due date is not null !
    @org.junit.jupiter.api.Test
    void testChekingOut() {
        Main.clearBo();
        ArrayList<Books> bo = Main.bo;

        LocalDate dueDate = null ;




        bo.add(new Books(1, "title1" , "author1")) ;
        bo.add(new Books(2, "title2" , "author2")) ;



        // Expected due date is the current date plus 14 days
         dueDate = Main.checkOutBook(1);



        // Assert that the dueDate is no longer null when a book is checked out successfully
        assertNotNull(dueDate);


    }




    // testing for checking in books
    // if a book is checked in, the due date is set to null !
    @org.junit.jupiter.api.Test
    void testChekingIn() {
        Main.clearBo();
        ArrayList<Books> bo = Main.bo;
        LocalDate dueDate ;

        bo.add(new Books(1, "title1" , "author1")) ;
        bo.add(new Books(2, "title2" , "author2")) ;


// Expected due date is null since the book was found and checked in
        dueDate = Main.checkInBook(2);


        // Assert that the dueDate is  null when a book is checked in successfully
        assertNull(dueDate);

    }

}