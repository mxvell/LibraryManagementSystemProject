package ua.prachyk.config.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.prachyk.config.models.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
   Optional <Person> findByFullName (String fullName);
}
