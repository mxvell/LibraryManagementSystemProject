package ua.prachyk.config.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.prachyk.config.models.Book;
import ua.prachyk.config.models.Person;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findByTitle(String title);
    List<Book> findByReader(Person reader);
    List<Book> findByTitleStartingWith(String startString);
}
