package lv.testtask.persistence.domain;

import javax.persistence.*;

@Entity
@Table
public class Authorities {



    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, unique = true)
    private Integer id;

    private String username;

    private String authority;

    public Authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authorities that = (Authorities) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Authorities{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", authority='").append(authority).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
