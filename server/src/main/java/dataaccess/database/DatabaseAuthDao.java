package dataaccess.database;

import dataaccess.AuthDao;
import dataaccess.DataAccessException;

public class DatabaseAuthDao extends AuthDao {
    public void addAuthToken(String username, String token) throws DataAccessException {
        String sql_command = String.format("INSERT INTO authdata VALUES(%s, %s)", username, token);
        this.executeCommand(sql_command);
    }

    public String authenticateToken(String token) throws DataAccessException {
        String sql_query = String.format("SELECT username FROM authdata WHERE token = '%s'", token);
        return (String) executeQueryAndGetOne(sql_query);
    }

    public void clear() throws DataAccessException {
        executeCommand("DELETE FROM authdata");
    }

    public void remove(String token) throws DataAccessException {
        executeCommand(String.format("DELETE FROM authdata WHERE token = '%s'", token));
    }
}
