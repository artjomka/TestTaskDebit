package lv.testtask.persistence.domain;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Loan {

    @Id
    @GeneratedValue
    private Integer id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime taken;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime returnTill;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = {@Parameter(name="currencyCode", value="EUR") })
    private Money amount;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = {@Parameter(name="currencyCode", value="EUR") })
    private Money interest;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentCurrencyUnit")
    private CurrencyUnit currency = CurrencyUnit.EUR;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan", cascade = CascadeType.ALL)
    private Set<LoanExtension> loanExtensions;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;




}
