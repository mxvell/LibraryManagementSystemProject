package ua.prachyk.config.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Component
@Entity
@Table(name = "Book")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    @NotEmpty
    @Size(min = 5,max = 250, message = "Name of book must be 5 or 250 charters size")
    private String title;
    @Column(name = "author")
    @NotEmpty
    @UniqueElements(message = "Error, author unique")
    private String author;
    @Column(name = "year")
    @Min(value = 1445, message = "Error oldest book, you can add higher 1445")
    private int year;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "taken_at")
    private Date takeAt;
    @Transient
    private boolean expired;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person reader;



    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
