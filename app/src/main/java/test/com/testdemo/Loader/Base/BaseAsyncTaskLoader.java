package test.com.testdemo.Loader.Base;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;

import test.com.testdemo.Api.Base.ApiException;
import test.com.testdemo.Api.Base.ApiResult;

public abstract class BaseAsyncTaskLoader<T> extends AsyncTaskLoader<ApiResult<T>> {
    public BaseAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    protected void onStartLoading() {
        this.forceLoad();
    }

    protected void onStopLoading() {
        this.cancelLoad();
    }

    public ApiResult<T> loadInBackground() {
        ApiResult result = new ApiResult();

        try {
            String response = this.action();
            result.setData(this.parseData(response));
        } catch (ApiException var5) {
            ApiException e = var5;

            try {
                result.setError(e);
            } catch (Exception var4) {
                result.setException(var4);
            }
        } catch (Exception var6) {
            result.setException(var6);
        }

        return result;
    }

    protected abstract String action() throws Exception;

    protected abstract T parseData(String var1) throws Exception;
}
