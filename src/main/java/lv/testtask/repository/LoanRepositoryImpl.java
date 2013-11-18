package lv.testtask.repository;

import lv.testtask.persistence.domain.Loan;
import lv.testtask.persistence.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveLoan(Loan loan) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(loan);
    }

    @Override
    public Loan getLoanById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Loan.class);
        criteria.add(Restrictions.eq("id", id));
        return (Loan) criteria.uniqueResult();

    }

    @Override
    public Set<Loan> getLoansForUser(Integer userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", userId));
        User user = (User) criteria.uniqueResult();
        return user.getLoans();
    }
}
