package dataaccess.database;

import dataaccess.AuthDao;
import dataaccess.DataAccessException;

public class DatabaseAuthDao extends AuthDao {
    public void addAuthToken(String username, String token) throws DataAccessException {
        String sql_command = "INSERT INTO authdata (username, token) VALUES(?, ?)";
        this.executeCommand(sql_command, username, token);
    }

    public String authenticateToken(String token) throws DataAccessException {
        String sql_query = "SELECT username FROM authdata WHERE token = ?";
        return executeQueryAndGetOne(sql_query, results -> results.getString("username"));
    }

    public void clear() throws DataAccessException {
        String sql_statement = "TRUNCATE TABLE authdata";
        executeCommand(sql_statement);
    }

    public void remove(String token) throws DataAccessException {
        executeCommand(String.format("DELETE FROM authdata WHERE token = '%s'", token));
    }
}
