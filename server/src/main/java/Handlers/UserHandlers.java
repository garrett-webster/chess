package Handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.exceptions.AlreadyTakenException;
import dataaccess.exceptions.BadRequestException;
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

    public void clear(Context context) {
        userService.clear();
        context.result("{}");
    }

    public void create(Context context) {
        var serializer = new Gson();
        try {
            RegisterResult result = userService.register(serializer.fromJson(context.body(), RegisterRequest.class));
            context.result(buildJson("username", result.username(), "authToken", result.authToken()));
        } catch (AlreadyTakenException e) {
            context.result(buildJson("message", "403 Already Taken Error: Username already taken."));
            context.status(403);
        } catch (DataAccessException e) {
            context.result(buildJson("message", "500 Data Access Error: Failed to create new user"));
            context.status(500);
        } catch (BadRequestException e) {
            context.result(buildJson("message",  "400 Bad Request Error: Failed to create new user"));
            context.status(400);
        }
    }
}
