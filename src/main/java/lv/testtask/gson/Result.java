package lv.testtask.gson;

import java.util.Collections;
import java.util.List;

public class Result {
    private final ResultStatus status;
    private final List<ErrorData> errorData;

    public Result(ResultStatus status, List<ErrorData> errorData) {
        this.status = status;
        this.errorData = errorData;
    }

    public Result (ResultStatus status) {
        this.status = status;
        this.errorData = Collections.EMPTY_LIST;

    }
    public List<ErrorData> getErrorData() {
        return errorData;
    }

    public ResultStatus getStatus() {
        return status;
    }


    public Boolean hasErrors(){
        return errorData != null && !errorData.isEmpty();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("errorData=").append(errorData);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
