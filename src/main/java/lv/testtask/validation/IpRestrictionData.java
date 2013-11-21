package lv.testtask.validation;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import lv.testtask.validation.annotation.IpRestrictionRule;
import org.joda.time.DateTime;

import javax.validation.constraints.Min;
import java.io.IOException;

public class IpRestrictionData implements DataSerializable {
    private  DateTime lastLoanTaken;

    @IpRestrictionRule(maxLoanAmountInDaytime = 3)
    @Min(value = 0)
    private  Integer loansTakenToday;

    public IpRestrictionData() {
    }

    public IpRestrictionData(DateTime lastLoanTaken, Integer loansTakenToday) {
        this.lastLoanTaken = lastLoanTaken;
        this.loansTakenToday = loansTakenToday;
    }

    public DateTime getLastLoanTaken() {
        return lastLoanTaken;
    }

    public Integer getLoansTakenToday() {
        return loansTakenToday;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IpRestrictionData{");
        sb.append("lastLoanTaken=").append(lastLoanTaken);
        sb.append(", loansTakenToday=").append(loansTakenToday);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(lastLoanTaken.toString());
        out.writeInt(loansTakenToday);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        lastLoanTaken = new DateTime(in.readUTF());
        loansTakenToday = in.readInt();

    }

    public void refreshAfterDaynightFromLastLoan (DateTime now) {
        if (lastLoanTaken.isBefore(now.plusHours(24))) {
            lastLoanTaken = new DateTime(now);
            loansTakenToday = 0;
        }
    }
}
