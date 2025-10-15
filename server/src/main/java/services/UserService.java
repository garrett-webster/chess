package services;

import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import dataaccess.exceptions.AlreadyTakenException;
import dataaccess.exceptions.BadRequestException;
import model.UserData;
import requestobjects.RegisterRequest;
import requestobjects.RegisterResult;

public class UserService {
    public DaoCollection DAOs;
    public AuthService authService;
    public UserService(DaoCollection DAOs) {
        this.DAOs = DAOs;
        authService = new AuthService(DAOs);
    }

    public void clear() {
        this.DAOs.userDao.clear();
    }

    public RegisterResult register(RegisterRequest request) throws AlreadyTakenException, DataAccessException {
        UserData user = new UserData(request.username(), request.password(), request.email());

        if (user.username() == null || user.password() == null || user.email() == null) throw new
                BadRequestException("A field was missing");


        if (DAOs.userDao.getUser(user.username()) != null) throw new
                AlreadyTakenException("Username " + user.username() + " already exists");

        DAOs.userDao.createUser(user);
        String token = authService.generateNewToken(user.username());
        return new RegisterResult(request.username(), token);
    }

//    TODO: Move this ot it's own handler class
}
