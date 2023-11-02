package ua.prachyk.config.utill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.prachyk.config.dao.PersonDAO;
import ua.prachyk.config.models.Person;
import ua.prachyk.config.services.PersonService;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (personService.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "Person in fullName existing");
        }
    }
}
