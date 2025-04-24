package com.sbc.sbcreviews.respository;

import com.sbc.sbcreviews.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByYearGreaterThan(Integer year);
    List<Book> findByAuthorAndYearGreaterThan(String author, Integer year);
    List<Book> findTop5ByOrderByYearDesc();

    @Query("SELECT b FROM Book b WHERE b.author = :author AND b.year BETWEEN :startYear AND :endYear")
    List<Book> findBooksByAuthorAndYearRange(@Param("author") String author,
                                             @Param("startYear") Integer startYear,
                                             @Param("endYear") Integer endYear);

    @Query(value = "SELECT * FROM books WHERE LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(author) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Book> searchBooks(@Param("keyword") String keyword);

    long countByAuthor(String author);

    void deleteByYear(Integer year);

    boolean existsByTitleAndAuthor(String title, String author);


}
