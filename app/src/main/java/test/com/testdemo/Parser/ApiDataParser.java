package test.com.testdemo.Parser;

import test.com.testdemo.Bean.ApiData;

public class ApiDataParser extends DataParser<ApiData> {
    @Override
    protected Class<ApiData> getType() {
        return ApiData.class;
    }
}
