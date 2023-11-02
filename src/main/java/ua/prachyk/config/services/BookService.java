package ua.prachyk.config.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.prachyk.config.models.Book;
import ua.prachyk.config.models.Person;
import ua.prachyk.config.repositories.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book>findAll(boolean sortByYear){
        if (sortByYear){
            return bookRepository.findAll(Sort.by("year"));
        }else {
            return bookRepository.findAll();
        }
    }
    public Book findOne(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }
    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear){
        if (sortByYear){
            return bookRepository.findAll(PageRequest.of(page, booksPerPage,Sort.by("year"))).getContent();
        }else {
            return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
        }
    }

    public List<Book>findByReader(Person reader){
        return bookRepository.findByReader(reader);
    }
    public List<Book>searchBook(String startWith){
        return bookRepository.findByTitleStartingWith(startWith);
    }
    public Book searchByTitle(String title){
        return bookRepository.findByTitle(title);
    }


    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updBook){
        Book bookToBeUpd = bookRepository.findById(id).get();
        updBook.setId(id);
        updBook.setReader(bookToBeUpd.getReader());
        bookRepository.save(updBook);
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
    public Person getBookOwner(int id){
        return bookRepository.findById(id).map(Book::getReader).orElse(null);
    }
    @Transactional
    public void release(int id){
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setReader(null);
                    book.setTakeAt(null);
    });
    }
    @Transactional
    public void assign(int id, Person selectedPerson){
        bookRepository.findById(id).ifPresent(
                book -> {
                   book.setReader(selectedPerson);
                   book.setTakeAt(new Date());
                });
    }
}
