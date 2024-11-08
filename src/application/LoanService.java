package application;

import domain.entities.Book;
import domain.entities.Loan;
import domain.entities.User;
import domain.repositories.LoanRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LoanService {
    private final LoanRepository loanRepository;
    private static final int MAX_LOANS = 999999;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void loanBook(Book book, User user) {
        String loanId = UUID.randomUUID().toString();
        Loan loan = new Loan(loanId, book, user, LocalDate.now());
        loanRepository.addLoan(loan);
        user.addLoan(loan);
    }

    public void returnBook(String loanId) {
        Loan loan = loanRepository.getLoanById(loanId);
        if (loan != null && !loan.isReturned()) {
            loan.returnBook(LocalDate.now());
        }
    }

    public List<Loan> getLoansByUserId(String userId) {
        return loanRepository.getLoansByUserId(userId);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.getAllLoans();
    }
}