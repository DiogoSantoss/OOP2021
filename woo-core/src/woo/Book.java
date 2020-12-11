package woo;

import java.io.Serializable;

public class Book extends Product implements Serializable{

    private String title;
    private String author;
    private String ISBN;

    /**
     * Constructor
     *
     * @param title
     *          the book's title
     * @param author
     *          the book's author
     * @param ISBN
     *          the book's ISBN
     */
    Book(String key, String title, String author, String ISBN, Supplier supplier ,int price, int criticalValue, int stock){
        super(key, supplier, price, criticalValue, stock);
        super.setPeriod(3);
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    /**
     * @return the product's title
     */
    String getTitle(){
        return this.title;
    }

    /**
     * @return the product's author
     */
    String getAuthor(){
        return this.author;
    }
    
    /**
     * @return the product's ISBN
     */
    String getISBN() {
        return this.ISBN;
    }

    @Override
    public String toString() {
        return "BOOK" + "|" + super.toString() + "|" + this.title + "|" + this.author + "|" + this.ISBN;
    }
}
