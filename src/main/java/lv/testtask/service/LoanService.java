package lv.testtask.service;


import lv.testtask.persistence.domain.User;

public interface LoanService {

    public String takeLoan(String loanData, String ip, User user);

    public String getLoansForUser(Integer userId);

}
