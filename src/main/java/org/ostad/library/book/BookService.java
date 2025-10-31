package org.ostad.library.book;

import java.util.List;

import org.ostad.library.exception.BookNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        book.setId(null);
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book updateBook(Long id, Book bookUpdate) {
        Book existing = getBookById(id);
        existing.setTitle(bookUpdate.getTitle());
        existing.setAuthor(bookUpdate.getAuthor());
        existing.setPublication(bookUpdate.getPublication());
        existing.setGenre(bookUpdate.getGenre());
        existing.setPublicationYear(bookUpdate.getPublicationYear());
        existing.setAvailableCopies(bookUpdate.getAvailableCopies());
        return bookRepository.save(existing);
    }

    public void deleteBook(Long id) {
        Book existing = getBookById(id);
        bookRepository.delete(existing);
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenreIgnoreCase(genre);
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByPublication(String publication) {
        return bookRepository.findByPublicationIgnoreCase(publication);
    }
}
