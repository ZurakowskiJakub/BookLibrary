package dev.jakubzur.booklibrary;

import java.util.List;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final List<String> tags;

    public Book(String isbn, String title, String author, List<String> tags) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.tags = tags;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public List<String> getTags() {
        return this.tags;
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
