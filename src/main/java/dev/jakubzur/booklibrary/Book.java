package dev.jakubzur.booklibrary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    private String isbn;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String tags;

    public Book() {

    }

    public Book(String isbn, String title, String author, String tags) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "{" +
                " isbn='" + getIsbn() + "'" +
                ", title='" + getTitle() + "'" +
                ", author='" + getAuthor() + "'" +
                ", tags='" + getTags() + "'" +
                "}";
    }

}
