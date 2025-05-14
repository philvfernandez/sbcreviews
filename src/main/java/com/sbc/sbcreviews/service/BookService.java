package com.sbc.sbcreviews.service;

import com.sbc.sbcreviews.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book book);
    void save(Book book);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);
    List<Book> findBooksByAuthor(String author);
    List<Book> searchBooks(String keyword);
    //List<Book> findRecentBooks();
    long countBooksByAuthor(String author);
}
