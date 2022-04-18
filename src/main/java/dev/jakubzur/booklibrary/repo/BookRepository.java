package dev.jakubzur.booklibrary.repo;

import org.springframework.data.repository.CrudRepository;

import dev.jakubzur.booklibrary.model.Book;

public interface BookRepository extends CrudRepository<Book, String> {

}
