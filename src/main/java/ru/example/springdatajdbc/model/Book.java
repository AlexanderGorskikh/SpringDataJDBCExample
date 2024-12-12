package ru.example.springdatajdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
}
