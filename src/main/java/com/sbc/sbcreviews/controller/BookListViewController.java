package com.sbc.sbcreviews.controller;

import com.sbc.sbcreviews.model.BookList;
import com.sbc.sbcreviews.service.BookListService;
import com.sbc.sbcreviews.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/{userId}/booklist")
public class BookListViewController {

    private final BookListService bookListService;
    private final BookService bookService;


    public BookListViewController(BookListService bookListService, BookService bookService) {
        this.bookListService = bookListService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getUserBookList(@PathVariable Long userId, Model model) {
        model.addAttribute("books", bookListService.getUserBookList(userId));
        model.addAttribute("allBooks", bookService.getBookById(userId));
        model.addAttribute("newBookList", new BookList());
        model.addAttribute("userId", userId);
        return "booklist";
    }

    @PostMapping
    public String addBookToList(@PathVariable Long userId,
                                @ModelAttribute BookList newBookList) {
        newBookList.setUserId(userId);
        bookListService.addBookToList(newBookList);
        return "redirect:/user/" + userId + "/booklist";
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long userId,
                               @PathVariable Long id,
                               @RequestParam BookList.Status status) {
        bookListService.updateBookStatus(id, status);
        return "redirect:/user/" + userId + "/booklist";
    }

    @PostMapping("/{id}/delete")
    public String removeBook(@PathVariable Long userId, @PathVariable Long id) {
        bookListService.removeBookFromList(id);
        return "redirect:/user/" + userId + "/booklist";
    }
}
