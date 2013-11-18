package lv.testtask.repository;

import lv.testtask.persistence.domain.Loan;

import java.util.Set;

public interface LoanRepository {

    public void saveLoan(Loan loan);

    public Loan getLoanById(Integer id);

    public Set<Loan> getLoansForUser(Integer userId);
}
