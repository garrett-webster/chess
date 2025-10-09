package dataaccess;

import chess.ChessGame;
import model.GameData;

public interface GameDao {
    public void createGame(GameData GameData) throws DataAccessException;

    public GameData getGame(int gameId) throws DataAccessException;

    public GameData getGame (String gameName);

    public GameData listGames();

    public void updateGame(GameData gameData) throws DataAccessException;

    public void addPlayerToGame(int playerId, int gameId, ChessGame.TeamColor team);

    public void deleteGame(int gameId) throws DataAccessException;

    public void clear();
}
