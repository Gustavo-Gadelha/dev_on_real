package infrastructure.repositories;

import domain.entities.Book;
import domain.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryBookRepository implements BookRepository {
    private List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book getBookById(String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> getBookByAuthorAndGenre(String author, String genre) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .filter(book -> book.getGenre().equals(genre))
                .toList();
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        if (keyword == null) return new ArrayList<>();
        return books.stream()
                .filter(book -> book.getTitle().contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}
