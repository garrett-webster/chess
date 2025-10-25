package dataaccess;

import java.sql.DriverManager;
import java.sql.SQLException;

import static dataaccess.DatabaseManager.loadPropertiesFromResources;

public class Dao {
    private static String databaseName;
    private static String dbUsername;
    private static String dbPassword;
    private static String connectionUrl;

    static {
        loadPropertiesFromResources();
    }

    DatabaseManager databaseManager = new DatabaseManager();
    public void executeCommand(String command) throws DataAccessException {
        try (var conn = DriverManager.getConnection(connectionUrl, dbUsername, dbPassword);
             var preparedStatement = conn.prepareStatement(command)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException("failed to create database", ex);
        }
    }
}
