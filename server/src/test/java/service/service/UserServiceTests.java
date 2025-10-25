package service.service;

import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import dataaccess.exceptions.AlreadyTakenException;
import dataaccess.exceptions.UserNotValidatedException;
import dataaccess.memory.MemoryUserDao;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import requestobjects.LoginRequest;
import requestobjects.LoginResult;
import requestobjects.RegisterRequest;
import services.UserService;

public class UserServiceTests {
    public static UserService userService;
    @BeforeEach
    public void setup() {
        DaoCollection daos = new DaoCollection();
        userService = new UserService(daos);
    }

    @Nested
    class RegisterTests {
        @Test
        public void createUserThatDoesNotExist() throws AlreadyTakenException, DataAccessException {
            RegisterRequest request = new RegisterRequest(
                    "Garrett", "password123", "garrett@email.com"
            );
            userService.register(request);

            MemoryUserDao dao = (MemoryUserDao) userService.daos.userDao;
            UserData user = dao.users.get("Garrett");

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

    @Nested
    class LoginTests {
        @BeforeEach
        public void registerGarrett() throws AlreadyTakenException, DataAccessException {
            RegisterRequest request = new RegisterRequest(
                    "Garrett", "password123", "garrett@email.com"
            );
            userService.register(request);
        }

        @Test
        public void loginUserThatExists() {
            LoginResult result = userService.login(new LoginRequest("Garrett", "password123"));

            Assertions.assertEquals("Garrett", result.username());
            Assertions.assertNotNull(result.authToken());
            Assertions.assertFalse(result.authToken().isEmpty());
        }

        @Test
        public void loginUserThatDoesNotExist() {
            Assertions.assertThrows(UserNotValidatedException.class,
                    () -> userService.login(new LoginRequest("Jerome", "password123")));
        }

        @Test
        public void badPassword() {
            Assertions.assertThrows(UserNotValidatedException.class,
                    () -> userService.login(new LoginRequest("Garrett", "notthepassword")));
        }
    }
}
