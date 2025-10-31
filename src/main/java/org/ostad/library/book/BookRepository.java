package org.ostad.library.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorIgnoreCase(String author);
    List<Book> findByGenreIgnoreCase(String genre);
    List<Book> findByPublicationIgnoreCase(String publication);
}
