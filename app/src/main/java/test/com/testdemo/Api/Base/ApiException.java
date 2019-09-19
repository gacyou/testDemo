package test.com.testdemo.Api.Base;

public class ApiException extends Exception {
    private final int status;
    private final String reason;
    private final String body;

    public ApiException(int status, String reason, String body) {
        this.status = status;
        this.reason = reason;
        this.body = body;
    }

    public int getStatus() {
        return this.status;
    }

    public String getReason() {
        return this.reason;
    }

    public String getBody() {
        return this.body;
    }
}
