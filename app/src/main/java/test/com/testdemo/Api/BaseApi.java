package test.com.testdemo.Api;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import test.com.testdemo.Api.Base.AbstractBaseApi;
import test.com.testdemo.BuildConfig;

public class BaseApi extends AbstractBaseApi {
    @Override
    protected Map<String, String> getHeader() {
        Map<String, String> headers = null;
        headers = new HashMap<>();
        return headers;
    }

    @Override
    protected String getAccessToken(Context context) { return ""; }

    @Override
    protected String toUrl(String path) { return BuildConfig.API_URL + path; }

}
