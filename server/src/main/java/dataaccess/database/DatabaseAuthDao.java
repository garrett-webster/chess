package dataaccess.database;

import dataaccess.AuthDao;

public class DatabaseAuthDao extends AuthDao {
    public void addAuthToken(String username, String token) {
        String sql_command = String.format("INSERT INTO authdata VALUES(%s, %s)", username, token);
    }

    public String authenticateToken(String token) {
        return "";
    }

    public void clear() {
    }

    public void remove(String token) {
    }
}
