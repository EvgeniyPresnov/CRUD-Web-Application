package ru.home.webapp.model.dao;

import ru.home.webapp.model.entities.Book;

import java.util.List;

/**
 * The interface defines an abstract API that performs CRUD operations
 *
 * @author Evgeniy Presnov
 */
public interface IBookDAO {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String bookID);
    List<Book> getListBooks();
    Book findBookById(String bookID);
}
