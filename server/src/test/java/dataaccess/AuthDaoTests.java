package dataaccess;

import dataaccess.database.DatabaseDaoCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthDaoTests {
    DaoCollection daos = new DatabaseDaoCollection();
    @BeforeEach
    public void setup() throws DataAccessException {
        daos.gameDao.clear();
        daos.authDao.clear();
        daos.userDao.clear();
    }

    @Test
    public void addAuthTokenTest() throws DataAccessException {
        String username = "Garrett";
        String token = "DummyToken";

        daos.authDao.addAuthToken(username, token);

        Assertions.assertEquals(username, daos.authDao.authenticateToken(token));
    }

    @Test
    public void addNullAuthTokenTest() {
        String username = "Garrett";
        String token = null;

        Assertions.assertThrows(DataAccessException.class, () -> daos.authDao.addAuthToken(username, token));
    }

    @Test
    public void authenticateTokenTest() throws DataAccessException {
        String username = "Garrett";
        String token = "DummyToken";
        daos.authDao.addAuthToken(username, token);

        Assertions.assertEquals(username, daos.authDao.authenticateToken(token));
    }

    @Test
    public void authenticateTokenThatDoesntExistTest() throws DataAccessException {
        String token = "DummyToken";

        Assertions.assertNull(daos.authDao.authenticateToken(token));
    }

    @Test
    public void removeTest() throws DataAccessException {
        String username = "Garrett";
        String token = "DummyToken";
        daos.authDao.addAuthToken(username, token);

        daos.authDao.remove(token);

        daos.authDao.authenticateToken(token);
        Assertions.assertNull(daos.authDao.authenticateToken(token));
    }

    @Test
    public void removeDoesNotExistTest() throws DataAccessException {
        String token = "DummyToken";

        daos.authDao.remove(token);

        daos.authDao.authenticateToken(token);
        Assertions.assertNull(daos.authDao.authenticateToken(token));
    }
}
