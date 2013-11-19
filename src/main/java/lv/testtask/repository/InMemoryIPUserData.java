package lv.testtask.repository;

import org.joda.time.DateTime;

public class InMemoryIPUserData {
    private final DateTime lastLoanTaken;
    private final Integer loansTakenInDay;

    public InMemoryIPUserData(DateTime lastLoanTaken, Integer loansTakenInDay) {
        this.lastLoanTaken = lastLoanTaken;
        this.loansTakenInDay = loansTakenInDay;
    }

    public DateTime getLastLoanTaken() {
        return lastLoanTaken;
    }

    public Integer getLoansTakenInDay() {
        return loansTakenInDay;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InMemoryIPUserData{");
        sb.append("lastLoanTaken=").append(lastLoanTaken);
        sb.append(", loansTakenInDay=").append(loansTakenInDay);
        sb.append('}');
        return sb.toString();
    }
}
