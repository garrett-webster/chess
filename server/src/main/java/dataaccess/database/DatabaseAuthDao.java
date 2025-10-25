package dataaccess.database;

import dataaccess.AuthDao;

public class DatabaseAuthDao implements AuthDao {
    public void addAuthToken(String username, String token) {
    }

    public String authenticateToken(String token) {
        return "";
    }

    public void clear() {
    }

    public void remove(String token) {
    }
}
