package requestobjects;

import com.google.gson.Gson;

import java.util.Map;

public abstract class Result {
    public static String buildJson(String... keysAndVals) {
        Map<String, String> pairs = new java.util.HashMap<>(Map.of());
        for (int i = 1; i < keysAndVals.length; i++){
            if (i%2 == 1) {
                pairs.put(keysAndVals[i-1], keysAndVals[i]);
            }
        }

        return new Gson().toJson(pairs);
    }

    public abstract String toJson(String... keysAndValues);
}
