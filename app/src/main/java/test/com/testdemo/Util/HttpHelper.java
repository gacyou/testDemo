package test.com.testdemo.Util;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import test.com.testdemo.Api.Base.ApiException;

public class HttpHelper {
    private static final MediaType _JSON = MediaType.parse("application/json; charset=utf-8");

    public HttpHelper() {
    }

    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        Request request = (new Builder()).headers(getHeaders(headers)).url(url + toQueryString(params)).get().build();
        return execute(request);
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        Request request = (new Builder()).headers(getHeaders(headers)).url(url).post(createJsonBody(params)).build();
        return execute(request);
    }

    public static String put(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        Request request = (new Builder()).headers(getHeaders(headers)).url(url).put(createJsonBody(params)).build();
        return execute(request);
    }

    public static String delete(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        Request request = (new Builder()).headers(getHeaders(headers)).url(url + toQueryString(params)).delete().build();
        return execute(request);
    }

    private static Headers getHeaders(Map<String, String> headers) {
        Headers.Builder builder = new Headers.Builder();
        if (headers != null) {
            Iterator var2 = headers.keySet().iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();
                builder.set(key, (String)headers.get(key));
            }
        }

        return builder.build();
    }

    private static String toQueryString(Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            android.net.Uri.Builder builder = new android.net.Uri.Builder();
            Iterator var2 = params.keySet().iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();
                builder.appendQueryParameter(key, (String)params.get(key));
            }

            return "?" + builder.build().getEncodedQuery();
        } else {
            return "";
        }
    }

    private static RequestBody createJsonBody(Map<String, String> params) {
        String raw = JsonUtil.mapToJsonString(params);
        RequestBody body = RequestBody.create(_JSON, raw);
        return body;
    }

    private static OkHttpClient initClient(Request request) throws Exception {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(15L, TimeUnit.SECONDS);
        client.setWriteTimeout(15L, TimeUnit.SECONDS);
        client.setReadTimeout(15L, TimeUnit.SECONDS);
        if (request.isHttps()) {
            SSLContext sslContext = SSLHelper.getSSLContext();
            client.setSslSocketFactory(sslContext.getSocketFactory());
        }

        return client;
    }

    private static String execute(Request request) throws Exception {
        OkHttpClient client = initClient(request);
        Iterator var2 = request.headers().names().iterator();

        String responseString;
        while(var2.hasNext()) {
            responseString = (String)var2.next();
        }

        Response response = client.newCall(request).execute();
        responseString = response.body().string();

        if (response.isSuccessful()) {
            return responseString;
        } else {
            throw new ApiException(response.code(), response.message(), responseString);
        }
    }
}

