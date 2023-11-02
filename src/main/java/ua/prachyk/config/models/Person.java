package ua.prachyk.config.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Component
@Entity
@Table(name = "Person")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Column(name = "name")
    @Size(min = 2, max = 100, message = "Name must be 2 or 100 charters size")
    private String fullName;
    @Column(name = "year")
    @Min(value = 1900, message = "Error, year must be higher 1900")
    private int yearOfBirth;
    @OneToMany(mappedBy = "reader")
    private List<Book> books;

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
}
