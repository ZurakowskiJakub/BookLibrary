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

  private final String BOOK_NOT_FOUND = "Cannot find the book(s) you are looking for.";
  private final String BOOK_ALREADY_EXISTS = "This book already exists.";

  @Autowired
  private BookRepository bookRepository;

  @GetMapping("/getBooks")
  public Iterable<Book> getBooks(
      @RequestParam(value = "isbn", required = false) String isbn) {

    List<Book> books = new ArrayList<Book>();

    if (!StringUtils.hasText(isbn)) {
      // isbn not present - find all
      books = (List<Book>) bookRepository.findAll();

      if (books.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.BOOK_NOT_FOUND);
      }
    } else {
      // isbn present - find one
      Optional<Book> book = bookRepository.findById(isbn);

      if (book.isPresent()) {
        books.add(book.get());
      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.BOOK_NOT_FOUND);
      }
    }

    return books;
  }

  @PostMapping("/addBook")
  public Book addBook(
      @RequestBody Book newBook) {

    Optional<Book> book = bookRepository.findById(newBook.getIsbn());
    if (book.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, this.BOOK_ALREADY_EXISTS);
    }

    bookRepository.save(newBook);

    return newBook;
  }

  @PutMapping("/updateBook")
  public Book updateBook(
      @RequestBody Book newBook) {

    Optional<Book> book = bookRepository.findById(newBook.getIsbn());
    if (book.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.BOOK_NOT_FOUND);
    }

    bookRepository.save(newBook);

    return newBook;
  }

}
