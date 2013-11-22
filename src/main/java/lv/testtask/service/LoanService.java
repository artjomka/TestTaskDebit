package lv.testtask.service;


import lv.testtask.persistence.domain.Loan;
import lv.testtask.persistence.domain.User;

public interface LoanService {

    public String takeLoan(String loanData, String ip, User user);

    public String getLoansForUser(Integer userId);

    public String extendLoan(String loanData, Integer days, Double amount);

    public String extendLoan(Loan loan, Integer days, Double amount);
}
