package lv.testtask.persistence.domain;

import lv.testtask.validation.annotation.MidnightRule;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table
@MidnightRule
public class Loan {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime taken;

    @Future
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime returnTill;

    @NotNull
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = {@Parameter(name="currencyCode", value="EUR") })
    private Money amount;

    @NotNull
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = {@Parameter(name="currencyCode", value="EUR") })
    private Money interest;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentCurrencyUnit")
    private CurrencyUnit currency = CurrencyUnit.EUR;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan", cascade = CascadeType.ALL)
    private Set<LoanExtension> loanExtensions;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;

    @NotNull
    private Integer status;


    public void setStatus(Integer status) {
        this.status = status;
    }
}
