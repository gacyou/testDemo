package test.com.testdemo.Api;

import org.json.JSONException;
import org.json.JSONObject;

import test.com.testdemo.Parser.Base.BaseParser;

public class BaseApiParser extends BaseParser {
    public static String getMessage(String jsonString) throws JSONException {
        return getString(jsonString, "message");
    }

    private static String getString(String jsonString, String key) throws JSONException {
        JSONObject json = new JSONObject(jsonString);

        return getString(json, key);
    }
}