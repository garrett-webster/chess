package dataaccess.local;

import chess.ChessGame;
import dataaccess.GameDao;
import model.GameData;
import requestobjects.CreateRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalGameDao implements GameDao {
    public Map<Integer, GameData> games = new HashMap<>();
    private int nextId = 1;

    public int create(CreateRequest request) {
        GameData newGame = new GameData(
                nextId, request.gameName(), null, null, new ChessGame()
        );
        games.put(nextId, newGame);

        nextId++;
        return nextId-1;
    }

    public List<GameData> list() {
        return new ArrayList<>(games.values());
    }

    public void clear() {
        games = new HashMap<>();
    }
}
