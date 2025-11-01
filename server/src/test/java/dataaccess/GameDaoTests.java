package dataaccess;

import dataaccess.database.DatabaseDaoCollection;
import dataaccess.exceptions.AlreadyTakenException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requestobjects.CreateRequest;
import requestobjects.JoinRequest;

import java.util.ArrayList;

public class GameDaoTests {
    DaoCollection daos = new DatabaseDaoCollection();
    @BeforeEach
    public void setup() throws DataAccessException {
        daos.gameDao.clear();
        daos.authDao.clear();
        daos.userDao.clear();
    }

    @Test
    public void createTest() throws DataAccessException {
        CreateRequest request = new CreateRequest("game");
        daos.gameDao.create(request);

        Assertions.assertEquals("game", daos.gameDao.getGame(1).gameName());
    }

    @Test
    public void createWithVoid() {
        CreateRequest request = new CreateRequest(null);
        Assertions.assertThrows(DataAccessException.class,() -> daos.gameDao.create(request));
    }

    @Test
    public void getGame() throws DataAccessException {
        CreateRequest request = new CreateRequest("game");
        daos.gameDao.create(request);

        Assertions.assertEquals("game", daos.gameDao.getGame(1).gameName());
    }

    @Test
    public void getGameThatDoesntExist() throws DataAccessException {
        Assertions.assertNull(daos.gameDao.getGame(1));
    }

    @Test
    public void listGames() throws DataAccessException {
        CreateRequest request = new CreateRequest("game");
        daos.gameDao.create(request);

        Assertions.assertNotNull(daos.gameDao.list());
    }

    @Test
    public void listEmptyGames() throws DataAccessException {
        Assertions.assertEquals(new ArrayList<>(), daos.gameDao.list());
    }

    @Test
    public void join() throws DataAccessException, AlreadyTakenException {
        CreateRequest request = new CreateRequest("game");
        daos.gameDao.create(request);

        JoinRequest joinRequest = new JoinRequest("WHITE", 1);
        daos.gameDao.join(joinRequest, "Garrett");

        Assertions.assertEquals("Garrett", daos.gameDao.getGame(1).whiteUsername());
    }

    @Test
    public void joinColorTaken() throws DataAccessException, AlreadyTakenException {
        CreateRequest request = new CreateRequest("game");
        daos.gameDao.create(request);

        JoinRequest joinRequest = new JoinRequest("WHITE", 1);
        daos.gameDao.join(joinRequest, "Garrett");

        JoinRequest joinRequest2 = new JoinRequest("WHITE", 1);
        Assertions.assertThrows(AlreadyTakenException.class, () -> daos.gameDao.join(joinRequest2, "Bob"));
    }
}
