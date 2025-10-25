package dataaccess;

import dataaccess.exceptions.AlreadyTakenException;
import model.GameData;
import requestobjects.CreateRequest;
import requestobjects.JoinRequest;

import java.util.List;

public abstract class GameDao extends Dao {
    abstract public int create(CreateRequest request) throws DataAccessException;
    abstract public GameData getGame(int gameID);
    abstract public List<GameData> list();
    abstract public void join(JoinRequest request, String username) throws AlreadyTakenException;
    abstract public void clear();
}
