package services;

import requestobjects.RegisterRequest;

public class UserService extends Service {
    public void clearUsers() {

    }

    public static String register(RegisterRequest request) {
        return buildJson("username", request.username(), "authToken", "dummy token");
    }
}
