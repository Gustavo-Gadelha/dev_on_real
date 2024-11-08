package infrastructure.loaders;

import domain.entities.Book;
import domain.entities.Loan;
import domain.entities.User;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataLoader {
    public static Map<String, User> loadUsers(String filePath) throws IOException {
        Map<String, User> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                users.put(parts[0], new User(parts[0], parts[1]));
            }
        }
        return users;
    }

    public static Map<String, Book> loadBooks(String filePath) throws IOException {
        Map<String, Book> books = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length < 4) continue;
                books.put(parts[0], new Book(parts[0], parts[1], parts[2], parts[3]));
            }
        }
        return books;
    }

    public static List<Loan> loadLoans(String filePath, Map<String, Book> books, Map<String, User> users) throws IOException {
        List<Loan> loans = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Book book = books.get(parts[1]);
                User user = users.get(parts[2]);
                LocalDate loanDate = LocalDate.parse(parts[3]);
                loans.add(new Loan(parts[0], book, user, loanDate));
            }
        }
        return loans;
    }
}