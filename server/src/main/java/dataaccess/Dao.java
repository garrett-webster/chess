package dataaccess;

import java.sql.DriverManager;
import java.sql.SQLException;

import static dataaccess.DatabaseManager.loadPropertiesFromResources;

public class Dao {
    private static String databaseName;
    private static String dbUsername;
    private static String dbPassword;
    private static String connectionUrl;
    DatabaseManager databaseManager = new DatabaseManager();

    static {
        loadPropertiesFromResources();
    }


    public void executeCommand(String command) throws DataAccessException {
        try (var conn = DriverManager.getConnection(connectionUrl, dbUsername, dbPassword);
             var preparedStatement = conn.prepareStatement(command)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException("failed to create execute command", ex);
        }
    }

    public Object executeQueryAndGetOne(String query) throws DataAccessException {
        try (var conn = DriverManager.getConnection(connectionUrl, dbUsername, dbPassword);
             var preparedStatement = conn.prepareStatement(query)) {
             var results = preparedStatement.executeQuery();

            if (results.next()) {
                return results.getString("username");
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new DataAccessException("failed to create execute command", ex);
        }
    }
}
