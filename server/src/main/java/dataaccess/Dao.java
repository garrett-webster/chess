package dataaccess;

import java.sql.ResultSet;
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


    public void executeCommand(String command, Object... params) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection();
             var preparedStatement = conn.prepareStatement(command)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataAccessException("failed to create execute command", ex);
        }
    }

    @FunctionalInterface
    public interface ResultMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    public <T> T executeQueryAndGetOne(String query, ResultMapper<T> resultMap, Object... params) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection();
             var preparedStatement = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            try (var results = preparedStatement.executeQuery()) {
                if (results.next()) {
                    return resultMap.map(results);
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException("failed to create execute command", ex);
        }
    }
}
