package com.sbc.sbcreviews.controller;

import com.sbc.sbcreviews.model.Book;
import com.sbc.sbcreviews.service.BookService;
import com.sbc.sbcreviews.service.BookServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping("/getAllBooks")
    public String getAllBook(
            @RequestParam(defaultValue = "title") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        List<Book> books = bookService.getAllBooksSorted(sortField,sortDir);
        model.addAttribute("allBooks", books);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseDir", sortDir.equals("asc") ? "desc" : "asc");
        return "index.html";
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/addNewReview")
    public String createBook(Model model) {
        Book book = new Book();
        model.addAttribute("allBooks", book);
        return "newReview";
    }

    @PostMapping("/saveBookReview")
    public String saveBookReview(@Valid @ModelAttribute("allBooks") Book book, BindingResult result) {
        if(result.hasErrors()) {
            return "newReview";
        }
        bookServiceImpl.createBook(book);
        return "redirect:/getAllBooks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("book", book);
            return "edit-book";
        }
        book.setId(id);
        bookService.save(book);
        return "redirect:/getAllBooks";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/getAllBooks";
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> findBooksByAuthor(@PathVariable("author") String author) {
        List<Book> books = bookService.findBooksByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookService.searchBooks(keyword);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/count{author}")
    public ResponseEntity<Long> countBooksByAuthor(@PathVariable("author") String author) {
        long count = bookService.countBooksByAuthor(author);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
