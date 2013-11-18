package lv.testtask.persistence.domain;

import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.*;

@Entity
@Table
public class LoanExtension {

    @Id
    @GeneratedValue
    private Integer id;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount")
    private Money amount;

    private Integer daysProlonged;

    @ManyToOne
    @JoinColumn(name = "loan_fk")
    private Loan loan;
}
