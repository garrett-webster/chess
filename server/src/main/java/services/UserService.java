package services;

import com.google.gson.Gson;
import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import dataaccess.exceptions.AlreadyTakenException;
import io.javalin.http.Context;
import model.UserData;
import requestobjects.RegisterRequest;
import requestobjects.RegisterResult;

import static server.Server.buildJson;

public class UserService {
    public DaoCollection DAOs;
    public UserService(DaoCollection DAOs) {
        this.DAOs = DAOs;
    }

    public void clearUsers() {
    }

    public RegisterResult register(RegisterRequest request) throws AlreadyTakenException, DataAccessException {
        UserData user = new UserData(request.username(), request.password(), request.email());


        if (DAOs.userDao.getUser(user.username()) != null) throw new
                AlreadyTakenException("Username " + user.username() + " already exists");

        DAOs.userDao.createUser(user);
        return new RegisterResult(request.username(), "dummy token");
    }

//    TODO: Move this ot it's own handler class
    public void create(Context context){
        var serializer = new Gson();
        try {
            RegisterResult result = register(serializer.fromJson(context.body(), RegisterRequest.class));
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
