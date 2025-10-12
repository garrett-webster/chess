package passoff.services;

import dataaccess.DaoCollection;
import dataaccess.local.LocalUserDao;
import model.UserData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import requestobjects.RegisterRequest;
import services.UserService;

import java.util.Objects;

public class UserServiceTests {
    public static UserService userService;
    @BeforeAll
    public static void setup() {
        DaoCollection DAOs = new DaoCollection();
        userService = new UserService(DAOs);
    }

    @Test
    public void getUserThatExists() {
        RegisterRequest request = new RegisterRequest(
                "Garrett", "password123", "garrett@email.com"
        );
        userService.register(request);

        LocalUserDao DAO = (LocalUserDao) userService.DAOs.userDao;
        UserData user = DAO.users.get("Garrett");

        assert Objects.equals(user.username(), "Garrett");
        assert Objects.equals(user.password(), "password123");
        assert Objects.equals(user.email(), "garrett@email.com");
    }

    @Test
    public void getUserWhoDoesntExist() {
        LocalUserDao DAO = (LocalUserDao) userService.DAOs.userDao;
        assert DAO.users.get("John") == null;
    }
}
