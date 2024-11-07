package domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String id;
    private String name;
    private List<Loan> loanHistory = new ArrayList<>();
    private double totalFines = 0.0;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void calculateTotalFines() {
        this.totalFines = loanHistory.stream().mapToDouble(Loan::getFine).sum();
    }

    public void addLoan(Loan loan) {
        loanHistory.add(loan);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Loan> getLoanHistory() { return loanHistory; }
    public void setLoanHistory(List<Loan> loanHistory) {
        this.loanHistory = loanHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}