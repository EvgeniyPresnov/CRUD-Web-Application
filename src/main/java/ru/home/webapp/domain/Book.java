package ru.home.webapp.domain;

import java.io.Serializable;
import java.util.Objects;

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

        Book other = (Book) obj;

        return Objects.equals(bookID, other.bookID)
                && Objects.equals(title, other.title)
                && Objects.equals(author, other.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, title, author);
    }

}
