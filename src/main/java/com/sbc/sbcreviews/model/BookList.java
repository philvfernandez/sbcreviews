package com.sbc.sbcreviews.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Link to the user
    private Long userId;

    //link to a book already in your Book entity
    private Long bookId;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        READING,
        COMPLETED,
        WISHLIST
    }
}
