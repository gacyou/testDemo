package test.com.testdemo.Api;

import test.com.testdemo.Api.Base.Params;

public class Api extends BaseApi {

    private static final String POSTS = "/photos";

    public String getData() throws Exception {
        Params params = new Params();

        return this.doGet(POSTS, params.getMap());
    }
}
