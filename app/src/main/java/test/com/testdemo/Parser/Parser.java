package test.com.testdemo.Parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Parser {
    protected static Gson gson() {
        return new GsonBuilder().create();
    }
}
