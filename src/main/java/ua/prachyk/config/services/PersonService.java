package ua.prachyk.config.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.prachyk.config.models.Book;
import ua.prachyk.config.models.Person;
import ua.prachyk.config.repositories.PersonRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findOne(int id){
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElse(null);
    }
    @Transactional
    public void save (Person person){
        personRepository.save(person);
    }
    @Transactional
    public void update(int id, Person updPerson){
        updPerson.setId(id);
        personRepository.save(updPerson);
    }
    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }
    public Optional<Person> getPersonByFullName(String fullName){
        return personRepository.findByFullName(fullName);
    }
    public List<Book>getBooksByPersonId(int id){
        Optional<Person>person = personRepository.findById(id);
        if (person.isPresent()){
            Hibernate.initialize(person.get().getBooks());
            person.get().getBooks().forEach(book -> {
                long diffInMillies = Math.abs(book.getTakeAt().getTime()) - new Date().getTime();
                if (diffInMillies > 864000000){
                    book.setExpired(true);
                }
            });
            return person.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }
    }
}
