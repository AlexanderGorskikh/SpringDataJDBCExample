package ru.example.springdatajdbc.repository;

import org.springframework.data.repository.CrudRepository;
import ru.example.springdatajdbc.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();

    Book findById(Long id);

    void save(Book book);

    void update(Book book);

    void deleteById(Long id);
}
