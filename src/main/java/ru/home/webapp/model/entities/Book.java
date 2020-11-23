package ru.home.webapp.model.entities;

import java.io.Serializable;

/**
 * This is class is simple POJO containing get/set methods to store data retrieved using DAO class
 *
 * @author Evgeniy Presnov
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bookID;
    private String title;
    private String author;

    public Book() {}

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book book = (Book) obj;

        return book.bookID.equals(bookID)
                && book.title.equals(title)
                && book.author.equals(author);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + bookID.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }

}
