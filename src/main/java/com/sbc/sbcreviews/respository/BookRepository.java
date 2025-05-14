package com.sbc.sbcreviews.respository;

import com.sbc.sbcreviews.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    @Query(value = "SELECT * FROM books WHERE LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(author) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Book> searchBooks(@Param("keyword") String keyword);

    long countByAuthor(String author);

}
