package dataaccess;

import chess.ChessGame;
import model.GameData;

public class GameDao {
    public void createGame(GameData GameData) throws DataAccessException {
        return;
    }

    public GameData getGame(int gameId) throws DataAccessException {
        return null;
    }

    public GameData getGame (String gameName) {
        return null;
    }

    public GameData listGames() {
        return null;
    }

    public void updateGame(GameData gameData) throws DataAccessException {
        return;
    }

    public void addPlayerToGame(int playerId, int gameId, ChessGame.TeamColor team) {
        return;
    }

    public void deleteGame(int gameId) throws DataAccessException {
        return;
    }

    public void clear() {
        return;
    }
}
