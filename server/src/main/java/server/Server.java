package server;

import com.google.gson.Gson;
import io.javalin.*;
import io.javalin.http.Context;
import java.util.Map;

public class Server {

    private final Javalin javalin;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        // Register your endpoints and exception handlers here.

        javalin.delete("/db", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                dbDelete(context);
            }
        });

    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }

    private void dbDelete(Context context) {
        context.result("{}");
    }

    private void userCreate(Context context){
        var serializer = new Gson();
        Map json = serializer.fromJson(context.body(), Map.class);
        context.result(buildJson("username", json.get("username").toString(), "authToken", "dummy token") );
    }

    private String buildJson(String... keysAndVals) {
        Map<String, String> pairs = new java.util.HashMap<>(Map.of());
        for (int i = 1; i < keysAndVals.length; i++){
            if (i%2 == 1) {
                pairs.put(keysAndVals[i-1], keysAndVals[i]);
            }
        }

        return new Gson().toJson(pairs);
    }
}
