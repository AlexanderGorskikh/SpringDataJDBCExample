package ru.example.springdatajdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.example.springdatajdbc.model.Book;
import ru.example.springdatajdbc.repository.BookRepository;
import ru.example.springdatajdbc.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(1L, "Title1", "Author1", 2021),
                new Book(2L, "Title2", "Author2", 2022)
        );

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById_Found() {
        Book book = new Book(1L, "Title1", "Author1", 2021);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book).orElseThrow(()-> new RuntimeException("not found")));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Title1", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(1L));

        assertThrows(RuntimeException.class, () -> bookService.getBookById(1L));
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateBook() {
        Book book = new Book(null, "Title1", "Author1", 2021);

        bookService.createBook(book);

        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testUpdateBook() {
        Book book = new Book(1L, "Title1", "Author1", 2021);

        bookService.updateBook(book);

        verify(bookRepository, times(1)).update(book);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
