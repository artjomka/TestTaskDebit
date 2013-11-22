package lv.testtask.persistence.domain;

import lv.testtask.LoanStatus;
import lv.testtask.validation.annotation.MidnightRule;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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
    private Set<LoanExtension> loanExtensions = new HashSet<LoanExtension>();

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;


    @Enumerated(EnumType.STRING)
    private LoanStatus status;


    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public void addLoanExtension(LoanExtension loanExtension){
        loanExtensions.add(loanExtension);
    }

    public void setReturnTill(DateTime returnTill) {
        this.returnTill = returnTill;
    }

    public DateTime getReturnTill() {
        return returnTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (id != null ? !id.equals(loan.id) : loan.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
