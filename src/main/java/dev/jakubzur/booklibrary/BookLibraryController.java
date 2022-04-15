package dev.jakubzur.booklibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookLibraryController {

  @Autowired
  private BookRepository bookRepository;

  @GetMapping("/getBooks")
  public Iterable<Book> getBooks(
      @RequestParam(value = "isbn", required = false) String isbn) {

    Iterable<Book> books = bookRepository.findAll();

    return books;

    // throw new UnsupportedOperationException("Not yet implemented");
  }

  @PostMapping("/addBook")
  public Book addBook(
      @RequestBody Book newBook) {

    bookRepository.save(newBook);

    return newBook;

    // throw new UnsupportedOperationException("Not yet implemented");
  }

}
