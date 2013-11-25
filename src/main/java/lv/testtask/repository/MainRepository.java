package lv.testtask.repository;

import lv.testtask.persistence.domain.Authorities;
import lv.testtask.persistence.domain.Loan;
import lv.testtask.persistence.domain.User;

import java.util.Set;

public interface MainRepository {

    public void saveLoan(Loan loan);

    public Loan getLoanById(Integer id);

    public Set<Loan> getLoansForUser(Integer userId);

    public void saveUser(User user);

    public User getUserById(Integer userId);

    public User getUserByUsernameAndMail(String phone, String email);

    public void saveAuthorities(Authorities authorities);

    public Authorities getAuthoritiesByUsername(String username);
}
