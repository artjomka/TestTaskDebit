package lv.testtask.repository;

import lv.testtask.persistence.domain.Loan;

public interface LoanRepository {

    public void saveLoan();

    public Loan getLoanById();
}
