package server;

import Handlers.UserHandlers;
import com.google.gson.Gson;
import dataaccess.DaoCollection;
import io.javalin.*;
import io.javalin.http.Context;
import services.AuthService;
import services.UserService;

import java.util.Map;

public class Server {
    private final Javalin javalin;
    DaoCollection DAOs = new DaoCollection();
    AuthService authService = new AuthService(DAOs);
    UserService userService = new UserService(DAOs);
    UserHandlers userHandlers = new UserHandlers(userService);

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        javalin.delete("/db", userHandlers::clear)
                .post("/user", userHandlers::create);

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

    public static String buildJson(String... keysAndVals) {
        Map<String, String> pairs = new java.util.HashMap<>(Map.of());
        for (int i = 1; i < keysAndVals.length; i++){
            if (i%2 == 1) {
                pairs.put(keysAndVals[i-1], keysAndVals[i]);
            }
        }

        return new Gson().toJson(pairs);
    }
}
