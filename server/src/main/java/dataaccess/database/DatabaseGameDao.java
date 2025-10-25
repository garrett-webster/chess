package dataaccess.database;

import dataaccess.GameDao;
import dataaccess.exceptions.AlreadyTakenException;
import model.GameData;
import requestobjects.CreateRequest;
import requestobjects.JoinRequest;

import java.util.List;

public class DatabaseGameDao implements GameDao {
    public int create(CreateRequest request) {
        return 0;
    }

    public GameData getGame(int gameID) {
        return null;
    }

    public List<GameData> list() {
        return null;
    }

    public void clear() {
    }

    public void join(JoinRequest request, String username) throws AlreadyTakenException {
    }
}
