package com.sbc.sbcreviews.service;

import com.sbc.sbcreviews.exception.ResourceNotFoundException;
import com.sbc.sbcreviews.model.Book;
import com.sbc.sbcreviews.respository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book testBook;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testBook = new Book(1L, "Test Book", "Test Author", "2025", "Phil Fernandez", "My First Review");
        testBook.setId(1L);
    }

    @Test
    void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(testBook));
        List<Book> books = bookService.getAllBooks();
        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(testBook));
        Book book = bookService.getBookById(1L);
        assertEquals(testBook, book);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBookByIdNotFound() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> bookService.getBookById(1L));
    }

    @Test
    void createBook() {
        when(bookRepository.save(testBook)).thenReturn(testBook);
        Book book = bookService.createBook(testBook);
        assertEquals(testBook, book);
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    void updateBook() {
        Book updatedBook = new Book(1L, "Updated Test Book", "Test Author", "2025", "Phil Fernandez", "My Updated Review");
        updatedBook.setId(1L);
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(testBook));

        Book book = bookService.updateBook(1L, updatedBook);

        assertEquals(updatedBook.getTitle(), book.getTitle());
        assertEquals(updatedBook.getAuthor(), book.getAuthor());
        assertEquals(updatedBook.getPublicationDate(), book.getPublicationDate());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(updatedBook);
    }

    @Test
    void deleteBook() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(testBook));
        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).delete(testBook);
    }

    @Test
    void searchBooks() {
        when(bookRepository.searchBooks("test")).thenReturn(Arrays.asList(testBook));
        List<Book> books = bookService.searchBooks("test");
        assertEquals(1, books.size());
        verify(bookRepository, times(1)).searchBooks("test");
    }

    /*
    @Test
    void findRecentBooks() {
        when(bookRepository.findTop5ByOrderByYearDesc()).thenReturn(Arrays.asList(testBook));
        List<Book> books = bookService.findRecentBooks();
        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findTop5ByOrderByYearDesc();
    }
     */

    @Test
    void countBooksByAuthor() {
        when(bookRepository.countByAuthor("Test Author")).thenReturn(1L);
        long count = bookService.countBooksByAuthor("Test Author");
        assertEquals(1L, count);
        verify(bookRepository, times(1)).countByAuthor("Test Author");
    }


}
