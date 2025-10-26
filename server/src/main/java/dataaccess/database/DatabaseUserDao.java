package dataaccess.database;

import dataaccess.DataAccessException;
import dataaccess.UserDao;
import model.UserData;

public class DatabaseUserDao extends UserDao {
    @Override
    public void createUser(UserData userData) throws DataAccessException {
        String sql_statement = "INSERT INTO users (username, password, email) VALUES(?,?,?)";
        executeCommand(sql_statement, userData.username(), userData.password(), userData.email());
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        String sql_statement = "SELECT username, password, email FROM users WHERE username = ?";

        return executeQueryAndGetOne(sql_statement, results -> new UserData(
                results.getString("username"),
                results.getString("password"),
                results.getString("email")
        ), username);
    }

    @Override
    public boolean validateWithPassword(String username, String password) {
        return false;
    }

    @Override
    public void clear() {
//        String sql_statement = "TRUNCATE TABLE users";
//        executeCommand(sql_statement);
    }
}
