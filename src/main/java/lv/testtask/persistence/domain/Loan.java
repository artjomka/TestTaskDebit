package lv.testtask.persistence.domain;

import org.hibernate.annotations.Type;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Loan {

    @Id
    @GeneratedValue
    private Integer id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime taken;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime returnTill;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount")
    private Money amount;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount")
    private Money interest;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentCurrencyUnit")
    private CurrencyUnit currency;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan", cascade = CascadeType.ALL)
    private Set<LoanExtension> loanExtensions;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;


}
