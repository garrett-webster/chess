package dataaccess;

import requestobjects.CreateRequest;

public interface GameDao extends DAO{
    int create(CreateRequest request) throws DataAccessException;

//    public GameData getGame(int gameID) throws DataAccessException;
//
//    public GameData getGame (String gameName);
//
//    public GameData listGames();
//
//    public void updateGame(GameData gameData) throws DataAccessException;
//
//    public void addPlayerToGame(int playerId, int gameID, ChessGame.TeamColor team);
//
//    public void deleteGame(int gameID) throws DataAccessException;
//
//    public void clear();
}
