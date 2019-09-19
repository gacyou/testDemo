package test.com.testdemo.Api.Base;

import android.content.Context;

import java.util.Map;

import test.com.testdemo.Util.HttpHelper;

public abstract class AbstractBaseApi {

    protected String accessToken;

    public AbstractBaseApi() {
    }

    public <T extends AbstractBaseApi> T withAccessToken(Context context) {
        this.accessToken = this.getAccessToken(context);
        return (T) this;
    }

    protected abstract Map<String, String> getHeader();

    protected abstract String getAccessToken(Context var1);

    protected abstract String toUrl(String var1);

    protected String doGet(String path, Map<String, String> params) throws Exception {
        return HttpHelper.get(this.toUrl(path), params, this.getHeader());
    }

    protected String doPost(String path, Map<String, String> params) throws Exception {
        return HttpHelper.post(this.toUrl(path), params, this.getHeader());
    }

    protected String doPut(String path, Map<String, String> params) throws Exception {
        return HttpHelper.put(this.toUrl(path), params, this.getHeader());
    }

    protected String doDelete(String path, Map<String, String> params) throws Exception {
        return HttpHelper.delete(this.toUrl(path), params, this.getHeader());
    }

}
