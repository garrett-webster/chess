package server;

import dataaccess.DaoCollection;
import io.javalin.*;
import io.javalin.http.Context;
import services.UserService;

public class Server {
    private final Javalin javalin;
    DaoCollection DAOs = new DaoCollection();
    UserService userService = new UserService(DAOs);

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        // Register your endpoints and exception handlers here.

        javalin.delete("/db", this::dbDelete);
        javalin.post("/user", userService::create);

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
}
