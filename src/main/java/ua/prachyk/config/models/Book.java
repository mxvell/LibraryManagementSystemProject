package ua.prachyk.config.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Book {
    private int id;
    @NotEmpty
    @Size(min = 5,max = 250, message = "Name of book must be 5 or 250 charters size")
    private String nameOfBook;
    @NotEmpty
    @UniqueElements(message = "Error, author unique")
    private String author;
    @Min(value = 1700, message = "Error oldest book, you can add higher 1700")
    private int releaseYear;

    public Book(String nameOfBook, String author, int releaseYear) {
        this.nameOfBook = nameOfBook;
        this.author = author;
        this.releaseYear = releaseYear;
    }
}
