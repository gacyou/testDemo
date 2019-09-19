package test.com.testdemo.Parser.Base;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseParser {

    public BaseParser() {
    }

    public static boolean hasValue(@NonNull JSONObject json, @NonNull String key) {
        return json.has(key) && !json.isNull(key);
    }

    protected static boolean getBoolean(@NonNull JSONObject json, @NonNull String key) {
        return json.has(key) && json.optBoolean(key, false);
    }

    protected static double getDouble(@NonNull JSONObject json, @NonNull String key) {
        return json.has(key) ? json.optDouble(key, 0.0D) : 0.0D;
    }

    protected static long getLong(@NonNull JSONObject json, @NonNull String key) {
        return json.has(key) ? json.optLong(key, 0L) : 0L;
    }

    protected static int getInt(@NonNull JSONObject json, @NonNull String key) {
        return json.has(key) ? json.optInt(key, 0) : 0;
    }

    protected static float getFloat(@NonNull JSONObject json, @NonNull String key) {
        return json.has(key) ? (float)json.optDouble(key, 0.0D) : 0.0F;
    }

    protected static String getString(@NonNull JSONObject json, @NonNull String key) {
        return hasValue(json, key) ? json.optString(key, "") : null;
    }

    protected static JSONObject getJSONObject(@NonNull JSONObject json, @NonNull String key) {
        return hasValue(json, key) ? json.optJSONObject(key) : null;
    }

    protected static JSONArray getJSONArray(@NonNull JSONObject json, @NonNull String key) {
        return hasValue(json, key) ? json.optJSONArray(key) : null;
    }

    protected static List<Integer> getIntList(@NonNull JSONObject json, @NonNull String key) throws JSONException {
        List<Integer> list = new ArrayList();
        if (hasValue(json, key)) {
            JSONArray dataJson = json.getJSONArray(key);
            list.addAll(toIntList(dataJson));
        }

        return list;
    }

    protected static List<Integer> toIntList(JSONArray jsonArray) throws JSONException {
        List<Integer> list = new ArrayList();
        if (jsonArray != null) {
            for(int i = 0; i < jsonArray.length(); ++i) {
                list.add(jsonArray.getInt(i));
            }
        }

        return list;
    }

    protected static int[] getIntArray(@NonNull JSONObject json, @NonNull String key) throws JSONException {
        int[] array;
        if (json.has(key)) {
            JSONArray dataJson = json.getJSONArray(key);
            array = new int[dataJson.length()];

            for(int i = 0; i < dataJson.length(); ++i) {
                array[i] = dataJson.getInt(i);
            }
        } else {
            array = new int[0];
        }

        return array;
    }

    protected static List<String> getStringList(@NonNull JSONObject json, @NonNull String key) throws JSONException {
        List<String> list = new ArrayList();
        if (json.has(key)) {
            JSONArray dataJson = json.getJSONArray(key);
            list.addAll(toStringList(dataJson));
        }

        return list;
    }

    protected static List<String> toStringList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList();
        if (jsonArray != null) {
            for(int i = 0; i < jsonArray.length(); ++i) {
                list.add(jsonArray.getString(i));
            }
        }

        return list;
    }

    protected static Map<String, String> getMap(@NonNull JSONObject json, @NonNull String key) {
        Map<String, String> map = new HashMap();
        if (json.has(key)) {
            JSONObject jsonObject = getJSONObject(json, key);
            map.putAll(toMap(jsonObject));
        }

        return map;
    }

    protected static Map<String, String> toMap(JSONObject jsonObject) {
        Map<String, String> map = new HashMap();
        if (jsonObject != null) {
            Iterator iterator = jsonObject.keys();

            while(iterator.hasNext()) {
                String k = (String)iterator.next();
                map.put(k, getString(jsonObject, k));
            }
        }

        return map;
    }

    protected static boolean validJson(String jsonString) {
        try {
            new JSONObject(jsonString);
            return true;
        } catch (JSONException var2) {
            return false;
        }
    }
}
