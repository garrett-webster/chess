package dataaccess.local;

import dataaccess.DataAccessException;
import dataaccess.UserDao;
import model.UserData;

import java.util.Map;

public class localUserDao implements UserDao {
    Map<String, UserData> users = Map.of();

    @Override
    public void createUser(UserData userData) throws DataAccessException {
        if (!users.containsKey(userData.username())) {
            users.put(userData.username(), userData);
        } else {
            throw new DataAccessException("User with username " + userData.username() + " already exists.");
        }
    }
}
