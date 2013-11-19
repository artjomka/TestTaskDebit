package lv.testtask.persistence.domain;


import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, unique = true)
    private Integer id;

    @NotNull
    private String phone;

    @Email
    @NotNull
    private String mail;

    @NotNull
    private String password;


    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateRegistered;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval = true)
    private Set<Loan> loans;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public DateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(DateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = phone != null ? phone.hashCode() : 0;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", dateRegistered=").append(dateRegistered);
        sb.append(", loans=").append(loans);
        sb.append('}');
        return sb.toString();
    }
}
