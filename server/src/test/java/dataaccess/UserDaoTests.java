package dataaccess;

import dataaccess.database.DatabaseDaoCollection;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDaoTests {
    DaoCollection daos = new DatabaseDaoCollection();
    String username = "Garrett";
    String password = "password";
    String email = "garrett@email.com";
    UserData userData;

    @BeforeEach
    public void setup() throws DataAccessException {
        daos.gameDao.clear();
        daos.authDao.clear();
        daos.userDao.clear();

        userData = new UserData(username, password, email);
        daos.userDao.createUser(userData);
    }

    @Test
    public void createUser() throws DataAccessException {
        Assertions.assertEquals(userData.email(), daos.userDao.getUser(username).email());
    }

    @Test
    public void createWithNullTest() {
        UserData userData = new UserData(null, null, null);
        Assertions.assertThrows(DataAccessException.class, () -> daos.userDao.createUser(userData));
    }

    @Test
    public void getUserTest() throws DataAccessException {
        Assertions.assertEquals(userData.email(), daos.userDao.getUser(username).email());
    }

    @Test
    public void getUserThatDoesntExistTest() throws DataAccessException {
        Assertions.assertNull(daos.userDao.getUser("Random"));
    }

    @Test
    public void validateWithPassword() throws DataAccessException {
        Assertions.assertTrue(daos.userDao.validateWithPassword(username, password));
    }

    @Test
    public void validatePasswordThatDoesntExist() throws DataAccessException {
        Assertions.assertFalse(daos.userDao.validateWithPassword(username, "random"));
    }
}
