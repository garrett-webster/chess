package passoff.services;

import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import dataaccess.exceptions.AlreadyTakenException;
import dataaccess.local.LocalUserDao;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requestobjects.RegisterRequest;
import services.UserService;

public class UserServiceTests {
    public static UserService userService;
    @BeforeEach
    public void setup() {
        DaoCollection DAOs = new DaoCollection();
        userService = new UserService(DAOs);
    }

    @Test
    public void createUserThatDoesNotExist() throws AlreadyTakenException, DataAccessException {
        RegisterRequest request = new RegisterRequest(
                "Garrett", "password123", "garrett@email.com"
        );
        userService.register(request);

        LocalUserDao DAO = (LocalUserDao) userService.DAOs.userDao;
        UserData user = DAO.users.get("Garrett");

        Assertions.assertEquals("Garrett", user.username());
        Assertions.assertEquals("password123", user.password());
        Assertions.assertEquals("garrett@email.com", user.email());
    }

    @Test
    public void createUserThatExists() throws AlreadyTakenException, DataAccessException {
        RegisterRequest request1 = new RegisterRequest(
                "Garrett", "password123", "garrett@email.com"
        );
        userService.register(request1);

        RegisterRequest request2 = new RegisterRequest(
                "Garrett", "password123", "garrett@email.com"
        );
        Assertions.assertThrows(AlreadyTakenException.class, () -> userService.register(request2));
    }
}
