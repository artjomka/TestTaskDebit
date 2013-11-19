package lv.testtask.persistence.domain;

import org.hibernate.annotations.*;
import org.joda.money.Money;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class LoanExtension {

    @Id
    @GeneratedValue
    private Integer id;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",  parameters = {@org.hibernate.annotations.Parameter(name="currencyCode", value="EUR")})
    private Money amount;

    private Integer daysProlonged;

    @ManyToOne
    @JoinColumn(name = "loan_fk")
    private Loan loan;
}
