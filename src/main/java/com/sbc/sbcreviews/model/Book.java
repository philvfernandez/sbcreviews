package com.sbc.sbcreviews.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;

    //@NotBlank(message = "ISBN is required")
    @Column(nullable = false)
    private String ISBN;

    @NotNull(message = "Publication Date is required")
    @Column(name = "\"publicationDate\"", nullable = false)
    private String publicationDate;

    @Column(name = "\"rating\"",nullable = false)
    private int rating;

    @NotNull(message = "Reviewer Is Required")
    @Column(name = "\"reviewer\"", nullable = false)
    private String reviewer;

    @Column(name = "\"review\"")
    private String review;



    //Default constructor
    public Book() {
    }

    //Parameterized constructor
    public Book(Long id, String title, String author, String publicationDate, int rating, String reviewer, String review) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.rating = rating;
        this.reviewer = reviewer;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(ISBN, book.ISBN) &&
                Objects.equals(publicationDate, book.publicationDate) &&
                Objects.equals(rating, book.rating) &&
                Objects.equals(reviewer, book.reviewer) &&
                Objects.equals(review, book.review);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", rating='" + rating + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
