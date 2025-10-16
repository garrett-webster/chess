package dataaccess;

import requestobjects.CreateRequest;

public interface GameDao extends DAO{
    int create(CreateRequest request) throws DataAccessException;

    public void addPlayerToGame(int playerId, int gameId, ChessGame.TeamColor team);

    public void deleteGame(int gameId) throws DataAccessException;

    public void clear();
}
