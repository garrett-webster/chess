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
    public AuthService authService;
    public UserService(DaoCollection DAOs) {
        this.DAOs = DAOs;
        authService = new AuthService(DAOs);
    }

    public void clearUsers() {
    }

    public RegisterResult register(RegisterRequest request) throws AlreadyTakenException, DataAccessException {
        UserData user = new UserData(request.username(), request.password(), request.email());

        if (DAOs.userDao.getUser(user.username()) != null) throw new
                AlreadyTakenException("Username " + user.username() + " already exists");

        DAOs.userDao.createUser(user);
        String token = authService.generateNewToken(user.username());
        return new RegisterResult(request.username(), token);
    }

//    TODO: Move this ot it's own handler class
}
