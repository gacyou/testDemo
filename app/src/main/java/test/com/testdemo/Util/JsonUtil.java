package test.com.testdemo.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class JsonUtil {
    public JsonUtil() {
    }

    public static String mapToJsonString(Map<String, String> map) {
        JSONObject json = new JSONObject();
        if (map != null) {
            Iterator var2 = map.keySet().iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();

                try {
                    if (key.startsWith("{") && key.endsWith("}")) {
                        json.putOpt(takeOffFix(key), new JSONObject((String)map.get(key)));
                    } else if (key.startsWith("[") && key.endsWith("]")) {
                        json.putOpt(takeOffFix(key), new JSONArray((String)map.get(key)));
                    } else {
                        json.putOpt(key, map.get(key));
                    }
                } catch (JSONException var5) {
                    var5.printStackTrace();
                }
            }
        }

        return json.toString();
    }

    public static String mapCollectionToJsonString(Collection<Map<String, String>> collection) {
        JSONArray jsonArray = new JSONArray();
        if (collection != null) {
            Iterator var2 = collection.iterator();

            while(var2.hasNext()) {
                Map<String, String> map = (Map)var2.next();
                jsonArray.put(new JSONObject(map));
            }
        }

        return jsonArray.toString();
    }

    public static String collectionToJsonString(Collection<String> collection) {
        JSONArray jsonArray = new JSONArray();
        if (collection != null) {
            Iterator var2 = collection.iterator();

            while(var2.hasNext()) {
                String value = (String)var2.next();
                jsonArray.put(value);
            }
        }

        return jsonArray.toString();
    }

    private static String takeOffFix(String s) {
        int length = s.length();
        return length > 2 ? s.substring(1, length - 1) : s;
    }
}
