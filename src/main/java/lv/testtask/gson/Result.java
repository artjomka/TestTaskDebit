package lv.testtask.gson;

public class Result {
    private ResultStatus status;
    private ErrorData data;

    public ErrorData getData() {
        return data;
    }

    public void setData(ErrorData data) {
        this.data = data;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("data=").append(data);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
