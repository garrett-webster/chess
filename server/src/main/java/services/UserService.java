package services;

import com.google.gson.Gson;
import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import io.javalin.http.Context;
import model.UserData;
import requestobjects.RegisterRequest;
import requestobjects.RegisterResult;

public class UserService {
    public DaoCollection DAOs;
    public UserService(DaoCollection DAOs) {
        this.DAOs = DAOs;
    }

    public void clearUsers() {
    }

    public RegisterResult register(RegisterRequest request) {
        UserData user = new UserData(request.username(), request.password(), request.email());

        try {
            DAOs.userDao.createUser(user);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return new RegisterResult(request.username(), "dummy token");
    }

    public void create(Context context){
        var serializer = new Gson();
        RegisterResult result = register(serializer.fromJson(context.body(), RegisterRequest.class));
        context.result(result.toJson());
    }
}
