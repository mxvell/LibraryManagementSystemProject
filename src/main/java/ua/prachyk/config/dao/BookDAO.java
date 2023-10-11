package ua.prachyk.config.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.prachyk.config.models.Book;
import ua.prachyk.config.models.Person;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index (){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(nameOfBook, author,  releaseYear) VALUES (?,?,?)",new Object[]{book},new BeanPropertyRowMapper<>(Book.class));
    }
    public void update(int id, Book updateBook){
        jdbcTemplate.update("UPDATE Book SET nameOfBook=?, author=?, releaseYear=? WHERE id=?", updateBook.getNameOfBook(),updateBook.getAuthor(),updateBook.getReleaseYear());
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",id);
    }
    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " + "WHERE Book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void release (int id){
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?",id);
    }
    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("UPDATE Book SET  person_id=? WHERE id=?",selectedPerson.getId(),id);
    }
}





















