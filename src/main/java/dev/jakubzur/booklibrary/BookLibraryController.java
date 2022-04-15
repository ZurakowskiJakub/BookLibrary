package dev.jakubzur.booklibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BookLibraryController {

  @Autowired
  private BookRepository bookRepository;

  @GetMapping("/getBooks")
  public Iterable<Book> getBooks(
      @RequestParam(value = "isbn", required = false) String isbn) {

    List<Book> books = new ArrayList<Book>();

    if (!StringUtils.hasText(isbn)) {
      books = (List<Book>) bookRepository.findAll();

      if (books.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find the book(s) you were looking for.");
      }
    } else {
      Optional<Book> book = bookRepository.findById(isbn);

      if (book.isPresent()) {
        books.add(book.get());
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find the book you were looking for.");
      }
    }

    return books;
  }

  @PostMapping("/addBook")
  public Book addBook(
      @RequestBody Book newBook) {

    Optional<Book> book = bookRepository.findById(newBook.getIsbn());
    if (book.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "This book already exists.");
    }

    bookRepository.save(newBook);

    return newBook;
  }

  @PutMapping("/updateBook")
  public Book updateBook(
      @RequestBody Book newBook) {

    Optional<Book> book = bookRepository.findById(newBook.getIsbn());
    if (book.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This book does not exists.");
    }

    bookRepository.save(newBook);

    return newBook;
  }

}
