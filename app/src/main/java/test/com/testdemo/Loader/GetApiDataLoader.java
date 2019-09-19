package test.com.testdemo.Loader;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import test.com.testdemo.Api.Api;
import test.com.testdemo.Bean.ApiData;
import test.com.testdemo.Loader.Base.BaseAsyncTaskLoader;
import test.com.testdemo.Parser.ApiDataParser;

public class GetApiDataLoader extends BaseAsyncTaskLoader<List<ApiData>> {

    public GetApiDataLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected String action() throws Exception {
        Api api = new Api();
        return api.getData();
    }

    @Override
    protected List<ApiData> parseData(String var1) throws Exception {
        ApiDataParser parser = new ApiDataParser();
        return parser.toList(var1);
    }
}
