package lv.testtask.validation;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import org.joda.time.DateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;

public class IpRestrictionData implements DataSerializable {
    private  DateTime lastLoanTaken;

    @Max(value = 3)
    @Min(value = 0)
    private  Integer loansTakenInDay;

    public IpRestrictionData() {
    }



    public DateTime getLastLoanTaken() {
        return lastLoanTaken;
    }

    public Integer getLoansTakenInDay() {
        return loansTakenInDay;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IpRestrictionData{");
        sb.append("lastLoanTaken=").append(lastLoanTaken);
        sb.append(", loansTakenInDay=").append(loansTakenInDay);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(lastLoanTaken.toString());
        out.writeInt(loansTakenInDay);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        lastLoanTaken = new DateTime(in.readUTF());
        loansTakenInDay = in.readInt();

    }

    public void refreshAfterDaynightFromLastLoan (DateTime now) {
        if (lastLoanTaken.isBefore(now.plusHours(24))) {
            lastLoanTaken = new DateTime(now);
            loansTakenInDay = 0;
        }
    }
}
