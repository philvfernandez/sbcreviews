package com.sbc.sbcreviews.controller;

import com.sbc.sbcreviews.model.BookList;
import com.sbc.sbcreviews.service.BookListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookList")
public class BookListController {

    private final BookListService service;


    public BookListController(BookListService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookList>> getUserBookList(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getUserBookList(userId));
    }

    @PostMapping
    public ResponseEntity<BookList> addBookToList(@RequestBody BookList bookList) {
        return ResponseEntity.ok(service.addBookToList(bookList));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookList> updateStatus(@PathVariable Long id,
                                                 @RequestParam BookList.Status status) {
        return service.updateBookStatus(id,status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable Long id) {
        service.removeBookFromList(id);
        return ResponseEntity.noContent().build();
    }
}
