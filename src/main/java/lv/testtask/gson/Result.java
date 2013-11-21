package lv.testtask.gson;

import java.util.List;

public class Result {
    private ResultStatus status;
    private List<ErrorData> errorData;

    public Result(List<ErrorData> errorData, ResultStatus status) {
        this.errorData = errorData;
        this.status = status;
    }

    public List<ErrorData> getErrorData() {
        return errorData;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
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
