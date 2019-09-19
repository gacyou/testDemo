package test.com.testdemo.Api;

import android.content.Context;

import org.json.JSONException;

import test.com.testdemo.Api.Base.ApiResult;
import test.com.testdemo.Loader.Base.AbstractApiLoaderCallback;

public abstract class ApiLoaderCallback<T> extends AbstractApiLoaderCallback<T> {

    @Override
    protected String toErrorMessage(ApiResult apiResult) {

        String message;
        try {
            message = BaseApiParser.getMessage(apiResult.getError().getBody());
        } catch (JSONException e) {
            int status = apiResult.getError().getStatus();
            String reason = apiResult.getError().getReason();

            message = "[" + status + "] " + reason;
        }

        return message;
    }

    @Override
    protected void handleForbidden(Context context) {

    }
}
