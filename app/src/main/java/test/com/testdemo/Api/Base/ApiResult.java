package test.com.testdemo.Api.Base;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private ApiException error;
    private Exception exception;
    private T data;

    public ApiResult() {
    }

    public ApiException getError() {
        return this.error;
    }

    public void setError(ApiException error) {
        this.error = error;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Exception getException() {
        return this.exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
