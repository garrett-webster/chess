package Handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.exceptions.AlreadyTakenException;
import io.javalin.http.Context;
import requestobjects.RegisterRequest;
import requestobjects.RegisterResult;
import services.UserService;

import static server.Server.buildJson;

public class UserHandlers {
    UserService userService;
    public UserHandlers(UserService userService) {
        this.userService = userService;
    }

    public void create(Context context) {
        var serializer = new Gson();
        try {
            RegisterResult result = userService.register(serializer.fromJson(context.body(), RegisterRequest.class));
            context.result(buildJson("username", result.username(), "authtoken", result.authToken()));
        } catch (AlreadyTakenException e) {
            context.result("{\"message\": \"403 Already Taken: Username already taken.\"");
            context.status(403);
        } catch (DataAccessException e) {
            context.result("{\"message\": \"500 Data Access Exception: Failed to create new user\"");
            context.status(500);
        }
    }
}
