package lv.testtask.persistence.domain;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, unique = true)
    private Integer id;

    private String username;

    private String mail;

    private String password;


    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateRegistered;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval = true)
    private Set<Loan> loans;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", dateRegistered=").append(dateRegistered);
        sb.append(", loans=").append(loans);
        sb.append('}');
        return sb.toString();
    }
}
