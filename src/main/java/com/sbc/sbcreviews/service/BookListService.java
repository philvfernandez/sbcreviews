package com.sbc.sbcreviews.service;

import com.sbc.sbcreviews.model.BookList;
import com.sbc.sbcreviews.respository.BookListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookListService {

    private final BookListRepository repository;

    public BookListService(BookListRepository repository) {
        this.repository = repository;
    }

    public List<BookList> getUserBookList(Long userId) {
        return repository.findByUserId(userId);
    }

    public BookList addBookToList(BookList bookList) {
        return repository.save(bookList);
    }

    public Optional<BookList> updateBookStatus(Long id, BookList.Status status) {
        return repository.findById(id).map(bookList -> {
            bookList.setStatus(status);
            return repository.save(bookList);
        });
    }

    public void removeBookFromList(Long id) {
        repository.deleteById(id);
    }
}
