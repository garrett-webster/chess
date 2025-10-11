package services;

import requestobjects.RegisterRequest;
import requestobjects.RegisterResult;

public class UserService {
    public void clearUsers() {

    }

    public static RegisterResult register(RegisterRequest request) {
        return new RegisterResult(request.username(), "dummy token");
    }
}
