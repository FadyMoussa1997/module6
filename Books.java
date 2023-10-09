
/*
 Fady Moussa
 Software Development 1
 9/3/2023
 */

// book class that has the barcode, title, and author details
public class Books {

    private int barcode ;
    private String title ;
    private String author ;



//constructor method for the books

    public Books(int barcode, String title, String author) {
        this.setBarcode(barcode);
        this.setTitle(title);
        this.setAuthor(author);
    }



    public int getBarcode () {

        return barcode ;
    }

    public void setBarcode (int barcode) {
        this.barcode = barcode ;
    }


    public String getTitle() {
        return title ;

    }

    public void setTitle (String title) {
        this.title = title ;
    }

    public String getAuthor () {
        return author ;
    }

    public void setAuthor (String author) {
        this.author = author ;
    }
    // to string method to display book details
    public String toString() {

        return barcode+". "+title+", "+author ;
    }


}
