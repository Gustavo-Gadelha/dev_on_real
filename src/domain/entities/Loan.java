package domain.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private String id;
    private Book book;
    private User user;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean isReturned;
    private double fine;
    private static int loanCount = 0;
    private static final double DAILY_FINE = 1.0;

    public Loan(String id, Book book, User user, LocalDate loanDate) {
        if (id == null || id.trim().isEmpty()) {
            id = "LOAN-" + System.currentTimeMillis();
        }
        this.id = id;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.isReturned = false;
        this.fine = 0.0;
        loanCount++;
    }

    public void returnBook(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.isReturned = true;
        loanCount--;
        calculateFine();
    }

    private void calculateFine() {
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(loanDate, returnDate);
        fine = daysBetween * DAILY_FINE;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public boolean isReturned() { return isReturned; }
    public void setReturned(boolean returned) { isReturned = returned; }
    public double getFine() { return fine; }
    public void setFine(double fine) { this.fine = fine; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
