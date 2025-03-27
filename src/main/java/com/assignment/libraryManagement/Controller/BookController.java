package com.assignment.libraryManagement.Controller;

import com.assignment.libraryManagement.Entity.Book;
import com.assignment.libraryManagement.Exceptions.BookNotFoundException;
import com.assignment.libraryManagement.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling book-related operations.
 * Provides endpoints for adding, updating, searching, and deleting books.
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ApplicationContext context;

    /**
     * Adds a new book to the collection.
     *
     * @param book The book object to add (validated).
     * @return The added book.
     */
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        return ResponseEntity.ok(bookService.addBook(book));
    }

    /**
     * @return list of all books present in the collection
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }

    /**
     * @return the book present in the collection according to bookId or Title present.
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchBook(@RequestParam String query) {
        Optional<Book> book = bookService.getBookBYIdOrTitle(query);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book not found for query: " + query);
        }
    }

    /**
     * Updates the details of an existing book.
     *
     * @param bookId The unique identifier of the book to be updated.
     * @param updatedBook The updated book details (validated).
     * @return The updated book wrapped in a ResponseEntity.
     * @throws BookNotFoundException if the book with the given ID is not found.
     */
    @PutMapping("/update/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @Valid @RequestBody Book updatedBook) {
        return ResponseEntity.ok(bookService.updateBook(bookId, updatedBook));
    }

    /**
     * Deletes a book from the collection.
     *
     * @param bookId The unique identifier of the book to be deleted.
     * @return A ResponseEntity with a success message.
     * @throws BookNotFoundException if the book with the given ID is not found.
     */
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("Book deleted successfully.");
    }

    /**
     * Shuts down the application gracefully.
     *
     * This method starts a separate thread to exit the application after a 2-second delay.
     * It ensures that Spring's ApplicationContext is closed properly before terminating the JVM.
     *
     * @return A message indicating that the application is shutting down.
     */
    @GetMapping("/exit")
    public String exitApplication() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000); // Optional delay before exit
                SpringApplication.exit(context);
                System.exit(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        return "Application closed...";
    }
}
