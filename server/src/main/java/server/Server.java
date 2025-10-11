package server;

import com.google.gson.Gson;
import io.javalin.*;
import io.javalin.http.Context;
import requestobjects.RegisterRequest;
import requestobjects.RegisterResult;
import services.UserService;

public class Server {
    private final Javalin javalin;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        // Register your endpoints and exception handlers here.

        javalin.delete("/db", this::dbDelete);
        javalin.post("/user", this::userCreate);

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
        RegisterResult result = UserService.register(serializer.fromJson(context.body(), RegisterRequest.class));
        context.result(result.toJson());
    }
}
