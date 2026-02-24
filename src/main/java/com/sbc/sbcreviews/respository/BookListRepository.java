package com.sbc.sbcreviews.respository;

import com.sbc.sbcreviews.model.BookList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookListRepository extends JpaRepository<BookList, Long> {
    List<BookList> findByUserId(Long userId);
}
