package dev.jakubzur.booklibrary.model;

import java.util.List;

public class BookLibrary {
    private final List<Book> books;

    public BookLibrary(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    @Override
    public String toString() {
        return "{" +
                " books='" + getBooks() + "'" +
                "}";
    }
}
