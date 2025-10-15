package dataaccess.local;

import dataaccess.AuthDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LocalAuthDao implements AuthDao {
    public Map<String, String> authTokens = new HashMap<>();

    public void addAuthToken(String username, String token) {
        authTokens.put(username, token);
    }

    public boolean authenticateToken(String username, String token) {
        return authTokens.containsKey(username) && Objects.equals(authTokens.get(username), token);
    }
}
