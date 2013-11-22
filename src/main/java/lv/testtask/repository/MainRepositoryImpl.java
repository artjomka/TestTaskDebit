package lv.testtask.repository;

import lv.testtask.persistence.domain.Loan;
import lv.testtask.persistence.domain.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository

public class MainRepositoryImpl implements MainRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveLoan(Loan loan) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(loan);
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
        return getUserById(userId).getLoans();
    }

    @Override
    public void saveUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);

    }

    @Override
    public User getUserById(Integer userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", userId));
        criteria.setFetchMode("loans", FetchMode.JOIN);
        User user = (User) criteria.uniqueResult();

        return user;
    }

    @Override
    public User getUserByPhoneAndMail(String phone, String email) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(User.class);
        criteria.add(Restrictions.eq("phone", phone));
        criteria.add(Restrictions.eq("email", email));
        User user = (User) criteria.uniqueResult();

        return user;
    }
}
