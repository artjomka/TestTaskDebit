package lv.testtask.service;


public interface LoanService {

    public String takeLoan(String loanData, String ip);

    public String getLoansForUser(Integer userId);

}
