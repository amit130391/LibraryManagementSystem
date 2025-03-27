package com.assignment.libraryManagement.Service;

import com.assignment.libraryManagement.Entity.Book;
import com.assignment.libraryManagement.Exceptions.BookNotFoundException;
import com.assignment.libraryManagement.Exceptions.DuplicateBookException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final Map<String, Book> bookCollection = new HashMap<>();

    public Book addBook(Book book){
        if(bookCollection.containsKey(book.getBookId()))
            throw new DuplicateBookException("Book with Id "+book.getBookId()+" already exists");
        bookCollection.put(book.getBookId(),book);
        return book;
    }

    public List<Book> getBooks(){
        return new ArrayList<>(bookCollection.values());
    }

    public Optional<Book> getBookBYIdOrTitle(String query){
        return bookCollection.values().stream().filter(book->book.getBookId().equalsIgnoreCase(query)||book.getTitle().equalsIgnoreCase(query)).findFirst();
    }

    public Book updateBook(String bookId, Book updatedBook) {
        if (!bookCollection.containsKey(bookId)) {
            throw new BookNotFoundException("Book ID not found: " + bookId);
        }
        bookCollection.put(bookId, updatedBook);
        return updatedBook;
    }

    public void deleteBook(String bookId) {
        if (bookCollection.remove(bookId) == null) {
            throw new BookNotFoundException("Book ID not found: " + bookId);
        }
    }


}
