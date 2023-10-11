package ua.prachyk.config.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Person {
    private int id;
    @NotEmpty
    @Size(min = 2, max = 100, message = "Name must be 2 or 100 charters size")
    private String fullName;
    @Min(value = 1900, message = "Error, year must be higher 1900")
    private int yearOfBirth;

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
}
