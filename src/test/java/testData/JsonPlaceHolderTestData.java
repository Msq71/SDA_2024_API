package testData;

import java.util.LinkedHashMap;
import java.util.Map;


public class JsonPlaceHolderTestData {

    public static Map<String, Object> jsonPlaceHolderMapper(Integer userId, String title, Boolean completed) {
        Map<String, Object> payLoad = new LinkedHashMap<>();

        if (userId != null) {
            payLoad.put("userId", userId);
        }
        if (title != null) {
            payLoad.put("title", title);
        }
        if (completed != null) {
            payLoad.put("completed", completed);
        }
        return payLoad;
    }
}
