
import java.io.*;
import java.util.*;


/*
 Fady Moussa
 Software Development 1
 10/8/2023
 */

public class Main {

    private static File file;
    private static ArrayList<Books> bo = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Scanner scanner = new Scanner(System.in);
        Scanner b1 = new Scanner(System.in);


        boolean exitLoop1 = false;
        boolean exitLoop2 = false;

        // first menu option to choose from creating a new file or loading an existing file

        while (!exitLoop1) {
            System.out.println("1. Press to open a new library file");
            System.out.println("2. Press to LOAD an existing library file");

            int choicefrom2 = scanner.nextInt();

            switch (choicefrom2) {
                case 1:
                    System.out.println("Enter a name for the NEW library file");
                    String filename = scanner.next();
                    createNewFile(filename);
                    bo = new ArrayList<>();
                    file = new File(filename + ".csv"); // Assign 'file' here
                    exitLoop1 = true;
                    break;
                case 2:
                    System.out.println("Enter the name of the existing library file");
                    filename = b1.nextLine();
                    file = new File(filename + ".csv"); // Assign 'file' here
                    if (file.exists()) {
                        bo = loadBooksFromFile(file);
                        System.out.println("Library File " + filename + " is Loaded and Ready to Use !");
                    } else {
                        System.out.println("FILE DOES NOT EXIST");
                    }
                    exitLoop1 = true;
                    break;

                default:
                    System.out.println("INVALID CHOICE !!");
            }
        }
// second menu options to choose options like inserting a book or deleting a book by barcode
        while (!exitLoop2) {
            System.out.println("1. INSERT A NEW BOOK");
            System.out.println("2. DELETE A BOOK");
            System.out.println("3. DISPLAY ALL BOOKS");
            System.out.println("0. EXIT");

            int choicefrom4 = scanner.nextInt();

            switch (choicefrom4) {
                case 1:
                    System.out.println("Enter book BARCODE!");
                    int barcode = scanner.nextInt();

                    System.out.println("Enter book TITLE!");
                    String title = scanner.next();

                    System.out.println("Enter book AUTHOR!");
                    String author = scanner.next();
                    bo.add(new Books(barcode, title, author));
                    saveBooksToFile(bo, file);
                    break;

                case 2:
                    System.out.println("Enter book ID to delete:");
                    int barcodeToDelete = scanner.nextInt();

                    // Finding and removing the book with the specified ID
                    boolean removed = false;
                    Iterator<Books> iterator = bo.iterator();
                    while (iterator.hasNext()) {
                        Books book = iterator.next();
                        if (book.getBarcode() == barcodeToDelete) {
                            iterator.remove();
                            removed = true;
                            break;
                        }
                    }
                    if (removed) {
                        System.out.println("Book with ID " + barcodeToDelete + " has been deleted.");
                    } else {
                        System.out.println("Book with ID " + barcodeToDelete + " not found.");
                    }
                    saveBooksToFile(bo, file);
                    break;

                case 3:
                    System.out.println("3. DISPLAYING .....");
                    displayBooks(bo);
                    break;

                case 0:
                    saveBooksToFile(bo, file);
                    System.out.println("Goodbye! Data has been saved to the Library file.");
                    exitLoop2 = true;
                    break;

                default:
                    System.out.println("WRONG CHOICE!!!");
            }
            System.out.println("-------------------------------");
        }


        scanner.close();
    }

    // The rest of code methods...




// this method creates a new file with the name provided by the user

    public static void createNewFile(String filepath) {

        File file = new File(filepath+".csv");
        try {
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("New File is Created Succssefuly");
            } else {
                System.out.println("FAILED TO CREATE THE FILE");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // this method loads the array of books from the file provided
    public static ArrayList<Books> loadBooksFromFile (File file) {
        ArrayList<Books> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int barcode = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    books.add(new Books(barcode, title, author));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
// this method saves the array of books to the chosen library file
    private static void saveBooksToFile(ArrayList<Books> books, File file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Books book : books) {
                writer.println(book.getBarcode() + "," + book.getTitle() + "," + book.getAuthor());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// this method is for displaying all books that exist within the folder
    private static void displayBooks(ArrayList<Books> books) {
        for (Books book : books) {
            System.out.println(book);
        }
    }

}


